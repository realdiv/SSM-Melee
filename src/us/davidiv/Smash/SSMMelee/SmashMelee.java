package us.davidiv.Smash.SSMMelee;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import us.davidiv.Smash.SSMMelee.Game.*;

public class SmashMelee extends JavaPlugin {

    private static Plugin plugin; // <-- Where you store an instance of your main class
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;
        new DoubleJump(this);
        new Knockback(this);
        new JoinMessage(this);
        new Stock(this);
        new Game(this);
        getCommand("gamestart").setExecutor(new GameStart(this));
        getCommand("gamestop").setExecutor(new GameStart(this));

    }

    public static Plugin getPlugin() {
        return plugin;
    }

}
