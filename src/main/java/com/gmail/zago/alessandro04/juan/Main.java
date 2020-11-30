package com.gmail.zago.alessandro04.juan;

import com.gmail.zago.alessandro04.juan.Configurazione.Config;
import com.gmail.zago.alessandro04.juan.Utilita.Utility;
import com.gmail.zago.alessandro04.juan.commands.JuanSpawnCommand;
import com.gmail.zago.alessandro04.juan.events.HorseEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Horse;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


/** The main class of the plugin. */
public final class Main extends JavaPlugin {

    public static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;



    public ConsoleCommandSender msg = getServer().getConsoleSender();

    public YamlConfiguration ConfigMessage = new YamlConfiguration();

    public PluginDescriptionFile pdFile = getDescription();
    public String errore = ChatColor.DARK_RED + "ERROR ";
    public String prefisso = ChatColor.GRAY + "[" + ChatColor.RED + pdFile.getName() + ChatColor.GRAY + "] ";
    public String Message = ConfigMessage.getString("No-Perms-Message");

    public JuanSpawnCommand JS = new JuanSpawnCommand(this);
    //collegamento a Config.java per far config ultra customChanged code - Still not working - Beta 1.1
    public Config cf = new Config(this);
    public Utility ut = new Utility(this);
    public Config messagesConfig = new Config(this);


    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Not able to detect Vault.", getDescription().getName()));
        }
        msg.sendMessage(prefisso + ChatColor.GOLD + "<<=========================>>");
        msg.sendMessage(prefisso + ChatColor.DARK_GREEN + "   The plugin is starting");
        msg.sendMessage(prefisso + ChatColor.YELLOW + "   Creator: " + ChatColor.LIGHT_PURPLE + "Zago");
        msg.sendMessage(prefisso + ChatColor.YELLOW + "   Co-Creator: " + ChatColor.LIGHT_PURPLE + "Stephirio");
        msg.sendMessage(prefisso + ChatColor.YELLOW + "   Version: " + ChatColor.RED + "beta");
        msg.sendMessage(prefisso + ChatColor.GOLD + "<<=========================>>");
        cf.SetupConfigFile();
        cf.setupMessages();
        JS.saveDefaultConfig();
        messagesConfig.ReloadConfig();
        getCommand("juan").setExecutor(new JuanSpawnCommand(this));
        getServer().getPluginManager().registerEvents(new HorseEvent(this), this);

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }



    /** Makes the vault economy API available for other classes */
    public Economy vaultEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        assert rsp != null;
        return rsp.getProvider();
    }

}
