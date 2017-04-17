package us.davidiv.Smash.SSMMelee.Game;

//Smash crystals! Flying wool block that changes colors maybe?

import org.bukkit.event.EventHandler;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;

import java.util.Random;

import static us.davidiv.Smash.SSMMelee.Game.Time.getCurrentTime;

public class Smash {

    @EventHandler
    public void Spawn(UpdateEvent e) {

        if (e.getType() != UpdateType.SECOND) {return;}

        if (!spawnChance()) {return;}

        if (getCurrentTime() < 600) {return;}

        if (!spawnChance()) {return;}

        spawnSmash();

    }

    @EventHandler
    public void SmashHandler(UpdateEvent e) {

        if (e.getType() != UpdateType.TICK) {return;}

        handleSmash();

    }

    private void spawnSmash() {
        //Spawn smash
    }

    private boolean spawnChance() {
        Random random = new Random();
        int r = random.nextInt(20);

        if (r != 17) {return true;}
        else {return false;}
    }

    private void handleSmash() {
        //Smash movement
    }


}
