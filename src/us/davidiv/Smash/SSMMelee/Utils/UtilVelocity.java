package us.davidiv.Smash.SSMMelee.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class UtilVelocity {

    public static Vector getDirectionFromBlock(Player base, Location target){
        Location po = base.getEyeLocation();

        return po.toVector().subtract(target.toVector());
    }


}
