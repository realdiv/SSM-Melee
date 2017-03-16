package us.davidiv.Smash.SSMMelee.Game;

//Knockback multiplier - take a look at the numbers MP uses, it seems to be pretty balanced
//TODO CHECK IF GAMEACTIVE = TRUE

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Knockback implements Listener {
    public Knockback(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    int kb = 0;
    int dI = 0;

    public Knockback() {
    }

    @EventHandler
    public void storePlayer(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();
        if (!SmashMelee.getPlugin().getConfig().contains("Players." + uuid)) {
            SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Knockback", 0);
            SmashMelee.getPlugin().saveConfig();
        } else {
            SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Knockback", 0);
            SmashMelee.getPlugin().saveConfig();
        }
    }

    @EventHandler
    public void Knockback(EntityDamageByEntityEvent e) {
        boolean dmgM;
        dmgM = false;
        Entity p = e.getEntity();
        Entity d = e.getDamager();
        String uuid = p.getUniqueId().toString();
        double damage = e.getDamage();
        int dI = (int) damage;
        if ((SmashMelee.getPlugin().getConfig().getInt("Players." + uuid + ".Knockback") + dI) >= 999) {
            SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Knockback", 999);
            kb = SmashMelee.getPlugin().getConfig().getInt("Players." + uuid + ".Knockback");
            //((Player) p).sendRawMessage("Knockback is " + kb);
            //((Player) p).sendRawMessage("Damage Int is " + dI);
        } else {
            SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Knockback",
                    (SmashMelee.getPlugin().getConfig().getInt("Players." + uuid + ".Knockback") + dI));
            kb = SmashMelee.getPlugin().getConfig().getInt("Players." + uuid + ".Knockback");
            //((Player) p).sendRawMessage("Knockback is " + kb);
            //((Player) p).sendRawMessage("Damage Int is " + dI);
        }
        SmashMelee.getPlugin().saveConfig();
        if (p instanceof Player) {
            p.setVelocity(d.getLocation().getDirection().multiply((kb / 100) + 0.5));
            dmgM = true;

        }
        if (dmgM == true){
            e.setDamage(0);
        }
    }

    @EventHandler
        public void KBReset(PlayerDeathEvent e) {
            Player p = e.getEntity();
            if (p instanceof Player) {
                String uuid = p.getUniqueId().toString();
                SmashMelee.getPlugin().getConfig().set("Players." + uuid + ".Knockback", 0);
                SmashMelee.getPlugin().saveConfig();
            }
    }

}
