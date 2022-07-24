package net.riches.mentionpings;

import net.riches.mentionpings.events.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MentionAlerts extends JavaPlugin {

    private static MentionAlerts instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
        getCommand("ignorepings").setExecutor(new IgnorePingsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveConfig();
    }

    public static MentionAlerts getInstance() {
        return instance;
    }
}
