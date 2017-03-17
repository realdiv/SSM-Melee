package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import us.davidiv.Smash.SSMMelee.SmashMelee;


public class GameStart implements CommandExecutor, Listener {
    public GameStart(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private Team team;
    private Scoreboard board;
    private int i = 16;
    private GameScoreboardManager GSM = new GameScoreboardManager(ChatColor.DARK_RED + "SSM MELEE");

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
                    SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Knockback", 0);
                    SmashMelee.getPlugin().saveConfig();
                }
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    String aname = player.getName();
                    String auuid = player.getUniqueId().toString();
                    int stock = SmashMelee.getPlugin().getConfig().getInt("Players." + auuid + ".Stock");
                    int kb = SmashMelee.getPlugin().getConfig().getInt("Players." + auuid + ".Knockback");
                    GSM.add(ChatColor.RED + "&4", i);
                    i--;
                    GSM.add(ChatColor.GRAY  + "" + aname, i);
                    i--;
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                    i--;
                    GSM.add(ChatColor.RED + "&4", i);
                    i--;
                    GSM.send(player);
                    GSM.update();
                }
                i = 16;
            }

            if (cmd.getName().contentEquals("gamestop")) {
                Player p = ((Player) sender).getPlayer();
                SmashMelee.getPlugin().getConfig().set("GameActive", false);
                SmashMelee.getPlugin().saveConfig();
                ((Player) sender).sendRawMessage("Game Deactivated");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    String uuid = player.getUniqueId().toString();
                    SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Stock", 0);
                    SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Knockback", 0);
                    SmashMelee.getPlugin().saveConfig();
                    GSM.reset();
                    GSM.send(player);
                }
            }
        }
        return true;
    }



}
