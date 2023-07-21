package net.noscape.project.supremetags.managers;

import net.noscape.project.supremetags.*;

import net.noscape.project.supremetags.handlers.Tag;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static net.noscape.project.supremetags.utils.Utils.msgPlayer;

public class TagManager {

    private final Map<String, Tag> tags = new HashMap<>();
    private final Map<Integer, String> dataItem = new HashMap<>();

    ArrayList<BukkitTask> scheduler = new ArrayList<>();

    private boolean isCost;

    private final String reload = SupremeTagsPremium.getInstance().getConfig().getString("messages.reload");
    private final String noperm = SupremeTagsPremium.getInstance().getConfig().getString("messages.no-permission");
    private final String notags = SupremeTagsPremium.getInstance().getConfig().getString("messages.no-tags");
    private final String commanddisabled = SupremeTagsPremium.getInstance().getConfig().getString("messages.tag-command-disabled");
    private final String invalidtag = SupremeTagsPremium.getInstance().getConfig().getString("messages.invalid-tag");
    private final String validtag = SupremeTagsPremium.getInstance().getConfig().getString("messages.valid-tag");
    private final String invalidcategory = SupremeTagsPremium.getInstance().getConfig().getString("messages.invalid-category");

    public TagManager(boolean isCost) {
        this.isCost = isCost;
    }

    public void createTag(Player player, String identifier, String tag_string, String description, String permission, double cost) {
        if (!tags.containsKey(identifier)) {

            String default_category = SupremeTagsPremium.getInstance().getConfig().getString("settings.default-category");

            List<String> tagList = new ArrayList<>();
            tagList.add(tag_string);

            int orderID = tags.size() + 1;

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID);
            tags.put(identifier, tag);

            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".tag", tag_string);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".permission", permission);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".order", orderID);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".description", description);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".category", default_category);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".display-item", "NAME_TAG");
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".cost", cost);
            SupremeTagsPremium.getInstance().saveConfig();
            SupremeTagsPremium.getInstance().reloadConfig();

            msgPlayer(player, "&8[&6&lTAG&8] &7New tag created &6" + identifier + " &f- " + tag_string);
        } else {
            msgPlayer(player, validtag);
        }
    }

    public void createTag(CommandSender player, String identifier, String tag_string, String description, String permission, double cost) {
        if (!tags.containsKey(identifier)) {

            String default_category = SupremeTagsPremium.getInstance().getConfig().getString("settings.default-category");

            List<String> tagList = new ArrayList<>();
            tagList.add(tag_string);

            int orderID = tags.size() + 1;

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID);
            tags.put(identifier, tag);

            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".tag", tag_string);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".permission", permission);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".order", orderID);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".description", description);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".category", default_category);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".display-item", "NAME_TAG");
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".cost", cost);
            SupremeTagsPremium.getInstance().saveConfig();
            SupremeTagsPremium.getInstance().reloadConfig();

            msgPlayer(player, "&8[&6&lTAG&8] &7New tag created &6" + identifier + " &f- " + tag_string);
        } else {
            msgPlayer(player, validtag);
        }
    }

    public void createTag(String identifier, String tag_string, String description, String permission, double cost) {
        if (!tags.containsKey(identifier)) {

            String default_category = SupremeTagsPremium.getInstance().getConfig().getString("settings.default-category");

            List<String> tagList = new ArrayList<>();
            tagList.add(tag_string);

            int orderID = tags.size() + 1;

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID);
            tags.put(identifier, tag);

            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".tag", tag_string);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".permission", permission);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".order", orderID);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".description", description);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".category", default_category);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".display-item", "NAME_TAG");
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".cost", cost);
            SupremeTagsPremium.getInstance().saveConfig();
            SupremeTagsPremium.getInstance().reloadConfig();
        }
    }

    public void createTag(String identifier, String material, String tag_string, String description, String permission, double cost) {
        if (!tags.containsKey(identifier)) {

            String default_category = SupremeTagsPremium.getInstance().getConfig().getString("settings.default-category");

            List<String> tagList = new ArrayList<>();
            tagList.add(tag_string);

            int orderID = tags.size() + 1;

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID);
            tags.put(identifier, tag);

            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".tag", tag_string);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".permission", permission);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".order", orderID);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".description", description);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".category", default_category);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".display-item", material);
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".cost", cost);
            SupremeTagsPremium.getInstance().saveConfig();
            SupremeTagsPremium.getInstance().reloadConfig();
        }
    }

    public void deleteTag(CommandSender player, String identifier) {
        if (tags.containsKey(identifier)) {
            tags.remove(identifier);

            try {
                SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier, null);
                SupremeTagsPremium.getInstance().saveConfig();
                SupremeTagsPremium.getInstance().reloadConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }

            msgPlayer(player, "&8[&6&lTAG&8] &7Tag &6" + identifier + " &7is now deleted!");
        } else {
            msgPlayer(player, invalidtag);
        }
    }

    public void deleteTag(Player player, String identifier) {
        if (tags.containsKey(identifier)) {
            tags.remove(identifier);

            try {
                SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier, null);
                SupremeTagsPremium.getInstance().saveConfig();
                SupremeTagsPremium.getInstance().reloadConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }

            msgPlayer(player, "&8[&6&lTAG&8] &7Tag &6" + identifier + " &7is now deleted!");
        } else {
            msgPlayer(player, invalidtag);
        }
    }

    public void loadTags() {
        int count = 0;
        for (String identifier : Objects.requireNonNull(SupremeTagsPremium.getInstance().getConfig().getConfigurationSection("tags")).getKeys(false)) {
            List<String> tag = SupremeTagsPremium.getInstance().getConfig().getStringList("tags." + identifier + ".tag");
            String category = SupremeTagsPremium.getInstance().getConfig().getString("tags." + identifier + ".category");
            String description = SupremeTagsPremium.getInstance().getConfig().getString("tags." + identifier + ".description");

            String permission;

            if (SupremeTagsPremium.getInstance().getConfig().getString("tags." + identifier + ".permission") != null) {
                permission = SupremeTagsPremium.getInstance().getConfig().getString("tags." + identifier + ".permission");
            } else {
                permission = "none";
            }

            int orderID = SupremeTagsPremium.getInstance().getConfig().getInt("tags." + identifier + ".order");

            double cost = SupremeTagsPremium.getInstance().getConfig().getDouble("tags." + identifier + ".cost");

            Tag t = new Tag(identifier, tag, category, permission, description, cost, orderID);
            tags.put(identifier, t);
            count++;
        }

        if (getTags().size() != 0) {
            for (Tag t : getTags().values()) {
                if (t.getTag().size() > 1) {
                    t.startAnimation();
                }
            }
        }

        Bukkit.getConsoleSender().sendMessage("[TAGS] loaded " + count + " tag(s) successfully.");
    }

    public Tag getTag(String tag) {
        Tag t = null;
        for (Tag tg : tags.values()) {
            if (tg.getIdentifier().equalsIgnoreCase(tag)) {
                t = tg;
            }
        }

        return t;
    }

    public void unloadTags() {
        if (!tags.isEmpty()) {
            tags.clear();
        }
    }

    public Map<String, Tag> getTags() {
        return tags;
    }
    public Map<Integer, String> getDataItem() { return dataItem; }

    public void setTag(Player player, String identifier, String tag) {
        if (tags.containsKey(identifier)) {
            Tag t = tags.get(identifier);
            List<String> tagsList = t.getTag();
            tagsList.add(tag);
            t.setTag(tagsList);

            try {
                SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".tag", tagsList);
                SupremeTagsPremium.getInstance().saveConfig();
                SupremeTagsPremium.getInstance().reloadConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }

            msgPlayer(player, "&8[&6&lTAG&8] &6" + t.getIdentifier() + "'s tag &7changed to " + t.getCurrentTag());
        } else {
            msgPlayer(player, invalidtag);
        }
    }

    public void setCategory(Player player, String identifier, String category) {
        if (tags.containsKey(identifier)) {
            Tag t = tags.get(identifier);
            t.setCategory(category);

            try {
                SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".category", t.getCategory());
                SupremeTagsPremium.getInstance().saveConfig();
                SupremeTagsPremium.getInstance().reloadConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }

            msgPlayer(player, "&8[&6&lTAG&8] &6" + t.getIdentifier() + "'s category &7changed to " + t.getCategory());
        } else {
            msgPlayer(player, invalidcategory);
        }
    }

    public void setTag(CommandSender player, String identifier, String tag) {
        if (tags.containsKey(identifier)) {
            Tag t = tags.get(identifier);
            List<String> tagsList = t.getTag();
            tagsList.add(tag);
            t.setTag(tagsList);

            try {
                SupremeTagsPremium.getInstance().getConfig().set("tags." + identifier + ".tag", t.getCurrentTag());
                SupremeTagsPremium.getInstance().saveConfig();
                SupremeTagsPremium.getInstance().reloadConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }

            msgPlayer(player, "&8[&6&lTAG&8] &6" + t.getIdentifier() + "'s tag &7changed to " + t.getCurrentTag());
        } else {
            msgPlayer(player, invalidtag);
        }
    }

    public boolean isCost() {
        return isCost;
    }

    public void setCost(boolean isCost) {
        this.isCost = isCost;
    }

    public void saveTag(Tag tag) {
        SupremeTagsPremium.getInstance().getConfig().set("tags." + tag.getIdentifier() + ".tag", tag.getTag());
        SupremeTagsPremium.getInstance().getConfig().set("tags." + tag.getIdentifier() + ".permission", tag.getPermission());
        SupremeTagsPremium.getInstance().getConfig().set("tags." + tag.getIdentifier() + ".description", tag.getDescription());
        SupremeTagsPremium.getInstance().getConfig().set("tags." + tag.getIdentifier() + ".category", tag.getCategory());
        SupremeTagsPremium.getInstance().getConfig().set("tags." + tag.getIdentifier() + ".cost", tag.getCost());
        SupremeTagsPremium.getInstance().saveConfig();
        SupremeTagsPremium.getInstance().reloadConfig();
    }
}
