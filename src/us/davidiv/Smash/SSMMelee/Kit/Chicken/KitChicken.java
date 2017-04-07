package us.davidiv.Smash.SSMMelee.Kit.Chicken;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitChicken {

    public static void Chicken(Player p) {
        Kit.kitM(p, Kits.CHICKEN, "TBD", "Chicken Rocket", Material.IRON_SWORD, Material.IRON_AXE);
        MobDisguise mob = new MobDisguise(DisguiseType.CHICKEN);
        disguiseEntity(p, mob);
    }

}
