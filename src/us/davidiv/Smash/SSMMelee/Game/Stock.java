package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Game.Game.setLiving;
import static us.davidiv.Smash.SSMMelee.Game.GameStart.getGame;
import static us.davidiv.Smash.SSMMelee.Game.Respawn.respawn;

public class Stock implements Listener {
    public Stock(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static HashMap<Player, Integer> stock = new HashMap<Player, Integer>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        if (!getGame()) {return;}

        Player p = e.getEntity();
        if (getStock(p) == null || getStock(p) <= 0) {return;}
        addStock(p, -1);

        if (getStock(p) > 0) {respawn(p);}
        else {setLiving(p, false);}
    }

    public static Integer getStock(Player p) {
        return stock.get(p);
    }

    public static void addStock(Player p, Integer add) {
        stock.put(p, (stock.get(p) + add));
    }

    public static void setStock(Player p, Integer amount) {
        stock.put(p, amount);
    }

}


