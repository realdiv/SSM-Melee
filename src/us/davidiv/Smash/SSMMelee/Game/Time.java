package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.inventivetalent.bossbar.BossBarAPI;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Game.DebugMode.ssmDebug;
import static us.davidiv.Smash.SSMMelee.Utils.UtilMath.timeHMS;

public class Time implements Listener {
    public Time(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static boolean timer;
    private static double currenttime;

    BossBarAPI bba = new BossBarAPI();

    @EventHandler
    public void GameTimer(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        if (!timer) {
            currenttime = 0;
            return;
        }

        currenttime += 0.05;

    }

    @EventHandler
    public void DisplayTime(UpdateEvent e) {

        if (e.getType() != UpdateType.SECOND) {return;}

        if (!timer) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (!ssmDebug(p)) continue;
            p.sendRawMessage(ChatColor.AQUA + timeHMS(getCurrentTime()));
            //addBar()

        }

    }

    public static void setTimer(boolean b) {
        timer = b;
    }

    public static double getCurrentTime() {return currenttime;}

}
