package us.davidiv.Smash.SSMMelee.Kit.Slime;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

import static me.libraryaddict.disguise.DisguiseAPI.disguiseEntity;
import static me.libraryaddict.disguise.DisguiseAPI.getDisguise;

public class Grow implements Listener {
    public Grow(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private DisguiseAPI ds;
    private HashMap<Player, Integer>siz = new HashMap<Player, Integer>();

    @EventHandler
    public void exp(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {
            Kits kitP = Kit.kit.get(p);

            if (kitP != Kits.SLIME) {return;}

            if (p.getExp() < .999 && !p.isDead() && p.getGameMode() == GameMode.SURVIVAL) p.setExp((p.getExp() + .01f));

        }
    }

    @EventHandler
    public void sizeCheck(UpdateEvent e) {
        if (e.getType() != UpdateType.HALF) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {
            Kits kitP = Kit.kit.get(p);

            if (kitP != Kits.SLIME) {return;}

            int size = 1;
            if (p.getExp() > 0.8) size = 3;
            else if (p.getExp() > 0.55) size = 2;
            siz.put(p, size);
        }
    }

    @EventHandler
    public void overheatCheck(UpdateEvent e) {
        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {

            Kits kitP = Kit.kit.get(p);
            Boolean magmacube = Overheat.overheat.get(p);
            int s = siz.get(p);

            if (kitP != Kits.SLIME) {return;}

            MobDisguise magmamob = new MobDisguise(DisguiseType.MAGMA_CUBE);
            SlimeWatcher magma = (SlimeWatcher) magmamob.getWatcher();
            magmamob.setViewSelfDisguise(false);
            magma.setSize(s);

            MobDisguise slimemob = new MobDisguise(DisguiseType.SLIME);
            SlimeWatcher slime = (SlimeWatcher) slimemob.getWatcher();
            slimemob.setViewSelfDisguise(false);
            slime.setSize(s);

            Disguise disguise = getDisguise(p);
            DisguiseType dis = disguise.getType();

            if (dis != DisguiseType.MAGMA_CUBE && magmacube) {
                disguiseEntity(p, magmamob);
            }

            if (dis != DisguiseType.SLIME && !magmacube) {
                disguiseEntity(p, slimemob);
            }
        }
    }


    @EventHandler
    public void updateSize(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        for (Player p : Bukkit.getOnlinePlayers()) {
            Kits kitP = Kit.kit.get(p);

            if (kitP != Kits.SLIME) {return;}

            int s = siz.get(p);

            Disguise mob = getDisguise(p);
            SlimeWatcher slime = (SlimeWatcher) mob.getWatcher();

            mob.setShowName(true); mob.setKeepDisguiseOnPlayerDeath(true);

            if (slime.getSize() != s) {
                slime.setSize(s);
                disguiseEntity(p, mob);
            }
        }
    }

    @EventHandler
    public void resetExp(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity();
        Kits kitP = Kit.kit.get(p);
        if (kitP == Kits.SLIME) {
            p.setExp(0);
        }
    }


}
