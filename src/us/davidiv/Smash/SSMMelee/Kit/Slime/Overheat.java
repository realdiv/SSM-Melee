package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Game.Kit.getKit;

public class Overheat implements Listener {
    public Overheat(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player, Double> oh = new HashMap<Player, Double>();
    public static HashMap<Player, Boolean> overheat = new HashMap<Player, Boolean>();

    @EventHandler
    public void Overheater(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player)) {return;}

        Player p = (Player) e.getDamager();
        if (getKit(p) != Kits.SLIME) {return;}

        double dmg = e.getDamage();
        oh.put(p, (oh.get(p) + dmg));

        double damage = oh.get(p);

        if (damage < 200) {return;}

        overheat.put(p, true);
        p.playSound(p.getLocation(), Sound.LAVA_POP, 2, 2);

    }

    public static Boolean getOverheat(Player p) {
        return overheat.get(p);
    }


}
