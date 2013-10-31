package com.gmail.jameshealey1994.simplepvptoggle.localisation;

import org.bukkit.ChatColor;

/**
 * Enum to represent a single entry in a Localisation, holds Faction and related deathPoints variable.
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public enum LocalisationEntry {
    // TODO Perhaps change to Messages.messagehere and Commands.CommandName.Description
    // TODO Perhaps change to different classes for each section (messages, each command)
    
    /**
     * Message telling player they do not have permission to use a command.
     */
    ERR_PERMISSION_DENIED("MsgPermissionDenied", "&cPermission denied"),
    
    /**
     * Message telling user the command can only be used by players.
     */
    ERR_PLAYER_ONLY_COMMAND("MsgOnlyPlayersCanUseThisCommand", "&cOnly players can use this command"),
    
    /**
     * Message telling user to specify a valid PvP status ('true' or 'false').
     */
    ERR_SPECIFY_PVP_STATUS("MsgSpecifyPVPStatus", "&cPlease specify a PVP status ('true' or 'false')"),
        
    /**
     * Message telling user that the player specified cannot be found.
     * The player is online or is invalid.
     */
    ERR_PLAYER_NOT_FOUND ("MsgPlayerNotFound", "&cPlayer is not online or is invalid: "),
    
    /**
     * Message telling user that the world specified cannot be found.
     */
    ERR_WORLD_NOT_FOUND ("MsgWorldNotFound", "&cWorld not found: "),
    
    /**
     * Message telling user that they need to specify a world.
     */
    ERR_SPECIFY_WORLD ("MsgSpecifyWorld", "&cPlease specify a world"),
    
    /**
     * Message telling user that they need to specify a player.
     */
    ERR_SPECIFY_PLAYER ("MsgSpecifyPlayer", "&cPlease specify a player"),
    
    /**
     * Message telling user the default PVP status of the server has been set.
     * Intended to precede a boolean value.
     * For example: "Server default PVP status set to:"
     */
    MSG_SERVER_DEFAULT_SET_TO ("MsgServerDefaultSetTo", "&7Server default PVP status set to: "),
    
    /**
     * Message telling user the default PVP status of a world has been set.
     * Intended to follow a world name and precede a boolean value.
     * For example: "default PVP status set to:"
     */
    MSG_WORLD_DEFAULT_SET_TO("MsgWorldDefaultSetTo", "&7default PVP status set to: "),

    /**
     * Message telling user the configuration has been reloaded.
     */
    MSG_CONFIG_RELOADED ("MsgConfigReloaded", "&7Configuration reloaded."),
    
    /**
     * Message telling user their current PVP status.
     * Intended to be followed by the status as a boolean.
     */
    MSG_CURRENT_PVP_STATUS ("MsgCurrentPVPStatusIs", "&7Current PVP status is "),
    
    /**
     * Description for Help command.
     */
    DESCRIPTION_HELP ("DescHelp", "Shows help menu"),
    
    /**
     * Description for Info command.
     */
    DESCRIPTION_INFO ("DescInfo", "Displays default PVP status of server and worlds"),
    
    /**
     * Description for Reload command.
     */
    DESCRIPTION_RELOAD ("DescReload", "Reloads config values"),
    
    /**
     * Description for Set command.
     */
    DESCRIPTION_SET ("DescSet", "Changes PVP status for [username] in [world] to <on / off>"),
    
    /**
     * Description for Set Server Default command.
     */
    DESCRIPTION_SET_SERVER_DEFAULT ("DescSetServerDefault", "Sets default PVP status for server to <on / off>"),
    
    /**
     * Description for Set World Default command.
     */
    DESCRIPTION_SET_WORLD_DEFAULT ("DescSetWorldDefault", "Sets default PVP status for [world] to <on / off>"),
    
    /**
     * Description for Status command.
     */
    DESCRIPTION_STATUS ("DescStatus", "Displays PVP status of [username] in [world]"),
    
    /**
     * Used between command name and description in the help command.
     */
    HELP_SEPARATOR ("HelpSeparator", " - ");
    
    /**
     * The name of the entry, as found in the localisation file.
     */
    private String name;
    
    /**
     * The default value of the entry, as found in the localisation file.
     */
    private String defaultValue;

    /**
     * Constructor.
     * 
     * @param name          name of the entry, as found in the localisation file
     * @param defaultValue  default value of the entry, as found in the
     *                      localisation file
     */
    LocalisationEntry(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the name of the LocalistionEntry.
     * 
     * @return  name of the LocalistionEntry
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the default value of the LocalisationEntry.
     * @return  default value of the LocalisationEntry
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return getName() + ": '" + getDefaultValue().replace("'", "''") + "'\n";
    }
}
