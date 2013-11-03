package com.gmail.jameshealey1994.simplepvptoggle.localisation;

/**
 * Enum to represent a single entry in a Localisation, holds Faction and related deathPoints variable.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public enum LocalisationEntry {
    // TODO Perhaps change to Messages.messagehere and Commands.CommandName.Description
    // TODO Perhaps change to different classes for each section (messages, each command)
    // TODO test and update javadocs for %1$s %2$s %2$s %3$s
    
    /**
     * Message telling player they do not have permission to use a command.
     */
    ERR_PERMISSION_DENIED("MsgPermissionDenied", null, "&cPermission denied"),

    /**
     * Message telling user the command can only be used by players.
     */
    ERR_PLAYER_ONLY_COMMAND("MsgOnlyPlayersCanUseThisCommand", null, "&cOnly players can use this command"),

    /**
     * Message telling user to specify a valid PvP status ('true' or 'false').
     */
    ERR_SPECIFY_PVP_STATUS("MsgSpecifyPVPStatus", null, "&cPlease specify a PVP status ('true' or 'false')"),

    /**
     * Message telling user that the player specified cannot be found.
     * Use %s if / where you would like to enter the invalid player name
     */
    ERR_PLAYER_NOT_FOUND ("MsgPlayerNotFound", "Use %s if / where you would like to enter the invalid player name", "&cPlayer is not online or is invalid: '%s'"),

    /**
     * Message telling user that the world specified cannot be found.
     * Use %s if / where you would like to enter the invalid world name
     */
    ERR_WORLD_NOT_FOUND ("MsgWorldNotFound", "Use %s if / where you would like to enter the invalid world name", "&cWorld not found: '%s'"),

    /**
     * Message telling user that they need to specify a world.
     */
    ERR_SPECIFY_WORLD ("MsgSpecifyWorld", null, "&cPlease specify a world"),

    /**
     * Message telling user that they need to specify a player.
     */
    ERR_SPECIFY_PLAYER ("MsgSpecifyPlayer", null, "&cPlease specify a player"),

    /**
     * Message telling user that they have sent too many arguments
     * for a command.
     */
    ERR_TOO_MANY_ARGUMENTS ("MsgTooManyArguments", null, "&cToo many arguments"),

    /**
     * Message telling player they attacked another player, and how much damage
     * they inflicted on that player.
     * Only shown when in debug mode.
     *
     * Use %s for the attacked player's name, then a second %s for the damage inflicted
     */
    DEBUG_ATTACKED_PLAYERNAME_FOR_DAMAGEAMOUNT_DAMAGE ("DebugAttackedPLAYERNAMEForDAMAGEAMOUNTDamage", "Use %s for the attacked player's name, then a second %s for the damage inflicted", "&7Attacked %s for %s damage"),

    /**
     * Message telling player they have been attacked by another player, and how
     * much damage they have been inflicted by that player.
     * Only shown when in debug mode.
     *
     * Use a %s for the attacker's name, and a second %s for the damage they inflicted
     */
    DEBUG_ATTACKED_BY_PLAYERNAME_FOR_DAMAGEAMOUNT_DAMAGE ("DebugAttackedByPLAYERNAMEForDAMAGEAMOUNTDamage", "Use a %s for the attacker's name, and a second %s for the damage they inflicted", "&7Attacked by %s for %s damage"),

    /**
     * Message telling player that they cannot attack, as they do not have PVP
     * enabled.
     */
    MSG_ATTACK_CANCELLED_YOU_DO_NOT_HAVE_PVP_ENABLED ("MsgAttackCancelledYouDoNotHavePVPEnabled", null, "&7Attack Cancelled - You do not have PVP enabled"),

    /**
     * Message telling player that they cannot attack, as they the player they
     * are attacking does not have PVP enabled.
     */
    MSG_ATTACK_CANCELLED_PLAYERNAME_DOES_NOT_HAVE_PVP_ENABLED ("MsgAttackCancelledPLAYERNAMEDoesNotHavePVPEnabled", "Use %s for the name of the player who does not have PVP enabled", "&7Attack Cancelled - %s does not have PVP enabled"),

    /**
     * Message telling user the default PVP status of the server has been set.
     * Use %s if / where you would like to enter the server's default PVP status
     */
    MSG_SERVER_DEFAULT_SET_TO ("MsgServerDefaultSetTo", "Use %s if / where you would like to enter the server's default PVP status", "&7Server default PVP status set to '%s'"),

    /**
     * Message telling user the default PVP status of a world has been set.
     * Use %s for the world name, and a second for the world's PVP value.
     */
    MSG_WORLD_DEFAULT_SET_TO("MsgWorldDefaultSetTo", "Use %s for the world name, and a second for the world's PVP value.", "&7Default PVP status of '%s' set to '%s'"),

    /**
     * Message telling user their PVP status in a world.
     * Use %s for the world name, and another %s for the PVP value.
     */
    MSG_YOU_SET_YOUR_PVP_STATUS_IN_WORLDNAME_TO_STATUS ("MsgYouSetYourPVPStatusInWORLDNAMEToSTATUS", "Use %s for the world name, and another %s for the PVP value", "&7You set your PVP status in '%s' to '%s'"),

    /**
     * Message telling user they set the PVP status of a player in a world.
     * Use %s for the player name, a second for the world name, and a third for
     * the PVP value.
     */
    MSG_YOU_SET_THE_PVP_STATUS_OF_PLAYERNAME_IN_WORLDNAME_TO_STATUS ("MsgYouSetThePVPStatusOfPLAYERNAMEInWORLDNAMEToSTATUS", "Use %s for the player name, a second for the world name, and a third for the PVP value", "&7You set the PVP status of '%s' in '%s' to '%s'"),

    /**
     * Message telling user that a user set their PVP status in a world.
     * Use %s for the user's name, a second for the world name, and a third for
     * the PVP value.
     */
    MSG_SENDERNAME_SET_YOUR_PVP_STATUS_IN_WORLDNAME_TO_STATUS ("MsgSENDERNAMESetYourPVPStatusInWORLDNAMEToSTATUS", "Use %s for the user's name, a second for the world name, and a third for the PVP value.", "&7%s set your PVP status in '%s' to '%s'"),

    /**
     * Message telling user the configuration has been reloaded.
     */
    MSG_CONFIG_RELOADED ("MsgConfigReloaded", null, "&7Configuration reloaded."),

    /**
     * Message telling user their current PVP status.
     * Use %s for the the world, and a second %s for the PVP value.
     */
    MSG_YOUR_PVP_STATUS_IN_WORLDNAME_IS_STATUS ("MsgYourPVPStatusInWORLDNAMEIsSTATUS", "Use %s for the the world, and a second %s for the PVP value.", "&7Your PVP status in '%s' is '%s'"),

    /**
     * Message telling user the current PVP status of a user.
     * Use %s for the player name, a second %s for the world, and a third %s for
     * the PVP value.
     */
    MSG_CURRENT_PVP_STATUS_OF_PLAYERNAME_IN_WORLDNAME_IS_STATUS ("MsgCurrentPVPStatusOfPLAYERNAMEInWORLDNAMEIsSTATUS", "Use %s for the player name, a second %s for the world, and a third %s for the PVP value", "&7PVP status of '%s' in '%s' is '%s'"),

    /**
     * Description for Help command.
     */
    DESCRIPTION_HELP ("DescHelp", null, "Shows help menu"),

    /**
     * Description for Info command.
     */
    DESCRIPTION_INFO ("DescInfo", null, "Displays default PVP status of server and worlds"),

    /**
     * Description for Reload command.
     */
    DESCRIPTION_RELOAD ("DescReload", null, "Reloads config values"),

    /**
     * Description for Set command.
     */
    DESCRIPTION_SET ("DescSet", null, "Changes PVP status for [username] in [world] to <on / off>"),

    /**
     * Description for Set Server Default command.
     */
    DESCRIPTION_SET_SERVER_DEFAULT ("DescSetServerDefault", null, "Sets default PVP status for server to <on / off>"),

    /**
     * Description for Set World Default command.
     */
    DESCRIPTION_SET_WORLD_DEFAULT ("DescSetWorldDefault", null, "Sets default PVP status for [world] to <on / off>"),

    /**
     * Description for Status command.
     */
    DESCRIPTION_STATUS ("DescStatus", null, "Displays PVP status of [username] in [world]"),

    /**
     * Displayed at the top of the Info command.
     */
    INFO_HEADER ("InfoHeader", null, "&7------ Default PVP Status ------"),

    /**
     * The server's default PVP status.
     * Use %s if / where you would like to enter the server's default PVP status
     */
    INFO_SERVER ("InfoServer", "Use %s if / where you would like to enter the server's default PVP status", "&dServer: %s"),

    /**
     * A world's default PVP status.
     * Use %s for the world name, and a second %s for the world's default PVP
     * status
     */
    INFO_WORLD ("InfoWorld", "Use %s for the world name, and a second %s for the world's default PVP status", "&d%s: %s"),

    /**
     * Displayed at the top of the Help command.
     * Use %s if / where you would like to enter a description of the plugin
     */
    HELP_HEADER ("HelpHeader", "Use %s if / where you would like to enter a description of the plugin", "&7------ %s ------"),

    /**
     * Displayed for each command in the help command.
     * Use %s for the plugin name, and a second %s for the description
     */
    HELP_ENTRY ("HelpSeparator", "Use %s for the plugin name, and a second %s for the description", "%s - %s");

    /**
     * The name of the entry, as found in the localisation file.
     */
    private String name;

    /**
     * The usage for the entry, used as a comment in the localisation file to
     * help the user.
     * An empty string should be ignored when writing to file.
     */
    private String usage;

    /**
     * The default value of the entry, as found in the localisation file.
     */
    private String defaultValue;

    /**
     * Constructor.
     *
     * @param name          name of the entry, as found in the localisation file
     * @param usage         usage for the entry, used as a comment in the
     *                      localisation file to help the user, can be empty
     * @param defaultValue  default value of the entry, as found in the
     *                      localisation file
     */
    LocalisationEntry(String name, String usage, String defaultValue) {
        this.name = name;
        this.usage = usage;
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
     * Returns the usage of the LocalistionEntry.
     *
     * @return  usage of the LocalistionEntry, empty string for no usage.
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Returns the default value of the LocalisationEntry.
     *
     * @return  default value of the LocalisationEntry
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        if (getUsage() == null) {
            return "\n" + getName() + ": '" + getDefaultValue().replace("'", "''") + "'\n";
        } else {
            return "\n# " + getUsage() + "\n" + getName() + ": '" + getDefaultValue().replace("'", "''") + "'\n";
        }
    }
}