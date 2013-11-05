package com.gmail.jameshealey1994.simplepvptoggle.utils;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Utility methods that interact with a configuration file for PVP values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class PVPConfigUtils {
    
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
    public static void setPlayerStatus(CommandSender sender, Player player, World world, boolean status, SimplePVPToggle plugin) {
        final String path = "Server.Worlds." + world.getName() + ".Players." + player.getName();
        plugin.getConfig().set(path, status);
        plugin.saveConfig();
        final boolean newStatus = PVPConfigUtils.getPlayerStatus(player, world, plugin);
        final Localisation localisation = new Localisation(plugin);

        if (sender.equals(player)) {
            // player set their own PVP status
            final Object[] formatObject = {world.getName(), newStatus};
            player.sendMessage(localisation.get(LocalisationEntry.MSG_YOU_SET_YOUR_PVP_STATUS_IN_WORLDNAME_TO_STATUS, formatObject));
        } else {
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_YOU_SET_THE_PVP_STATUS_OF_PLAYERNAME_IN_WORLDNAME_TO_STATUS, new Object[] {player.getDisplayName(), world.getName(), newStatus}));
            player.sendMessage(localisation.get(LocalisationEntry.MSG_SENDERNAME_SET_YOUR_PVP_STATUS_IN_WORLDNAME_TO_STATUS, new Object[] {sender.getName(), world.getName(), newStatus}));
        }
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
    public static boolean getPlayerStatus(Player player, World world, SimplePVPToggle plugin) {
        return (plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Players." + player.getName(),
                getWorldStatus(world, plugin)));
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
    public static boolean getWorldStatus(World world, SimplePVPToggle plugin) {
        return  plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Default",
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
    public static boolean getServerStatus(SimplePVPToggle plugin) {
        return  plugin.getConfig().getBoolean("Server.Default",
                false);
    }
    
    /**
     * Sets the default PVP status of the passed world to the passed state,
     * saves the config, then sends a message to the sender.
     * 
     * @param sender    sender of the command, to be sent a confirmation message
     * @param world     world to set the default PVP status of
     * @param status    status to change to
     * @param plugin    plugin with the config storing PVP status values
     */
    public static void setWorldStatus(CommandSender sender, World world, boolean status, SimplePVPToggle plugin) {
        final String path = "Server.Worlds." + world.getName() + ".Default";
        plugin.getConfig().set(path, status);
        plugin.saveConfig();
        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_WORLD_DEFAULT_SET_TO, new Object[] {world.getName(), plugin.getConfig().getBoolean(path)}));
    }
}