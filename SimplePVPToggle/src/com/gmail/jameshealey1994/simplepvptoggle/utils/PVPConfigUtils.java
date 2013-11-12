package com.gmail.jameshealey1994.simplepvptoggle.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for PVP values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class PVPConfigUtils {

    /**
     * The string for the value in the config this class is interacting with.
     */
    public static final String CONFIG_STRING = "PVP";

    /**
     * The default value, used if no other values are found.
     */
    public static final boolean DEFAULT = false;

    /**
     * Sets PVP status of the passed player in the passed world to the passed
     * state, then saves the config.
     *
     * @param player    player to set the PVP status of
     * @param world     world in which to set the player's PVP status
     * @param status    status to change to
     * @param plugin    plugin with the config storing PVP status values
     */
    public static void setPlayerStatus(Player player, World world, boolean status, Plugin plugin) {
        final String path = "Server.Worlds." + world.getName() + ".Players." + player.getName() + "." + CONFIG_STRING;
        plugin.getConfig().set(path, status);
        plugin.saveConfig();
    }

    /**
     * Sets the default PVP status of the passed world to the passed state,
     * then saves the config.
     *
     * @param world     world to set the default PVP status of
     * @param status    status to change to
     * @param plugin    plugin with the config storing PVP status values
     */
    public static void setWorldStatus(World world, boolean status, Plugin plugin) {
        final String path = "Server.Worlds." + world.getName() + "." + CONFIG_STRING;
        plugin.getConfig().set(path, status);
        plugin.saveConfig();
    }

    /**
     * Return the specified player's PvP status in a specified world.
     * The method first looks for a specific value for the player in the world.
     *
     * If that is not found, the method checks the default value for the world.
     *
     * If that is not found, the method checks the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is false.
     *
     * @param player    the player being checked
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores PVP data
     * @return          the PVP status of player in world
     */
    public static boolean getPlayerStatus(Player player, World world, Plugin plugin) {
        return (plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Players." + player.getName() + "." + CONFIG_STRING,
                plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Players." + player.getName(),
                getWorldStatus(world, plugin))));
    }

    /**
     * Return the default PvP status of a specified world.
     * The method first looks for the default value for the world.
     *
     * If that is not found, the method checks the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is false.
     *
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores PVP data
     * @return          the default PVP status of world
     */
    public static boolean getWorldStatus(World world, Plugin plugin) {
        return  plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + "." + CONFIG_STRING,
                plugin.getConfig().getBoolean("Server.Worlds." + world.getName(),
                getServerStatus(plugin)));
    }

    /**
     * Return the default PvP status of the server.
     * The method first looks for the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is false.
     *
     * @param plugin    plugin with config which stores PVP data
     * @return          the default PVP status of the server
     */
    public static boolean getServerStatus(Plugin plugin) {
        return  plugin.getConfig().getBoolean("Server." + CONFIG_STRING,
                plugin.getConfig().getBoolean("Server",
                DEFAULT));
    }
}