package com.gmail.jameshealey1994.simplepvptoggle.utils;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Helper class to house methods related to interfacing with the config.
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class PVPConfigUtils {

    /**
     * Check if a player's PvP value is set to true in the config.
     * It first looks for a specific value for that player in that world.
     * 
     * If that is not found, it is set to the default value for the world the
     * player is currently in.
     * 
     * If that is not found, it is set to the default value for the server.
     * 
     * If there is an error with the default value for the server, it is set to
     * false by default.
     * 
     * @param player    the player being checked
     * @param world     the world of the player being checked
     * @param plugin    plugin with config which stores PVP data
     * @return          if the player can PVP in that world
     */
    public static boolean getPVPStatus(Player player, World world, SimplePVPToggle plugin) {
        // Debug messages to show the config values
//        player.sendMessage("Server.Worlds." + world.getName() + ".Players." + player.getName() + ": " + this.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Players." + player.getName()));
//        player.sendMessage("Server.Worlds." + world.getName() + ".Default" + ": " + this.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Default"));
//        player.sendMessage("Server.Worlds." + world.getName() + ": " + this.getConfig().getBoolean("Server.Worlds." + world.getName()));
//        player.sendMessage("Server.Default" + ": " + plugin.getConfig().getBoolean("Server.Default"));
        
        return (plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Players." + player.getName(),
                plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Default",
                plugin.getConfig().getBoolean("Server.Worlds." + world.getName(),
                plugin.getConfig().getBoolean("Server.Default", false)))));
    }
    
    /**
     * Sets PVP status of the passed player in the passed world to the passed
     * state, saves the config, then sends a message to the player.
     * 
     * @param sender    sender of the command
     * @param player    player to set the PVP status of
     * @param world     world in which to set the player's PVP status
     * @param status    status to change to
     * @param plugin    plugin with the config storing PVP status values
     */
    public static void setPlayerPVPStatus(CommandSender sender, Player player, World world, boolean status, SimplePVPToggle plugin) {      
        plugin.getConfig().set("Server.Worlds." + world.getName() + ".Players." + player.getName(), status);
        plugin.saveConfig();
        final boolean newStatus = PVPConfigUtils.getPVPStatus(player, world, plugin);
        if (sender.equals(player)) { // player set their own PVP status
            player.sendMessage(ChatColor.GRAY + "You set your current PVP status in '" + world.getName() + "' to '" + newStatus + "'");
        } else {
            sender.sendMessage(ChatColor.GRAY + "You set the current PVP status of '" + player.getDisplayName() + "' in '" + world.getName() + "' to '" + newStatus + "'");
            
            player.sendMessage(ChatColor.GRAY + sender.getName() + " set your current PVP status in '" + world.getName() + "' to '" + newStatus + "'");
        }
    }
}
