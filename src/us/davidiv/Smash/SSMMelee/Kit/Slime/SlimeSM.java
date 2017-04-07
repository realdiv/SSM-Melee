package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class SlimeSM implements Listener {
    public SlimeSM(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

   /* @EventHandler
    public void Smash(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getHeldItemSlot() == 2) {
            e.setCancelled(true);
            p.sendRawMessage("[" + ChatColor.RED + "Debug" + ChatColor.WHITE + "] smash debug");
        }
        if (p.getInventory().getHeldItemSlot() != 2) {
            return;
        }
    } */

}
