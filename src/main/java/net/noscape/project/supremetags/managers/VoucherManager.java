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

import java.util.Objects;

import static net.noscape.project.supremetags.utils.Utils.*;

public class VoucherManager {

    public VoucherManager() {}

    public boolean isVoucherTag(ItemStack item) {
        NBTItem nbt = new NBTItem(item);

        String displayname = deformat(Objects.requireNonNull(item.getItemMeta()).getDisplayName());

        if (nbt.hasNBTData()) {
            if (displayname.startsWith("Tag Voucher:") && nbt.getString("permission") != null) {
                return true;
            }
        }

        return false;
    }

    public boolean hasTag(Player player, ItemStack item) {
        return player.hasPermission(getVoucherPermission(item));
    }

    public void giveTag(Player player) {

    }

    public void withdrawTag(Player player, String tag_name) {

        if (isInventoryFull(player)) {
            msgPlayer(player, "&8[&6&lTags&8] &7This withdraw process has cancelled, inventory full!");
            return;
        }

        /// remove permission
        /// HERE .......

        Tag t = SupremeTagsPremium.getInstance().getTagManager().getTag(tag_name);

        removePermission(player, t.getPermission());

        ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta tagMeta = tag.getItemMeta();

        assert tagMeta != null;

        NBTItem nbt = new NBTItem(tag);
        nbt.setString("permission", t.getPermission());

        tagMeta.setDisplayName(format("&7Tag"));

        msgPlayer(player, "&8[&6&lTags&8] &7Tag Withdrawn!");
    }

    public String getVoucherPermission(ItemStack item) {
        NBTItem nbt = new NBTItem(item);

        String permission = null;

        if (isVoucherTag(item)) {
            permission = nbt.getString("permission");
        }

        return permission;
    }

    public boolean isInventoryFull(Player player) {
        PlayerInventory inventory = player.getInventory();
        int firstEmptySlot = inventory.firstEmpty();
        return firstEmptySlot == -1;
    }

    private void removePermission(Player player, String permission) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            Node permissionNode = Node.builder(permission).build();
            user.data().remove(permissionNode);

            luckPerms.getUserManager().saveUser(user);
            luckPerms.getUserManager().cleanupUser(user);
            player.recalculatePermissions();
        }
    }
}
