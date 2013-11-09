package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisable;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommandEnvironment;
import com.gmail.jameshealey1994.simplepvptoggle.commands.DefaultSimplePVPToggleCommandEnvironment;
import com.gmail.jameshealey1994.simplepvptoggle.commands.HelpCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommandExecutor;
import com.gmail.jameshealey1994.simplepvptoggle.listeners.SimplePVPToggleListener;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to simply and easily set the PVP status of players, worlds, or the
 * entire server.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SimplePVPToggle extends JavaPlugin implements Localisable {

    /**
     * The current command environment for the plugin (subset of commands
     * accessible from the current state of the plugin).
     */
    private SimplePVPToggleCommandEnvironment commandEnvironment = new DefaultSimplePVPToggleCommandEnvironment();

    /**
     * The current localisation for the plugin.
     */
    private Localisation localisation = new Localisation(this);

    @Override
    public void onEnable() {

        // Save a copy of the default config.yml if one is not there
        saveDefaultConfig();

        // Register events
        getServer().getPluginManager().registerEvents(new SimplePVPToggleListener(this), this);

        // Set command executors and default command
        getCommand("spt").setExecutor(new SimplePVPToggleCommandExecutor(this, new HelpCommand()));
    }

    /**
     * Returns an array of commands belonging to the plugin.
     *
     * @return      commands belonging to the plugin
     */
    public SimplePVPToggleCommand[] getCommands() {
        return commandEnvironment.getCommands();
    }

    /**
     * Gets the current command environment for the plugin.
     *
     * @return      the current command environment for the plugin
     */
    public SimplePVPToggleCommandEnvironment getCommandEnvironment() {
        return commandEnvironment;
    }

    /**
     * Sets the current command environment for the plugin.
     *
     * @param commandEnvironment    the new command environment for the plugin
     */
    public void setCommandEnvironment(SimplePVPToggleCommandEnvironment commandEnvironment) {
        this.commandEnvironment = commandEnvironment;
    }

    @Override
    public Localisation getLocalisation() {
        return localisation;
    }

    @Override
    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }
}