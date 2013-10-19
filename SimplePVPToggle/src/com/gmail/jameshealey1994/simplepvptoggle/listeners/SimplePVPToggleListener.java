package com.gmail.jameshealey1994.simplepvptoggle.listeners;

import org.bukkit.configuration.file.FileConfiguration;
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
     * Configuration file used to get PvP value.
     */
    FileConfiguration config;
    
    /**
     * Constructor used to set config.
     * @param config Config used to retrieve PvP values from
     */
    public SimplePVPToggleListener(FileConfiguration config) {
        this.config = config;
    }
    
    /**
     * Entity damaged by another entity event.
     * Cancels the event and sends damager a message if either damager or attacked player have not enabled PvP
     * @param event The event being handled
     */
    @EventHandler (priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                final Player attacker = (Player) event.getDamager();
                if (canPVP(attacker)) {
                    final Player attackedPlayer = (Player) event.getEntity();
                    if (canPVP(attackedPlayer)) {
                        // PVP is allowed. No changes needed.
                    } else {
                        attacker.sendMessage("Attack Cancelled - " + attackedPlayer.getDisplayName() + " does not have PvP enabled.");
                    }
                } else {
                    attacker.sendMessage("Attack Cancelled - You do not have PvP enabled.");
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
        // TODO: use correct strings
        return (config.getBoolean("Server.WorldName.PlayerName", config.getBoolean("ServerDefault.WorldName", config.getBoolean("ServerDefault", false))));
    }
}