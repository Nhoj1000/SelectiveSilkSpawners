package io.github.nhoj1000.selectivesilkspawners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetConverter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
            ((Player) sender).getInventory().addItem(SelectiveSilkSpawners.getConverterTool());
        return true;
    }
}
