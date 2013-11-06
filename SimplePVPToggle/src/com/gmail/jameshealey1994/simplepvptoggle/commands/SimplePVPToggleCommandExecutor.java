package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.CommandUtils;
import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command executor for the SimplePVPToggle plugin.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SimplePVPToggleCommandExecutor implements CommandExecutor {

    /**
     * Plugin the commands are executed for.
     */
    private SimplePVPToggle plugin;

    /**
     * The default command, executed when no arguments are given.
     * Can be null, in which case no command is executed.
     */
    private SimplePVPToggleCommand defaultCommand;

    /**
     * Constructor to set plugin instance variable.
     *
     * @param plugin            plugin used to set internal plugin value
     * @param defaultCommand    default command, executed when no args are given
     */
    public SimplePVPToggleCommandExecutor(SimplePVPToggle plugin, SimplePVPToggleCommand defaultCommand) {
        this.plugin = plugin;
        this.defaultCommand = defaultCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
        if (args.length > 0) {
            for (SimplePVPToggleCommand command : plugin.getCommands()) {
                if (command.getAliases().contains(args[0].toLowerCase())) {
                    if (canExecuteCommand(command, sender)) {
                        return command.execute(plugin, sender, args[0], Arrays.copyOfRange(args, 1, args.length));
                    } else {
                        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.ERR_PERMISSION_DENIED));
                        return true;
                    }
                }
            }
        } else { // No args given
            if (defaultCommand == null) {
                return false;
            } else {
                return defaultCommand.execute(plugin, sender, commandlabel, args);
            }
        }
        return false;
    }

    /**
     * Returns if there's a chance the sender can execute the command.
     * Some commands could pass this check, but then be denied from within the
     * command's execute method.
     *
     * @param command   the command being executed
     * @param sender    the sender of the command
     * @return          if there's a chance the sender can execute the command
     */
    private boolean canExecuteCommand(SimplePVPToggleCommand command, CommandSender sender) {
        return ((!(sender instanceof Player))
                || ((sender instanceof Player) && (CommandUtils.canExecute(command, sender, true))));
    }
}