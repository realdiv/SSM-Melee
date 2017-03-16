package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;


public class GameStart implements CommandExecutor, Listener {
    public GameStart(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("gamestart")) {
                Player p = ((Player) sender).getPlayer();
                SmashMelee.getPlugin().getConfig().set("GameActive", true);
                SmashMelee.getPlugin().saveConfig();
                ((Player) sender).sendRawMessage("Game Activated");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    String uuid = player.getUniqueId().toString();
                    SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Stock", 4);
                    SmashMelee.getPlugin().saveConfig();
                }
            }

            if (cmd.getName().contentEquals("gamestop")) {
                Player p = ((Player) sender).getPlayer();
                SmashMelee.getPlugin().getConfig().set("GameActive", false);
                SmashMelee.getPlugin().saveConfig();
                ((Player) sender).sendRawMessage("Game Deactivated");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    String uuid = player.getUniqueId().toString();
                    SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Stock", 0);
                    SmashMelee.getPlugin().saveConfig();
                }
            }
        }
        return true;
    }



}
