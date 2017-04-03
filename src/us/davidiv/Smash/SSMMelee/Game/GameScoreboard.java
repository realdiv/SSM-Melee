package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class GameScoreboard implements Listener {
    public GameScoreboard(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private Team team;
    private Scoreboard board;
    private int i = 16;
    private GameScoreboardManager GSM = new GameScoreboardManager(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "     SSM MELEE    ");


    int kb, stock;
    String stockC;

    @EventHandler
    public void ScoreboardDeathUpdate(PlayerDeathEvent e) {
        Boolean gameActive = Game.game.get("Game");
        if (gameActive) {
            Player p = e.getEntity();
            String name = p.getName();
            String uuid = p.getUniqueId().toString();
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                String aname = player.getName();
                stock = Stock.stock.get(player);
                kb = Knockback.knockback.get(player);
                if (stock == 4) {
                    stockC = ("••••");
                }
                if (stock == 3) {
                    stockC = ("•••");
                }
                if (stock == 2) {
                    stockC = ("••");
                }
                if (stock == 1) {
                    stockC = ("•");
                }
                if (stock == 0) {
                    stockC = "";
                }
                GSM.add(ChatColor.RED + " ", i);
                i--;
                GSM.add(ChatColor.GRAY + "" + aname, i);
                i--;

                //4 STOCK

                if (kb < 100 && stock == 4) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 4) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 4) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 4) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 599 && stock == 4) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }

                //3 STOCK

                if (kb < 100 && stock == 3) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 3) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 3) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 3) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 599 && stock == 3) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }

                //2 STOCK

                if (kb < 100 && stock == 2) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 2) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 2) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 2) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 599 && stock == 2) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }

                //1 STOCK

                if (kb < 100 && stock == 1) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 1) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 1) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 1) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 599 && stock == 1) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }

                //NO STOCK

                if (stock == 0) {
                    GSM.add(ChatColor.GRAY + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GRAY + stockC, i);
                }
                i--;
                GSM.add(ChatColor.RED + "&4", i);
                i--;
                GSM.send(player);
                GSM.update();
            }
            GSM.add(ChatColor.RED + " ", i);
            i = 16;
        }
    }

    @EventHandler
    public void ScoreboardKBUpdate(EntityDamageByEntityEvent e) {
        Boolean gameActive = Game.game.get("Game");
        if (gameActive) {
            Entity p = e.getEntity();
            String name = p.getName();
            String uuid = p.getUniqueId().toString();
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                String aname = player.getName();
                stock = Stock.stock.get(player);
                kb = Knockback.knockback.get(player);
                if (stock == 4) {
                    stockC = ("••••");
                }
                if (stock == 3) {
                    stockC = ("•••");
                }
                if (stock == 2) {
                    stockC = ("••");
                }
                if (stock == 1) {
                    stockC = ("•");
                }
                if (stock == 0) {
                    stockC = "";
                }
                GSM.add(ChatColor.RED + " ", i);
                i--;
                GSM.add(ChatColor.GRAY + "" + aname, i);
                i--;

                //4 STOCK

                if (kb < 100 && stock == 4) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 4) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 4) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 4) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }
                if (kb > 599 && stock == 4) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                }

                //3 STOCK

                if (kb < 100 && stock == 3) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 3) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 3) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 3) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }
                if (kb > 599 && stock == 3) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stockC, i);
                }

                //2 STOCK

                if (kb < 100 && stock == 2) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 2) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 2) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 2) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }
                if (kb > 599 && stock == 2) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stockC, i);
                }

                //1 STOCK

                if (kb < 100 && stock == 1) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 99 && kb < 200 && stock == 1) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 199 && kb < 300 && stock == 1) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 299 && kb < 600 && stock == 1) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }
                if (kb > 599 && stock == 1) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                }

                //NO STOCK

                if (stock == 0) {
                    GSM.add(ChatColor.GRAY + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GRAY + stockC, i);
                }
                i--;
                GSM.add(ChatColor.RED + "&4", i);
                i--;
                GSM.send(player);
                GSM.update();
            }
            GSM.add(ChatColor.RED + " ", i);
            i = 16;
        }
    }
}


