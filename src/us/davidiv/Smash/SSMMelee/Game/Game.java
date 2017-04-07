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

public class Game implements Listener {
    public Game(SmashMelee plugin) {plugin.getServer().getPluginManager().registerEvents(this, plugin);}

    private GameScoreboardManager GSM = new GameScoreboardManager(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "     SSM MELEE    ");

    public static HashMap<Player, Boolean> alive = new HashMap<Player, Boolean>();
    public static HashMap<String, Boolean> game = new HashMap<String, Boolean>();

    @EventHandler
    public void winnerCheck(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        Boolean gameActive = game.get("Game");
        Boolean b;

        if (!gameActive) {return;}

        int size = alive.size();
        if (size != 1) {return;}
        for (Player player : alive.keySet()) {
            int stock = Stock.stock.get(player);
            if (stock <= 0) {return;}

            String name = player.getName();
            Bukkit.broadcastMessage(ChatColor.AQUA + "" + name + " has won the game!");
            game.put("Game", false);
            gameActive = game.get("Game");
        }

        if (gameActive) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setGameMode(GameMode.SURVIVAL);
            p.teleport(new Location(Bukkit.getWorld("HyruleCastle"), -27.5, 30.0, 19.5));
            p.setAllowFlight(false);
            p.setFlying(false);
            Knockback.knockback.put(p, 0);
            Stock.stock.put(p, 0);
            GSM.reset();
            GSM.send(p);
            p.sendRawMessage(ChatColor.GREEN + "Thank you for playing the test version of SSM Melee!");
        }
    }


}