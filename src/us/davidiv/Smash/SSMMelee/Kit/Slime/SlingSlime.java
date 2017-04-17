package us.davidiv.Smash.SSMMelee.Kit.Slime;

/*
Slime is frozen in air and is pulled back into the direction its looking.
Try to find a good way to make the slime rotate around the place it used the ability,
relative to its rotation, relative to how charged the ability is.
*/

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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

import static us.davidiv.Smash.SSMMelee.Game.CooldownManager.cooldown;
import static us.davidiv.Smash.SSMMelee.Game.CooldownManager.cooldownEnd;
import static us.davidiv.Smash.SSMMelee.Game.Kit.getKit;
import static us.davidiv.Smash.SSMMelee.Utils.UtilVelocity.getDirectionFromBlock;

public class SlingSlime implements Listener{
    public SlingSlime(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /*int timer = 50;
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
    }*/

    private HashMap<Player, Double>recharge = new HashMap<>();
    private static HashMap<Player, Location>center = new HashMap<>();
    private HashMap<Player, Integer> active = new HashMap<>();
    private double slingcooldown = 6.0;

    @EventHandler
    public void cd(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : recharge.keySet()) {

            if (p.getItemInHand().getType() == Material.IRON_AXE)
                cooldown(p, recharge.get(p), slingcooldown, "Sling-Slime");

            recharge.put(p, (recharge.get(p) - 0.05));
            if (recharge.get(p) > 0) {continue;}

            if (p.getItemInHand().getType() == Material.IRON_AXE)
                cooldownEnd(p, "Sling-Slime");

            recharge.remove(p);


        }

    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack i = e.getPlayer().getItemInHand();

        if (recharge.containsKey(p)) {return;}

        if (getKit(p) != Kits.SLIME) {return;}

        if (i.getType() != Material.IRON_AXE) {return;}

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){return;}

        if (!center.containsKey(p)) {
            center.put(p, p.getLocation());
            p.sendRawMessage(ChatColor.BLUE + "onClick debug: " + center.get(p).toString());
        }

        recharge.put(p, slingcooldown);
        active.put(p, 80);

    }

    @EventHandler
    public void charge(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : active.keySet()) {

            Location loc = center.get(p);
            p.setVelocity(getDirectionFromBlock(p, calculateOffset(p)).multiply(-0.4));
            p.sendRawMessage("Debug: " + center.get(p).toString());

            active.put(p, active.get(p) - 1);

            if (active.get(p) != 0) {continue;}

            active.remove(p);
            center.remove(p);

        }

    }

    @EventHandler
    public void ojnwdpaw(UpdateEvent e) {
        if (e.getType() != UpdateType.ATICK) {return;}

        //Player p = getPlayer("MixtapeDropper");
        //debugYaw(p, p.getLocation());

    }

    //Debug this shit (This actually works as it's supposed to, the center is just broken

    private Location calculateOffset(Player p) {
        double yaw = p.getLocation().getYaw();
        double pitch = p.getLocation().getPitch();
        Location c = center.get(p);
        if (pitch == -90) c = c.subtract(0.0, 1.5, 0.0);
        else if (pitch >= -89 && pitch <= -46) {
            if (yaw <= 0 && yaw >= -45) c = c.subtract(1.5, 0.75, 1.5); //Pos X/Z
            else if (yaw <= -46 && yaw >= -90) c = c.subtract(1.5, 0.75, 0.0); //Pos X
            else if (yaw <= -91 && yaw >= -135) c = c.subtract(1.5, 0.75, -1.5); //Pos X/Neg Z
            else if (yaw <= -136 && yaw > -180) c = c.subtract(0.0, 0.75, -1.5); //Neg Z
            else if (yaw <= -181 && yaw >= -225) c = c.subtract(-1.5, 0.75, -1.5); //Neg X/Z
            else if (yaw <= -226 && yaw >= -270) c = c.subtract(-1.5, 0.75, 0.0); //Neg X
            else if (yaw <= -271 && yaw >= -315) c = c.subtract(-1.5, 0.75, 1.5); //Neg X/Pos Z
            else if (yaw <= -316 && yaw >= -360) c = c.subtract(0.0, 0.75, 1.5); //Pos Z
        }
        else if (pitch >= -45 && pitch <= -0) {
            if (yaw <= 0 && yaw >= -45) c = c.subtract(1.5, 0.25, 1.5); //Pos X/Z
            else if (yaw <= -46 && yaw >= -90) c = c.subtract(1.5, 0.25, 0.0); //Pos X
            else if (yaw <= -91 && yaw >= -135) c = c.subtract(1.5, 0.25, -1.5); //Pos X/Neg Z
            else if (yaw <= -136 && yaw > -180) c = c.subtract(0.0, 0.25, -1.5); //Neg Z
            else if (yaw <= -181 && yaw >= -225) c = c.subtract(-1.5, 0.25, -1.5); //Neg X/Z
            else if (yaw <= -226 && yaw >= -270) c = c.subtract(-1.5, 0.25, 0.0); //Neg X
            else if (yaw <= -271 && yaw >= -315) c = c.subtract(-1.5, 0.25, 1.5); //Neg X/Pos Z
            else if (yaw <= -316 && yaw >= -360) c = c.subtract(0.0, 0.25, 1.5); //Pos Z
        }
        else if (pitch >= 0 && pitch <= 45) {
            if (yaw <= 0 && yaw >= -45) c = c.subtract(1.5, -0.25, 1.5); //Pos X/Z
            else if (yaw <= -46 && yaw >= -90) c = c.subtract(1.5, -0.25, 0.0); //Pos X
            else if (yaw <= -91 && yaw >= -135) c = c.subtract(1.5, -0.25, -1.5); //Pos X/Neg Z
            else if (yaw <= -136 && yaw > -180) c = c.subtract(0.0, -0.25, -1.5); //Neg Z
            else if (yaw <= -181 && yaw >= -225) c = c.subtract(-1.5, -0.25, -1.5); //Neg X/Z
            else if (yaw <= -226 && yaw >= -270) c = c.subtract(-1.5, -0.25, 0.0); //Neg X
            else if (yaw <= -271 && yaw >= -315) c = c.subtract(-1.5, -0.25, 1.5); //Neg X/Pos Z
            else if (yaw <= -316 && yaw >= -360) c = c.subtract(0.0, -0.25, 1.5); //Pos Z
        }
        else if (pitch >= 46 && pitch <= 89) {
            if (yaw <= 0 && yaw >= -45) c = c.subtract(1.5, -0.75, 1.5); //Pos X/Z
            else if (yaw <= -46 && yaw >= -90) c = c.subtract(1.5, -0.75, 0.0); //Pos X
            else if (yaw <= -91 && yaw >= -135) c = c.subtract(1.5, -0.75, -1.5); //Pos X/Neg Z
            else if (yaw <= -136 && yaw > -180) c = c.subtract(0.0, -0.75, -1.5); //Neg Z
            else if (yaw <= -181 && yaw >= -225) c = c.subtract(-1.5, -0.75, -1.5); //Neg X/Z
            else if (yaw <= -226 && yaw >= -270) c = c.subtract(-1.5, -0.75, 0.0); //Neg X
            else if (yaw <= -271 && yaw >= -315) c = c.subtract(-1.5, -0.75, 1.5); //Neg X/Pos Z
            else if (yaw <= -316 && yaw >= -360) c = c.subtract(0.0, -0.75, 1.5); //Pos Z
        }
        else if (pitch == 90) c = c.subtract(0.0, -1.5, 0.0);
        return c;
    }

    public static Location getCenter(Player p) {
        return center.get(p);
    }

}
