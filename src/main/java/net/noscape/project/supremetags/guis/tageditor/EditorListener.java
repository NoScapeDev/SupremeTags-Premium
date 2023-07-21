package net.noscape.project.supremetags.guis.tageditor;

import net.noscape.project.supremetags.SupremeTagsPremium;
import net.noscape.project.supremetags.enums.EditingType;
import net.noscape.project.supremetags.handlers.Editor;
import net.noscape.project.supremetags.handlers.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

import static net.noscape.project.supremetags.utils.Utils.msgPlayer;

public class EditorListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!SupremeTagsPremium.getInstance().getEditorList().containsKey(player)) return;

        String message = e.getMessage();

        Editor editor = SupremeTagsPremium.getInstance().getEditorList().get(player);


        if (editor.getType() == EditingType.CHANGING_TAG) {
            e.setCancelled(true);

            Tag tag = SupremeTagsPremium.getInstance().getTagManager().getTag(editor.getIdentifier());
            List<String> tagList = tag.getTag();
            tagList.add(message);

            tag.setTag(tagList);
            SupremeTagsPremium.getInstance().getTagManager().saveTag(tag);

            SupremeTagsPremium.getInstance().getEditorList().remove(player);
            msgPlayer(player, "&8[&6&lTags&8] &7Data Updated.");
        } else if (editor.getType() == EditingType.CHANGING_CATEGORY) {
            e.setCancelled(true);

            Tag tag = SupremeTagsPremium.getInstance().getTagManager().getTag(editor.getIdentifier());
            tag.setCategory(message);
            SupremeTagsPremium.getInstance().getTagManager().saveTag(tag);

            SupremeTagsPremium.getInstance().getEditorList().remove(player);
            msgPlayer(player, "&8[&6&lTags&8] &7Data Updated.");
        } else if (editor.getType() == EditingType.CHANGING_DESCRIPTION) {
            e.setCancelled(true);

            Tag tag = SupremeTagsPremium.getInstance().getTagManager().getTag(editor.getIdentifier());
            tag.setDescription(message);
            SupremeTagsPremium.getInstance().getTagManager().saveTag(tag);

            SupremeTagsPremium.getInstance().getEditorList().remove(player);
            msgPlayer(player, "&8[&6&lTags&8] &7Data Updated.");
        } else if (editor.getType() == EditingType.CHANGING_PERMISSION) {
            e.setCancelled(true);

            Tag tag = SupremeTagsPremium.getInstance().getTagManager().getTag(editor.getIdentifier());
            tag.setPermission(message);
            SupremeTagsPremium.getInstance().getTagManager().saveTag(tag);

            SupremeTagsPremium.getInstance().getEditorList().remove(player);
            msgPlayer(player, "&8[&6&lTags&8] &7Data Updated.");
        }
    }
}
