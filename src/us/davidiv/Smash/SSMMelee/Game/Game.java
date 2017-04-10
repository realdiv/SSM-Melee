package us.davidiv.Smash.SSMMelee.Game;

//Class to handle objective and other good shit

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Game.GameStart.getGame;
import static us.davidiv.Smash.SSMMelee.Game.GameStart.setGame;
import static us.davidiv.Smash.SSMMelee.Game.Stock.getStock;
import static us.davidiv.Smash.SSMMelee.Game.Stock.setStock;

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
        for (Player player : alive.keySet()) {
            if (getStock(player) <= 0) {return;}

            String name = player.getName();
            Bukkit.broadcastMessage(ChatColor.AQUA + "" + name + " has won the game!");
            setGame(false);
        }

        if (getGame()) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setGameMode(GameMode.SURVIVAL);
            p.teleport(new Location(Bukkit.getWorld("HyruleCastle"), -27.5, 30.0, 19.5));
            p.setAllowFlight(false);
            p.setFlying(false);
            Knockback.knockback.put(p, 0);
            setStock(p, 0);
            GSM.reset();
            GSM.send(p);
            p.sendRawMessage(ChatColor.GREEN + "Thank you for playing the test version of SSM Melee!");
        }
    }

}