package us.davidiv.Smash.SSMMelee.Game;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static us.davidiv.Smash.SSMMelee.Utils.UtilMath.rt2d;

public class CooldownManager {

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

        double percentage = ((time*100)/total);

        bar = (ability + ChatColor.GREEN + " ||||||||||||||||||||||||| " + ChatColor.WHITE + time);
        if (percentage >= 96) bar = (ability + ChatColor.GREEN + " ||||||||||||||||||||||||" + ChatColor.RED + "| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 92) bar = (ability + ChatColor.GREEN + " |||||||||||||||||||||||" + ChatColor.RED + "|| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 88) bar = (ability + ChatColor.GREEN + " ||||||||||||||||||||||" + ChatColor.RED + "||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 84) bar = (ability + ChatColor.GREEN + " |||||||||||||||||||||" + ChatColor.RED + "|||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 80) bar = (ability + ChatColor.GREEN + " ||||||||||||||||||||" + ChatColor.RED + "||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 76) bar = (ability + ChatColor.GREEN + " |||||||||||||||||||" + ChatColor.RED + "|||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 72) bar = (ability + ChatColor.GREEN + " ||||||||||||||||||" + ChatColor.RED + "||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 68) bar = (ability + ChatColor.GREEN + " |||||||||||||||||" + ChatColor.RED + "|||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 64) bar = (ability + ChatColor.GREEN + " ||||||||||||||||" + ChatColor.RED + "||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 60) bar = (ability + ChatColor.GREEN + " |||||||||||||||" + ChatColor.RED + "|||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 56) bar = (ability + ChatColor.GREEN + " ||||||||||||||" + ChatColor.RED + "||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 52) bar = (ability + ChatColor.GREEN + " |||||||||||||" + ChatColor.RED + "|||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 48) bar = (ability + ChatColor.GREEN + " ||||||||||||" + ChatColor.RED + "||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 44) bar = (ability + ChatColor.GREEN + " |||||||||||" + ChatColor.RED + "|||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 40) bar = (ability + ChatColor.GREEN + " ||||||||||" + ChatColor.RED + "||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 36) bar = (ability + ChatColor.GREEN + " |||||||||" + ChatColor.RED + "|||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 32) bar = (ability + ChatColor.GREEN + " ||||||||" + ChatColor.RED + "||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 28) bar = (ability + ChatColor.GREEN + " |||||||" + ChatColor.RED + "|||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 24) bar = (ability + ChatColor.GREEN + " ||||||" + ChatColor.RED + "||||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 20) bar = (ability + ChatColor.GREEN + " |||||" + ChatColor.RED + "|||||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 16) bar = (ability + ChatColor.GREEN + " ||||" + ChatColor.RED + "||||||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 12) bar = (ability + ChatColor.GREEN + " |||" + ChatColor.RED + "|||||||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 8) bar = (ability + ChatColor.GREEN  + " ||" + ChatColor.RED + "||||||||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage >= 4) bar = (ability + ChatColor.GREEN  + " |" + ChatColor.RED + "|||||||||||||||||||||||| " + ChatColor.WHITE + rt2d(time));
        else if (percentage > 0) bar = (ability + ChatColor.RED + " ||||||||||||||||||||||||| " + ChatColor.WHITE + 0.0);

        return bar;
    }

}
