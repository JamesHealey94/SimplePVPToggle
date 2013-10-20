package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Class representing a SimplePVPToggle reload command.
 * Allows you to reload the config
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ReloadCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public ReloadCommand() {
        super();
        this.aliases.add("reload");
        this.aliases.add("r");
        
        this.permissions.add(new Permission("spt.reload"));
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        /*
         * Command can be used by console, and any players with the correct permission.
         */
        
        if (sender instanceof Player) {
            if (!hasPerms((Player) sender, this.getPermissions())) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return false;
            }
        }
        
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Configuration reloaded.");
        return true;
    }
 }
