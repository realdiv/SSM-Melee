package us.davidiv.Smash.SSMMelee.Game;

//KitSpider manager for making stats easy

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.davidiv.Smash.SSMMelee.Kit.Kits;

import java.util.HashMap;

public class Kit {

    public static HashMap<Player, Kits>kit  = new HashMap<Player, Kits>();


    public static void kitM(Player p, Kits kitName, String pName, String sName, Material primaryItem, Material secondaryItem) {
        Kit.kit.put(p, kitName);
        ItemStack primary = new ItemStack(primaryItem);
        ItemMeta imP = primary.getItemMeta();
        imP.setDisplayName(pName);
        primary.setItemMeta(imP);
        ItemStack secondary = new ItemStack(secondaryItem);
        ItemMeta imS = secondary.getItemMeta();
        imS.setDisplayName(sName);
        secondary.setItemMeta(imS);
        ItemStack cmp = new ItemStack(Material.COMPASS);
        p.getInventory().clear();
        p.getInventory().addItem(primary, secondary, cmp);
    }

}
