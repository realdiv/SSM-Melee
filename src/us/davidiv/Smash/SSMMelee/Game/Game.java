package us.davidiv.Smash.SSMMelee.Game;

//Class to handle objective and other good shit

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Game.GameStart.*;
import static us.davidiv.Smash.SSMMelee.Game.Stock.getStock;

public class Game implements Listener {
    public Game(SmashMelee plugin) {plugin.getServer().getPluginManager().registerEvents(this, plugin);}

    private GameScoreboardManager GSM = new GameScoreboardManager(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "     SSM MELEE    ");

    public static HashMap<Player, Boolean> alive = new HashMap<Player, Boolean>();

    @EventHandler
    public void winnerCheck(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        if (!getGame()) {return;}

        int size = alive.size();
        if (size != 1) {return;}

        for (Player p : alive.keySet()) {

            if (getStock(p) <= 0) {return;}

            Bukkit.broadcastMessage(ChatColor.AQUA + "" + p.getName() + " has won the game!");
            setGame(false);

        }

        if (getGame()) {return;}
        gameDeactivate();

        for (Player p : Bukkit.getOnlinePlayers())
            p.sendRawMessage(ChatColor.GREEN + "Thank you for playing the test version of SSM Melee!");
    }

    public static Boolean getLiving(Player p) {
        return alive.get(p);
    }

    public static void setLiving(Player p, Boolean life) {
        if (life) alive.put(p, life);
        else if (!life) alive.remove(p);
    }


}