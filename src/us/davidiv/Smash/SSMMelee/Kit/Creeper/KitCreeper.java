package us.davidiv.Smash.SSMMelee.Kit.Creeper;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitCreeper {

    public static void Creeper(Player p) {
        Kit.kitM(p, Kits.CREEPER, "TBD", "Detonate", Material.IRON_AXE, Material.IRON_SPADE);
        MobDisguise mob = new MobDisguise(DisguiseType.CREEPER);
        CreeperWatcher creeper = (CreeperWatcher) mob.getWatcher();
        disguiseEntity(p, mob);
    }

}