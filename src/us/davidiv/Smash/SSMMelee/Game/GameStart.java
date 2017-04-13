package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import java.util.ArrayList;
import java.util.Random;

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
    private static Boolean teams;

    public static ArrayList<Player>blue = new ArrayList<>();
    public static ArrayList<Player>red = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("start")) gameActivate(false);
                else if (args[0].contentEquals("stop")) gameDeactivate();
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("start") && args[1].equalsIgnoreCase("teams")) gameActivate(true);
                else if (args[0].equalsIgnoreCase("start") && args[1].equalsIgnoreCase("solo")) gameActivate(false);
            }

        }

        return true;
    }

    public static Boolean getGame() {
        return game;
    }

    public static Boolean getTeams() {
        return teams;
    }

    public static void setGame(Boolean active) {
        game = active;
    }

    private void gameActivate(boolean t) {
        if (t) {
            game = true;
            teams = true;
            Bukkit.broadcastMessage("[" + ChatColor.GREEN + "SSM-Melee" + ChatColor.WHITE + "] teams game started!");
            for (Player player : Bukkit.getOnlinePlayers()) {
                setKnockback(player, 0);
                setStock(player, 4);
                setLiving(player, true);

                Random r = new Random();
                int team = r.nextInt(2);

                if (blue.size() == red.size()) {
                    if (team == 1) blue.add(player);
                    if (team == 2) red.add(player);
                }
                else if (blue.size() < red.size()) {
                    blue.add(player);
                }
                else if (blue.size() > red.size()) {
                    red.add(player);
                }
            }
            updateSmashScoreboard();
        }
        if (!t) {
            game = true;
            teams = false;
            Bukkit.broadcastMessage("[" + ChatColor.GREEN + "SSM-Melee" + ChatColor.WHITE + "] solo game started!");
            for (Player player : Bukkit.getOnlinePlayers()) {
                setKnockback(player, 0);
                setStock(player, 4);
                setLiving(player, true);
            }
            updateSmashScoreboard();
        }
    }

    private void gameDeactivate() {
        game = false;
        teams = null;
        Bukkit.broadcastMessage("[" + ChatColor.GREEN + "SSM-Melee" + ChatColor.WHITE + "] game ended!");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (red.contains(player)) red.remove(player);
            if (blue.contains(player)) blue.remove(player);
            player.setGameMode(GameMode.SURVIVAL);
            Knockback.knockback.put(player, 0);
            setStock(player, 0);
            setLiving(player, false);
            GSM.reset();
            GSM.send(player);
        }
    }

    public static ArrayList<Player> getBlueTeam() {
        return blue;
    }

    public static ArrayList<Player> getRedTeam() {
        return red;
    }

}
