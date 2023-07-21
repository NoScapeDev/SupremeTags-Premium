package net.noscape.project.supremetags.managers;

import net.noscape.project.supremetags.*;

import net.noscape.project.supremetags.handlers.Tag;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static net.noscape.project.supremetags.utils.Utils.msgPlayer;

public class TagManager {

    private final Map<String, Tag> tags = new HashMap<>();
    private final Map<Integer, String> dataItem = new HashMap<>();

    private boolean isCost;

    private File tagFile;
    private FileConfiguration tagConfig;

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

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID, true);
            tags.put(identifier, tag);

            getTagConfig().set("tags." + identifier + ".tag", tag_string);
            getTagConfig().set("tags." + identifier + ".permission", permission);
            getTagConfig().set("tags." + identifier + ".order", orderID);
            getTagConfig().set("tags." + identifier + ".description", description);
            getTagConfig().set("tags." + identifier + ".withdrawable", true);
            getTagConfig().set("tags." + identifier + ".category", default_category);
            getTagConfig().set("tags." + identifier + ".display-item", "NAME_TAG");
            getTagConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            getTagConfig().set("tags." + identifier + ".cost", cost);
            saveTagConfig();

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

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID, true);
            tags.put(identifier, tag);

            getTagConfig().set("tags." + identifier + ".tag", tag_string);
            getTagConfig().set("tags." + identifier + ".permission", permission);
            getTagConfig().set("tags." + identifier + ".order", orderID);
            getTagConfig().set("tags." + identifier + ".description", description);
            getTagConfig().set("tags." + identifier + ".withdrawable", true);
            getTagConfig().set("tags." + identifier + ".category", default_category);
            getTagConfig().set("tags." + identifier + ".display-item", "NAME_TAG");
            getTagConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            getTagConfig().set("tags." + identifier + ".cost", cost);
            saveTagConfig();

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

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID, true);
            tags.put(identifier, tag);

            getTagConfig().set("tags." + identifier + ".tag", tag_string);
            getTagConfig().set("tags." + identifier + ".permission", permission);
            getTagConfig().set("tags." + identifier + ".order", orderID);
            getTagConfig().set("tags." + identifier + ".description", description);
            getTagConfig().set("tags." + identifier + ".withdrawable", true);
            getTagConfig().set("tags." + identifier + ".category", default_category);
            getTagConfig().set("tags." + identifier + ".display-item", "NAME_TAG");
            getTagConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            getTagConfig().set("tags." + identifier + ".cost", cost);
            saveTagConfig();
        }
    }

    public void createTag(String identifier, String material, String tag_string, String description, String permission, double cost) {
        if (!tags.containsKey(identifier)) {

            String default_category = SupremeTagsPremium.getInstance().getConfig().getString("settings.default-category");

            List<String> tagList = new ArrayList<>();
            tagList.add(tag_string);

            int orderID = tags.size() + 1;

            Tag tag = new Tag(identifier, tagList, default_category, permission, description, cost, orderID, true);
            tags.put(identifier, tag);

            getTagConfig().set("tags." + identifier + ".tag", tag_string);
            getTagConfig().set("tags." + identifier + ".permission", permission);
            getTagConfig().set("tags." + identifier + ".order", orderID);
            getTagConfig().set("tags." + identifier + ".description", description);
            getTagConfig().set("tags." + identifier + ".withdrawable", true);
            getTagConfig().set("tags." + identifier + ".category", default_category);
            getTagConfig().set("tags." + identifier + ".display-item", material);
            getTagConfig().set("tags." + identifier + ".displayname", "&7Tag: %tag%");
            getTagConfig().set("tags." + identifier + ".cost", cost);
            saveTagConfig();
        }
    }

    public void deleteTag(CommandSender player, String identifier) {
        if (tags.containsKey(identifier)) {
            tags.remove(identifier);

            try {
                getTagConfig().set("tags." + identifier, null);
                saveTagConfig();
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
                getTagConfig().set("tags." + identifier, null);
                saveTagConfig();
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
        for (String identifier : Objects.requireNonNull(getTagConfig().getConfigurationSection("tags")).getKeys(false)) {
            List<String> tag = getTagConfig().getStringList("tags." + identifier + ".tag");
            String category = getTagConfig().getString("tags." + identifier + ".category");
            String description = getTagConfig().getString("tags." + identifier + ".description");

            String permission;

            if (getTagConfig().getString("tags." + identifier + ".permission") != null) {
                permission = getTagConfig().getString("tags." + identifier + ".permission");
            } else {
                permission = "none";
            }

            int orderID = getTagConfig().getInt("tags." + identifier + ".order");

            double cost = getTagConfig().getDouble("tags." + identifier + ".cost");

            boolean withdrawable = getTagConfig().getBoolean("tags." + identifier + ".withdrawable");

            Tag t = new Tag(identifier, tag, category, permission, description, cost, orderID, withdrawable);
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

    public void saveTagConfig() {
        if (tagFile != null && tagConfig != null) {
            try {
                tagConfig.save(tagFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            loadFile();
            saveTagConfig();
        }
    }

    public FileConfiguration getTagConfig() {
        return tagConfig;
    }

    public void loadFile() {
        File file = new File(SupremeTagsPremium.getInstance().getDataFolder() + "tags.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.tagFile = file;
        this.tagConfig = YamlConfiguration.loadConfiguration(file);
    }
}
