package io.github.nhoj1000.selectivesilkspawners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
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

            if(blockState instanceof TileState) {
                TileState state = (TileState) blockState;
                PersistentDataContainer container = state.getPersistentDataContainer();
                container.set(new NamespacedKey(plugin, "silkable"), PersistentDataType.INTEGER, 1);
                state.update();
            }
        }
    }
}
