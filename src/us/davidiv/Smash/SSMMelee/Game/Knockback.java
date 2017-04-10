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

import static us.davidiv.Smash.SSMMelee.Game.GameStart.getGame;

public class Knockback implements Listener {
    public Knockback(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player, Integer> knockback = new HashMap<Player, Integer>();

    @EventHandler
    public void Knockback(EntityDamageByEntityEvent e) {

        if (!getGame()) {return;}

        Player p = (Player) e.getEntity();
        Entity d = e.getDamager();

        double damage = e.getDamage();
        int dI = (int) damage;

        if ((getKnockback(p) + dI) >= 999) { setKnockback(p, 999);}
        else {addKnockback(p, dI);}

        int kb = getKnockback(p);

        e.setDamage(0);
        e.setCancelled(true);

        p.setVelocity(d.getLocation().getDirection().multiply((kb / 100) + 1));
    }

    @EventHandler
    public void KBFire(EntityDamageEvent e) {
        if (!getGame()) {return;}
            //do things
    }

    //TODO RE-ADD HIT EFFECT WITH PACKETS


    @EventHandler
        public void KBReset(PlayerDeathEvent e) {
            Player p = e.getEntity();
            setKnockback(p, 0);

    }

    public static Integer getKnockback(Player p) {
        return knockback.get(p);
    }

    public static void setKnockback(Player p, Integer multiplier) {
        knockback.put(p, multiplier);
    }

    public static void addKnockback(Player p, Integer add) {
        knockback.put(p, getKnockback(p) + add);
    }

}
