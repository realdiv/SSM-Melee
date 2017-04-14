package us.davidiv.Smash.SSMMelee;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;
import us.davidiv.Smash.SSMMelee.Game.*;
import us.davidiv.Smash.SSMMelee.Game.Void;
import us.davidiv.Smash.SSMMelee.Kit.Slime.*;
import us.davidiv.Smash.SSMMelee.Kit.Spider.Needler;
import us.davidiv.Smash.SSMMelee.Kit.Spider.SpiderLeap;
import us.davidiv.Smash.SSMMelee.Kit.Spider.Vortex;

import static us.davidiv.Smash.SSMMelee.Game.GameStart.setGame;

public class SmashMelee extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        startUpdateTick();
        startUpdateHalf();
        startUpdateSecond();

        //Random
        new JoinMessage(this);
        new BlockProtection(this);
        new StockCommand(this);

        //Game Mechanics
        new DoubleJump(this);
        new Knockback(this);
        new Stock(this);
        new Game(this);
        new GameScoreboard(this);
        new Respawn(this);
        new Void(this);
        new Stun(this);

        //Slime
        new SlimeToss(this);
        new SlingSlime(this);
        new Overheat(this);
        new Grow(this);
        new HighJump(this);
        new SlimeSM(this);

        //Spider
        new Needler(this);
        new SpiderLeap(this);
        new Vortex(this);

        getCommand("game").setExecutor(new GameStart(this));
        getCommand("stock").setExecutor(new StockCommand(this));
        getCommand("kit").setExecutor(new KitCommand(this));

        setGame(false);

    }

    public static Plugin getPlugin() {
        return plugin;
    }

    private void startUpdateTick(){
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.TICK));
            }
        }.runTaskTimer(this, 0L, 1L);
    }

    private void startUpdateHalf(){
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.HALF));
            }
        }.runTaskTimer(this, 0L, 10L);
    }

    private void startUpdateSecond(){
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateType.SECOND));
            }
        }.runTaskTimer(this, 0L, 20L);
    }

}
