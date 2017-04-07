package us.davidiv.Smash.SSMMelee.Kit.Squid;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitSquid {

    public static void Squid(Player p) {
        Kit.kitM(p, Kits.SQUID, "TBD", "TBD", Material.IRON_SWORD, Material.IRON_AXE);
        MobDisguise mob = new MobDisguise(DisguiseType.SQUID);
        disguiseEntity(p, mob);
    }

}
