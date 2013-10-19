package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.listeners.SimplePVPToggleListener;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to simply and easily set the PVP status of players, worlds, or the entire server.
 * @author James Healey
 */
public class SimplePVPToggle extends JavaPlugin {

    @Override
    public void onEnable() {
        
        getServer().getPluginManager().registerEvents(new SimplePVPToggleListener(this), this);;

        // This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
        getCommand("spt").setExecutor(new SimplePVPToggleCommandExecutor(this));
    }
    
    /**
     * Reloads values from configuration file.
     * @param sender The sender of the command
     * @return If reload was completed correctly.
     */
    public boolean reload(CommandSender sender) {
        if (sender.hasPermission("spt.reload")) {
            reloadConfig();
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Configuration reloaded.");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "You need permission 'spt.reload' to use this command.");
            return false;
        }
    }
}