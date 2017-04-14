package us.davidiv.Smash.SSMMelee.Game;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.davidiv.Smash.SSMMelee.SmashMelee;

import static us.davidiv.Smash.SSMMelee.Kit.CaveSpider.KitCaveSpider.CaveSpider;
import static us.davidiv.Smash.SSMMelee.Kit.Chicken.KitChicken.Chicken;
import static us.davidiv.Smash.SSMMelee.Kit.Creeper.KitCreeper.Creeper;
import static us.davidiv.Smash.SSMMelee.Kit.Skeleton.KitSkeleton.Skeleton;
import static us.davidiv.Smash.SSMMelee.Kit.Slime.KitSlime.Slime;
import static us.davidiv.Smash.SSMMelee.Kit.Spider.KitSpider.Spider;
import static us.davidiv.Smash.SSMMelee.Kit.Squid.KitSquid.Squid;
import static us.davidiv.Smash.SSMMelee.Kit.Wolf.KitWolf.Wolf;
import static us.davidiv.Smash.SSMMelee.Kit.Zombie.KitZombie.Zombie;

public class KitCommand implements CommandExecutor {
    public KitCommand(SmashMelee plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("kit")) {
            Player p = (Player) sender;

                if(args.length == 0 ) {
                    p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.WHITE + "Cave Spider, Chicken, Creeper, Skeleton, Slime, Spider, Squid, Wolf & Zombie!");
                    p.sendMessage(" ");
                    p.sendMessage(ChatColor.RED + "Select your kit by doing /kit <kit>");
                }

                if(args.length == 1) {
                    if (args[0].equalsIgnoreCase("Skeleton")) {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Skeleton!");
                        Skeleton(p);
                    }

                    if (args[0].equalsIgnoreCase("Spider"))  {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Spider!");
                        Spider(p);
                    }

                    if (args[0].equalsIgnoreCase("Slime"))  {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Slime!");
                        Slime(p);
                    }

                    if (args[0].equalsIgnoreCase("Squid"))  {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Squid!");
                        Squid(p);
                    }

                    if (args[0].equalsIgnoreCase("Creeper"))  {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Creeper!");
                        Creeper(p);
                    }

                    if (args[0].equalsIgnoreCase("Wolf")) {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Wolf!");
                        Wolf(p);
                    }

                    if (args[0].equalsIgnoreCase("Zombie")) {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Zombie!");
                        Zombie(p);
                    }

                    if (args[0].equalsIgnoreCase("Chicken") || args[0].equalsIgnoreCase("Chick")) {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Chicken!");
                        Chicken(p);
                    }

                    if (args[0].equalsIgnoreCase("CaveSpider") || args[0].equalsIgnoreCase("Cave_Spider") || args[0].equalsIgnoreCase("1041")) {
                        p.sendMessage(ChatColor.GREEN + "You've equipped Cave Spider!");
                        CaveSpider(p);
                    }

                }

                else if (args.length >= 3) {
                    p.sendMessage(ChatColor.RED + "Did you mean /kit <kit>?");
                }
        }
        return true;
    }
}
