package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.BooleanParser;
import org.bukkit.command.CommandSender;

/**
 * Class representing a SimplePVPToggle set server default command.
 * Allows you to set the default PVP status of the server
 * 
 * /pvp setserverdefault <on / off>     Sets default PVP status for server to
 *                                      <on / off>
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SetServerDefaultCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public SetServerDefaultCommand() {
        this.aliases.add("setserverdefault");

        this.permissions.add(SimplePVPTogglePermissions.SETSERVERDEFAULT.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by console and players with the correct permission.
         */
        final Localisation localisation = plugin.getLocalisation();

        if (args.length > 0) {
            // Using Boolean instead of boolean as it can be null (if player gives something other than 'true' or 'false')
            final Boolean newValue = BooleanParser.parse(args[0]);
            if (newValue == null) {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
                return true;
            }
            
            final String path = "Server.Default";
            plugin.getConfig().set(path, newValue.booleanValue());
            plugin.saveConfig();
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_SERVER_DEFAULT_SET_TO, new Object[] {plugin.getConfig().getBoolean(path)}));
            return true;
        } else {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SET_SERVER_DEFAULT);
    }
}