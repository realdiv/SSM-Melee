package us.davidiv.Smash.SSMMelee.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static us.davidiv.Smash.SSMMelee.Game.Knockback.getKnockback;

public class UtilKnockback {

    public static void meleeKnockback(Player p, Entity d) {
        p.setVelocity(d.getLocation().getDirection().multiply((getKnockback(p) / 100) + 1));
    }

    public static void Knockback() {
        //Uses getRelativeDirection
        //For abilities
    }

    public static void posKnockback(Entity base, Entity target, Double kb) {
        target.setVelocity(getDirectionFrom(base, target).multiply(kb * -1));
    }

    private static Vector getDirectionFrom(Player base, Player target){
        Location po = base.getEyeLocation();
        Location to = target.getEyeLocation();

        return po.toVector().subtract(to.toVector());
    }

    private static Vector getDirectionFrom(Entity base, Entity target){
        Vector bo = base.getLocation().getDirection();
        Vector to = target.getLocation().getDirection();

        return bo.subtract(to);
    }

}
