package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.BooleanParser;
import com.gmail.jameshealey1994.simplepvptoggle.utils.DebugConfigUtils;
import org.bukkit.command.CommandSender;

/**
 * Class representing a debug command.
 * Allows you to change the debug status
 *
 * /... debug <on / off / toggle>    Changes debug status
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DebugCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public DebugCommand() {
        this.aliases.add("debug");
        this.aliases.add("d");

        this.permissions.add(SimplePVPTogglePermissions.DEBUG.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by console, and any players with the correct permission.
         */
        final Localisation localisation = plugin.getLocalisation();

        if (args.length == 0) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }

        final boolean current = DebugConfigUtils.getDebugEnabled(plugin);
        final Boolean debugStatus = BooleanParser.parse(args[0], current);
        if (debugStatus == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }
        DebugConfigUtils.setDebugEnabled(sender, debugStatus, plugin);
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_DEBUG);
    }
}