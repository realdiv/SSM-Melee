package us.davidiv.Smash.SSMMelee.Game;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.HashMap;

public class Respawn implements Listener {
    public Respawn(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static HashMap<Player, Integer> time = new HashMap<>();

    public static void respawn(Player p) {
        setRespawnTime(p, 5);
        p.setHealth(20.0);
        p.teleport(new Location(Bukkit.getWorld("HyruleCastle"), -27.5, 30.0, 19.5));

        timer(p);

        p.setGameMode(GameMode.SPECTATOR);
        new BukkitRunnable() {
            public void run() {
                timer(p);
                if (getRespawnTime(p) <= 0) {
                    timer(p);
                    revive(p);
                    cancel();
                }
                addRespawnTime(p, -1);
            }
        }.runTaskTimer(SmashMelee.getPlugin(), 0L, 20L);
    }

    private static void revive(Player p) {
        p.setGameMode(GameMode.SURVIVAL);

        //tp to spawnpoint of map
        p.teleport(new Location(Bukkit.getWorld("HyruleCastle"), -27.5, 30.0, 19.5));

        p.setAllowFlight(false);
        p.setFlying(false);
    }

    private static void timer(Player p) {
        CraftPlayer player = (CraftPlayer) p;
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ChatColor.GREEN + getRespawnTime(p) + "\"}"), 0, 20, 0);
        player.getHandle().playerConnection.sendPacket(title);
    }

    private static Integer getRespawnTime(Player p) {
        return time.get(p);
    }

    private static void addRespawnTime(Player p, Integer add) {
        setRespawnTime(p, (getRespawnTime(p) + add));
    }

    private static void setRespawnTime(Player p, Integer seoonds) {
        time.put(p, seoonds);
    }
}




