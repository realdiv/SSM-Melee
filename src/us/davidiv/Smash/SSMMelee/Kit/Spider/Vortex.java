package us.davidiv.Smash.SSMMelee.Kit.Spider;

import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Vortex implements Listener {
    public Vortex(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
