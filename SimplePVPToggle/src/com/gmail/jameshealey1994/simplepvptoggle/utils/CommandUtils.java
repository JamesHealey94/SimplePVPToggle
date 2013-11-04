package com.gmail.jameshealey1994.simplepvptoggle.utils;

import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPTogglePermissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Utility methods that relate to commands and permissions.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class CommandUtils {
    /**
     * Returns if a sender is allowed to execute a command.
     * 
     * @param command       the command with permissions being checked
     * @param sender        sender of the command
     * @param allowConsole  true if the command is allowed to be executed by
     *                      console, else false
     * @return              if a player has permissions for the command
     */
    public static boolean canExecute(SimplePVPToggleCommand command, CommandSender sender, boolean allowConsole) {
        if (sender instanceof Player) {
            if (command.getPermissions().isEmpty()) {
                return true;
            }

            final Player player = (Player) sender;
            for (Permission p : command.getPermissions()) {
                if (player.hasPermission(p)) {
                    return true;
                }
            }
            return false;
        } else {
            return allowConsole;
        }
    }

    /**
     * Returns if a sender has a certain permission.
     * 
     * @param sender        sender of the command
     * @param permission    permission being checked
     * @param allowConsole  true if the command is allowed to be executed by
     *                      console, else false
     * @return              if a player has permissions for the command
     */
    public static boolean canExecute(CommandSender sender, SimplePVPTogglePermissions permission, boolean allowConsole) {
        if (sender instanceof Player) {            
            final Player player = (Player) sender;
            return player.hasPermission(permission.getPermission());
        } else {
            return allowConsole;
        }
    }
}