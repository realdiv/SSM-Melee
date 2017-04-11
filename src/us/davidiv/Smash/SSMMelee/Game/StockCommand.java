package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static org.bukkit.Bukkit.getPlayer;
import static us.davidiv.Smash.SSMMelee.Game.GameScoreboard.updateSmashScoreboard;
import static us.davidiv.Smash.SSMMelee.Game.Stock.setStock;

public class StockCommand implements CommandExecutor, Listener {
    public StockCommand(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Did you mean /stock <amount> <player>?");
            return false;
        }

        if (args.length == 1) {
            Player p = (Player) sender;
            setStock(p, Integer.parseInt(args[0]));
            p.sendRawMessage(ChatColor.GREEN + "Your stock has been set to " + args[0] + "!");
        }
        else {
            Player p = getPlayer(args[1]);
            setStock(p, Integer.parseInt(args[0]));
            if (p == sender) {p.sendRawMessage(ChatColor.GREEN + "Your stock has been set to " + args[0] + "!");}
            else {
                sender.sendMessage(ChatColor.GREEN + p.getName() + "'s stock has been set to " + args[0] + "!");
                p.sendRawMessage(ChatColor.GREEN + "Your stock has been set to " + args[0] + "!");
            }
        }

        updateSmashScoreboard();

        return true;
    }

}
