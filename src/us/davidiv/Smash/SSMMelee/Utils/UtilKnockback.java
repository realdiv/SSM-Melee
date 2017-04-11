package us.davidiv.Smash.SSMMelee.Utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static us.davidiv.Smash.SSMMelee.Game.Knockback.getKnockback;

public class UtilKnockback {

    public static void meleeKnockback(Player p, Entity d) {
        p.setVelocity(d.getLocation().getDirection().multiply((getKnockback(p) / 100) + 1));
    }

    public static void Knockback() {
        //For abilities
    }

}
