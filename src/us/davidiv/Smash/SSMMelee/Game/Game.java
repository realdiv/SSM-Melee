package us.davidiv.Smash.SSMMelee.Game;

//Class to handle objective and other good shit

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Game implements Listener {
    public Game(SmashMelee plugin) {plugin.getServer().getPluginManager().registerEvents(this, plugin);}

    @EventHandler
    public void createGameActive(PlayerJoinEvent e) {
        if (!SmashMelee.getPlugin().getConfig().contains("GameActive")) {
            SmashMelee.getPlugin().getConfig().set("GameActive", false);
        }
    }




}
