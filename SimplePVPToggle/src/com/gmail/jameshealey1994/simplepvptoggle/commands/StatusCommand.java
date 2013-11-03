package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.CommandUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Allows you to see your current PVP status for your current world.
 * /pvp status [username] [world]   Displays PVP status of [username] in [world]
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class StatusCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public StatusCommand() {
        this.aliases.add("status");
        this.aliases.add("s");

        this.permissions.add(SimplePVPTogglePermissions.STATUS_SELF.getPermission());
        this.permissions.add(SimplePVPTogglePermissions.STATUS_OTHERS.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by console, and any players with the correct permission.
         * Although it depends on the arguments given.
         */
        final Localisation localisation = plugin.getLocalisation();
//
//        if (!(CommandUtils.canExecute(this, sender, true))) {
//            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PERMISSION_DENIED));
//            return true;
//        }

        final Player target;
        final World world;

        switch (args.length) {
            case 0: {
                // /pvp status
                if (!(sender instanceof Player)) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PLAYER));
                    return false;
                }
                target = (Player) sender;
                world = target.getWorld();
                break;
            }
            case 1: {
                // /pvp status ([username] or [world] or error)
                // /pvp status username - current status of username in username's current world
                // /pvp status world - senders current status in specified world, unless sender isnt player
                // /pvp status somethingelse - error
                if (plugin.getServer().getPlayer(args[0]) == null) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_FOUND, new Object[] {args[0]}));
                        return false;
                    } else {
                        target = (Player) sender;
                        world = plugin.getServer().getWorld(args[0]);
                        if (world == null) {
                            sender.sendMessage(localisation.get(LocalisationEntry.ERR_WORLD_NOT_FOUND, new Object[] {args[0]}));
                            return false;
                        }
                        break;
                    }
                } else {
                    target = plugin.getServer().getPlayer(args[0]);
                }
                world = target.getWorld();
                break;
            }
            case 2: {
                // /pvp status [username] [world]
                target = plugin.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_FOUND, new Object[] {args[0]}));
                    return false;
                } else {
                    if (!(CommandUtils.canExecute(sender, SimplePVPTogglePermissions.STATUS_OTHERS, true))) {
                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_PERMISSION_DENIED));
                        return true;
                    }
                }

                world = plugin.getServer().getWorld(args[1]);
                if (world == null) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_WORLD_NOT_FOUND, new Object[] {args[1]}));
                    return false;
                }
                break;
            }
            default: {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOO_MANY_ARGUMENTS));
                return false;
            }
        }

        sendStatus(sender, target, world, plugin);
        return true;
    }

    /**
     * Sends the sender a message containing the current PVP status of the
     * specified player in the specified world.
     *
     * @param sender    sender of the command, to be told the message
     * @param player    player to have PVP status checked
     * @param world     the world to check the players PVP status in
     * @param plugin    plugin used for configuration and localisation
     */
    private void sendStatus(CommandSender sender, Player player, World world, SimplePVPToggle plugin) {
        final boolean status = PVPConfigUtils.getPlayerStatus(player, world, plugin);
        final Localisation localisation = plugin.getLocalisation();

        if (sender.equals(player)) {
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_YOUR_PVP_STATUS_IN_WORLDNAME_IS_STATUS, new Object[] {world.getName(), status}));
        } else {
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_CURRENT_PVP_STATUS_OF_PLAYERNAME_IN_WORLDNAME_IS_STATUS, new Object[] {player.getDisplayName(), world.getName(), status}));
        }
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_STATUS);
    }
}