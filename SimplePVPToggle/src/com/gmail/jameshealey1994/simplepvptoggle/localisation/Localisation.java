package com.gmail.jameshealey1994.simplepvptoggle.localisation;

import org.bukkit.ChatColor;

/**
 * Class to help with localisation and custom messages.
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class Localisation {
    // TODO: Find a good way of dealing with variables inside the strings.
    // For example: ChatColor.RED + "World '" + args[1] + "' not found"
    
    // TODO: Change to get data from file
    
    // TODO: Maybe - Ability to change file at runtime (Localisation variable in Plugin)
    
    /**
     * Message telling player they do not have permission to use a command.
     */
    public static final String NO_PERMS = ChatColor.RED + "You do not have permission to use this command.";

    /**
     * Message telling user to specify a valid PvP status ('true' or 'false').
     */
    public static final String SPECIFY_PVP_STATUS = ChatColor.RED + "Please specify a PVP status ('true' or 'false')";
    
    /**
     * Message telling user the default PVP status of the server has been set.
     * Intended to precede a boolean value.
     * For example: "Server default PVP status set to:"
     */
    public static final String SERVER_DEFAULT_SET_TO = ChatColor.GRAY + "Server default PVP status set to:";
    
    /**
     * Message telling user the default PVP status of a world has been set.
     * Intended to follow a world name and precede a boolean value.
     * For example: " default PVP status set to:"
     */
    public static final String WORLD_DEFAULT_SET_TO = ChatColor.GRAY + " default PVP status set to:";    
    
    /**
     * Message telling user the configuration has been reloaded.
     */
    public static final String CONFIG_RELOADED = ChatColor.LIGHT_PURPLE + "Configuration reloaded.";
    
    /**
     * Message telling user the command can only be used by players.
     */
    public static final String ONLY_PLAYERS = ChatColor.RED + "Only players can use this command.";
}
