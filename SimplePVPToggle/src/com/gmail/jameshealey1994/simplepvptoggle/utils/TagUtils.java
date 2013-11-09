package com.gmail.jameshealey1994.simplepvptoggle.utils;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.tag.TagAPI;

/**
 * Utility methods that interact with TagAPI.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class TagUtils {

    /**
     * The name of the TagAPI plugin.
     */
    public static final String NAME = "TagAPI";

    /**
     * Returns if TagAPI is enabled.
     *
     * @param plugin    plugin with related server and plugin manager
     * @return          if TagAPI is enabled
     */
    public static boolean isEnabled(JavaPlugin plugin) {
        return plugin.getServer().getPluginManager().isPluginEnabled(NAME);
    }

    /**
     * If TagAPI is enabled, then refresh player tag.
     *
     * @param player    player with tag to refresh
     * @param plugin    plugin with possible TagAPI
     */
    public static void refreshPlayer(Player player, JavaPlugin plugin) {
        if (isEnabled(plugin)) {
            TagAPI.refreshPlayer(player);
        }
    }

    /**
     * If TagAPI is enabled, then refresh player tag for a certain player.
     *
     * @param player    player with tag to refresh
     * @param forWhom   player to update display to
     * @param plugin    plugin with possible TagAPI
     */
    public static void refreshPlayer(Player player, Player forWhom, JavaPlugin plugin) {
        if (isEnabled(plugin)) {
            TagAPI.refreshPlayer(player, forWhom);
        }
    }

    /**
     * If TagAPI is enabled, then refresh player tag for a certain players.
     *
     * @param player    player with tag to refresh
     * @param forWhom   players to update display to
     * @param plugin    plugin with possible TagAPI
     */
    public static void refreshPlayer(Player player, Set<Player> forWhom, JavaPlugin plugin) {
        if (isEnabled(plugin)) {
            TagAPI.refreshPlayer(player, forWhom);
        }
    }

    /**
     * Reloads all the player tags on the server.
     * Useful if the tags have changed in the configuration
     *
     * @param plugin    plugin with possible TagAPI
     */
    public static void reload(JavaPlugin plugin) {
        if (isEnabled(plugin)) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                TagAPI.refreshPlayer(player, new HashSet(player.getWorld().getPlayers()));
            }
        }
    }
}