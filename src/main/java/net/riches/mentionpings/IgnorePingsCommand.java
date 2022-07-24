package net.riches.mentionpings;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class IgnorePingsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> players = MentionAlerts.getInstance().getConfig().getStringList("config.players-ignoring");

        if (players.contains(((Player)sender).getDisplayName())) {
            players.remove(((Player)sender).getDisplayName());
            MentionAlerts.getInstance().getConfig().set("config.players-ignoring", players);
            MentionAlerts.getInstance().saveConfig();

            sender.sendMessage(ChatColor.GREEN + "Successfully " + ChatColor.AQUA + "enabled" + ChatColor.GREEN + " pings!");
            return true;
        }

        players.add(sender.getName());
        MentionAlerts.getInstance().getConfig().set("config.players-ignoring", players);
        MentionAlerts.getInstance().saveConfig();

        sender.sendMessage(ChatColor.GREEN + "Successfully " + ChatColor.AQUA + "disabled" + ChatColor.GREEN + " pings!");
        return true;
    }
}
