package us.davidiv.Smash.SSMMelee;

import org.bukkit.plugin.java.JavaPlugin;

import us.davidiv.Smash.SSMMelee.Game.DoubleJump;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SmashMelee extends JavaPlugin {

    private static Plugin plugin; // <-- Where you store an instance of your main class
    @Override
    public void onEnable() {
        plugin = this;
        new DoubleJump(this);


    }



    //TO-DO Create scoreboard for team objective tracking
    //TO-DO Create command that starts the game(s)
    //TO-DO Add team functions
    //TO-DO Add separate spawn-points for teams
    //TO-DO Add grenades(?)
    //TO-DO Change Double Jump's velocity to be more similar to MPs
    //TO-DO Nullify damage on Arrows if the player who shot it is dead
    //Finished Fix shot arrows acting like Arrow Point
    //Finished Add cool-down on Arrow Point
    //Finished Add extra Arrow Point
    //Finished Cancel item drops
    //Finished Cancel item damage
    //Finished Fix hunger bar


    public static Plugin getPlugin() {
        return plugin;
    }

}
