package com.gmail.jameshealey1994.simplepvptoggle.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for cooldown values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class CooldownConfigUtils {

    /**
     * The string for the value in the config this class is interacting with.
     */
    public static final String CONFIG_STRING = "Cooldown";

    /**
     * The default value, used if no other values are found.
     */
    public static final int DEFAULT = 0;

    /**
     * Return the specified player's cooldown value in a specified world.
     * The method first looks for a specific value for the player in the world.
     *
     * If that is not found, the method checks the default value for the world.
     *
     * If that is not found, the method checks the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is 0.
     *
     * @param player    the player being checked
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores cooldown data
     * @return          the cooldown value of player in world
     */
    public static int getPlayerValue(Player player, World world, Plugin plugin) {
        return (plugin.getConfig().getInt("Server.Worlds." + world.getName() + ".Players." + player.getName() + "." + CONFIG_STRING,
                getWorldValue(world, plugin)));
    }

    /**
     * Return the default cooldown value of a specified world.
     * The method first looks for the default value for the world.
     *
     * If that is not found, the method checks the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is 0.
     *
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores cooldown data
     * @return          the default cooldown value of world
     */
    public static int getWorldValue(World world, Plugin plugin) {
        return  plugin.getConfig().getInt("Server.Worlds." + world.getName() + "." + CONFIG_STRING,
                getServerValue(plugin));
    }

    /**
     * Return the default cooldown value of the server.
     * The method first looks for the default value for the server.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is 0.
     *
     * @param plugin    plugin with config which stores cooldown data
     * @return          the default cooldown value of the server
     */
    public static int getServerValue(Plugin plugin) {
        return  plugin.getConfig().getInt("Server." + CONFIG_STRING,
                DEFAULT);
    }
}