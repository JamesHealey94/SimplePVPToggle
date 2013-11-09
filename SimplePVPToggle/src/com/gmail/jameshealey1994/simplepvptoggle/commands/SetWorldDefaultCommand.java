package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.BooleanParser;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PermissionUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.TagUtils;
import java.util.HashSet;
import java.util.List;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Class representing a setworlddefault command.
 * Allows you to set the default PVP status of a world
 *
 * /... setworlddefault [world] <status>    Sets default PVP status for [world]
 *                                          to <status>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SetWorldDefaultCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public SetWorldDefaultCommand() {
        this.aliases.add("setworlddefault");

        this.permissions.add(SimplePVPTogglePermissions.SETWORLDDEFAULT.getPermission());
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        /*
         * Possible:
         * /... setworlddefault [world] <status>
         * /... setworlddefault <status>
         */
        final Localisation localisation = plugin.getLocalisation();
        final String booleanString;
        final World world;

        switch (args.length) {
            case 0: {
                // /... setworlddefault
                return false;
            }
            case 1: {
                // /... setworlddefault <status>    if player, set world default, else error
                // /... setworlddefault error       specify PVP status, or world if sender is not a player
                if (sender instanceof Player) {
                    world = ((Player) sender).getWorld();
                } else {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_WORLD));
                    return false;
                }

                booleanString = args[0];
                break;
            }
            case 2: {
                // /... setworlddefault [world] <status>    set [world] default to <status>
                world = plugin.getServer().getWorld(args[0]);
                if (world == null) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_WORLD_NOT_FOUND, new Object[] {args[0]}));
                    return false;
                }

                booleanString = args[1];
                break;
            }
            default: {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOO_MANY_ARGUMENTS));
                return false;
            }
        }

        final boolean current = PVPConfigUtils.getWorldStatus(world, plugin);
        final Boolean status = BooleanParser.parse(booleanString, current);
        if (status == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }

        final Permission permission = SimplePVPTogglePermissions.SETWORLDDEFAULT.getPermission();
        if (!(PermissionUtils.canExecute(sender, permission, world, true, plugin))) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PERMISSION_DENIED));
            return true;
        }
        PVPConfigUtils.setWorldStatus(sender, world, status, plugin);

        final List<Player> playersInWorld = world.getPlayers();
        for (Player p : playersInWorld) {
                TagUtils.refreshPlayer(p, new HashSet(playersInWorld), plugin);
        }

        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SET_WORLD_DEFAULT);
    }
}