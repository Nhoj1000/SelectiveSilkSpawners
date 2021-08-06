package io.github.nhoj1000.selectivesilkspawners;

import io.github.nhoj1000.selectivesilkspawners.converterfunctions.GetConverter;
import io.github.nhoj1000.selectivesilkspawners.converterfunctions.OnConverterClick;
import io.github.nhoj1000.selectivesilkspawners.spawnerbreakevent.SpawnerBreakListeners;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SelectiveSilkSpawners extends JavaPlugin {
    private static SelectiveSilkSpawners plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new OnSpawnerBreak(), this);
        getServer().getPluginManager().registerEvents(new OnSpawnerPlace(), this);
        getServer().getPluginManager().registerEvents(new SpawnerBreakListeners(), this);
        getServer().getPluginManager().registerEvents(new OnConverterClick(), this);

        getCommand("getSilkConverter").setExecutor(new GetConverter());
    }

    public static ItemStack getConverterTool(){
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta stickMeta = stick.getItemMeta();
        stickMeta.setDisplayName(ChatColor.GOLD + "Spawner Converter Stick");
        List<String> stickLore = new ArrayList<>();
        stickLore.add("Right Click to see if a spawner is silk-able");
        stickLore.add("Left Click to toggle silk-ability");
        stickMeta.setLore(stickLore);
        stick.setItemMeta(stickMeta);
        return stick;
    }

    public static SelectiveSilkSpawners getPlugin(){
        return plugin;
    }
}
