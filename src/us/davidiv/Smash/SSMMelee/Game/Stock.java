package us.davidiv.Smash.SSMMelee.Game;

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

        if (!getGame()) {return;}

        Player p = e.getEntity();
        if (getStock(p) == null || getStock(p) <= 0) {return;}

        addStock(p, -1);

        respawn(p);
    }

    public static Integer getStock(Player p) {
        return stock.get(p);
    }

    public static void addStock(Player p, Integer add) {
        setStock(p, (getStock(p) + add));
    }

    public static void setStock(Player p, Integer amount) {
        stock.put(p, amount);
    }

}


