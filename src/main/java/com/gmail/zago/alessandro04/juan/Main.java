package com.gmail.zago.alessandro04.juan;

import com.gmail.zago.alessandro04.juan.Configurazione.Config;
import com.gmail.zago.alessandro04.juan.Utilita.Utility;
import com.gmail.zago.alessandro04.juan.commands.JuanSpawnCommand;
import com.gmail.zago.alessandro04.juan.events.HorseEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public ConsoleCommandSender msg = getServer().getConsoleSender();

    public YamlConfiguration ConfigMessage = new YamlConfiguration();

    public PluginDescriptionFile pdFile = getDescription();
    public String errore = ChatColor.DARK_RED + "ERROR ";
    public String prefisso = ChatColor.GRAY + "[" + ChatColor.RED + pdFile.getName() + ChatColor.GRAY + "] ";
    public String Message = ConfigMessage.getString("No-Perms-Message");

    public JuanSpawnCommand JS = new JuanSpawnCommand(this);
    //collegamento a Config.java per far config ultra custom
    public Config cf = new Config(this);
    public Utility ut = new Utility(this);
    public Config messagesConfig = new Config(this);


    @Override
    public void onEnable() {
        msg.sendMessage(prefisso + ChatColor.GOLD + "===================================");
        msg.sendMessage(prefisso + ChatColor.DARK_GREEN + "Il plugin e stato abilitato");
        msg.sendMessage(prefisso + ChatColor.GOLD + "creatore:" + ChatColor.GREEN + "Maseterdragon0");
        msg.sendMessage(prefisso + ChatColor.GOLD + "Versione:" + ChatColor.GREEN + " 1.0.beta");
        msg.sendMessage(prefisso + ChatColor.GOLD + "===================================");
        cf.SetupConfigFile();
        cf.setupMessages();
        messagesConfig.ReloadConfig();
        getCommand("juan").setExecutor(new JuanSpawnCommand(this));

    }

    @Override
    public void onDisable() {
        msg.sendMessage(prefisso + ChatColor.GOLD + "===================================");
        msg.sendMessage(prefisso + ChatColor.RED + "Il plugin e stato Spento con successo");
        msg.sendMessage(prefisso + ChatColor.GOLD + "creatore:" + ChatColor.GREEN + "Maseterdragon0");
        msg.sendMessage(prefisso + ChatColor.GOLD + "Versione:" + ChatColor.GREEN + " 1.0.beta");
        msg.sendMessage(prefisso + ChatColor.GOLD + "===================================");
    }
}
