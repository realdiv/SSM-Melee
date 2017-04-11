package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Game.Game.setLiving;
import static us.davidiv.Smash.SSMMelee.Game.GameScoreboard.updateSmashScoreboard;
import static us.davidiv.Smash.SSMMelee.Game.Knockback.setKnockback;
import static us.davidiv.Smash.SSMMelee.Game.Stock.setStock;


public class GameStart implements CommandExecutor, Listener {
    public GameStart(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private Team team;
    private Scoreboard board;
    private int i = 16;
    private GameScoreboardManager GSM = new GameScoreboardManager(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "     SSM MELEE    ");

    String stockC;

    private static Boolean game;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("gamestart")) {
                game = true;
                Player p = ((Player) sender).getPlayer();
                ((Player) sender).sendRawMessage("Game Activated");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    setKnockback(player, 0);
                    setStock(player, 4);
                    setLiving(player, true);
                }
                updateSmashScoreboard();
            }

            if (cmd.getName().contentEquals("gamestop")) {
                game = false;
                Player p = ((Player) sender).getPlayer();
                ((Player) sender).sendRawMessage("Game Deactivated");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.setGameMode(GameMode.SURVIVAL);
                    Knockback.knockback.put(player, 0);
                    setStock(player, 0);
                    setLiving(player, false);
                    GSM.reset();
                    GSM.send(player);
                }
            }
        }
        return true;
    }

    public static Boolean getGame() {
        return game;
    }

    public static void setGame(Boolean active) {
        game = active;
    }

}
