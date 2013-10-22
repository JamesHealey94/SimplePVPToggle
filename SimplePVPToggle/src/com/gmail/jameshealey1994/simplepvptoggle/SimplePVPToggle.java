package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.commands.HelpCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.ReloadCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommandExecutor;
import com.gmail.jameshealey1994.simplepvptoggle.commands.StatusCommand;
import com.gmail.jameshealey1994.simplepvptoggle.listeners.SimplePVPToggleListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to simply and easily set the PVP status of players, worlds, or the entire server.
 * @author James Healey
 */
public class SimplePVPToggle extends JavaPlugin {

    /**
     * Commands belonging to the plugin.
     * Listed in priority order (earlier items in the array have a higher priority, when it comes to aliases for example).
     */
    //TODO: Find a nice way to take this relatively unstable code out of this otherwise relatively stable class.
    final private SimplePVPToggleCommand[] commands = new SimplePVPToggleCommand[]{new HelpCommand(), new ReloadCommand(), new StatusCommand()};

    @Override
    public void onEnable() {

        // Save a copy of the default config.yml if one is not there
        saveDefaultConfig();

        // Register events
        getServer().getPluginManager().registerEvents(new SimplePVPToggleListener(this), this);

        // Set command executors
        getCommand("spt").setExecutor(new SimplePVPToggleCommandExecutor(this, new HelpCommand()));
    }

    /**
     * Returns commands belonging to the plugin.
     * @return Commands belonging to the plugin
     */
    public SimplePVPToggleCommand[] getCommands() {
        return commands;
    }
    
    /**
     * Helper method used to check if a player's PvP value is set to true in the config.
     * It first looks for a specific value for that player in that world.
     * If that is not found, it is set to the default value for the world the player is currently in.
     * If that is not found, it is set to the default value for the server.
     * If there is an error with the default value for the server, it is set to false by default.
     * @param player The player being checked
     * @return If the player can PVP in that world
     */
    public boolean canPVP(Player player) {
        // Debug messages to show the config values
        // TODO: Add some more statements to help players with "lazy" configs (without .Default, for example).
//        player.sendMessage("Server.Worlds." + player.getWorld().getName() + ".Players." + player.getName() + ": " + this.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName() + ".Players." + player.getName()));
//        player.sendMessage("Server.Worlds." + player.getWorld().getName() + ".Default" + ": " + this.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName() + ".Default"));
//        player.sendMessage("Server.Worlds." + player.getWorld().getName() + ": " + this.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName()));
//        player.sendMessage("Server.Default" + ": " + plugin.getConfig().getBoolean("Server.Default"));
        
        return (this.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName() + ".Players." + player.getName(),
                this.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName() + ".Default",
                this.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName(),
                this.getConfig().getBoolean("Server.Default", false)))));
    }
}