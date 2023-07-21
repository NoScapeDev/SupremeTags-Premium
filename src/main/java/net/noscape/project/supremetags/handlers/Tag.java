package net.noscape.project.supremetags.handlers;

import net.noscape.project.supremetags.SupremeTagsPremium;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    private String identifier;
    private List<String> tag;
    private String category;
    private String permission;
    private String description;
    private double cost;
    private String current_tag;
    private int order;

    private BukkitTask animationTask;

    public Tag(String identifier, List<String> tag, String category, String permission, String description, double cost, int order) {
        this.identifier = identifier;
        this.tag = tag;
        this.category = category;
        this.permission = permission;
        this.description = description;
        this.cost = cost;
        this.order = order;
    }

    public Tag(String identifier, List<String> tag, String category, String permission, String description, double cost) {
        this.identifier = identifier;
        this.tag = tag;
        this.category = category;
        this.permission = permission;
        this.description = description;
        this.cost = cost;
    }

    public Tag(String identifier, List<String> tag, String description) {
        this.identifier = identifier;
        this.tag = tag;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void startAnimation() {
        // Check if scheduler is needed (don't schedule if higher than '9999' or negative)
        int speed = SupremeTagsPremium.getInstance().getConfig().getInt("settings.animated-tag-speed");
        if (speed <= 0 || speed > 9999) {
            // Handle invalid speed value
            return;
        }

        // Stop the animation task if it's already running
        stopAnimation();

        animationTask = new BukkitRunnable() {
            int currentIndex = 0;

            @Override
            public void run() {
                // Increment the animation frame index and loop back to the beginning if necessary
                currentIndex = (currentIndex + 1) % tag.size();

                // Get the current frame
                current_tag = tag.get(currentIndex);
            }
        }.runTaskTimerAsynchronously(SupremeTagsPremium.getInstance(), 0, 20L * speed);
    }

    public void stopAnimation() {
        if (animationTask != null) {
            animationTask.cancel();
            animationTask = null;
        }
    }

    private List<String> getAnimatedTag(String currentFrame) {
        // Modify this method according to your animation requirements
        // This example assumes the currentFrame is a prefix to be added to each tag line
        List<String> animatedTag = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : tag) {
            stringBuilder.setLength(0); // Clear the StringBuilder
            stringBuilder.append(currentFrame).append(line);
            animatedTag.add(stringBuilder.toString());
        }
        return animatedTag;
    }


    public String getCurrentTag() {
        return current_tag;
    }

    public int getOrder() {
        return order;
    }
}