package us.davidiv.Smash.SSMMelee.Kit.CaveSpider;

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

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;

public class KitCaveSpider implements CommandExecutor, Listener {
    public KitCaveSpider(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Kit.kitM(p, Kits.CAVESPIDER, "TBD", "Blood Rush", Material.IRON_SWORD, Material.IRON_AXE);
        MobDisguise mob = new MobDisguise(DisguiseType.CAVE_SPIDER);
        disguiseEntity(p, mob);
        return true;
    }

}
