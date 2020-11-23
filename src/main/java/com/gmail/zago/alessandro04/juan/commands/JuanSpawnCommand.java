package com.gmail.zago.alessandro04.juan.commands;

import com.gmail.zago.alessandro04.juan.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class JuanSpawnCommand implements CommandExecutor {
    private Main plugin;
    public JuanSpawnCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (p.hasPermission("juan.first.juan")){

                Horse horse = p.getWorld().spawn(p.getLocation(), Horse.class);

                if (plugin.getConfig().getBoolean("First-Horse-NameVisible")){
                    horse.setCustomName(plugin.ut.chatcolor(plugin.getConfig().getString("First-Horse-Name")).replace("%first-horse-name%",p.getName()));
                    horse.setCustomNameVisible(true);
                }else{
                    horse.setCustomName("Joan");
                    horse.setCustomNameVisible(true);
                }
                if (plugin.getConfig().getBoolean("First-Horse-Color")){
                    horse.setColor(Horse.Color.valueOf(plugin.getConfig().getString("Horse-Color")));
                }

                if (plugin.getConfig().getBoolean("First-Horse-Give-Armor-Toggle")){
                    String ArmorNull = plugin.getConfig().getString("First-Horse-Give-Armor");
                    horse.getInventory().setArmor(new ItemStack(Material.valueOf(ArmorNull)));
                }
                if (plugin.getConfig().getBoolean("First-Horse-Mount")){
                    horse.addPassenger(p);
                }
                if (plugin.getConfig().getBoolean("First-Horse-Silent")){
                    horse.setSilent(true);
                }
                if (plugin.getConfig().getBoolean("First-Horse-JumpStrength")){
                    horse.setJumpStrength(plugin.getConfig().getDouble("First-Horse-Jump"));
                }
                if (plugin.getConfig().getBoolean("First-Horse-Invincible")){
                    horse.setInvulnerable(true);
                }
                if (plugin.getConfig().getBoolean("First-Horse-Glow")){
                    horse.setGlowing(true);
                }
                if (plugin.getConfig().getBoolean("First-Horse-MaxHealth")){
                    horse.setMaxHealth(plugin.getConfig().getInt("First-Horse-SetMaxHealth"));
                }

                horse.setTamed(true);
                horse.setAdult();
                horse.setOwner(p.getPlayer());
                horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                Vector vel = new Vector(0, 0, 0);
                horse.setVelocity(vel);
            }else{
                p.sendMessage(plugin.ut.chatcolor(plugin.ConfigMessage.getString("No-Perms-Message")));
                return true;
            }
        }else{
            System.out.println("This command can be used only from a player");
        }
        return false;
    }
}
