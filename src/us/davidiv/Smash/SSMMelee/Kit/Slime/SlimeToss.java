package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
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

import static us.davidiv.Smash.SSMMelee.Game.GameScoreboard.updateSmashScoreboard;
import static us.davidiv.Smash.SSMMelee.Game.Kit.getKit;
import static us.davidiv.Smash.SSMMelee.Game.Knockback.getKnockback;
import static us.davidiv.Smash.SSMMelee.Game.Knockback.playDamageAnimation;
import static us.davidiv.Smash.SSMMelee.Game.Knockback.setKnockback;
import static us.davidiv.Smash.SSMMelee.Kit.Slime.Overheat.getOverheat;

public class SlimeToss implements Listener {
    public SlimeToss(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private HashMap<Player, Boolean>rc = new HashMap<>();
    private HashMap<Player, Integer>charge = new HashMap<>();
    private HashMap<Player, Integer>setcharge = new HashMap<>();

    private HashMap<Entity, Player>tossEntity = new HashMap<>();




    //Initial right-click activation

    @EventHandler
    public void rightclick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack i = e.getPlayer().getItemInHand();

        if (getKit(p) != Kits.SLIME) {return;}

        if (i.getType() != Material.IRON_SWORD) {return;}

        if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){return;}

        rc.put(p, true);
    }

    //Slime Toss charge-up

    @EventHandler
    public void charge(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : rc.keySet()) {

            if (getKit(p) != Kits.SLIME) {continue;}

            if (!p.isBlocking() || p.getExp() < 0.01f) {
                setcharge.put(p, getCharge(p));
                charge.remove(p);
                rc.remove(p);
                continue;
            }

            p.setExp((p.getExp() - .02f));
            addCharge(p, 1);
            p.getWorld().playSound(p.getLocation(), Sound.SLIME_WALK, 0.5f, (float) (0.5 + 1.5 * ((getCharge(p) / 20) / 3d)));

            if (getCharge(p) > 98) {
                setcharge.put(p, getCharge(p));
                charge.remove(p);
                rc.remove(p);
            }
        }
    }

    //Checks charge, tosses slime with correct size

    @EventHandler
    public void slimetoss(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : setcharge.keySet()) {

            if (setcharge.get(p) <= 0) {continue;}

            if (getKit(p) != Kits.SLIME) {continue;}

            int energy = setcharge.get(p);

            if (energy > 80) tossSlime(p, 3, 2.2);
            else if (energy > 40) tossSlime(p, 2, 3.0);
            else if (energy > 1) tossSlime(p, 1, 4.0);

            setcharge.remove(p);

        }
    }

    //Collisions

    @EventHandler
    public void collision(UpdateEvent e) {
        if (e.getType() != UpdateType.TICK) {return;}

        for (Entity entityy : tossEntity.keySet()) {

            Slime slime = (Slime) entityy;

            if (slime.isOnGround()) {slimeRemove(slime); continue;}

            double radius = .9;
            if (slime.getSize() == 2) radius = .9;
            else if (slime.getSize() == 3) radius = .90;

            for (Entity entity : slime.getNearbyEntities(radius, radius, radius)) {

                if (entity instanceof Player) {

                    Player p = (Player) entity;

                    if (p == tossEntity.get(slime)) {continue;}

                    setKnockback((Player) entity, (getKnockback((Player) entity) + (slime.getSize() * 10)));

                    entity.setVelocity(slime.getVelocity().multiply((getKnockback((Player) entity) / 100) * (slime.getSize()/2) + 1));

                    playDamageAnimation(p);
                    updateSmashScoreboard();

                    tossEntity.remove(slime);
                }
            }
        }
    }

    private void thrown(Player p, Entity slime) {
        tossEntity.put(slime, p);
    }

    private void slimeRemove(Entity slime) {
        Bukkit.getScheduler().runTaskLater(SmashMelee.getPlugin(), new Runnable() {

            @Override
            public void run() {
                if (slime.getType() == EntityType.SLIME) {
                    Slime eSlime = (Slime) slime;
                    eSlime.setHealth(0.0);
                }
                if (slime.getType() == EntityType.MAGMA_CUBE) {
                    Slime eSlime = (MagmaCube) slime;
                    eSlime.setHealth(0.0);
                }
                tossEntity.remove(slime);
            }
        }, 80L);
    }

    //Slime entity method

    private void tossSlime(Player p, Integer size, double speed) {
        if (!getOverheat(p)) {
            Slime slime = p.getWorld().spawn(p.getLocation().setDirection(p.getLocation().getDirection()), Slime.class);
            slime.setSize(size);
            slime.setVelocity(p.getLocation().getDirection().multiply(speed));
            int x = (int) speed;
            thrown(p, slime);
        } else {
            MagmaCube cube = p.getWorld().spawn(p.getLocation().setDirection(p.getLocation().getDirection()), MagmaCube.class);
            cube.setSize(size);
            cube.setVelocity(p.getLocation().getDirection().multiply(speed));
            int x = (int) speed;
            thrown(p, cube);
        }
    }

    private void addCharge(Player p, Integer add) {
        charge.put(p, getCharge(p) + add);
    }

    private Integer getCharge(Player p) {
        if (charge.get(p) == null) return 0;
        else return charge.get(p);
    }

}
