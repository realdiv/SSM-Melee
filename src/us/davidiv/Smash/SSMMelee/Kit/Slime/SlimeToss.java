package us.davidiv.Smash.SSMMelee.Kit.Slime;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.davidiv.Smash.SSMMelee.Game.Kit;
import us.davidiv.Smash.SSMMelee.Game.Knockback;
import us.davidiv.Smash.SSMMelee.Kit.Kits;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Game.GameScoreboard.updateSmashScoreboard;

/*
Slime Rocket rip-off xD!
 */

public class SlimeToss implements Listener {
    public SlimeToss(SmashMelee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private double exp = 0.999;
    private int l = 0;
    //Change to hashmap???

    public void thrown(Player p, Slime slime) {
        new BukkitRunnable() {
            public void run() {
                if (slime.isOnGround()) { cancel(); }
                for (Entity entity : slime.getNearbyEntities(2.0, 2.0, 2.0)) {
                    if (entity instanceof Player) {
                        if (entity != (Entity) p) {
                            Knockback.knockback.put((Player) entity, (Knockback.knockback.get((Player) entity) + 40));
                            entity.setVelocity(slime.getLocation().getDirection().multiply((Knockback.knockback.get((Player) entity) / 100) * 2));
                            updateSmashScoreboard();
                            cancel();
                        }
                    }
                }
            }
        }.runTaskTimer(SmashMelee.getPlugin(), 0L, 1L);
    }

    //TODO MAKE VELOCITY BASED OFF OF SLIME VELOCITY DIRECTION (?)
    //TODO MAKE KNOCKBACK BASED OFF OF SIZE
    //TODO ADD METHOD FOR MAGMA CUBES

    @EventHandler
    public void SlimeToss(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Kits kitName = Kit.kit.get(p);
        if (kitName == Kits.SLIME) {
            ItemStack i = e.getPlayer().getItemInHand();
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (i.getType() != null && i.getType() == Material.IRON_SWORD) {
                    new BukkitRunnable() {
                        public void run() {
                            Kits kitName = Kit.kit.get(p);
                            Boolean magmacube = Overheat.overheat.get(p);
                            if (kitName != Kits.SLIME) {
                                cancel();
                            }
                            if (p.isBlocking()) {
                                p.setExp((p.getExp() - .02f));
                                l++;
                                if (magmacube) {
                                    p.getWorld().playSound(p.getLocation(), Sound.MAGMACUBE_WALK, 0.5f, (float) (0.5 + 1.5 * ((l / 20) / 3d)));
                                } else {
                                    p.getWorld().playSound(p.getLocation(), Sound.SLIME_WALK, 0.5f, (float) (0.5 + 1.5 * ((l / 20) / 3d)));
                                }
                                exp = exp - .01;
                                if (exp < 0.01) {
                                    if (magmacube) {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), MagmaCube.class);
                                        slime.setSize(3);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                    } else {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), Slime.class);
                                        slime.setSize(3);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                        thrown(p, slime);
                                    }
                                }
                            }


                            if (!p.isBlocking() || p.getExp() < 0.01) {
                                if (magmacube) {
                                    if (exp < 0.2) {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), MagmaCube.class);
                                        slime.setSize(3);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                    } else if (exp < 0.5) {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), MagmaCube.class);
                                        slime.setSize(2);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                    } else if (exp < 0.999) {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), MagmaCube.class);
                                        slime.setSize(1);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                    }
                                } else {
                                    if (exp < 0.2) {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), Slime.class);
                                        slime.setSize(3);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                        thrown(p, slime);
                                    } else if (exp < 0.5) {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), Slime.class);
                                        slime.setSize(2);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                        thrown(p, slime);
                                    } else if (exp < 0.999) {
                                        Slime slime = p.getWorld().spawn(p.getLocation(), Slime.class);
                                        slime.setSize(1);
                                        slime.setVelocity(p.getLocation().getDirection().multiply(4));
                                        cancel();
                                        thrown(p, slime);
                                    }
                                }

                                cancel();
                            }


                        }
                    }.runTaskTimer((SmashMelee.getPlugin()), 0L, 1L);
                    exp = 0.999;
                    l = 0;
                    //for () For loop for a Bukkit Runnable FOR each Slime : (
                    //This will be aids!
                }
            }
        }
    }

}
