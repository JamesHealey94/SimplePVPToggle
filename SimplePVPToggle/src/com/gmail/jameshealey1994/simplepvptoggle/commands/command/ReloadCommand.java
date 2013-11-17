package com.gmail.jameshealey1994.simplepvptoggle.commands.command;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.commands.SimplePVPTogglePermission;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.TagUtils;
import org.bukkit.command.CommandSender;

/**
 * Class representing a SimplePVPToggle reload command.
 * Allows you to reload the config
 *
 * /... reload      Reloads config values
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

        this.permissions.add(SimplePVPTogglePermission.RELOAD.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        plugin.reloadConfig();
        TagUtils.reload(plugin);
        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CONFIG_RELOADED));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_RELOAD);
    }
}