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
import us.davidiv.Smash.SSMMelee.Kit.CaveSpider.KitCaveSpider;
import us.davidiv.Smash.SSMMelee.Kit.Chicken.KitChicken;
import us.davidiv.Smash.SSMMelee.Kit.Creeper.KitCreeper;
import us.davidiv.Smash.SSMMelee.Kit.Skeleton.KitSkeleton;
import us.davidiv.Smash.SSMMelee.Kit.Slime.*;
import us.davidiv.Smash.SSMMelee.Kit.Spider.KitSpider;
import us.davidiv.Smash.SSMMelee.Kit.Spider.Needler;
import us.davidiv.Smash.SSMMelee.Kit.Spider.SpiderLeap;
import us.davidiv.Smash.SSMMelee.Kit.Squid.KitSquid;
import us.davidiv.Smash.SSMMelee.Kit.Wolf.KitWolf;
import us.davidiv.Smash.SSMMelee.Kit.Zombie.KitZombie;

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
        new KitSlime(this);
        new SlimeToss(this);
        new SlingSlime(this);
        new Overheat(this);
        new Grow(this);
        new HighJump(this);
        new SlimeSM(this);

        //Spider
        new KitSpider(this);
        new Needler(this);
        new SpiderLeap(this);


        //Creeper
        new KitCreeper(this);
        

        getCommand("gamestart").setExecutor(new GameStart(this));
        getCommand("gamestop").setExecutor(new GameStart(this));
        getCommand("stock").setExecutor(new GameStart(this));
        getCommand("kitspider").setExecutor(new KitSpider(this));
        getCommand("kitslime").setExecutor(new KitSlime(this));
        getCommand("kitcreeper").setExecutor(new KitCreeper(this));
        getCommand("kitchicken").setExecutor(new KitChicken(this));
        getCommand("kitsquid").setExecutor(new KitSquid(this));
        getCommand("kitwolf").setExecutor(new KitWolf(this));
        getCommand("kitskeleton").setExecutor(new KitSkeleton(this));
        getCommand("kitcavespider").setExecutor(new KitCaveSpider(this));
        getCommand("kitzombie").setExecutor(new KitZombie(this));

        Game.game.put("Game", false);

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
