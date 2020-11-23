package com.gmail.zago.alessandro04.juan.commands;

import com.gmail.zago.alessandro04.juan.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LetherCommand implements CommandExecutor {

    private Main plugin;
    public LetherCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("uno")){

                }
            }
        }else{
            System.out.println("null");
        }

        return true;
    }
}
