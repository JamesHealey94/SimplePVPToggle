package com.gmail.jameshealey1994.simplepvptoggle.commands;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Allows you to see your current PVP status for your current world.
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

        // TODO: Sort out permission structure (class for permissions?)
        this.permissions.add(new Permission("spt.status.self"));
    }

    @Override
    public boolean execute(SimplePVPToggle plugin, CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players may use that command.");
            return true;
        }

        final Player player = (Player) sender;
        if (!hasPerms(player)) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        final boolean status = plugin.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName() + ".Players." + player.getName(),
                plugin.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName() + ".Default",
                plugin.getConfig().getBoolean("Server.Worlds." + player.getWorld().getName(),
                plugin.getConfig().getBoolean("Server.Default"))));

        player.sendMessage(ChatColor.GRAY + "Current PVP status is " + status);
        return true;
    }
}