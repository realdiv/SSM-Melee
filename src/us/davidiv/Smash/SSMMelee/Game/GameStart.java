package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;


public class GameStart implements CommandExecutor, Listener {
    public GameStart(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private Team team;
    private Scoreboard board;
    private int i = 16;
    private GameScoreboardManager GSM = new GameScoreboardManager(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "     SSM MELEE    ");

    String stockC;

    public static HashMap<String, Boolean> GameActive = new HashMap<String, Boolean>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("gamestart")) {
                Game.game.put("Game", true);
                Player p = ((Player) sender).getPlayer();
                ((Player) sender).sendRawMessage("Game Activated");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    Knockback.knockback.put(player, 0);
                    Stock.stock.put(player, 4);
                    Game.alive.put(player, true);
                }
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    String aname = player.getName();
                    int kb = Knockback.knockback.get(player);
                    int stockA = Stock.stock.get(player);
                    if (stockA == 4) {
                        stockC = ("••••");
                    }
                    if (stockA == 3) {
                        stockC = ("•••");
                    }
                    if (stockA == 2) {
                        stockC = ("••");
                    }
                    if (stockA == 1) {
                        stockC = ("•");
                    }
                    if (stockA == 0) {
                        stockC = "";
                    }


                    GSM.add(ChatColor.RED + " ", i);
                    i--;
                    GSM.add(ChatColor.GRAY  + "" + aname, i);
                    i--;
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                    i--;
                    GSM.add(ChatColor.RED + "&4", i);
                    i--;
                    GSM.send(player);
                    GSM.update();
                }
                i = 16;
            }

            if (cmd.getName().contentEquals("gamestop")) {
                Game.game.put("Game", false);
                Player p = ((Player) sender).getPlayer();
                ((Player) sender).sendRawMessage("Game Deactivated");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.setGameMode(GameMode.SURVIVAL);
                    Knockback.knockback.put(player, 0);
                    Stock.stock.put(player, 0);
                    Game.alive.remove(player);
                    GSM.reset();
                    GSM.send(player);
                }
            }
            if (cmd.getName().contentEquals("stock")) {
                Player p = ((Player) sender).getPlayer();
                Knockback.knockback.put(p, 1041);
                Stock.stock.put(p, 2);
                int kb = Knockback.knockback.get(p);
                int stockA = Stock.stock.get(p);
                String aname = p.getName();

                GSM.add(ChatColor.RED + " ", i);
                i--;
                GSM.add(ChatColor.GRAY  + "" + aname, i);
                i--;
                GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockA, i);
                i--;
                GSM.add(ChatColor.RED + "&4", i);
                i--;
                GSM.update();
                GSM.send(p);
            }
        }
        return true;
    }



}
