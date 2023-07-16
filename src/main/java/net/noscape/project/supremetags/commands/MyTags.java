package net.noscape.project.supremetags.commands;

import net.noscape.project.supremetags.SupremeTags;
import net.noscape.project.supremetags.guis.PersonalTagsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.noscape.project.supremetags.utils.Utils.msgPlayer;

public class MyTags implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String reload = SupremeTags.getInstance().getConfig().getString("messages.reload");
        String notags = SupremeTags.getInstance().getConfig().getString("messages.no-tags");
        String noperm = SupremeTags.getInstance().getConfig().getString("messages.no-permission");

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("mytags")) {
            if (player.hasPermission("supremetags.mytags")) {
                new PersonalTagsMenu(SupremeTags.getMenuUtil(player)).open();
            } else {
                msgPlayer(player, noperm);
            }
        }
        return false;
    }
}
