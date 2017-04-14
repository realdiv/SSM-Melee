package us.davidiv.Smash.SSMMelee.Game;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CooldownManager {

    public static void cooldownMessage(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + cooldownBar() + "\"}");
        PacketPlayOutChat cooldown = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(cooldown);
    }

    private static String cooldownBar() {
        return null;
    }

}
