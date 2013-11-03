package com.gmail.jameshealey1994.simplepvptoggle.commands;

import org.bukkit.permissions.Permission;

/**
 * Enum defining the permissions for SimplePVPToggleCommands.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public enum SimplePVPTogglePermissions {

    /**
     * Permission for the change command, specifically for changing your own
     * status.
     */
    CHANGE_SELF ("spt.change.self"),

    /**
     * Permission for the change command, specifically for changing the status
     * of another player.
     */
    CHANGE_OTHERS ("spt.change.others"),

    /**
     * Permission for SetWorldDefaultCommand.
     */
    SETWORLDDEFAULT ("spt.setworlddefault"),

    /**
     * Permission for SetServerDefaultCommand.
     */
    SETSERVERDEFAULT ("spt.setserverdefault"),

    /**
     * Permission for StatusCommand, specifically for displaying your own PVP
     * status.
     */
    STATUS_SELF ("spt.status.self"),

    /**
     * Permission for StatusCommand, specifically for displaying another's PVP
     * status.
     */
    STATUS_OTHERS ("spt.status.others"),

    /**
     * Permission for InfoCommand.
     */
    INFO ("spt.info"),

    /**
     * Permission for ReloadCommand.
     */
    RELOAD ("spt.reload");

    /**
     * The name of the permission.
     */
    private String name;

    /**
     * Constructor.
     *
     * @param name  the name of the permission
     */
    SimplePVPTogglePermissions(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the Permission.
     *
     * @return  name of the Permission
     */
    public String getName() {
        return name;
    }

    /**
     * Returns Permission of the enum.
     *
     * @return  Permission of the enum
     */
    public Permission getPermission() {
        return new Permission(this.getName());
    }
}