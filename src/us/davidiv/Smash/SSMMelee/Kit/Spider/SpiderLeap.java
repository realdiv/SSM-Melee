package us.davidiv.Smash.SSMMelee.Kit.Spider;

import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

/**
 * Created by David on 3/15/2017.
 */
public class SpiderLeap implements Listener {
    public SpiderLeap(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
