package us.davidiv.Smash.SSMMelee;

import org.bukkit.ChatColor;

public class Message {
    public enum MessageType {
        GAME, DEBUG, KILL, DEATH, WINNER, DEAD, CHAT
    }

    private static String format;

    public static String msg(MessageType type, String message) {
        if (type == MessageType.GAME) format = ("[" + ChatColor.GREEN + "SSM-Melee" + ChatColor.WHITE + "] " + message);
        else if (type == MessageType.DEBUG) format = ("[" + ChatColor.RED + "Debug" + "] " + message);
        return format;
    }

}
