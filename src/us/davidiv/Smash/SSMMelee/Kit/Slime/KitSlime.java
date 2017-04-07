package us.davidiv.Smash.SSMMelee.Kit.Slime;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitSlime {

    public static void Slime(Player p) {
        Kit.kitM(p, Kits.SLIME, "Slime Toss", "Sling Slime", Material.IRON_SWORD, Material.IRON_AXE);
        p.setExp(0);
        Overheat.overheat.put(p, false);
        Overheat.oh.put(p, 0.0);
        MobDisguise mob = new MobDisguise(DisguiseType.SLIME);
        SlimeWatcher slime = (SlimeWatcher) mob.getWatcher();
        mob.setShowName(true);
        mob.setViewSelfDisguise(false);
        mob.setKeepDisguiseOnPlayerDeath(true);
        disguiseEntity(p, mob);
    }

}
