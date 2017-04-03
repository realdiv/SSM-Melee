package us.davidiv.Smash.SSMMelee.Game;

//Basic stock system, keep the number of stock at 4 until I make an in-game tester

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

public class Stock implements Listener {
    public Stock(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player, Integer> stock = new HashMap<Player, Integer>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Boolean gameActive = Game.game.get("Game");
        if (gameActive) {
            Player p = e.getEntity();
            Player d = (Player) p.getKiller();
            if (stock.get(p) > 0) {
                stock.put(p, (stock.get(p) - 1));
                int stockAmount = stock.get(p);
                //Bukkit.broadcastMessage(p + " has been killed by" + d + "")
            }
        }
    }

}


