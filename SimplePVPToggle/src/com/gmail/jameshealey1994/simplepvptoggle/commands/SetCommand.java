package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.Helper;
import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Allows you to change the PVP status of players.
 * /pvp <on / off> [username] [world]       Changes PVP status for [username] in
 *                                          [world] to <on / off>
 * 
 * /pvp toggle [username] [world]           Toggles PVP status for [username] in
 *                                          [world]
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SetCommand extends SimplePVPToggleCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public SetCommand() {
        this.aliases.add("toggle");
        this.aliases.addAll(Arrays.asList(Helper.POSITIVE_VALUES));
        this.aliases.addAll(Arrays.asList(Helper.NEGATIVE_VALUES));
        
        // TODO: Sort out permission structure (class for permissions?)
        this.permissions.add(new Permission("spt.status.self"));
        this.permissions.add(new Permission("spt.status.*"));
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        
        /*
         * Command can be used by console, and any players with the correct permission.
         * Although it depends on the arguments given.
         */
        final Localisation localisation = plugin.getLocalisation();
        final Boolean status;
        if (commandLabel.equalsIgnoreCase("toggle")) {
            // /pvp toggle ...
            if (args.length == 0) {
                // /pvp toggle
                if (sender instanceof Player) {
                    status = !plugin.canPVP((Player) sender);
                } else {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PLAYER));
                    return false;
                }
            } else {
                // /pvp toggle <username> ...
                final Player target = plugin.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_FOUND) + args[0]);
                    return false;
                }
                status = !plugin.canPVP(target);
                
                if (args.length > 1) {
                    // /pvp toggle <username> <world>
                    final World world = plugin.getServer().getWorld(args[1]);
                    if (world != null) {
                        setPlayerPVPStatus(sender, target, world, status, plugin);
                        return true;
                    } else {
                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_FOUND) + args[0]);
                        return false;
                    }
                } else {
                    // /pvp toggle <username>
                    setPlayerPVPStatus(sender, target, target.getWorld(), status, plugin);
                    return true;
                }
            }
        } else {
             // /pvp on ...     /pvp off ...
            status = Helper.parseBoolean(commandLabel);
        }
        
        if (status == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PVP_STATUS));
            return false;
        }
        
        if (args.length == 0) {
            // /pvp <on / off>
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                setPlayerPVPStatus(sender, player, player.getWorld(), status, plugin);
                return true;
            } else {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PLAYER));
                return false;
            }
        } else {
            // /pvp <on / off / toggle> ...
            final Player player = plugin.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_FOUND) + args[0]);
            } else {
                if (args.length == 1) {
                    // /pvp <on / off / toggle> <username>
                    setPlayerPVPStatus(sender, player, player.getWorld(), status, plugin);
                    return true;
                } else {
                    // /pvp <on / off / toggle> <username> ...
                    final World world = plugin.getServer().getWorld(args[1]);
                    if (world == null) {
                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_WORLD_NOT_FOUND) + args[1]);
                    } else {
                        // /pvp <on / off / toggle> <username> <world>
                        setPlayerPVPStatus(sender, player, world, status, plugin);
                        return true;
                    }
                }    
            }
        }
        return false;
    }
    
    /**
     * Sets PVP status of the passed player in the passed world to the passed
     * state, saves the config, then sends a message to the player.
     * 
     * @param sender    sender of the command
     * @param player    player to set the PVP status of
     * @param world     world in which to set the player's PVP status
     * @param status    status to change to
     * @param plugin    plugin with the config storing PVP status values
     */
    public void setPlayerPVPStatus(CommandSender sender, Player player, World world, boolean status, SimplePVPToggle plugin) {      
        plugin.getConfig().set("Server.Worlds." + world.getName() + ".Players." + player.getName(), status);
        plugin.saveConfig();
        if (sender.equals(player)) { // player set their own PVP status
            player.sendMessage(ChatColor.GRAY + "You set your current PVP status to '" + plugin.canPVP(player) + "' in world '" + world.getName() + "'");
        } else {
            sender.sendMessage(ChatColor.GRAY + "You set the current PVP status of '" + player.getDisplayName() + "' to '" + plugin.canPVP(player) + "' in world '" + world.getName() + "'");
            player.sendMessage(ChatColor.GRAY + sender.getName() + " set your current PVP status to '" + plugin.canPVP(player) + "' in world '" + world.getName() + "'");
        }
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SET);
    }
}