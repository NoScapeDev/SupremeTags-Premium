package net.noscape.project.supremetags.guis.configeditor;

import net.noscape.project.supremetags.SupremeTagsPremium;
import net.noscape.project.supremetags.handlers.menu.Menu;
import net.noscape.project.supremetags.handlers.menu.MenuUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

import static net.noscape.project.supremetags.utils.Utils.format;

public class ConfigEditorMenu extends Menu {

    public ConfigEditorMenu(MenuUtil menuUtil) {
        super(menuUtil);
    }

    @Override
    public String getMenuName() {
        return format("&8SupremeTags Configuration");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.valueOf(Objects.requireNonNull(SupremeTagsPremium.getInstance().getConfig().getString("gui.layout.glass-material")).toUpperCase()))) {
            e.setCancelled(true);
        }

        if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName().equalsIgnoreCase(format("&cClose"))) {
            player.closeInventory();
        }

        // categories
        if (e.getSlot() == 19) {
            if (e.getCurrentItem().getType().equals(Material.LIME_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.categories", false);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.categories", true);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            }
        }

        // cost system
        if (e.getSlot() == 21) {
            if (e.getCurrentItem().getType().equals(Material.LIME_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.cost-system", false);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.cost-system", true);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            }
        }

        // locked view
        if (e.getSlot() == 23) {
            if (e.getCurrentItem().getType().equals(Material.LIME_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.locked-view", false);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.locked-view", true);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            }
        }

        // personal tags
        if (e.getSlot() == 25) {
            if (e.getCurrentItem().getType().equals(Material.LIME_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.personal-tags.enable", false);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.personal-tags.enable", true);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            }
        }

        // active tag glow
        if (e.getSlot() == 47) {
            if (e.getCurrentItem().getType().equals(Material.LIME_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.active-tag-glow", false);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.active-tag-glow", true);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            }
        }

        // layout type
        if (e.getSlot() == 49) {
            if (e.getCurrentItem().getType().equals(Material.WHITE_STAINED_GLASS_PANE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.layout-type", "BORDER");
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();

                super.open();
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.layout-type", "FULL");
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            }
        }

        // forced tag
        if (e.getSlot() == 51) {
            if (e.getCurrentItem().getType().equals(Material.LIME_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.forced-tag", false);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
                SupremeTagsPremium.getInstance().getConfig().set("settings.forced-tag", true);
                SupremeTagsPremium.getInstance().saveConfig();

                SupremeTagsPremium.getInstance().reloadConfig();
                super.open();
            }
        }

    }

    @Override
    public void setMenuItems() {

        /// add all items needed.
        inventory.setItem(10, makeItem(Material.PAPER, format("&c&lCategories")));
        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.categories")) {
            inventory.setItem(19, makeItem(Material.LIME_DYE, format("&a&lEnabled")));
        } else {
            inventory.setItem(19, makeItem(Material.GRAY_DYE, format("&7&lDisabled")));
        }

        inventory.setItem(12, makeItem(Material.PAPER, format("&6&lCost System")));
        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.cost-system")) {
            inventory.setItem(21, makeItem(Material.LIME_DYE, format("&a&lEnabled")));
        } else {
            inventory.setItem(21, makeItem(Material.GRAY_DYE, format("&7&lDisabled")));
        }

        // continue with other toggles
        inventory.setItem(14, makeItem(Material.PAPER, format("&e&lLocked View")));
        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.locked-view")) {
            inventory.setItem(23, makeItem(Material.LIME_DYE, format("&a&lEnabled")));
        } else {
            inventory.setItem(23, makeItem(Material.GRAY_DYE, format("&7&lDisabled")));
        }

        inventory.setItem(16, makeItem(Material.PAPER, format("&a&lPersonal Tags")));
        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.personal-tags.enable")) {
            inventory.setItem(25, makeItem(Material.LIME_DYE, format("&a&lEnabled")));
        } else {
            inventory.setItem(25, makeItem(Material.GRAY_DYE, format("&7&lDisabled")));
        }

        inventory.setItem(38, makeItem(Material.PAPER, format("&b&lActive Tag Glow")));
        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.active-tag-glow")) {
            inventory.setItem(47, makeItem(Material.LIME_DYE, format("&a&lEnabled")));
        } else {
            inventory.setItem(47, makeItem(Material.GRAY_DYE, format("&7&lDisabled")));
        }

        inventory.setItem(40, makeItem(Material.PAPER, format("&3&lLayout Type")));
        if (SupremeTagsPremium.getInstance().getConfig().getString("settings.layout-type").equalsIgnoreCase("FULL")) {
            inventory.setItem(49, makeItem(Material.WHITE_STAINED_GLASS_PANE, format("&7Type: &f&lFull")));
        } else {
            inventory.setItem(49, makeItem(Material.GRAY_STAINED_GLASS_PANE, format("&7Type: &f&lBorder")));
        }

        inventory.setItem(42, makeItem(Material.PAPER, format("&4&lForced Tag")));
        if (SupremeTagsPremium.getInstance().getConfig().getBoolean("settings.forced-tag")) {
            inventory.setItem(51, makeItem(Material.LIME_DYE, format("&a&lEnabled")));
        } else {
            inventory.setItem(51, makeItem(Material.GRAY_DYE, format("&7&lDisabled")));
        }


        inventory.setItem(53, makeItem(Material.BARRIER, format("&cClose")));

        ///fillEmpty();
    }
}
