package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Game.GameStart.getGame;

public class Void implements Listener {
    public Void(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //TODO ADD MAP: VOID BORDER: X: Y: Z: CONFIG

    public void kill(Player p) {
        p.getWorld().strikeLightning(p.getLocation());
        p.setHealth(0.0);
        p.setHealth(20.0);
        p.setGameMode(GameMode.SPECTATOR);
    }

    @EventHandler
    public void VoidBorder(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        if (!getGame()) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {
            World world = Bukkit.getServer().getWorld("HyruleCastle");
            if (p.getWorld() == world && !p.isDead() && p.getGameMode() != GameMode.SPECTATOR) {
                if (p.getLocation().getX() > 25 || p.getLocation().getX() < -78) {
                    kill(p);
                }
                if (p.getLocation().getY() > 90 || p.getLocation().getY() < 2) {
                    kill(p);
                }
                if (p.getLocation().getZ() > 74 || p.getLocation().getZ() < -47) {
                    kill(p);
                }
            }
        }
    }



}
