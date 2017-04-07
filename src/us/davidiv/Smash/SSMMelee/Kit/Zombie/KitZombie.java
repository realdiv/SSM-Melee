package us.davidiv.Smash.SSMMelee.Kit.Zombie;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitZombie {

    public static void Zombie(Player p) {
        Kit.kitM(p, Kits.ZOMBIE, "TBD", "TBD", Material.IRON_SWORD, Material.BOW);
        MobDisguise mob = new MobDisguise(DisguiseType.ZOMBIE);
        disguiseEntity(p, mob);
    }

}