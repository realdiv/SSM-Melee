package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

public class Stun implements Listener {
    public Stun(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static HashMap<Player, Integer>stunned = new HashMap<Player, Integer>();

    public static void stun(Player p, Integer ticks) {
        stunned.put(p, ticks);
    }

    @EventHandler
    public void onStun(UpdateEvent e) {
        if (e.getType() != UpdateType.TICK) {return;}
        for (Player p : stunned.keySet()) {
            //Add stun effect
        }
    }

}
