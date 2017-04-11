package us.davidiv.Smash.SSMMelee.Events;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import java.util.ArrayList;

public class CustomDamageEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private DamageCause eventCause;
    private double initialDamage;
    private LivingEntity damageeEntity;
    private LivingEntity damagerEntity;
    private Projectile projectile;
    private boolean knockback;
    private boolean ignoreRate;
    private boolean ignoreArmor;
    private Player damageePlayer;
    private Player damagerPlayer;
    private Location knockbackOrigin;
    private String reason;

    private ArrayList<Double> damageMult = new ArrayList<>();
    private ArrayList<Double> damageMod = new ArrayList<>();
    private ArrayList<Double> knockbackMod = new ArrayList<>();

    public CustomDamageEvent(LivingEntity damagee, LivingEntity damager, Projectile projectile,
                             DamageCause cause, double damage, boolean knockback, boolean ignoreRate, boolean ignoreArmor, String reason){

        eventCause = cause;
        initialDamage = damage;

        damageeEntity = damagee;
        damagerEntity = damager;
        this.projectile = projectile;
        this.knockback = knockback;
        this.ignoreRate = ignoreRate;
        this.ignoreArmor = ignoreArmor;

        if(damageeEntity instanceof Player){
            damageePlayer = (Player) damageeEntity;
        }

        if(damagerEntity instanceof Player){
            damagerPlayer = (Player) damagerEntity;
        }
    }

    public DamageCause getCause(){
        return eventCause;
    }

    public Player getDamagerPlayer(){
        return damagerPlayer;
    }

    public Player getDamageePlayer(){
        return damageePlayer;
    }

    public void addMult(double mult){
        damageMult.add(mult);
    }

    public void addMod(double mod){
        damageMod.add(mod);
    }

    public void addKnockback(double kb){
        knockbackMod.add(kb);
    }

    public double getInitalDamage(){
        return initialDamage;
    }

    public double getDamage(){
        double damage = getInitalDamage();
        for(double d : damageMod){
            damage+=d;
        }

        for(double d : damageMult){
            damage*=d;
        }

        return damage;
    }

    public Projectile getProjectile(){
        return projectile;
    }

    public void setIgnoreArmor(boolean b){
        ignoreArmor = b;
    }

    public void setIgnoreRate(boolean b){
        ignoreRate = b;
    }

    public void setKnockback(boolean b){
        knockback = b;
    }

    public void setKnockbackOrigin(Location loc){
        knockbackOrigin = loc;
    }

    public void setReason(String s){
        reason = s;
    }

    public String getReason(){
        return reason;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public LivingEntity getDamageeEntity() {
        return damageeEntity;
    }
}

