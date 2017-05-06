package us.davidiv.Smash.SSMMelee.Game;

//Knockback multiplier - take a look at the numbers MP uses, it seems to be pretty balanced
//TODO CHECK IF GAMEACTIVE = TRUE

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Game.GameStart.getGame;
import static us.davidiv.Smash.SSMMelee.Utils.UtilKnockback.meleeKnockback;

public class Knockback implements Listener {
    public Knockback(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player, Integer> knockback = new HashMap<Player, Integer>();

    @EventHandler
    public void Knockback(EntityDamageByEntityEvent e) {

        if (!getGame()) {return;}

        Player p = (Player) e.getEntity();
        Entity d = e.getDamager();

        double damage = e.getDamage();
        int dI = (int) damage;

        if ((getKnockback(p) + dI) >= 999) { setKnockback(p, 999);}
        else {addKnockback(p, dI);}

        e.setDamage(0);
        e.setCancelled(true);

        meleeKnockback(p, d);
        playDamageAnimation(p);

    }

    @EventHandler
    public void KBFire(EntityDamageEvent e) {
        if (!getGame()) {return;}
            //do things
    }

    //TODO RE-ADD HIT EFFECT WITH PACKETS


    @EventHandler
    public void KBReset(PlayerDeathEvent e) {
        Player p = e.getEntity();
        setKnockback(p, 0);
    }

    public static Integer getKnockback(Player p) {
        return knockback.get(p);
    }

    public static void setKnockback(Player p, Integer multiplier) {
        knockback.put(p, multiplier);
    }

    private void addKnockback(Player p, Integer add) {
        knockback.put(p, getKnockback(p) + add);
    }

    public static void playDamageAnimation(Player player) {
        PacketContainer entityStatus = new PacketContainer(Packets.Server.ENTITY_STATUS);

        entityStatus.getIntegers().write(0, player.getEntityId());
        entityStatus.getBytes().write(0, (byte) 2);
        for (Player p : player.getWorld().getPlayers()) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(p, entityStatus);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        }
    }

}
