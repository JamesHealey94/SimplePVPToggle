package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Class representing a SimplePVPToggle set default server command.
 * Allows you to set the default PVP status of the server
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SetDefaultServerCommand extends SetDefaultCommand {

    /**
     * Constructor to add aliases and permissions, and set path.
     */
    public SetDefaultServerCommand() {
        // TODO: Some way of using the superclasses aliases, then this classes.
        this.aliases.add("server");
        this.aliases.add("s");
        
        this.permissions.add(new Permission("spt.setdefault.*"));
        this.permissions.add(new Permission("spt.setdefault.server"));
        
        this.path = "Server.Default";
    }
    
    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        
        /*
         * Command can be used by console, and any players with the correct permission.
         */
        if (sender instanceof Player) {
            if (!hasPerms((Player) sender)) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }
        }

        if (args.length > 0) {
            // Use Boolean instead of boolean as it can be null (if player gives something other than 'true' or 'false')
            final Boolean newValue = Boolean.valueOf(args[0]);
            if (newValue == null) {
                sender.sendMessage(ChatColor.RED + args[0] + " needs to be a boolean value ('true' or 'false')");
                return true;
            }
            plugin.getConfig().set(path, newValue.booleanValue());
            plugin.saveConfig(); // TODO: Is this necessary?
            sender.sendMessage(ChatColor.GRAY + "Server default PVP status set to: " + plugin.getConfig().getBoolean("Server.Default"));
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Please specify a PVP status ('true' or 'false')");
            return false;
        }
    }
}
