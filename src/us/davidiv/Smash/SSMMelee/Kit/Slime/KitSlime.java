package us.davidiv.Smash.SSMMelee.Kit.Slime;

/*

Kite Playstyle (EVA, DEF)
High attack, (x)high regen, low armor/kb resistance, exceptional evasion

*/

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

public class KitSlime implements CommandExecutor, Listener {
    public KitSlime(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static DisguiseAPI ds;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Kit.kitM(p, Kits.SLIME, "Slime Toss", "Sling Slime", Material.IRON_SWORD, Material.IRON_AXE);
        p.setExp(0);
        Overheat.overheat.put(p, false);
        Overheat.oh.put(p, 0.0);
        MobDisguise mob = new MobDisguise(DisguiseType.SLIME);
        SlimeWatcher slime = (SlimeWatcher) mob.getWatcher();
        //MobDisguise mobF = new MobDisguise(DisguiseType.MAGMA_CUBE);
        //SlimeWatcher magma = (SlimeWatcher) mobF.getWatcher();
        mob.setShowName(true);
        mob.setViewSelfDisguise(false);
        mob.setKeepDisguiseOnPlayerDeath(true);
        //mobF.setShowName(true);
        //mobF.setViewSelfDisguise(false);
        //mobF.setKeepDisguiseOnPlayerDeath(true);
        ds.disguiseEntity(p, mob);
        /*new BukkitRunnable() {
            public void run() {
                String kitP = Kit.kit.get(p);
                if (kitP.equals("Slime")) {
                    if (p.getExp() < .999 && !p.isDead() && p.getGameMode() == GameMode.SURVIVAL)
                    p.setExp((p.getExp() + .01f));
                } else {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(SmashMelee.getPlugin(), 0L, 1L);
        new BukkitRunnable() {
            public void run() {
                String kitP = Kit.kit.get(p);
                    Boolean magmacube = Overheat.overheat.get(p);
                    if (kitP.equals("Slime") && magmacube) {

                        int size = 1;
                        if (p.getExp() > 0.8) size = 3;
                        else if (p.getExp() > 0.55) size = 2;

                        if (magma.getSize() != size) {
                            magma.setSize(size);
                            ds.disguiseEntity(p, mobF);
                        }


                    } else if (kitP.equals("Slime") && !magmacube) {

                        int size = 1;
                        if (p.getExp() > 0.8) size = 3;
                        else if (p.getExp() > 0.55) size = 2;

                        if (slime.getSize() != size) {
                            slime.setSize(size);
                            ds.disguiseEntity(p, mob);
                        }

                } else { cancel(); }
            }
        }.runTaskTimer(SmashMelee.getPlugin(), 0L, 10L); */
        return true;
    }

    //TODO DONT LET BUKKITRUNNABLES STACK ON /KITSLIME COMMAND


}
