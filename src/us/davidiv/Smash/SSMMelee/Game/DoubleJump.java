package us.davidiv.Smash.SSMMelee.Game;

//Unique double jumps are to be kept within kit files

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class DoubleJump implements Listener {
    public DoubleJump(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public DoubleJump() {
    }

    public void velocity(Player p, double vel, double str, double yAdd, double yMax, boolean groundBoost) {
        if (p.getGameMode() == GameMode.SURVIVAL) {
            p.setAllowFlight(false);
            p.setFlying(false);
            p.setVelocity(p.getLocation().getDirection().multiply(vel));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL) {
            if (!player.getAllowFlight()) {
                if (player.isOnGround()) {
                    player.setAllowFlight(true);
                    player.setFlying(false);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFall(EntityDamageEvent event){
        if(event.getEntity() instanceof Player) {
            if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }

}
