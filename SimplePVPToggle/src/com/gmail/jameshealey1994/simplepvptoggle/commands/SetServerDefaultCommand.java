package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.Helper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

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
        
        //TODO: Check - Player with .* but not .server should be allowed.
        //this.permissions.add(new Permission("spt.setdefault.*")); needed?
        this.permissions.add(new Permission("spt.setdefault.server"));
    }
    
    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        
        /*
         * Command can be used by console and players with the correct permission.
         */
        if (sender instanceof Player) {
            if (!hasPerms((Player) sender)) {
                sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_PERMISSION_DENIED));
                return true;
            }
        }

        if (args.length > 0) {
            /*
             * Using Boolean instead of boolean as it can be null (if player gives something other than 'true' or 'false')
             */
            final Boolean newValue = Helper.parseBoolean(args[0]);
            if (newValue == null) {
                sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_SPECIFY_PVP_STATUS));
                return true;
            }
            
            final String path = "Server.Default";
            plugin.getConfig().set(path, newValue.booleanValue());
            plugin.saveConfig();
            sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_SERVER_DEFAULT_SET_TO) + plugin.getConfig().getBoolean(path));
            return true;
        } else {
            sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_SPECIFY_PVP_STATUS));
            return false;
        }
    }
}
