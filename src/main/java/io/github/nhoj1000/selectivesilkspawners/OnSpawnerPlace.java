package io.github.nhoj1000.selectivesilkspawners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.TileState;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class OnSpawnerPlace implements Listener {
    private final SelectiveSilkSpawners plugin = SelectiveSilkSpawners.getPlugin();

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();
        if(block.getType().equals(Material.SPAWNER)) {
            BlockState blockState = block.getState();

            //ensures the placed spawner can be silk touched
            TileState state = (TileState) blockState;
            PersistentDataContainer container = state.getPersistentDataContainer();
            container.set(new NamespacedKey(plugin, "silkable"), PersistentDataType.INTEGER, 1);

            //null pointer check
            if (e.getItemInHand().getItemMeta() == null) return;

            //gets entity spawning ID from persistentdatacontainer
            PersistentDataContainer mobContainer = e.getItemInHand().getItemMeta().getPersistentDataContainer();
            if(mobContainer.has(new NamespacedKey(plugin, "mobID"), PersistentDataType.STRING)) {
                String mobID = mobContainer.get(new NamespacedKey(plugin, "mobID"), PersistentDataType.STRING);
                System.out.println(mobID);
                CreatureSpawner cs = (CreatureSpawner) state;
                cs.setSpawnedType(EntityType.valueOf(mobID));
            }
            state.update();
        }
    }
}
