package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Game.GameStart.*;
import static us.davidiv.Smash.SSMMelee.Game.Knockback.getKnockback;
import static us.davidiv.Smash.SSMMelee.Game.Stock.getStock;

public class GameScoreboard implements Listener {
    public GameScoreboard(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static int i = 16;
    public static GameScoreboardManager GSM = new GameScoreboardManager(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "     SSM MELEE    ");


    private static int kb, stock;
    private static String stockC;

    public static void updateSmashScoreboard() {
        if (getGame()) {
            if (!getTeams()) {
                GSM.reset();
                GSM.add(ChatColor.RED + " ", i);
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    stock = getStock(player);
                    kb = getKnockback(player);
                    if (stock > 4) {
                        stockC = (getStock(player).toString());
                    } else if (stock == 4) {
                        stockC = ("••••");
                    } else if (stock == 3) {
                        stockC = ("•••");
                    } else if (stock == 2) {
                        stockC = ("••");
                    } else if (stock == 1) {
                        stockC = ("•");
                    } else if (stock == 0) {
                        stockC = "";
                    }
                    i--;
                    GSM.add(ChatColor.GRAY + "" + player.getName(), i);
                    i--;

                    //>4 STOCK
                    if (kb < 100 && stock > 4) {
                        GSM.add(ChatColor.GREEN + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                    }
                    if (kb > 99 && kb < 200 && stock > 4) {
                        GSM.add(ChatColor.YELLOW + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                    }
                    if (kb > 199 && kb < 300 && stock > 4) {
                        GSM.add(ChatColor.GOLD + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                    }
                    if (kb > 299 && kb < 600 && stock > 4) {
                        GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                    }
                    if (kb > 599 && stock > 4) {
                        GSM.add(ChatColor.DARK_RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.GREEN + stockC, i);
                    }


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
            else if (getTeams()) {
                GSM.reset();
                GSM.add(ChatColor.RED + " ", i);
                i--;
                for (Player player : getRedTeam()) {
                    stock = getStock(player);
                    kb = getKnockback(player);
                    if (stock > 4) {
                        stockC = (getStock(player).toString());
                    } else if (stock == 4) {
                        stockC = ("••••");
                    } else if (stock == 3) {
                        stockC = ("•••");
                    } else if (stock == 2) {
                        stockC = ("••");
                    } else if (stock == 1) {
                        stockC = ("•");
                    } else if (stock == 0) {
                        stockC = "";
                    }
                    GSM.add(ChatColor.GRAY + "" + player.getName(), i);
                    i--;
                    GSM.add(ChatColor.RED + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.RED + stockC, i);
                    i--;
                    GSM.add(ChatColor.RED + "&4", i);
                    i--;
                }
                for (Player player : getBlueTeam()) {
                    stock = getStock(player);
                    kb = getKnockback(player);
                    if (stock > 4) {
                        stockC = (getStock(player).toString());
                    } else if (stock == 4) {
                        stockC = ("••••");
                    } else if (stock == 3) {
                        stockC = ("•••");
                    } else if (stock == 2) {
                            stockC = ("••");
                    } else if (stock == 1) {
                        stockC = ("•");
                    } else if (stock == 0) {
                        stockC = "";
                    }
                    GSM.add(ChatColor.GRAY + "" + player.getName(), i);
                    i--;
                    GSM.add(ChatColor.BLUE + "" + kb + ChatColor.WHITE + "%" + ChatColor.WHITE + "  |  " + ChatColor.BLUE + stockC, i);
                    i--;
                    GSM.add(ChatColor.RED + "&4", i);
                    i--;
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    GSM.send(p);
                    GSM.update();
                }
                i = 16;
            }
        }
    }

    @EventHandler
    public void ScoreboardStockUpdate(PlayerDeathEvent e) {
        updateSmashScoreboard();
    }

    @EventHandler
    public void ScoreboardKBUpdate(EntityDamageByEntityEvent e) {
        updateSmashScoreboard();
    }

}


