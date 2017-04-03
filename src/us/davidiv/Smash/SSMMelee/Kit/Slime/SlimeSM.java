package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

/**
 * Created by David on 4/3/2017.
 */
public class SlimeSM implements Listener {
    public SlimeSM(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void Smash(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();
        //do stuff here
    }

}
