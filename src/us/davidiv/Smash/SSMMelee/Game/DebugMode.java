package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.ArrayList;

public class DebugMode implements CommandExecutor, Listener {
    public DebugMode(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static ArrayList<Player> debugmode = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if (!debugmode.contains(p))
            debugmode.add(p);

        else if (debugmode.contains(p))
            debugmode.remove(p);

        return true;
    }

    public static boolean ssmDebug(Player p) {
        if (!debugmode.contains(p)) return false;
        else return true;
    }

}
