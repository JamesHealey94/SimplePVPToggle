package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.BooleanParser;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import java.util.Arrays;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Allows you to change the PVP status of players.
 * /pvp <on / off / toggle> [username] [world]      Changes PVP status for
 *                                                  [username] in [world]
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SetCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public SetCommand() {
        this.aliases.addAll(Arrays.asList(BooleanParser.TOGGLE_VALUES));
        this.aliases.addAll(Arrays.asList(BooleanParser.POSITIVE_VALUES));
        this.aliases.addAll(Arrays.asList(BooleanParser.NEGATIVE_VALUES));

        this.permissions.add(SimplePVPTogglePermissions.CHANGE_SELF.getPermission());
        this.permissions.add(SimplePVPTogglePermissions.CHANGE_OTHERS.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {

        /*
         * Command can be used by console, and any players with the correct permission.
         * Although it depends on the arguments given.
         */
        final Localisation localisation = plugin.getLocalisation();

        final Boolean status;
        final Player target;
        final World world;

        switch (args.length) {
            case 0: {
                // /pvp on/off/toggle - Only used by players (with correct permissions)
                if (!(sender instanceof Player)) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PLAYER));
                    return false;
                }
                target = (Player) sender;
                world = target.getWorld();
                break;
            }
            case 1: {
                // /pvp on/off/toggle ([username] or [world] or error)
                // /pvp on/off/toggle world - change senders current status in specified world, unless sender isnt player
                // /pvp on/off/toggle username - change current status of username in username's current world
                // /pvp on/off/toggle somethingelse - error
                if (plugin.getServer().getPlayer(args[0]) == null) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_FOUND, new Object[] {args[0]}));
                        return false;
                    } else {
                        target = (Player) sender;
                    }
                } else {
                    target = plugin.getServer().getPlayer(args[0]);
                }

                /*
                 * a worldname should be higher priority than a playername (for
                 * example, /pvp on world would set the players PVP to on in
                 * world, even if there was a player called world).
                 */
                if (plugin.getServer().getWorld(args[0]) == null) {
                    world = target.getWorld();
                } else {
                    world = plugin.getServer().getWorld(args[0]);
                }
                break;
            }
            case 2: {
                // /pvp on/off/toggle [username] [world]
                target = plugin.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_FOUND, new Object[] {args[0]}));
                    return false;
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

        status = BooleanParser.parse(commandLabel, PVPConfigUtils.getPlayerStatus(target, world, plugin));

        if (status == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }

        // If the sender is a player, and the target is either someone else or in another world
        if ((sender instanceof Player)
                && ((!(sender.equals(target))) || (!(((Player) sender).getWorld().equals(world))))) {
            final Player player = (Player) sender;
            if (!(player.hasPermission(SimplePVPTogglePermissions.CHANGE_OTHERS.getPermission()))) {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_PERMISSION_DENIED)); // TODO test on setting your own status in another world (whioh you dont have perms for)
                return true;
            }
        }

        PVPConfigUtils.setPlayerStatus(sender, target, world, status, plugin);
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SET);
    }
}