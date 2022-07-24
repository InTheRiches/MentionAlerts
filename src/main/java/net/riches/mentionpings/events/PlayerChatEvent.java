package net.riches.mentionpings.events;

import net.riches.mentionpings.MentionAlerts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class PlayerChatEvent implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!e.getMessage().contains(p.getDisplayName()))
                continue;

            String msg = e.getMessage();
            e.setMessage(msg.replace(p.getDisplayName(), ChatColor.GOLD + "@" + p.getDisplayName() + ChatColor.RESET));

            if (p.equals(e.getPlayer())) continue;

            pingPlayer(p);
        }
    }

    public void pingPlayer(Player p) {
        List<String> players = MentionAlerts.getInstance().getConfig().getStringList("config.players-ignoring");

        if (players.contains(p.getDisplayName())) return;

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f);
    }
}
