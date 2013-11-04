package com.gmail.jameshealey1994.simplepvptoggle.localisation;

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
     * %1$s - invalid player name
     */
    ERR_PLAYER_NOT_FOUND ("MsgPlayerNotFound", "%1$s - invalid player name", "&cPlayer is not online or is invalid: '%1$s'"),

    /**
     * Message telling user that the world specified cannot be found.
     * %1$s - invalid world name
     */
    ERR_WORLD_NOT_FOUND ("MsgWorldNotFound", "%1$s - invalid world name", "&cWorld not found: '%1$s'"),

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
     * %1$s - attacked player's name
     * %2$s - damage inflicted
     */
    DEBUG_ATTACKED_PLAYERNAME_FOR_DAMAGEAMOUNT_DAMAGE ("DebugAttackedPLAYERNAMEForDAMAGEAMOUNTDamage", "%1$s - attacked player's name\n# %2$s - damage inflicted", "&7Attacked %1$s for %2$s damage"),

    /**
     * Message telling player they have been attacked by another player, and how
     * much damage they have been inflicted by that player.
     * Only shown when in debug mode.
     *
     * %1$s - attacker's name
     * %2$s - damage inflicted
     */
    DEBUG_ATTACKED_BY_PLAYERNAME_FOR_DAMAGEAMOUNT_DAMAGE ("DebugAttackedByPLAYERNAMEForDAMAGEAMOUNTDamage", "%1$s - attacker's name\n# %2$s - damage inflicted", "&7Attacked by %1$s for %2$s damage"),

    /**
     * Message telling player that they cannot attack, as they do not have PVP
     * enabled.
     */
    MSG_ATTACK_CANCELLED_YOU_DO_NOT_HAVE_PVP_ENABLED ("MsgAttackCancelledYouDoNotHavePVPEnabled", null, "&7Attack Cancelled - You do not have PVP enabled"),

    /**
     * Message telling player that they cannot attack, as they the player they
     * are attacking does not have PVP enabled.
     * %1$s - attacked player's name
     */
    MSG_ATTACK_CANCELLED_PLAYERNAME_DOES_NOT_HAVE_PVP_ENABLED ("MsgAttackCancelledPLAYERNAMEDoesNotHavePVPEnabled", "%1$s - attacked player's name", "&7Attack Cancelled - %1$s does not have PVP enabled"),

    /**
     * Message telling user the default PVP status of the server has been set.
     * %1$s - server's default PVP status
     */
    MSG_SERVER_DEFAULT_SET_TO ("MsgServerDefaultSetTo", "%1$s - server's default PVP status", "&7Server default PVP status set to '%1$s'"),

    /**
     * Message telling user the default PVP status of a world has been set.
     * %1$s - world name
     * %2$s - world's default PVP status
     */
    MSG_WORLD_DEFAULT_SET_TO("MsgWorldDefaultSetTo", "%1$s - world name\n# %2$s - world's default PVP status", "&7Default PVP status of '%1$s' set to '%2$s'"),

    /**
     * Message telling player their PVP status in a world.
     * %1$s - world name
     * %2$s - PVP status
     */
    MSG_YOU_SET_YOUR_PVP_STATUS_IN_WORLDNAME_TO_STATUS ("MsgYouSetYourPVPStatusInWORLDNAMEToSTATUS", "%1$s - world name\n# %2$s - PVP status", "&7You set your PVP status in '%1$s' to '%2$s'"),

    /**
     * Message telling user they set the PVP status of a player in a world.
     * %1$s - player name
     * %2$s - world name
     * %3$s - PVP status
     */
    MSG_YOU_SET_THE_PVP_STATUS_OF_PLAYERNAME_IN_WORLDNAME_TO_STATUS ("MsgYouSetThePVPStatusOfPLAYERNAMEInWORLDNAMEToSTATUS", "%1$s - player name\n# %2$s - world name\n# %3$s - PVP status", "&7You set the PVP status of '%1$s' in '%2$s' to '%3$s'"),

    /**
     * Message telling player that a user set their PVP status in a world.
     * %1$s - sender name
     * %2$s - world name
     * %3$s - PVP status
     */
    MSG_SENDERNAME_SET_YOUR_PVP_STATUS_IN_WORLDNAME_TO_STATUS ("MsgSENDERNAMESetYourPVPStatusInWORLDNAMEToSTATUS", "%1$s - sender name\n# %2$s - world name\n# %3$s - PVP status", "&7%1$s set your PVP status in '%2$s' to '%3$s'"),

    /**
     * Message telling user the configuration has been reloaded.
     */
    MSG_CONFIG_RELOADED ("MsgConfigReloaded", null, "&7Configuration reloaded."),

    /**
     * Message telling player their PVP status in a world.
     * %1$s - world name
     * %2$s - PVP status
     */
    MSG_YOUR_PVP_STATUS_IN_WORLDNAME_IS_STATUS ("MsgYourPVPStatusInWORLDNAMEIsSTATUS", "%1$s - world name\n# %2$s - PVP status", "&7Your PVP status in '%1$s' is '%2$s'"),

    /**
     * Message telling user the PVP status of a player in a world.
     * %1$s - player name
     * %2$s - world name
     * %3$s - PVP status
     */
    MSG_CURRENT_PVP_STATUS_OF_PLAYERNAME_IN_WORLDNAME_IS_STATUS ("MsgCurrentPVPStatusOfPLAYERNAMEInWORLDNAMEIsSTATUS", "%1$s - player name\n# %2$s - world name\n# %3$s - PVP status", "&7PVP status of '%1$s' in '%2$s' is '%3$s'"),

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
     * %1$s - server's default PVP status
     */
    INFO_SERVER ("InfoServer", "%1$s - server's default PVP status", "&dServer: %1$s"),

    /**
     * A world and it's default PVP status.
     * %1$s - world name
     * %2$s - world's default PVP status
     */
    INFO_WORLD ("InfoWorld", "%1$s - world name\n# %2$s - world's default PVP status", "&d%1$s: %2$s"),

    /**
     * Displayed at the top of the Help command.
     * %1$s - plugin description
     */
    HELP_HEADER ("HelpHeader", "%1$s - plugin description", "&7------ %1$s ------"),

    /**
     * Displayed for each command in the help command.
     * %1$s - command name
     * %2$s - command description
     */
    HELP_ENTRY ("HelpSeparator", "%1$s - command name\n# %2$s - command description", "%1$s: &7%2$s");

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