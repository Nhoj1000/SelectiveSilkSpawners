package io.github.nhoj1000.selectivesilkspawners;

import io.github.nhoj1000.selectivesilkspawners.spawnerbreakevent.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class OnSpawnerBreak implements Listener {
    SelectiveSilkSpawners plugin = SelectiveSilkSpawners.getPlugin();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block blockBroken = e.getBlock();
        if(blockBroken.getType().equals(Material.SPAWNER) && e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            PersistentDataContainer container = ((TileState) blockBroken.getState()).getPersistentDataContainer();
            if(container.getOrDefault(new NamespacedKey(plugin, "silkable"), PersistentDataType.INTEGER, 0) == 1) {
                Bukkit.getServer().getPluginManager().callEvent(new SpawnerBreakEvent(e.getPlayer(), blockBroken));
                //prevents exp duplication
                e.setExpToDrop(0);
            }
        }
    }
}
