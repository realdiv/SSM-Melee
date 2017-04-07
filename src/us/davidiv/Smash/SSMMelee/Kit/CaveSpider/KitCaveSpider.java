package us.davidiv.Smash.SSMMelee.Kit.CaveSpider;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitCaveSpider {

    public static void CaveSpider(Player p) {
        Kit.kitM(p, Kits.CAVESPIDER, "TBD", "Blood Rush", Material.IRON_SWORD, Material.IRON_AXE);
        MobDisguise mob = new MobDisguise(DisguiseType.CAVE_SPIDER);
        disguiseEntity(p, mob);
    }

}
