package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import us.davidiv.Smash.SSMMelee.Game.DoubleJump;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

/**
 * Created by David on 3/15/2017.
 */
public class HighJump implements Listener {
    public HighJump(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void DoubleJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        Kits kitP = Kit.kit.get(p);
        if (kitP == Kits.SLIME) {
            DoubleJump.velocity(p, p.getLocation().getDirection(), 1.3, true, 0.8, 0.5, 7.0, false);
        }
    }

}
