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

    //TODO ADD LINE BETWEEN TITLE AND FIRST NAME (BUG)

    private Team team;
    private Scoreboard board;
    private int i = 16;
    private GameScoreboardManager GSM = new GameScoreboardManager(ChatColor.DARK_RED + "SSM MELEE");

/*
0-99% green
100-199% yellow
200-299% orange
300-599% red
600-999% dark red
1000%+ RAINBOW
*/


    @EventHandler
    public void ScoreboardTest(PlayerDeathEvent e) {
        if (SmashMelee.getPlugin().getConfig().getBoolean("GameActive", true)) {
            Player p = e.getEntity();
            String name = p.getName();
            String uuid = p.getUniqueId().toString();
            GSM.add(ChatColor.RED + "&4", i);
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                String aname = player.getName();
                String auuid = player.getUniqueId().toString();
                int stock = SmashMelee.getPlugin().getConfig().getInt("Players." + auuid + ".Stock");
                int kb = SmashMelee.getPlugin().getConfig().getInt("Players." + auuid + ".Knockback");
                GSM.add(ChatColor.RED + "&4", i);
                i--;
                GSM.add(ChatColor.GRAY + "" + aname, i);
                i--;
                if (kb < 99 && stock == 4) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 4) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 4) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 4) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 599 && stock == 4) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb < 99 && stock == 3) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 3) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 3) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 3) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 599 && stock == 3) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb < 99 && stock == 2) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 2) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 2) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 2) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 599 && stock == 2) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb < 99 && stock == 1) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 1) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 1) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 1) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 599 && stock == 1) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (stock == 0) {
                    GSM.add(ChatColor.GRAY + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GRAY + stock, i);
                }
                i--;
                GSM.add(ChatColor.RED + "&4", i);
                i--;
                GSM.send(player);
                GSM.update();
            }
            i = 16;
        }
    }

    @EventHandler
    public void ScoreboardKBUpdate(EntityDamageByEntityEvent e) {
        if (SmashMelee.getPlugin().getConfig().getBoolean("GameActive", true)) {
            Entity p = e.getEntity();
            String name = p.getName();
            String uuid = p.getUniqueId().toString();
            GSM.add(ChatColor.RED + "&4", i);
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                String aname = player.getName();
                String auuid = player.getUniqueId().toString();
                int stock = SmashMelee.getPlugin().getConfig().getInt("Players." + auuid + ".Stock");
                int kb = SmashMelee.getPlugin().getConfig().getInt("Players." + auuid + ".Knockback");
                GSM.add(ChatColor.RED + "&4", i);
                i--;
                GSM.add(ChatColor.GRAY + "" + aname, i);
                i--;
                if (kb < 99 && stock == 4) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 4) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 4) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 4) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb > 599 && stock == 4) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stock, i);
                }
                if (kb < 99 && stock == 3) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 3) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 3) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 3) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb > 599 && stock == 3) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.YELLOW + stock, i);
                }
                if (kb < 99 && stock == 2) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 2) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 2) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 2) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb > 599 && stock == 2) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GOLD + stock, i);
                }
                if (kb < 99 && stock == 1) {
                    GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 99 && kb < 200 && stock == 1) {
                    GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 199 && kb < 300 && stock == 1) {
                    GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 299 && kb < 600 && stock == 1) {
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (kb > 599 && stock == 1) {
                    GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stock, i);
                }
                if (stock == 0) {
                    GSM.add(ChatColor.GRAY + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GRAY + stock, i);
                }
                i--;
                GSM.add(ChatColor.RED + "&4", i);
                i--;
                GSM.send(player);
                GSM.update();
            }
            i = 16;
        }
    }


}
