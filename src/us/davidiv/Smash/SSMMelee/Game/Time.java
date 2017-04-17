package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Time implements Listener {
    public Time(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static boolean timer;
    private static double currenttime;

    @EventHandler
    public void GameTimer(UpdateEvent e) {

        if (e.getType() != UpdateType.DECI) {return;}

        if (!timer) {return;}

        currenttime += 0.1;

    }

    public static void setTimer(boolean b) {
        timer = b;
    }

    public static double getCurrentTime() {
        return currenttime;
    }

}
