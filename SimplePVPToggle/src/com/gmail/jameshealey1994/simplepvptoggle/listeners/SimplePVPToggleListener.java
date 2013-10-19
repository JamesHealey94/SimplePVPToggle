package com.gmail.jameshealey1994.simplepvptoggle.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 * Listener class for the SimplePVPToggle plugin.
 * @author James Healey
 */
public class SimplePVPToggleListener implements Listener {

    FileConfiguration config;
    
    public SimplePVPToggleListener(FileConfiguration config) {
        this.config = config;
    }
    
    /**
     * Temporary event to be changed later.
     * Probably unused and to be removed. Just here as an example for later.
     * @param event The event being handled
     */
    @EventHandler (priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
            if (event.getEntityType().equals(EntityType.PLAYER)) {
                final Player attackedPlayer = (Player) event.getEntity();
                if (canPVP(attackedPlayer)) {
                    // check the attacked and the damaged can both PVP, if not, cancel.
                }
            }
        }
    }
    
    /**
     * 
     * @param player
     * @return 
     */
    public boolean canPVP(Player player) {      
        // check player
        if (config.getBoolean("ServerDefault", false)) {
            
        }
        
        // player.getWorld() check default value for server from config
        
        // check world the player is in
        
        return false;
    }
}