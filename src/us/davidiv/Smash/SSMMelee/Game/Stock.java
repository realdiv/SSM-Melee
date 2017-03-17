package us.davidiv.Smash.SSMMelee.Game;

//Basic stock system, keep the number of stock at 4 until I make an in-game tester

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Stock implements Listener {
    public Stock(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void stockCreate(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();
        if(!SmashMelee.getPlugin().getConfig().contains("Players." + uuid+ ".Stock")) {
            SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Stock", 0);
            SmashMelee.getPlugin().saveConfig();
        }

        else {
            SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Stock", 0);
            SmashMelee.getPlugin().saveConfig();
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (SmashMelee.getPlugin().getConfig().getBoolean("GameActive", true)) {
            Player p = e.getEntity();
            String uuid = p.getUniqueId().toString();
            if (SmashMelee.getPlugin().getConfig().getBoolean("GameActive", true)) {
                int stock = SmashMelee.getPlugin().getConfig().getInt("Players." + uuid + ".Stock");
                SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Stock", (stock - 1));
                SmashMelee.getPlugin().saveConfig();
                if (SmashMelee.getPlugin().getConfig().getInt("Players." + uuid + ".Stock") == 0) {
                    p.sendRawMessage("Out of stock");
                }
            }
        }
    }



}
