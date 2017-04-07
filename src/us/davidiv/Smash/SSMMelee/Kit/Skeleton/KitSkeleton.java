package us.davidiv.Smash.SSMMelee.Kit.Skeleton;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitSkeleton {

    public static void Skeleton(Player p) {
        Kit.kitM(p, Kits.SKELETON, "Bow", "TBD", Material.BOW, Material.IRON_AXE);
        MobDisguise mob = new MobDisguise(DisguiseType.SKELETON);
        disguiseEntity(p, mob);
    }

}
