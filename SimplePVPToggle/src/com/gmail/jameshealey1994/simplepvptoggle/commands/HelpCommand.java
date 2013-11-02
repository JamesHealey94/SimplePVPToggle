package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a SimplePVPToggle help command.
 * 
 * /pvp [help]      Shows help menu
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class HelpCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public HelpCommand() {
        // TODO Make aliases configurable?
        this.aliases.add("help");
        this.aliases.add("h");
    }
    
    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by anyone.
         */
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "------ " + plugin.getDescription().getFullName() + " ------");
        final Localisation localisation = plugin.getLocalisation();
        for (SimplePVPToggleCommand command : plugin.getCommands()) {
            if ((!(sender instanceof Player)) || (command.hasPerms((Player) sender))) {
                // TODO: What if a command has no aliases? Perhaps a name property is needed.
                sender.sendMessage(command.aliases.get(0) + localisation.get(LocalisationEntry.HELP_SEPARATOR) + command.getDescription(localisation));
            }
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_HELP);
    }
}
