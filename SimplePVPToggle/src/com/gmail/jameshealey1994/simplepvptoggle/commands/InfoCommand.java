package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

/**
 * Class representing a SimplePVPToggle info command.
 * Allows you to see the default PVP status of the server and it's worlds
 * 
 * /pvp info        Displays default PVP status of server and worlds
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class InfoCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public InfoCommand() {
        this.aliases.add("info");
        this.aliases.add("i");
        
        this.permissions.add(SimplePVPTogglePermissions.INFO.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by anyone.
         */        
        final Localisation localisation = plugin.getLocalisation();
        sender.sendMessage(localisation.get(LocalisationEntry.INFO_HEADER));
        sender.sendMessage(localisation.get(LocalisationEntry.INFO_SERVER, new Object[] {PVPConfigUtils.getServerStatus(plugin)}));
        for (World world : plugin.getServer().getWorlds()) {
            sender.sendMessage(localisation.get(LocalisationEntry.INFO_WORLD, new Object[] {world.getName(), PVPConfigUtils.getWorldStatus(world, plugin)}));
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_INFO);
    }
}
