package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.BooleanParser;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PermissionUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.TagUtils;
import java.util.Arrays;
import java.util.HashSet;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Allows you to change the PVP status of players.
 *
 * /... <status> [username] [world]         Sets PVP status for [username] in
 *                                          [world] to <status>
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
        final Localisation localisation = plugin.getLocalisation();
        final Player target;
        final World world;

        switch (args.length) {
            case 0: {
                // /... <status>        Only used by players (with correct permissions)
                if (!(sender instanceof Player)) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PLAYER));
                    return false;
                }
                target = (Player) sender;
                world = target.getWorld();
                break;
            }
            case 1: {
                // /... <status> ([username] or [world] or error)
                // /... <status> world              Change senders current status in specified world, unless sender isnt player
                // /... <status> username           Change current status of username in username's current world
                // /... <status> somethingelse      Error
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

        final Boolean status = BooleanParser.parse(commandLabel, PVPConfigUtils.getPlayerStatus(target, world, plugin));
        if (status == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }

        final Permission permission;
        if (sender.equals(target)) {
            permission = SimplePVPTogglePermissions.CHANGE_SELF.getPermission();
        } else {
            permission = SimplePVPTogglePermissions.CHANGE_OTHERS.getPermission();
        }

        if (!(PermissionUtils.canExecute(sender, permission, world, true, plugin))) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PERMISSION_DENIED));
            return true;
        }

        PVPConfigUtils.setPlayerStatus(sender, target, world, status, plugin);
        TagUtils.refreshPlayer(target, new HashSet<>(world.getPlayers()), plugin);
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SET);
    }
}