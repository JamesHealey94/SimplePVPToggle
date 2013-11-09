package com.gmail.jameshealey1994.simplepvptoggle.listeners;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.utils.ColorUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

/**
 * Listener class for TagAPI.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TagListener implements Listener {

    /**
     * Plugin with config file used to get PvP value.
     */
    SimplePVPToggle plugin;

    /**
     * Constructor used to set plugin.
     *
     * @param plugin    plugin used for config to retrieve PvP values from
     */
    public TagListener(SimplePVPToggle plugin) {
        this.plugin = plugin;
    }

    /**
     * Sets name tag for players.
     * If a player has PVP enabled, their name is prefixed by the value
     * specified in the config
     *
     * @param event event being handled
     */
    @EventHandler (priority = EventPriority.HIGH)
    public void onNameTag(PlayerReceiveNameTagEvent event) {
        final Player playerSeen = event.getNamedPlayer();
        final String path = "Prefix";
        if (PVPConfigUtils.getPlayerStatus(playerSeen, playerSeen.getWorld(), plugin)) {
            event.setTag(ColorUtils.addColor(plugin.getConfig().getString(path, "")) + playerSeen.getDisplayName());
        }
    }
}