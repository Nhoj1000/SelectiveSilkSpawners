package io.github.nhoj1000.selectivesilkspawners.spawnerbreakevent;

import io.github.nhoj1000.selectivesilkspawners.SelectiveSilkSpawners;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class SpawnerBreakListeners implements Listener {

    @EventHandler
    public void onSpawnerBreak(SpawnerBreakEvent e) {
        CreatureSpawner cs = (CreatureSpawner) e.getSpawner().getState();
        EntityType mob = cs.getSpawnedType();
        ItemStack spawnerToGive = new ItemStack(Material.SPAWNER);
        ItemMeta meta = spawnerToGive.getItemMeta();

        //sets item display name for aesthetics
        meta.setDisplayName(mob.name() + " Spawner");

        //stores mob id in persistentdatacontainer
        PersistentDataContainer mobStorage = meta.getPersistentDataContainer();
        mobStorage.set(new NamespacedKey(SelectiveSilkSpawners.getPlugin(), "mobID"), PersistentDataType.STRING, mob.name());

        spawnerToGive.setItemMeta(meta);
        e.getBreaker().getInventory().addItem(spawnerToGive);
    }
}
