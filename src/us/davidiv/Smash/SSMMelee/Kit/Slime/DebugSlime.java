package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Kit.Slime.SlingSlime.getCenter;

public class DebugSlime implements CommandExecutor, Listener {
    public DebugSlime(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(getCenter((Player) sender) + "");
        return true;
    }
}
