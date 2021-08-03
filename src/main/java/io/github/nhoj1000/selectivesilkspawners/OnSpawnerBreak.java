package io.github.nhoj1000.selectivesilkspawners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.PlayerInventory;

public class OnSpawnerBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(!e.getBlock().getType().equals(Material.SPAWNER)) return;

        Player p = e.getPlayer();
        PlayerInventory inventory = p.getInventory();
    }
}
