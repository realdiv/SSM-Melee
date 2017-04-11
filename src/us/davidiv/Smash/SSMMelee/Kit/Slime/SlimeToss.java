package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.Game.Knockback;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Game.GameScoreboard.updateSmashScoreboard;
import static us.davidiv.Smash.SSMMelee.Game.Kit.getKit;

public class SlimeToss implements Listener {
    public SlimeToss(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private double exp = 0.999;
    private int l = 0;

    private HashMap<Player, Boolean>rc = new HashMap<>();
    private HashMap<Player, Integer>charge = new HashMap<>();
    private HashMap<Player, Integer>setcharge = new HashMap<>();

    @EventHandler
    public void rightclick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack i = e.getPlayer().getItemInHand();

        if (getKit(p) != Kits.SLIME) {return;}

        if (i.getType() != Material.IRON_SWORD) {return;}

        if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){return;}

        rc.put(p, true);
    }

    @EventHandler
    public void charge(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : rc.keySet()) {

            if (getKit(p) != Kits.SLIME) {continue;}

            if (!p.isBlocking()) {
                setcharge.put(p, getCharge(p));
                charge.remove(p);
                rc.remove(p);
                return;
            }

            p.setExp((p.getExp() - .02f));
            addCharge(p, 1);
            p.getWorld().playSound(p.getLocation(), Sound.SLIME_WALK, 0.5f, (float) (0.5 + 1.5 * ((getCharge(p) / 20) / 3d)));

            if (getCharge(p) > 98) {
                setcharge.put(p, getCharge(p));
                charge.remove(p);
                rc.remove(p);
                return;
            }
        }
    }

    @EventHandler
    public void slimetoss(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : setcharge.keySet()) {

            if (setcharge.get(p) <= 0) {continue;}

            if (getKit(p) != Kits.SLIME) {continue;}

            int exp = setcharge.get(p);

            if (exp > 80) tossSlime(p, 3, 4.0);
            else if (exp > 40) tossSlime(p, 2, 3.0);
            else if (exp > 1) tossSlime(p, 1, 2.0);

            setcharge.remove(p);

        }
    }

    private void thrown(Player p, Slime slime) {
        new BukkitRunnable() {
            public void run() {
                if (slime.isOnGround()) { cancel(); }
                for (Entity entity : slime.getNearbyEntities(2.0, 2.0, 2.0)) {
                    if (entity instanceof Player) {
                        if (entity != (Entity) p) {
                            Knockback.knockback.put((Player) entity, (Knockback.knockback.get((Player) entity) + 40));
                            entity.setVelocity(slime.getLocation().getDirection().multiply((Knockback.knockback.get((Player) entity) / 100) * 2));
                            updateSmashScoreboard();
                            cancel();
                        }
                    }
                }
            }
        }.runTaskTimer(SmashMelee.getPlugin(), 0L, 1L);
    }

    //TODO MAKE VELOCITY BASED OFF OF SLIME VELOCITY DIRECTION (?)
    //TODO MAKE KNOCKBACK BASED OFF OF SIZE
    //TODO ADD METHOD FOR MAGMA CUBES

    private void tossSlime(Player p, Integer size, Double speed) {
        Slime slime = p.getWorld().spawn(p.getLocation().setDirection(p.getLocation().getDirection()), Slime.class);
        slime.setSize(size);
        slime.setVelocity(p.getLocation().getDirection().multiply(speed));
        thrown(p, slime);
    }

    private void addCharge(Player p, Integer add) {
        charge.put(p, getCharge(p) + add);
    }

    private Integer getCharge(Player p) {
        if (charge.get(p) == null) return 0;
        else return charge.get(p);
    }

}
