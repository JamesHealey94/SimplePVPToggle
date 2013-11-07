package com.gmail.jameshealey1994.simplepvptoggle.utils;

import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPToggleCommand;
import java.util.logging.Level;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Utility methods that relate to commands and permissions.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class PermissionUtils {

    /**
     * Returns if there's a chance the sender can execute the command.
     * Some commands could pass this check, but then be denied from within the
     * command's execute method.
     *
     * @param command   the command being executed
     * @param sender    the sender of the command
     * @return          if there's a chance the sender can execute the command
     */
    public static boolean canExecute(SimplePVPToggleCommand command, CommandSender sender) {
        return ((!(sender instanceof Player))
                || ((sender instanceof Player) && (canExecute(command, sender, true))));
    }

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
     * Returns if a sender has a certain permission in a certain world.
     *
     * @param sender            sender of the command
     * @param permission        permission being checked
     * @param world             world the player's permissions are being checked
     *                          in
     * @param allowConsole      true if the command is allowed to be executed by
     *                          console, else false
     * @param plugin            plugin with associated Vault permissions
     * @return                  if a player has permissions for the command in
     *                          the world specified
     */
    public static boolean canExecute(CommandSender sender, Permission permission, World world, boolean allowConsole, JavaPlugin plugin) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (player.getWorld().equals(world)) {
                return player.hasPermission(permission);
            } else {
                final net.milkbowl.vault.permission.Permission vaultPerms = getVaultPerms(plugin);
                if (vaultPerms == null) {
                    sender.sendMessage("Vault not found - Tell your administrator to add Vault!");
                    return false;
                }
                return getVaultPerms(plugin).playerHas(world, player.getName(), permission.getName());
            }
        } else {
            return allowConsole;
        }
    }

    /**
     * Returns Vault permissions for a specified plugin.
     *
     * @param plugin    plugin with associated Vault permissions
     * @return          Vault permissions associated with the plugin passed
     */
    public static net.milkbowl.vault.permission.Permission getVaultPerms(JavaPlugin plugin) {
        final RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> rsp;
        try {
            rsp = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        } catch (NoClassDefFoundError ex) {
            plugin.getLogger().severe("ERROR - Vault not found");
            plugin.getLogger().severe("Commands using Vault will default to false");
            return null;
        }
        return rsp.getProvider();
    }
}