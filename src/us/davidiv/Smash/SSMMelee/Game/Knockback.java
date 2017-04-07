package us.davidiv.Smash.SSMMelee.Game;

//Knockback multiplier - take a look at the numbers MP uses, it seems to be pretty balanced
//TODO CHECK IF GAMEACTIVE = TRUE

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

public class Knockback implements Listener {
    public Knockback(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player, Integer> knockback = new HashMap<Player, Integer>();

    int kb = 0;
    int dI = 0;

    @EventHandler
    public void Knockback(EntityDamageByEntityEvent e) {
        Boolean gameActive = Game.game.get("Game");
        if (gameActive) {
            Player p = (Player) e.getEntity();
            Entity d = e.getDamager();
            double damage = e.getDamage();
            int dI = (int) damage;
            if (((knockback.get(p)) + dI) >= 999) {
                knockback.put(p, 999);
                kb = knockback.get(p);
            } else {
                knockback.put(p, ((knockback.get(p)) + dI));
                kb = knockback.get(p);
            }
            e.setDamage(0);
            e.setCancelled(true);
            p.setVelocity(d.getLocation().getDirection().multiply((kb / 100) + 1));
        }
    }

    @EventHandler
    public void KBFire(EntityDamageEvent e) {
        Boolean gameActive = Game.game.get("Game");
        if (gameActive) {
            Player p = (Player) e.getEntity();
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                knockback.put(p, ((knockback.get(p)) + 2));
            }
        }
    }

    //TODO RE-ADD HIT EFFECT WITH PACKETS


    @EventHandler
        public void KBReset(PlayerDeathEvent e) {
            Player p = e.getEntity();
            Knockback.knockback.put(p, 0);

    }

}
