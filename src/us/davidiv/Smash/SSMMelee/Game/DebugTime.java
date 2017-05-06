package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Game.Time.getCurrentTime;
import static us.davidiv.Smash.SSMMelee.Utils.UtilMath.rt2d;

public class DebugTime implements CommandExecutor, Listener {
    public DebugTime(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        sender.sendMessage("Time: " + rt2d(getCurrentTime()));
        return true;
    }

}
