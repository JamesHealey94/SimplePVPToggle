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
         * Set up status
         */
        Boolean status = null;
        if (commandLabel.equalsIgnoreCase("toggle")) { // /pvp toggle ...
            if (args.length == 0) { // /pvp toggle
                if (sender instanceof Player) {
                    status = !plugin.canPVP((Player) sender);
                } else {
                    sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_ONLY_PLAYERS_CAN_USE_THIS_COMMAND));
                    return false;
                }
            } else {
                sender.sendMessage("/pvp toggle ... ([username] [world] ?)");
                // /pvp toggle ... ([username] [world] ?)
                // TODO
            }
        } else {
            status = Helper.parseBoolean(commandLabel); // /pvp on ...     /pvp off ...
        }
        
        if (status == null) {
            return false;
        }
        
        /*
         * Use status to set a players status
         */
        if (args.length == 0) { // /pvp <on / off>
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                setPlayerPVPStatus(sender, player, player.getWorld(), status, plugin);
                return true;
            }
        } else { // /pvp <on / off / toggle> ...
            final Player player = plugin.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' is not online or is invalid");
            } else {
                if (args.length == 1) { // /pvp <on / off / toggle> <username>
                    setPlayerPVPStatus(sender, player, player.getWorld(), status, plugin);
                    return true;
                } else { // /pvp <on / off / toggle> <username> ...
                    final World world = plugin.getServer().getWorld(args[1]);
                    if (world == null) {
                        sender.sendMessage(ChatColor.RED + "World '" + args[1] + "' not found");
                    } else {
                        setPlayerPVPStatus(sender, player, world, status, plugin); // /pvp <on / off / toggle> <username> <world>
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
            if (world.equals(player.getWorld())) {
                player.sendMessage(ChatColor.GRAY + "You set your current PVP status to '" + plugin.canPVP(player) + "'");
            } else {
                player.sendMessage(ChatColor.GRAY + "You set your current PVP status to '" + plugin.canPVP(player) + "' in world '" + world.getName() + "'");
            }
        } else {
            if (world.equals(player.getWorld())) {
                sender.sendMessage(ChatColor.GRAY + "You set the current PVP status of '" + player.getDisplayName() + "' to '" + plugin.canPVP(player) + "'");
                player.sendMessage(ChatColor.GRAY + sender.getName() + " set your current PVP status to '" + plugin.canPVP(player) + "'");
            } else {
                sender.sendMessage(ChatColor.GRAY + "You set the current PVP status of '" + player.getDisplayName() + "' to '" + plugin.canPVP(player) + "' in world '" + world.getName() + "'");
                player.sendMessage(ChatColor.GRAY + sender.getName() + " set your current PVP status to '" + plugin.canPVP(player) + "' in world '" + world.getName() + "'");
            }
        }
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SET);
    }
}