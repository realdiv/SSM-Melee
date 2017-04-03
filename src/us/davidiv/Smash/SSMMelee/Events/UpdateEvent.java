package us.davidiv.Smash.SSMMelee.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private static UpdateType type;

    public UpdateEvent(UpdateType type){
        this.type = type;
    }

    public UpdateType getType(){
        return type;
    }

    public HandlerList getHandlers(){
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
