package com.gmail.jameshealey1994.simplepvptoggle.utils;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import org.bukkit.command.CommandSender;

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
     * Returns if debug mode is enabled, by checking the configuration file.
     *
     * @param plugin    plugin with associated configuration file
     * @return          if debug mode is enabled
     */
    public static boolean getDebugEnabled(SimplePVPToggle plugin) {
        return plugin.getConfig().getBoolean(PATH_DEBUG, false);
    }

    /**
     * Sets the debug mode for the plugin, saves the config file, then sends a
     * message to the sender.
     *
     * @param sender        sender of the status
     * @param debugStatus   new debug status
     * @param plugin        plugin with associated config file
     */
    public static void setDebugEnabled(CommandSender sender, boolean debugStatus, SimplePVPToggle plugin) {
        plugin.getConfig().set(PATH_DEBUG, debugStatus);
        plugin.saveConfig();
        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_DEBUG_SET_TO_STATUS, new Object[] {getDebugEnabled(plugin)}));
    }
}