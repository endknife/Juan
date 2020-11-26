package com.gmail.zago.alessandro04.juan.Configurazione;

import com.gmail.zago.alessandro04.juan.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Config {

    private Main plugin;
    public Config(Main plugin) {
        this.plugin = plugin;
    }

    public void SetupConfigFile() {
        File file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) {
            plugin.msg.sendMessage(plugin.prefisso + plugin.errore + ChatColor.RED + "Config.yml non trovato, creazione di quello default.");
            try {
                plugin.saveDefaultConfig();
            } catch (Exception e) {
                e.printStackTrace();
                plugin.msg.sendMessage( plugin.prefisso + plugin.errore + ChatColor.RED + " Ce stato un errore con la creazione del config file!");
                return;
            }

        }
        plugin.getConfig();
        plugin.msg.sendMessage(plugin.prefisso + ChatColor.GREEN + "Config.yml caricato con successo");
    }

    public void ReloadConfig() {
        File file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) {

            plugin.msg.sendMessage(
                    plugin.prefisso + plugin.errore + ChatColor.RED + "Config.yml non trovato, creazione di quello default.");
            try {
                plugin.saveDefaultConfig();
            } catch (Exception e) {
                e.printStackTrace();
                plugin.msg.sendMessage(ChatColor.RED + " Ce stato un errore con la creazione del config file!");
                return;
            }
        }
        plugin.reloadConfig();
        plugin.msg.sendMessage(plugin.prefisso + ChatColor.GREEN + "Config.yml caricato con successo");
    }

    public void setupMessages() {
        File Message = new File(plugin.getDataFolder(), "Message.yml");
        if (!Message.exists()) {
            plugin.saveResource("Message.yml", false);
            plugin.msg.sendMessage(plugin.prefisso + plugin.errore + ChatColor.RED + "Message.yml non trovato, creazione di quello default.");
        }
        try {
            plugin.ConfigMessage.load(Message);
            plugin.msg.sendMessage(plugin.prefisso + ChatColor.GREEN + "Message.yml caricato con successo");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            this.plugin.msg.sendMessage(ChatColor.RED + " Ce stato un errore nel caricare il file Message.yml!");
        }
    }
    
}
