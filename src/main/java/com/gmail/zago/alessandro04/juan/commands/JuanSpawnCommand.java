package com.gmail.zago.alessandro04.juan.commands;

import com.gmail.zago.alessandro04.juan.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JuanSpawnCommand implements CommandExecutor {
    private Main plugin;
    private File configFile = null;
    private FileConfiguration dataConfig = null;
    public JuanSpawnCommand(Main plugin){
        this.plugin = plugin;
    }


    public void reloadConfig() {
        if (configFile == null) configFile = new File(plugin.getDataFolder(), "horses.yml");
        dataConfig = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = plugin.getResource("horses.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataConfig.setDefaults(defaultConfig);
        }
    }


    public FileConfiguration getConfig() {
        if (dataConfig == null) reloadConfig();
        return dataConfig;
    }


    public void saveConfig() {
        if (dataConfig == null || configFile == null) return;
        try {
            getConfig().save(configFile);
        } catch (IOException e) {
            plugin.msg.sendMessage("Could not save the config to " + configFile);
        }
    }


    public void saveDefaultConfig() {
        if (configFile == null) configFile = new File(plugin.getDataFolder(), "horses.yml");
        if (!configFile.exists()) plugin.saveResource("horses.yml", false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (p.hasPermission("juan.first.juan")){

                Horse horse = p.getWorld().spawn(p.getLocation(), Horse.class);
                getConfig().set(String.valueOf(p.getUniqueId()), String.valueOf(horse.getEntityId()));
                saveConfig();
                reloadConfig();

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
