package us.davidiv.Smash.SSMMelee.Kit.Slime;

/*

Kite Playstyle (EVA, DEF)
High attack, (x)high regen, low armor/kb resistance, exceptional evasion

*/

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class KitSlime implements CommandExecutor, Listener {
    public KitSlime(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static DisguiseAPI ds;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Kit.kitM(p, Kits.SLIME, "Slime Toss", "Sling Slime", Material.IRON_SWORD, Material.IRON_AXE);
        p.setExp(0);
        Overheat.overheat.put(p, false);
        Overheat.oh.put(p, 0.0);
        MobDisguise mob = new MobDisguise(DisguiseType.SLIME);
        SlimeWatcher slime = (SlimeWatcher) mob.getWatcher();
        mob.setShowName(true);
        mob.setViewSelfDisguise(false);
        mob.setKeepDisguiseOnPlayerDeath(true);
        ds.disguiseEntity(p, mob);
        return true;
    }

}
