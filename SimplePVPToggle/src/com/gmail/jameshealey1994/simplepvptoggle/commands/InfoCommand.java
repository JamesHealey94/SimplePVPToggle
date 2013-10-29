package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import org.bukkit.ChatColor;
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
        
        // TODO: Sort out permission structure (class for Permissions?)
    }
    
    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by anyone.
         */
        sender.sendMessage(ChatColor.GRAY + "--- Default PVP Status ---");
        sender.sendMessage(ChatColor.GRAY + "Server: " + plugin.getConfig().getBoolean("Server.Default", false));
        for (World world : plugin.getServer().getWorlds()) {
            sender.sendMessage(ChatColor.GRAY + world.getName() + ": "
                    + plugin.getConfig().getBoolean("Server.Worlds." + world.getName() + ".Default",
                      plugin.getConfig().getBoolean("Server.Worlds." + world.getName(),
                      plugin.getConfig().getBoolean("Server.Default",
                      false))));
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_INFO);
    }
}
