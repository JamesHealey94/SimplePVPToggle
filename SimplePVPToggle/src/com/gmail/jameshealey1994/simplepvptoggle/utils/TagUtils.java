package com.gmail.jameshealey1994.simplepvptoggle.utils;

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
            
            // Debug - TODO remove
//            plugin.getLogger().log(Level.INFO, "refreshPlayer({0})", player.getDisplayName());
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
            
            // Debug - TODO remove
//            plugin.getLogger().log(Level.INFO, "refreshPlayer({0}, {1})", new Object[]{player.getDisplayName(), forWhom.getDisplayName()});
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
            
            // Debug - TODO remove
//            plugin.getLogger().info("--------------------------");
//            for (Player p : forWhom) {
//                plugin.getLogger().log(Level.INFO, "refreshPlayer({0}, {1})", new Object[]{player.getDisplayName(), p.getDisplayName()});
//            }
//            plugin.getLogger().info("--------------------------");
        }
    }
}