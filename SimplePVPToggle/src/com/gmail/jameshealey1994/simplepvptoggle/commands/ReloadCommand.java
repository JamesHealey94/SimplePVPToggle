package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Class representing a SimplePVPToggle reload command.
 * Allows you to reload the config
 * 
 * /pvp reload      Reloads config values
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ReloadCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public ReloadCommand() {
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
            if (!hasPerms((Player) sender)) {
                sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_PERMISSION_DENIED));
                return true;
            }
        }
        
        plugin.reloadConfig();
        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CONFIG_RELOADED));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_RELOAD);
    }
 }
