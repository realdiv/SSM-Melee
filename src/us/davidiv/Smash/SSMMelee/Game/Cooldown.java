package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Events.UpdateEvent;
import us.davidiv.Smash.SSMMelee.Events.UpdateType;

public class Cooldown implements Listener {

    //public static CooldownManager CDM = new CooldownManager();

    @EventHandler
    public void cooldown(UpdateEvent e) {

        if (e.getType() != UpdateType.SECOND) {return;}

    }


}
