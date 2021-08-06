package io.github.nhoj1000.selectivesilkspawners.converterfunctions;

import io.github.nhoj1000.selectivesilkspawners.SelectiveSilkSpawners;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;



public class OnConverterClick implements Listener {
    SelectiveSilkSpawners plugin = SelectiveSilkSpawners.getPlugin();

    @EventHandler
    public void converterRightClick(PlayerInteractEvent e) {
        if(e.getHand() == EquipmentSlot.OFF_HAND) return;   //Handles double firing of event from both hands

        Player player = e.getPlayer();
        if(player.getInventory().getItemInMainHand().equals(SelectiveSilkSpawners.getConverterTool())) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block target = e.getClickedBlock();
                if (target.getType() != Material.SPAWNER) {
                    player.sendMessage(ChatColor.RED + "Not a spawner!");
                    return;
                }

                PersistentDataContainer container = ((TileState) target.getState()).getPersistentDataContainer();
                int silkable = container.getOrDefault(new NamespacedKey(plugin, "silkable"), PersistentDataType.INTEGER, 0);
                if (silkable == 1)
                    player.sendMessage("This spawner " + ChatColor.GREEN + "can " + ChatColor.WHITE + "be mined with silk touch");
                else
                    player.sendMessage("This spawner " + ChatColor.RED + "cannot " + ChatColor.WHITE + "be mined with silk touch");
            } else if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
                Block target = e.getClickedBlock();
                if (target.getType() != Material.SPAWNER) {
                    player.sendMessage(ChatColor.RED + "Not a spawner!");
                    return;
                }

                TileState state = ((TileState) target.getState());
                PersistentDataContainer container = state.getPersistentDataContainer();
                int silkable = container.getOrDefault(new NamespacedKey(plugin, "silkable"), PersistentDataType.INTEGER, 0);
                if (silkable == 0) {
                    container.set(new NamespacedKey(plugin, "silkable"), PersistentDataType.INTEGER, 1);
                    player.sendMessage("This spawner " + ChatColor.GREEN + "can now " + ChatColor.WHITE + "be mined with silk touch");
                } else {
                    container.set(new NamespacedKey(plugin, "silkable"), PersistentDataType.INTEGER, 0);
                    player.sendMessage("This spawner " + ChatColor.RED + "can no longer " + ChatColor.WHITE + "be mined with silk touch");
                }
                state.update();
                e.setCancelled(true);
            }
        }
    }

}
