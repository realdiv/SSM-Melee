package us.davidiv.Smash.SSMMelee.Game;

//Class to handle objective and other good shit

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class Game implements CommandExecutor, Listener {
    public Game(SmashMelee plugin) {plugin.getServer().getPluginManager().registerEvents(this, plugin);}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ((Player) sender).sendRawMessage("DEBUG");
        if (sender instanceof Player) {
            if (!SmashMelee.getPlugin().getConfig().contains("GameActive")) {
                SmashMelee.getPlugin().getConfig().set("GameActive.", false);
                SmashMelee.getPlugin().saveConfig();
                ((Player) sender).sendRawMessage("Created GameActive config, please run the command again");
            } else {
                Player player = (Player) sender;
                SmashMelee.getPlugin().getConfig().set("GameActive.", true);
                SmashMelee.getPlugin().saveConfig();
            }
        }
        return true;
    }




}
