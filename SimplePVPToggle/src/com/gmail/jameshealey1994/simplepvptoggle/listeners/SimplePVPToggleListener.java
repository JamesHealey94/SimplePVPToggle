package com.gmail.jameshealey1994.simplepvptoggle.listeners;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Listener class for the SimplePVPToggle plugin.
 * @author JamesHealey94 <jameshealey1994.gmail.com>
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
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        
        /*
         * Currently works with melee, arrows and potions, but not from dispensers.
         * Should it work with dispensers? Perhaps some config values for dispensers?.
         */
        
        if (event.getEntity() instanceof Player) {
            final Player attackedPlayer = (Player) event.getEntity();
            final Player attacker;
            
            if (event.getDamager() instanceof Player) {
                attacker = (Player) event.getDamager();
            } else if (event.getDamager() instanceof Projectile) {
                if (((Projectile) event.getDamager()).getShooter() instanceof Player) {
                    attacker = (Player) ((Projectile) event.getDamager()).getShooter();
                } else {
                    return;
                }
            } else {
                return;
            }

            if (plugin.canPVP(attacker)) {
                if (plugin.canPVP(attackedPlayer)) {
                    // Debug messages to show the config values
                    // TODO: Remove
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
            
            // Stop arrows bouncing back, possibly hitting you.
            if ((event.isCancelled()) && (event.getDamager() instanceof Projectile)) {
                ((Projectile) event.getDamager()).setBounce(false);
            }
        }
    }
}