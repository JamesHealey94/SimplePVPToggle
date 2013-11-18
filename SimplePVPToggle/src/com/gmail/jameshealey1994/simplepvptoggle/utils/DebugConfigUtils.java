package com.gmail.jameshealey1994.simplepvptoggle.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for debug values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class DebugConfigUtils {

    /**
     * The path to the Debug value in the config.
     */
    public static final String PATH_DEBUG = "Debug";

    /**
     * The default value, used if no other values are found.
     */
    public static final boolean DEFAULT = false;

    /**
     * Returns if debug mode is enabled, by checking the configuration file.
     *
     * @param plugin    plugin with associated configuration file
     * @return          if debug mode is enabled
     */
    public static boolean getDebugEnabled(Plugin plugin) {
        return plugin.getConfig().getBoolean(PATH_DEBUG, DEFAULT);
    }

    /**
     * Sets the debug mode for the plugin, then saves the config file.
     *
     * @param sender        sender of the status
     * @param debugStatus   new debug status
     * @param plugin        plugin with associated config file
     */
    public static void setDebugEnabled(CommandSender sender, boolean debugStatus, Plugin plugin) {
        plugin.getConfig().set(PATH_DEBUG, debugStatus);
        plugin.saveConfig();
    }
}