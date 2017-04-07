package us.davidiv.Smash.SSMMelee.Kit.Spider;

/*

Hybrid Playstyle (AGI, ATK)
Medium attack, medium regen, medium armor/kb resistance , exceptional agility

*/

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitSpider {

    public static void Spider(Player p) {
        Kit.kitM(p, Kits.SPIDER, "Stinger", "Web-Hook", Material.IRON_SWORD, Material.IRON_AXE);
        MobDisguise mob = new MobDisguise(DisguiseType.SPIDER);
        mob.setShowName(true);
        mob.setViewSelfDisguise(false);
        mob.setKeepDisguiseOnPlayerDeath(true);
        disguiseEntity(p, mob);
        //p.sendRawMessage(msg(Message.MessageType.GAME, "you chose Spider kit!"));
    }

}
