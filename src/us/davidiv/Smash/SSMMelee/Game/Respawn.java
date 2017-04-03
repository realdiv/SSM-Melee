package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Respawn implements Listener {
    public Respawn(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //TODO ADD COUNTDOWN FOR RESPAWN

    @EventHandler
    public void respawn(PlayerDeathEvent e) {
        Boolean gameActive = Game.game.get("Game");
        if (gameActive) {
            Player p = e.getEntity();
            if (Stock.stock.get(p) > 0) {
                p.setHealth(20.0);
                p.setGameMode(GameMode.SPECTATOR);
                Bukkit.getScheduler().runTaskLater(SmashMelee.getPlugin(), new Runnable() {

                    @Override
                    public void run() {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(new Location(Bukkit.getWorld("HyruleCastle"), -27.5, 30.0, 19.5));
                        p.setAllowFlight(false);
                        p.setFlying(false);
                    }
                }, 80L);
            }

            if (Stock.stock.get(p) <= 0) {
                p.setHealth(20.0);
                p.setGameMode(GameMode.SPECTATOR);
                p.teleport(new Location(Bukkit.getWorld("HyruleCastle"), -27.5, 30.0, 19.5));
                p.sendRawMessage(ChatColor.DARK_RED + "YOU ARE OUT OF THE GAME!");
                Game.dead.put(p, true);
                Game.winner.remove(p);
            }
        }
    }

    //TODO CHECK IF PLAYER ISDEAD = TRUE

}




