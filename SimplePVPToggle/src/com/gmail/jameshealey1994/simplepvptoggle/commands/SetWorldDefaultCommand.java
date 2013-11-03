package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.BooleanValuesUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Abstract class representing a SimplePVPToggle set world default command.
 * Allows you to set the default PVP status of a world
 * 
 * /pvp setworlddefault [world] <on / off>      Sets default PVP status for
 *                                              [world] to <on / off>
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
        
        // TODO - Try to fix: 
        // if a player has spt.setdefault.world in another world,
        // then does /spt setworlddefault thatworld true,
        // it won't work currently, but would be nice to
        // -- Vault will fix, but will mean adding a dependency --
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        /*
         * Possible:
         * /pvp setworlddefault [world] <on / off> 
         * /pvp setworlddefault <on / off>
         */
        final Localisation localisation = plugin.getLocalisation();
        final Boolean status;
        final World world;

        switch (args.length) {
            case 0: {
                // /pvp setworlddefault
                return false;
            }
            case 1: {
                // /pvp setworlddefault <on / off>              if player, set world default, else error
                // /pvp setworlddefault error                   specify PVP status
                if (sender instanceof Player) {
                    world = ((Player) sender).getWorld();
                } else {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ONLY_COMMAND));
                    return false;
                }
                
                status = BooleanValuesUtils.parse(args[0]);
                break;
            }
            case 2: {
                // /pvp setworlddefault [world] <on / off>      set world default
                world = plugin.getServer().getWorld(args[0]);
                if (world == null) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_WORLD_NOT_FOUND, new Object[] {args[0]}));
                    return false;
                }
                
                status = BooleanValuesUtils.parse(args[1]);
                break;
            }
            default: {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOO_MANY_ARGUMENTS));
                return false;
            }
        }

        if (status == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PVP_STATUS));
            return false;
        }

        PVPConfigUtils.setWorldStatus(sender, world, status, plugin);
        return true;
        
//        if (args.length > 0) {
//            /*
//             * Using Boolean instead of boolean as it can be null (if player gives something other than 'true' or 'false')
//             */
//            Boolean newState = BooleanValuesUtils.parse(args[0]);
//            if (newState == null) {
//                final World world = plugin.getServer().getWorld(args[0]);
//                if (world == null) {
//                    return false;
//                } else {
//                    /*
//                     * Possible:
//                     * /pvp setworlddefault [world] <on / off> 
//                     */
//                    if (args.length > 1) {
//                        newState = BooleanValuesUtils.parse(args[1]);
//                        if (newState == null) {
//                            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PVP_STATUS));
//                        } else {
//                            // /pvp setworlddefault [world] <on / off> 
//                            if (sender instanceof Player) {
//                                final Player player = (Player) sender;
//                                // TODO check player has perm "spt.setdefault.world" in world specified instead of world where they sent command
//                                if (!player.hasPermission(SimplePVPTogglePermissions.SETWORLDDEFAULT.getPermission())) {
//                                    player.sendMessage(localisation.get(LocalisationEntry.ERR_PERMISSION_DENIED));
//                                    return true;
//                                }
//                            }
//                            
//                            PVPConfigUtils.setWorldStatus(sender, world, newState, plugin);
//                            return true;
//                        }
//                    } else {
//                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PVP_STATUS));
//                    }
//                }
//            } else {
//                // /pvp setworlddefault <on / off>
//                final Player player = (Player) sender;
//                
//                if (player == null) {
//                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ONLY_COMMAND));
//                    return true;
//                }
//                
//                // TODO check player has perm "spt.setdefault.world" in world specified instead of world where they sent command
//                if (!player.hasPermission(SimplePVPTogglePermissions.SETWORLDDEFAULT.getPermission())) {
//                    player.sendMessage(localisation.get(LocalisationEntry.ERR_PERMISSION_DENIED));
//                    return true;
//                }
//                
//                PVPConfigUtils.setWorldStatus(player, player.getWorld(), newState, plugin);
//                return true;
//            }
//        } else {
//            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PVP_STATUS));
//        }
//        return false;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SET_WORLD_DEFAULT);
    }
}