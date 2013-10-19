package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command executor for the SimplePVPToggle plugin.
 * @author James Healey
 */
public class SimplePVPToggleCommandExecutor implements CommandExecutor {

    /**
     * Plugin the commands are executed for.
     */
    private SimplePVPToggle plugin;

    /**
     * Constructor to set plugin instance variable.
     * @param plugin The plugin used to set internal plugin value
     */
    public SimplePVPToggleCommandExecutor(SimplePVPToggle plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        
        /*
         * TODO: Add commands from plugin.yml
         */
        
        if (cmd.getName().equalsIgnoreCase("spt")) {
            if ((args.length > 0) && (args[0].equalsIgnoreCase("reload"))) {
                return plugin.reload(sender);
            }
	} else if (cmd.getName().equalsIgnoreCase("basic2")) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command can only be run by a player.");
		} else {
			Player player = (Player) sender;
			// do something
		}
		return true;
	}
	return false;
    }
}
