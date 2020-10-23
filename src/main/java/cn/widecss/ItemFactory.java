package cn.widecss;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemFactory {

    public static ItemStack getNiceDiamondSword() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.addEnchantment(Enchantment.DAMAGE_ALL, 10);
        item.addEnchantment(Enchantment.DURABILITY, 10);
        return item;
    }

    public static ItemStack getASetOfZombieSpawnEgg() {
        ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
        item.setAmount(64);
        return item;
    }
}
