package us.davidiv.Smash.SSMMelee;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessage implements Listener {
    public JoinMessage(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

        @EventHandler
        public void JoinMessage (PlayerJoinEvent e){
            Player p = e.getPlayer();
            String name = p.getName();
            if (name.equals("Davidiv")) {
                e.setJoinMessage(ChatColor.AQUA + "Welcome to your test server, " + name + "!");
            } else {
                e.setJoinMessage(ChatColor.AQUA + "Welcome to Davidiv's test server, " + name + "!");

            }
        }

        @EventHandler
        public void Chat(AsyncPlayerChatEvent e) {
            Player p = e.getPlayer();
            String name = p.getName();
            String msg = e.getMessage();
            if (name.equals("Davidiv")) {
                e.setMessage(ChatColor.LIGHT_PURPLE.BOLD + "DEV " + ChatColor.GRAY + name + " " + ChatColor.WHITE + msg);
            } else {
                e.setMessage(ChatColor.AQUA.BOLD + "RANDY " + ChatColor.GRAY + name + " " + ChatColor.WHITE + msg);
            }
        }

}
