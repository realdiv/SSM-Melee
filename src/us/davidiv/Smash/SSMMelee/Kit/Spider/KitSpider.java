package us.davidiv.Smash.SSMMelee.Kit.Spider;

/*

Hybrid Playstyle (AGI, ATK)
Medium attack, medium regen, medium armor/kb resistance , exceptional agility

*/

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class KitSpider implements CommandExecutor, Listener {
    public KitSpider(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static DisguiseAPI ds;
    public static MobDisguise mob = new MobDisguise(DisguiseType.SPIDER);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Kit.kitM(p, Kits.SPIDER, "Stinger", "Web-Hook", Material.IRON_SWORD, Material.IRON_AXE);
        MobDisguise mob = new MobDisguise(DisguiseType.SPIDER);
        mob.setShowName(true);
        mob.setViewSelfDisguise(false);
        mob.setKeepDisguiseOnPlayerDeath(true);
        ds.disguiseEntity(p, mob);
        return true;
    }

}
