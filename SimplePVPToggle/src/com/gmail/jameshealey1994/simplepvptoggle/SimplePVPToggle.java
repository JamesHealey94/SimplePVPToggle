package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.commands.HelpCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommandExecutor;
import com.gmail.jameshealey1994.simplepvptoggle.listeners.SimplePVPToggleListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to simply and easily set the PVP status of players, worlds, or the
 * entire server.
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SimplePVPToggle extends JavaPlugin {

    /**
     * The current command environment for the plugin (subset of commands
     * accessible from the current state of the plugin).
     */
    private SimplePVPToggleCommandEnvironment commandEnvironment = new DefaultSimplePVPToggleCommandEnvironment();

    /**
     * Gets the current command environment for the plugin.
     * 
     * @return  the current command environment for the plugin
     */
    public SimplePVPToggleCommandEnvironment getCommandEnvironment() {
        return commandEnvironment;
    }
    
    /**
     * Sets the current command environment for the plugin.
     * 
     * @param commandEnvironment    the new current command environment for
     *                              the plugin
     */
    public void setCommandEnvironment(SimplePVPToggleCommandEnvironment commandEnvironment) {
        this.commandEnvironment = commandEnvironment;
    }
    
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
     * Returns an array of commands belonging to the plugin.
     * 
     * @return  commands belonging to the plugin
     */
    public SimplePVPToggleCommand[] getCommands() {
        return commandEnvironment.getCommands();
    }
    
    /**
     * Check if a player's PvP value is set to true in the config.
     * It first looks for a specific value for that player in that world.
     * 
     * If that is not found, it is set to the default value for the world the
     * player is currently in.
     * 
     * If that is not found, it is set to the default value for the server.
     * 
     * If there is an error with the default value for the server, it is set to
     * false by default.
     * 
     * @param player    the player being checked
     * @return          if the player can PVP in that world
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