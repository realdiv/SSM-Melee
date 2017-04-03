package us.davidiv.Smash.SSMMelee.Kit.Slime;

/*
Slime is frozen in air and is pulled back into the direction its looking.
Try to find a good way to make the slime rotate around the place it used the ability,
relative to its rotation, relative to how charged the ability is.
*/

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class SlingSlime implements Listener{
    public SlingSlime(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    int timer = 50;
    int fly = 40;

    @EventHandler
    public void Sling(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Kits kitName = Kit.kit.get(p);
        if (kitName == Kits.SLIME) {
            ItemStack i = e.getPlayer().getItemInHand();
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (i.getType() != null && i.getType() == Material.IRON_AXE) {
                    new BukkitRunnable() {
                        public void run() {
                            Kits kitName = Kit.kit.get(p);
                            if (kitName == Kits.SLIME) {
                                p.setVelocity(p.getLocation().getDirection().multiply(-.1));
                                p.getWorld().playEffect(p.getLocation(), Effect.FLAME, 30, 1);
                                timer--;
                                if (timer <= 0) {
                                    p.setVelocity(p.getLocation().getDirection().multiply(3));
                                    p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 30);
                                    cancel();
                                }
                            } else {
                                cancel();
                            }
                        }
                    }.runTaskTimer(SmashMelee.getPlugin(), 0L, 1L);
                    timer = 50;
                }
            }
        }
    }


}
