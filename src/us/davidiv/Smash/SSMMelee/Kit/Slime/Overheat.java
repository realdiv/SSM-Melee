package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

public class Overheat implements Listener {
    public Overheat(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player, Double> oh = new HashMap<Player, Double>();
    public static HashMap<Player, Boolean> overheat = new HashMap<Player, Boolean>();

    @EventHandler
    public void Overheater(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getDamager();
        Kits kitName = Kit.kit.get(p);
        if (kitName == Kits.SLIME) {
            double dmg = e.getDamage();
            oh.put(p, (oh.get(p) + dmg));
            double damage = oh.get(p);
            if (damage >= 200) {
                overheat.put(p, true);
            }
        }
    }



}
