package com.gmail.jameshealey1994.simplepvptoggle.commands.command;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import java.util.ArrayList;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

/**
 * Abstract class to represent a SimplePVPToggle command.
 * /... <commandnamehere> [args]
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class SimplePVPToggleCommand {

    /**
     * Aliases for the command.
     */
    protected ArrayList<String> aliases = new ArrayList<>();

    /**
     * Permissions for the command.
     */
    protected ArrayList<Permission> permissions = new ArrayList<>();

    /**
     * Executes the command.
     *
     * @param plugin        plugin relating to the command
     * @param sender        sender of the command
     * @param commandLabel  actual command used (not an alias)
     * @param args          arguments given with the command
     * @return              if the sender used the command correctly (if false,
     *                      Bukkit sends a usage message to the sender)
     */
    abstract public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args);

    /**
     * Returns the list of aliases.
     *
     * @return  list of aliases
     */
    final public ArrayList<String> getAliases() {
        return aliases;
    }

    /**
     * Returns the list of permissions.
     * @return  list of permissions
     */
    final public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Returns the description specified in the localisation passed.
     * @param localisation  the localisation containing the description
     *
     * @return the description specified in the localisation passed
     */
    public abstract String getDescription(Localisation localisation);
}