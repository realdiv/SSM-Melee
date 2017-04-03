package us.davidiv.Smash.SSMMelee.Game;

//Class to handle objective and other good shit

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

public class Game implements Listener {
    public Game(SmashMelee plugin) {plugin.getServer().getPluginManager().registerEvents(this, plugin);}

    private GameScoreboardManager GSM = new GameScoreboardManager(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "     SSM MELEE    ");

    public static HashMap<Player, Boolean> dead = new HashMap<Player, Boolean>();
    public static HashMap<Player, Boolean> winner = new HashMap<Player, Boolean>();
    public static HashMap<String, Boolean> game = new HashMap<String, Boolean>();

    int i = 2;

        @EventHandler
        public void winnerCheck(PlayerDeathEvent e) {
            Boolean gameActive = game.get("Game");
            Player pU = (Player) e.getEntity();
            if (Stock.stock.get(pU) <= 0) {
                Game.winner.remove(pU);
            }
            if (gameActive) {
                int size = winner.size();
                if (size == 1) {
                    for (Player player : winner.keySet()) {
                        int stock = Stock.stock.get(player);
                        if (stock > 0) {
                            String name = player.getName();
                            Bukkit.broadcastMessage(ChatColor.AQUA + "" + name + " has won the game!");
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        p.setGameMode(GameMode.SURVIVAL);
                                        p.teleport(new Location(Bukkit.getWorld("HyruleCastle"), -27.5, 30.0, 19.5));
                                        p.setAllowFlight(false);
                                        p.setFlying(false);
                                        Knockback.knockback.put(p, 0);
                                        Stock.stock.put(p, 0);
                                        dead.put(p, false);
                                        GSM.reset();
                                        GSM.send(p);
                                    }
                            game.put("Game", false);
                                }
                            }
                        }
                    }
                }
}