package us.davidiv.Smash.SSMMelee.Kit.Spider;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Game.Kit.getKit;

public class Needler implements Listener {
    public Needler(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private HashMap<Player, Integer> needler = new HashMap<Player, Integer>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack i = e.getPlayer().getItemInHand();

        if (getKit(p) != Kits.SPIDER) {return;}

        if (i.getType() != Material.IRON_SWORD) {return;}

        if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){return;}

        needler.put(p, 8);
    }

    @EventHandler
    public void onUpdate(UpdateEvent e) {
        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (!needler.containsKey(p)) {continue;}

            if(!p.isBlocking()){continue;}

            int count = needler.get(p)-1;

            if(count<=0){
                needler.remove(p);
                continue;
            } else {
                needler.put(p, count); }

            Arrow arrow = p.getWorld().spawnArrow(p.getEyeLocation().add(p.getLocation().getDirection()), p.getLocation().getDirection(), 1.2f, 6);
            arrow.setShooter(p);
            p.getWorld().playSound(p.getLocation(), Sound.SPIDER_IDLE, 0.8f, 2f);

        }

    }

}
