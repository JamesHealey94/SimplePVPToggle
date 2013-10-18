package com.gmail.jameshealey1994.simplepvptoggle;

import com.gmail.jameshealey1994.simplepvptoggle.listeners.SimplePVPToggleListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to simply and easily set the PVP status of players, worlds, or the entire server.
 * @author James Healey
 */
public class SimplePVPToggle extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new SimplePVPToggleListener(), this);

        // This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
        getCommand("basic").setExecutor(new SimplePVPToggleCommandExecutor(this));
    }
}