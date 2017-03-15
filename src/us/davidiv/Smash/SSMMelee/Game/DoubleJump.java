package us.davidiv.Smash.SSMMelee.Game;

//Unique double jumps are to be kept within kit files

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class DoubleJump implements Listener {
    public DoubleJump(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public DoubleJump() {
    }

    public void velocity(Player p, double vel, double str, double yAdd, double yMax, boolean groundBoost) {
        /*if (p.getGameMode() == GameMode.SURVIVAL) {
            p.setAllowFlight(false);
            p.setFlying(false);
            p.setVelocity(p.getLocation().getDirection().multiply(vel));
            p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 5, 5);

        }*/

        velocity(p, p.getLocation().getDirection(), str, false, 0, yAdd, yMax, groundBoost);

    }

    public static void velocity(Player p, Vector vec, double str, boolean ySet, double yBase, double yAdd, double yMax, boolean groundBoost) {
        if (Double.isNaN(vec.getX()) || Double.isNaN(vec.getY()) || Double.isNaN(vec.getZ()) || vec.length() == 0)
            return;

        if (p.getGameMode() == GameMode.SURVIVAL) {
            //YSet
            if (ySet)
                vec.setY(yBase);

            //Modify
            vec.normalize();
            vec.multiply(str);

            //YAdd
            vec.setY(vec.getY() + yAdd);

            //Limit
            if (vec.getY() > yMax)
                vec.setY(yMax);

            if (groundBoost)
                if (p.isOnGround())
                    vec.setY(vec.getY() + 0.2);

            //Velocity
            p.setFallDistance(0);

            p.setVelocity(vec);
            p.setAllowFlight(false);
            p.setFlying(false);
            p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 5, 5);
        }
    }

    @EventHandler
    public void jump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        velocity(p, p.getLocation().getDirection(), 2.0, false, 0, 3.0, 3.0, true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL) {
            if (!p.getAllowFlight()) {
                if (p.isOnGround()) {
                    p.setAllowFlight(true);
                    p.setFlying(false);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFall(EntityDamageEvent e){
        if(e.getEntity() instanceof Player) {
            if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }

}
