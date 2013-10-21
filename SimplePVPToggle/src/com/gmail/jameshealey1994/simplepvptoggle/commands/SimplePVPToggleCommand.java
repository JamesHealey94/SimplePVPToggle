package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import java.util.ArrayList;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Abstract class to represent a SimplePVPToggle command.
 * Perhaps subcommand may be a better term, as the commands will be /spt <commandnamehere> [args]
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class SimplePVPToggleCommand {
    
    /**
     * An ArrayList of aliases for the command.
     */
    protected ArrayList<String> aliases;
    
    /**
     * An ArrayList of permissions for the command.
     */
    protected ArrayList<Permission> permissions;

    /**
     * Constructor to initialise alises and permissions ArrayLists.
     */
    public SimplePVPToggleCommand() {
        this.aliases = new ArrayList<>();
        this.permissions = new ArrayList<>();
    }
    
    /**
     * Executes the command.
     * @param plugin The plugin relating to the command
     * @param sender The sender of the command
     * @param commandLabel The actual command used (not an alias)
     * @param args The arguments given with the command (args[0] will be /spt or an alias, args[1] will be a subcommand alias)
     * @return If the command executed correctly
     */
    abstract public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args);

    /**
     * Returns the list of aliases.
     * @return The list of aliases
     */
    final public ArrayList<String> getAliases() {
        return aliases;
    }

    /**
     * Returns the list of permissions.
     * @return The list of permissions
     */
    final public ArrayList<Permission> getPermissions() {
        return permissions;
    }
    
    /**
     * Returns if a player has a permission from a list of permissions passed.
     * @param player The player to check if they have permissions
     * @param permissions The list of permissions to check from
     * @return If a player has a permission from a list of permissions passed
     */
    protected boolean hasPerms(Player player, ArrayList<Permission> permissions) {
        boolean hasPerms = false;
        for (Permission p : permissions) {
            if (player.hasPermission(p)) {
                hasPerms = true;
            }
        }
        return hasPerms;
    }
}
