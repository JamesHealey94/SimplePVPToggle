package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.commands.HelpCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommandExecutor;
import com.gmail.jameshealey1994.simplepvptoggle.listeners.SimplePVPToggleListener;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to simply and easily set the PVP status of players, worlds, or the entire server.
 * @author James Healey
 */
public class SimplePVPToggle extends JavaPlugin {

    /**
     * Commands belonging to the plugin.
     */
    private List<SimplePVPToggleCommand> commands;

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
    public List<SimplePVPToggleCommand> getCommands() {
        return commands;
    }
}