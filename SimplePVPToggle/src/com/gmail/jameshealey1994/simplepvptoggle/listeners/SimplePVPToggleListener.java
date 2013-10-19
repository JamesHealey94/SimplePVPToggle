package com.gmail.jameshealey1994.simplepvptoggle.listeners;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Listener class for the SimplePVPToggle plugin.
 * @author James Healey
 */
public class SimplePVPToggleListener implements Listener {

    /**
     * Plugin with config file used to get PvP value.
     */
    SimplePVPToggle plugin;
    
    /**
     * Constructor used to set plugin.
     * @param plugin Plugin used for config to retrieve PvP values from
     */
    public SimplePVPToggleListener(SimplePVPToggle plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Entity damaged by another entity event.
     * Cancels the event and sends damager a message if either damager or attacked player have not enabled PvP
     * @param event The event being handled
     */
    @EventHandler (priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        // TODO: Check - Does it work with projectiles, e.g. bow and arrow?
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                final Player attacker = (Player) event.getDamager();
                if (canPVP(attacker)) {
                    final Player attackedPlayer = (Player) event.getEntity();
                    if (canPVP(attackedPlayer)) {
                        attacker.sendMessage(ChatColor.GRAY + "Attacked " + attackedPlayer.getDisplayName() + " for " + event.getDamage() + " damage.");
                        attackedPlayer.sendMessage(ChatColor.GRAY + "Attacked by " + attacker.getDisplayName() + " for " + event.getDamage() + " damage.");
                        // PVP is allowed. No changes needed.
                    } else {
                        event.setCancelled(true);
                        attacker.sendMessage(ChatColor.GRAY + "Attack Cancelled - " + attackedPlayer.getDisplayName() + " does not have PvP enabled.");
                    }
                } else {
                    event.setCancelled(true);
                    attacker.sendMessage(ChatColor.GRAY + "Attack Cancelled - You do not have PvP enabled.");
                }
            }
        }
    }
    
    /**
     * Helper method used to check if a player's PvP value is set to true in the config.
     * It first looks for a specific value for that player in that world.
     * If that is not found, it is set to the default value for the world the player is currently in.
     * If that is not found, it is set to the default value for the server.
     * If there is an error with the default value for the server, it is set to false by default.
     * @param player The player being checked
     * @return If the player can PVP in that world
     */
    public boolean canPVP(Player player) {
        // TODO: Test use of correct strings (especially with different spaces and cases).
        return (plugin.getConfig().getBoolean("Server." + player.getWorld().getName() + "." + player.getName(),
                plugin.getConfig().getBoolean("Server." + player.getWorld().getName() + ".Default",
                plugin.getConfig().getBoolean("Server." + player.getWorld().getName(),
                plugin.getConfig().getBoolean("Server.Default", false)))));
    }
}