package us.davidiv.Smash.SSMMelee.Kit.Creeper;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitCreeper implements CommandExecutor, Listener {
    public KitCreeper(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Kit.kitM(p, Kits.CREEPER, "TBD", "Detonate", Material.IRON_AXE, Material.IRON_SPADE);
        MobDisguise mob = new MobDisguise(DisguiseType.CREEPER);
        CreeperWatcher creeper = (CreeperWatcher) mob.getWatcher();
        disguiseEntity(p, mob);
        return true;
    }

}