package net.noscape.project.supremetags.managers;

import net.noscape.project.supremetags.*;

import java.util.*;

public class CategoryManager {

    private final List<String> catorgies = new ArrayList<>();
    private final Map<String, Integer> catorgiesTags = new HashMap<>();

    public CategoryManager() {}

    public void initCategories() {
        catorgies.clear();
        catorgies.addAll(Objects.requireNonNull(SupremeTagsPremium.getInstance().getConfig().getConfigurationSection("categories")).getKeys(false));
        loadCategoriesTags();
    }

    public void loadCategoriesTags() {
        catorgiesTags.clear();

        for (String cats : getCatorgies()) {
            int value = 0;
            for (String tags : Objects.requireNonNull(SupremeTagsPremium.getInstance().getConfig().getConfigurationSection("tags")).getKeys(false)) {
                String cat = SupremeTagsPremium.getInstance().getConfig().getString("tags." + tags + ".category");
                if (cats.equals(cat)) {
                    value++;
                }
            }
            catorgiesTags.put(cats, value);
        }
    }

    public List<String> getCatorgies() {
        return catorgies;
    }

    public Map<String, Integer> getCatorgiesTags() {
        return catorgiesTags;
    }
}
