package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a SimplePVPToggle reload command.
 * Allows you to reload the config
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class HelpCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public HelpCommand() {
        this.aliases.add("help");
        this.aliases.add("h");
        
        // TODO: Sort out permission structure (class for Permissions?)
    }
    
    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by anyone.
         */
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "------ " + plugin.getDescription().getFullName() + " ------");
        
        for (SimplePVPToggleCommand command : plugin.getCommands()) {
            if ((!(sender instanceof Player)) || (command.hasPerms((Player) sender))) {
                // TODO: What if a command has no aliases? Perhaps a name property is needed.
                // TODO: Add a description property to commands?
                sender.sendMessage(command.aliases.get(0) + " - description here"/* + command.getDescription()*/);
            }
        }
        return true;
    }
}
