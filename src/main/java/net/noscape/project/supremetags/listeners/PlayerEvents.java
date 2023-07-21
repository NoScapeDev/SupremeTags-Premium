package net.noscape.project.supremetags.listeners;

import net.noscape.project.supremetags.*;
import net.noscape.project.supremetags.handlers.*;
import net.noscape.project.supremetags.storage.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

import java.util.*;

import static net.noscape.project.supremetags.utils.Utils.*;

public class PlayerEvents implements Listener {

    private final Map<String, Tag> tags;

    public PlayerEvents() {
        tags = SupremeTagsPremium.getInstance().getTagManager().getTags();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UserData.createPlayer(player);

        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.personal-tags.enable")) {
            SupremeTagsPremium.getInstance().getPlayerConfig().loadPlayer(player);
        }

        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.forced-tag")) {
            String activeTag = UserData.getActive(player.getUniqueId());
            if (activeTag.equalsIgnoreCase("None")) {
                String defaultTag = SupremeTagsPremium.getInstance().getConfig().getString("settings.default-tag");
                UserData.setActive(player, defaultTag);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (!SupremeTagsPremium.getInstance().getPlayerManager().getPlayerTags(player.getUniqueId()).isEmpty()) {
            PlayerConfig.save(player);
        }

        SupremeTagsPremium.getInstance().getSetupList().remove(player);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String format = e.getFormat();

        // Store the value of UserData.getActive(player.getUniqueId()) in a local variable
        String activeTag = UserData.getActive(player.getUniqueId());
        String replace = format.replace("{tag}", "").replace("{supremetags_tag}", "").replace("{TAG}", "");
        if (activeTag == null || activeTag.equalsIgnoreCase("None")) {
            e.setFormat(replace);
        } else {
            // Store the value of SupremeTags.getInstance().getTagManager().getTags().get(activeTag) in a local variable
            Tag tag = SupremeTagsPremium.getInstance().getTagManager().getTags().get(activeTag);
            if (tag == null) {
                e.setFormat(replace);
            } else {
                String t;

                if (tag.getCurrentTag() != null) {
                    t = tag.getCurrentTag();
                } else {
                    t = tag.getTag().get(0);
                }

                t = replacePlaceholders(player, t); // Replace placeholders for the player only

                e.setFormat(format.replace("{tag}", format(t)).replace("{supremetags_tag}", format(t)).replace("{TAG}", format(t)));
            }
        }
    }


    public Map<String, Tag> getTags() {
        return tags;
    }
}