package com.gmail.jameshealey1994.simplepvptoggle.commands.command;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PermissionUtils;
import org.bukkit.command.CommandSender;

/**
 * Class representing a help command.
 *
 * /... [help]      Shows help menu
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class HelpCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public HelpCommand() {
        this.aliases.add("help");
        this.aliases.add("h");
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();
        /*
         * Command can be used by anyone.
         */
        sender.sendMessage(localisation.get(LocalisationEntry.HELP_HEADER,
                new Object[] {plugin.getDescription().getFullName()}));

        for (SimplePVPToggleCommand command : plugin.getCommands()) {
            if (PermissionUtils.canExecute(command, sender, true)) {
                sender.sendMessage(localisation.get(LocalisationEntry.HELP_ENTRY, new Object[] {command.aliases.get(0), command.getDescription(localisation)}));
            }
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_HELP);
    }
}