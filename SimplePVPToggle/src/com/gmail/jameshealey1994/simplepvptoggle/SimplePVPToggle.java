package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.commands.HelpCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.ReloadCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SetDefaultServerCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommandExecutor;
import com.gmail.jameshealey1994.simplepvptoggle.listeners.SimplePVPToggleListener;
import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to simply and easily set the PVP status of players, worlds, or the entire server.
 * @author James Healey
 */
public class SimplePVPToggle extends JavaPlugin {

    /**
     * Commands belonging to the plugin.
     */
    private ArrayList<SimplePVPToggleCommand> commands = new ArrayList<>();

    @Override
    public void onEnable() {

        // Save a copy of the default config.yml if one is not there
        saveDefaultConfig();

        // Register events
        getServer().getPluginManager().registerEvents(new SimplePVPToggleListener(this), this);

        // Set command executors
        getCommand("spt").setExecutor(new SimplePVPToggleCommandExecutor(this, new HelpCommand()));
        
        // TODO: Take volatile code out of stable class.
        commands.add(new HelpCommand());
        commands.add(new ReloadCommand());
        commands.add(new SetDefaultServerCommand());
    }

    /**
     * Returns commands belonging to the plugin.
     * @return Commands belonging to the plugin
     */
    public ArrayList<SimplePVPToggleCommand> getCommands() {
        return commands;
    }
}