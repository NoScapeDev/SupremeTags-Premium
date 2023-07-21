package net.noscape.project.supremetags.managers;

import de.tr7zw.nbtapi.NBTItem;
import me.arcaniax.hdb.api.PlayerClickHeadEvent;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.noscape.project.supremetags.SupremeTagsPremium;
import net.noscape.project.supremetags.handlers.Tag;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Objects;

import static net.noscape.project.supremetags.utils.Utils.*;

public class VoucherManager {

    public VoucherManager() {}

    public void withdrawTag(Player player, String tag_name) {
        if (isInventoryFull(player)) {
            msgPlayer(player, "&8[&6&lTags&8] &7This withdraw process has cancelled, inventory full!");
            return;
        }

        Tag t = SupremeTagsPremium.getInstance().getTagManager().getTag(tag_name);

        if (t == null) {
            msgPlayer(player, "&8[&6&lTags&8] &7This withdraw process has cancelled, this tag does not exist!");
            return;
        }

        if (!player.hasPermission(t.getPermission())) {
            msgPlayer(player, "&8[&6&lTags&8] &7This withdraw process has cancelled, you don't own this tag!");
            return;
        }

        if (!t.isWithdrawable()) {
            msgPlayer(player, "&8[&6&lTags&8] &7This withdraw process has cancelled, this tag is not withdrawable!");
            return;
        }

        removePermission(player, t.getPermission());

        ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta tagMeta = tag.getItemMeta();

        assert tagMeta != null;

        NBTItem nbt = new NBTItem(tag);
        nbt.setString("identifier", t.getIdentifier());

        tagMeta.setDisplayName(format("&7Tag: " + t.getTag().get(0)));

        nbt.getItem().setItemMeta(tagMeta);
        player.getInventory().addItem(nbt.getItem());

        msgPlayer(player, "&8[&6&lTags&8] &7Tag &f" + t.getIdentifier() + " &7Withdrawn!");
    }

    public boolean isInventoryFull(Player player) {
        PlayerInventory inventory = player.getInventory();
        int firstEmptySlot = inventory.firstEmpty();
        return firstEmptySlot == -1;
    }

    private void removePermission(Player player, String permission) {
        PermissionAttachment attachment = player.addAttachment(SupremeTagsPremium.getInstance());
        attachment.setPermission(permission, false);

        player.recalculatePermissions();
    }
}
