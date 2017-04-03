package us.davidiv.Smash.SSMMelee.Game;

//Void border than instantly kills you when you cross it - 9001 damage for the memes

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Void implements Listener {
    public Void(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //TODO ADD MAP: VOID BORDER: X: Y: Z: CONFIG

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Boolean gameActive = Game.game.get("Game");
        if (gameActive) {
            Player p = e.getPlayer();
            World world = Bukkit.getServer().getWorld("HyruleCastle");
            if (p.getWorld() == world && !p.isDead() && p.getGameMode() != GameMode.SPECTATOR) {
                if (p.getLocation().getX() > 25 || p.getLocation().getX() < -78) {
                    p.getWorld().strikeLightning(p.getLocation());
                    p.setHealth(0.0);
                    p.setHealth(20.0);
                    p.setGameMode(GameMode.SPECTATOR);
                }
                if (p.getLocation().getY() > 90 || p.getLocation().getY() < 2) {
                    p.getWorld().strikeLightning(p.getLocation());
                    p.setHealth(0.0);
                    p.setHealth(20.0);
                    p.setGameMode(GameMode.SPECTATOR);
                }
                if (p.getLocation().getZ() > 74 || p.getLocation().getZ() < -47) {
                    p.getWorld().strikeLightning(p.getLocation());
                    p.setHealth(0.0);
                    p.setHealth(20.0);
                    p.setGameMode(GameMode.SPECTATOR);
                }

            }
        }
    }



}
