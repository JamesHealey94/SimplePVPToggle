package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.utils.Helper;
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
        this.aliases.add("t");
        this.aliases.add("on");
        this.aliases.add("off");

        // TODO: Sort out permission structure (class for permissions?)
        this.permissions.add(new Permission("spt.status.self"));
        this.permissions.add(new Permission("spt.status.*"));
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        if (args.length < 0) {
            return false;
        }
        
        /*
         * Set up status
         */
        Boolean status = null;
        if (commandLabel.equalsIgnoreCase("toggle")) { // /pvp toggle ...
            if (args[0] == null) { // /pvp toggle
                if (sender instanceof Player) {
                    status = !plugin.canPVP((Player) sender);
                } else {
                    sender.sendMessage(Localisation.ONLY_PLAYERS);
                    return false;
                }
            }
        } else {
            status = Helper.parseBoolean(args[0]); // /pvp on ...     /pvp off ...
        }
        
        if (status == null) {
            return false;
        }
        
        /*
         * Use status to set a players status
         */
        if (args[0] == null) { // /pvp <on / off>
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                setPlayerPVPStatus(player, player.getWorld(), status, plugin);
                return true;
            }
        } else { // /pvp <on / off / toggle> ...
            final Player player = plugin.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' is not online or is invalid");
            } else {
                if (args[1] == null) { // /pvp <on / off / toggle> <username>
                    setPlayerPVPStatus(player, player.getWorld(), status, plugin);
                    return true;
                } else { // /pvp <on / off / toggle> <username> ...
                    final World world = plugin.getServer().getWorld(args[1]);
                    if (world == null) {
                        sender.sendMessage(ChatColor.RED + "World '" + args[1] + "' not found");
                    } else {
                        setPlayerPVPStatus(player, world, status, plugin); // /pvp <on / off / toggle> <username> <world>
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
     * @param player    player to set the PVP status of
     * @param world     world in which to set the player's PVP status
     * @param status    status to change to
     * @param plugin    plugin with the config storing PVP status values
     */
    public void setPlayerPVPStatus(Player player, World world, boolean status, SimplePVPToggle plugin) {
        plugin.getConfig().set("Server.Worlds." + world.getName() + "." + player.getName(), status);
        plugin.saveConfig();
        player.sendMessage(ChatColor.GRAY + "Current PVP status is " + plugin.canPVP(player));
    }
}
