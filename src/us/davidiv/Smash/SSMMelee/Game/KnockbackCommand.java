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
import static us.davidiv.Smash.SSMMelee.Game.Knockback.setKnockback;

public class KnockbackCommand implements CommandExecutor, Listener {
    public KnockbackCommand(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Did you mean /knockback <amount> <player>?");
            return false;
        }

        if (args.length == 1) {
            Player p = (Player) sender;
            setKnockback(p, Integer.parseInt(args[0]));
            p.sendRawMessage(ChatColor.GREEN + "Your knockback has been set to " + args[0] + "!");
        } else {
            Player p = getPlayer(args[1]);
            setKnockback(p, Integer.parseInt(args[0]));
            if (p == sender) {
                p.sendRawMessage(ChatColor.GREEN + "Your knockback has been set to " + args[0] + "!");
            } else {
                sender.sendMessage(ChatColor.GREEN + p.getName() + "'s knockback has been set to " + args[0] + "!");
                p.sendRawMessage(ChatColor.GREEN + "Your knockback has been set to " + args[0] + "!");
            }
        }

        updateSmashScoreboard();

        return true;
    }

}