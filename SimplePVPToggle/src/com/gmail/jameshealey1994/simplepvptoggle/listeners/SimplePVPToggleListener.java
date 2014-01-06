package com.gmail.jameshealey1994.simplepvptoggle.listeners;

import com.gmail.jameshealey1994.simplepvptoggle.SimplePVPToggle;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.Localisation;
import com.gmail.jameshealey1994.simplepvptoggle.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simplepvptoggle.utils.DebugConfigUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.LastPVPActionTimeConfigUtils;
import com.gmail.jameshealey1994.simplepvptoggle.utils.PVPConfigUtils;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Listener class for the SimplePVPToggle plugin.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SimplePVPToggleListener implements Listener {

    /**
     * Plugin with config file used to get PvP value.
     */
    private SimplePVPToggle plugin;

    /**
     * Constructor used to set plugin.
     *
     * @param plugin    plugin used for config to retrieve PvP values from
     */
    public SimplePVPToggleListener(SimplePVPToggle plugin) {
        this.plugin = plugin;
    }

    /**
     * Entity damaged by another entity event.
     * Cancels the event and sends damager a message if either damager or
     * attacked player have not enabled PvP
     *
     * @param event event being handled
     */
    @EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
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

            final World world = attacker.getWorld();
            final Localisation localisation = plugin.getLocalisation();
            if (PVPConfigUtils.getPlayerStatus(attacker, world, plugin)) {
                if (PVPConfigUtils.getPlayerStatus(attackedPlayer, world, plugin)) {
                    LastPVPActionTimeConfigUtils.update(attacker, world, plugin);
                    if (DebugConfigUtils.getDebugEnabled(plugin)) {
                        attacker.sendMessage(localisation.get(LocalisationEntry.DEBUG_ATTACKED_PLAYER, new Object[] {attackedPlayer.getDisplayName(), event.getDamage()}));
                        attackedPlayer.sendMessage(localisation.get(LocalisationEntry.DEBUG_ATTACKED_BY_PLAYER, new Object[] {attacker.getDisplayName(), event.getDamage()}));
                    }
                } else {
                    event.setCancelled(true);
                    attacker.sendMessage(localisation.get(LocalisationEntry.MSG_ATTACK_CANCELLED_PLAYER_DOES_NOT_HAVE_PVP_ENABLED, new Object[] {attackedPlayer.getDisplayName()}));
                }
            } else {
                event.setCancelled(true);
                attacker.sendMessage(localisation.get(LocalisationEntry.MSG_ATTACK_CANCELLED_PVP_NOT_ENABLED));
            }

            // Stop arrows bouncing back, possibly hitting you.
            if ((event.isCancelled()) && (event.getDamager() instanceof Projectile)) {
                ((Projectile) event.getDamager()).setBounce(false);
            }
        }
    }
}