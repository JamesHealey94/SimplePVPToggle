package com.gmail.jameshealey1994.simplepvptoggle.commands.command;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPTogglePermission;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.BooleanParser;
import com.gmail.jameshealey1994.simplepvptoggle.utils.TagUtils;
import java.util.HashSet;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a setserverdefault command.
 * Allows you to set the default PVP status of the server
 *
 * /... setserverdefault <status>       Sets default PVP status for server to
 *                                      <status>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SetServerDefaultCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public SetServerDefaultCommand() {
        this.aliases.add("setserverdefault");

        this.permissions.add(SimplePVPTogglePermission.SETSERVERDEFAULT.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        if (args.length > 0) {
            final String path = "Server.Default";
            final boolean current = plugin.getConfig().getBoolean(path);

            final Boolean newValue = BooleanParser.parse(args[0], current);
            if (newValue == null) {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
                return false;
            }

            plugin.getConfig().set(path, newValue.booleanValue());
            plugin.saveConfig();
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_SERVER_PVP_SET, new Object[] {plugin.getConfig().getBoolean(path)}));
            
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                TagUtils.refreshPlayer(p, new HashSet<>(p.getWorld().getPlayers()), plugin);
            }

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