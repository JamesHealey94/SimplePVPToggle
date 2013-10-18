package com.gmail.jameshealey1994.simplepvptoggle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Listener class for the SimplePVPToggle plugin.
 * @author James Healey
 */
public class SimplePVPToggleListener implements Listener {

    /**
     * Temporary onLogin event to be called on player login.
     * Probably unused and to be removed. Just here as an example for later.
     * @param event
     */
    @EventHandler (priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent event) {
        // Your code here...
    }
}
