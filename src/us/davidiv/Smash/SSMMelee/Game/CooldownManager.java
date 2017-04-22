package us.davidiv.Smash.SSMMelee.Game;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static us.davidiv.Smash.SSMMelee.Utils.UtilMath.rt2d;

public class CooldownManager {

    private HashMap<Player, Integer> player = new HashMap<>();
    private HashMap<Integer, String> ability = new HashMap<>();
    private HashMap<Integer, Double> time = new HashMap<>();
    private HashMap<Player, HashMap<String, Double>> cooldown = new HashMap<>();
    private Integer total;

    public CooldownManager() {
    }

    public void register(Player p, String abilityname, Double cd) {
        total++;
        HashMap<String, Double> reg = new HashMap<>();
        reg.put(abilityname, cd);
        cooldown.put(p, reg);
    }

    public Double getTime(Player p, String ability) {
        return time.get(player.get(p));
    }


    public static void cooldown(Player p, Double cooldown, Double total, String ability) {
        cooldownMessage(p, cooldown, total, ability);
    }

    public static void cooldownEnd(Player p, String ability) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.GREEN + ability + " has recharged!" + "\"}");
        PacketPlayOutChat cooldown = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(cooldown);
    }

    private static void cooldownMessage(Player p, Double time, Double total, String ability) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + cooldownBar(time, total, ability) + "\"}");
        PacketPlayOutChat cooldown = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(cooldown);
    }

    private static String cooldownBar(Double time, Double total, String ability) {
        String bar;
        String prog = "";

        double percentage = ((time * 100)/total);
        double green = (percentage/2);
        double red = ((100 - percentage)/2);

        for (int c = (int) green; c > 0; c--) {prog = prog + (ChatColor.GREEN + "|");}
        for (int c = (int) red; c > 0; c--) {prog = prog + (ChatColor.RED + "|");}

        bar = (ability + ChatColor.GREEN + " " + prog + " " + ChatColor.WHITE + rt2d(time));

        return bar;
    }

}
