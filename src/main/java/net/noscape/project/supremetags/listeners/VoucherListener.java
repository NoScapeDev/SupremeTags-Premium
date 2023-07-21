package net.noscape.project.supremetags.listeners;

import de.tr7zw.nbtapi.NBTItem;
import net.md_5.bungee.api.ChatColor;
import net.noscape.project.supremetags.SupremeTagsPremium;
import net.noscape.project.supremetags.handlers.Tag;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Objects;

import static net.noscape.project.supremetags.utils.Utils.msgPlayer;

public class VoucherListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Check if the event is related to a player clicking in their inventory
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        NBTItem item = new NBTItem(Objects.requireNonNull(event.getCurrentItem()));

        // Check if the clicked item is a name tag
        if (event.getCurrentItem() != null && item.hasNBTData() && item.getString("identifier") != null) {
            Tag tag = SupremeTagsPremium.getInstance().getTagManager().getTag(item.getString("identifier"));
            String permission = tag.getPermission();

            // Add the permission to the player
            PermissionAttachment attachment = player.addAttachment(SupremeTagsPremium.getInstance());
            attachment.setPermission(permission, true);

            event.getCurrentItem().setAmount(event.getCurrentItem().getAmount() - 1);
            event.setCurrentItem(event.getCurrentItem());

            // Update the player's permissions (required for the changes to take effect immediately)
            player.recalculatePermissions();

            msgPlayer(player, "&8[&6&lTag&8] &7You have received the tag: " + tag.getTag().get(0));
        }
    }
}
