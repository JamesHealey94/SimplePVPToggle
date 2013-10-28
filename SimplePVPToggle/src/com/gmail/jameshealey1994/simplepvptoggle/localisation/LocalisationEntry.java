package com.gmail.jameshealey1994.simplepvptoggle.localisation;

/**
 * Enum to represent a single entry in a Localisation, holds Faction and related deathPoints variable.
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public enum LocalisationEntry {
    /**
     * Message telling player they do not have permission to use a command.
     */
    MSG_PERMISSION_DENIED("MsgPermissionDenied", "&cPermission denied"),
    
    /**
     * Message telling user the command can only be used by players.
     */
    MSG_ONLY_PLAYERS_CAN_USE_THIS_COMMAND("MsgOnlyPlayersCanUseThisCommand", "&cOnly players can use this command"),
    
    /**
     * Message telling user to specify a valid PvP status ('true' or 'false').
     */
    MSG_SPECIFY_PVP_STATUS("MsgSpecifyPVPStatus", "&cPlease specify a PVP status ('true' or 'false')"),
    /**
     * Message telling user the default PVP status of the server has been set.
     * Intended to precede a boolean value.
     * For example: "Server default PVP status set to:"
     */
    MSG_SERVER_DEFAULT_SET_TO ("MsgServerDefaultSetTo", "&7Server default PVP status set to:"),
    
    /**
     * Message telling user the default PVP status of a world has been set.
     * Intended to follow a world name and precede a boolean value.
     * For example: " default PVP status set to:"
     */
    MSG_WORLD_DEFAULT_SET_TO("MsgWorldDefaultSetTo", "&7 default PVP status set to:"),

    /**
     * Message telling user the configuration has been reloaded.
     */
    MSG_CONFIG_RELOADED ("MsgConfigReloaded", "&7Configuration reloaded.");
    
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
