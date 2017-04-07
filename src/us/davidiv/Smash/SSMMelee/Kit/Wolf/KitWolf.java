package us.davidiv.Smash.SSMMelee.Kit.Wolf;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitWolf {

    public static void Wolf(Player p) {
        Kit.kitM(p, Kits.WOLF, "TBD", "TBD", Material.IRON_AXE, Material.IRON_SPADE);
        MobDisguise mob = new MobDisguise(DisguiseType.WOLF);
        disguiseEntity(p, mob);
    }

}
