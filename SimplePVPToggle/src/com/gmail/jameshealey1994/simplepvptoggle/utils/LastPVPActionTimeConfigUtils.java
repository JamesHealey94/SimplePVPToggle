package com.gmail.jameshealey1994.simplepvptoggle.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for LastPVPActionTime
 * values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class LastPVPActionTimeConfigUtils {

    /**
     * The string for the value in the config this class is interacting with.
     */
    public static final String CONFIG_STRING = "LastPVPActionTime";

    /**
     * The default value, used if no other values are found.
     */
    public static final int DEFAULT = 0;

    /**
     * Sets the LastPVPActionTime value of the passed player in the passed world
     * to the current Unix time in milliseconds, then saves the config.
     *
     * @param player    player to set the LastPVPActionTime value of
     * @param world     world in which to set the player's LastPVPActionTime
     *                  value
     * @param plugin    plugin with the config storing LastPVPActionTime values
     */
    public static void update(Player player, World world, Plugin plugin) {
        final String path = "Server.Worlds." + world.getName() + ".Players." + player.getName() + "." + CONFIG_STRING;
        plugin.getConfig().set(path, System.currentTimeMillis());
        plugin.saveConfig();
    }

    /**
     * Return the specified player's LastPVPActionTime in a specified world.
     * The method first looks for a specific value for the player in the world.
     *
     * If there is an error with the default value for the server, the default
     * is returned.
     *
     * Currently the default is 0.
     *
     * @param player    the player being checked
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores LastPVPActionTime data
     * @return          the LastPVPActionTime value of player in world
     */
    public static long getLastPVPActionTime(Player player, World world, Plugin plugin) {
        return plugin.getConfig().getLong("Server.Worlds." + world.getName() + ".Players." + player.getName() + "." + CONFIG_STRING,
                DEFAULT);
    }

    /**
     * Returns cooldown time left for a player in a world specified.
     *
     * @param player        the player with a LastPVPAction value
     * @param world         the world the player is in
     * @param plugin        plugin with config which stores necessary data
     * @return              the cooldown time left for player in world
     */
    public static long getCooldownTimeLeft(Player player, World world, Plugin plugin) {
        return getLastPVPActionTime(player, world, plugin) + (CooldownConfigUtils.getWorldValue(world, plugin) * 1000) - System.currentTimeMillis();
    }
}