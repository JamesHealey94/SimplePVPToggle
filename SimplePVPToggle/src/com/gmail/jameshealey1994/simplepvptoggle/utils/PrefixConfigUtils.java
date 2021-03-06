package com.gmail.jameshealey1994.simplepvptoggle.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for prefix values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class PrefixConfigUtils {

    /**
     * The string for the value in the config this class is interacting with.
     */
    public static final String CONFIG_STRING = "Prefix";

    /**
     * The default value, used if no other values are found.
     */
    public static final String DEFAULT = "";

    /**
     * Return the specified player's prefix in a specified world.
     * The method first looks for a specific value for the player in the world.
     *
     * If that is not found, the method checks the default value for the world.
     *
     * If that is not found, the method checks the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is an empty string.
     *
     * @param player    the player being checked
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores prefix data
     * @return          the prefix value of player in world
     */
    public static String getPlayerPrefix(Player player, World world, Plugin plugin) {
        return (plugin.getConfig().getString("Server.Worlds." + world.getName() + ".Players." + player.getName() + "." + CONFIG_STRING,
                getWorldPrefix(world, plugin)));
    }

    /**
     * Return the prefix value of a specified world.
     * The method first looks for the default value for the world.
     *
     * If that is not found, the method checks the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is an empty string.
     *
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores prefix data
     * @return          the prefix value of world
     */
    public static String getWorldPrefix(World world, Plugin plugin) {
        return  plugin.getConfig().getString("Server.Worlds." + world.getName() + "." + CONFIG_STRING,
                getServerPrefix(plugin));
    }

    /**
     * Return the prefix value of the server.
     * The method first looks for the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is an empty string.
     *
     * @param plugin    plugin with config which stores prefix data
     * @return          the default prefix value of the server
     */
    public static String getServerPrefix(Plugin plugin) {
        return  plugin.getConfig().getString("Server." + CONFIG_STRING,
                DEFAULT);
    }
}