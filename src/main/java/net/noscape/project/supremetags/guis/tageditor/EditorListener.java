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
        edit(e);
    }

    public void edit(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!SupremeTagsPremium.getInstance().getEditorList().containsKey(player)) return;

        String message = e.getMessage();

        Editor editor = SupremeTagsPremium.getInstance().getEditorList().get(player);

        EditingType type = editor.getType();

        Tag tag = SupremeTagsPremium.getInstance().getTagManager().getTag(editor.getIdentifier());

        e.setCancelled(true);

        switch (type) {
            case CHANGING_TAG:
                List<String> tagList = tag.getTag();
                tagList.add(message);

                tag.setTag(tagList);
                break;
            case CHANGING_PERMISSION:
                tag.setPermission(message);
                break;
            case CHANGING_CATEGORY:
                tag.setCategory(message);
                break;
            case CHANGING_COST:
                // do checks to make sure that the message is a double/number...

                tag.setCost(Double.parseDouble(message));
                break;
            case CHANGING_DESCRIPTION:
                tag.setDescription(message);
                break;
            case CHANGING_ORDER:
                // do checks to make sure that the message is a integer/number...

                tag.setOrder(Integer.parseInt(message));
                break;
        }

        // reopen the specific tag editor.
        new SpecificTagMenu(SupremeTagsPremium.getMenuUtilIdentifier(player, editor.getIdentifier())).open();

        SupremeTagsPremium.getInstance().getEditorList().remove(player);
        SupremeTagsPremium.getInstance().getTagManager().saveTag(tag);
        msgPlayer(player, "&8[&6&lTags&8] &7Tag has been updated.");
    }
}
