package net.noscape.project.supremetags.listeners;

import net.noscape.project.supremetags.SupremeTagsPremium;
import net.noscape.project.supremetags.handlers.SetupTag;
import net.noscape.project.supremetags.handlers.Tag;
import net.noscape.project.supremetags.storage.PlayerConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

import static net.noscape.project.supremetags.utils.Utils.*;

public class SetupListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!SupremeTagsPremium.getInstance().getEditorList().containsKey(player) && SupremeTagsPremium.getInstance().getSetupList().containsKey(player)) {
            event.setCancelled(true);

            SetupTag setup = SupremeTagsPremium.getInstance().getSetupList().get(player);
            int currentStage = setup.getStage();

            if (currentStage == 1) {
                setup.setIdentifier(deformat(event.getMessage()));
                setup.setStage(2);
                msgPlayer(player, "&6&lStage 2: &7What do you want the tag to look like? &6&o(type in normal chat)",
                        "&eUse normal or hex color formats (&#<code> or &-0-9/a-f) *without the -*");
            } else if (currentStage == 2 && setup.getIdentifier() != null && setup.getTag() == null) {
                setup.setTag(event.getMessage());
                setup.setStage(3);
            } else if (currentStage == 3 && setup.getIdentifier() != null && setup.getTag() != null) {
                setup.setStage(3);

                List<String> tagList = new ArrayList<>();
                tagList.add(setup.getTag());

                // Save the tag and perform any necessary actions
                Tag tag = new Tag(setup.getIdentifier(), tagList, "");
                SupremeTagsPremium.getInstance().getPlayerManager().addTag(player, tag);
                PlayerConfig.save(player);
                SupremeTagsPremium.getInstance().getPlayerManager().load(player);

                SupremeTagsPremium.getInstance().getSetupList().remove(player);

                msgPlayer(player, "&6&lComplete: &7New Tag: &6" + setup.getIdentifier() + " &7- " + format(setup.getTag()) + " &7has been created.");
            }
        }
    }
}