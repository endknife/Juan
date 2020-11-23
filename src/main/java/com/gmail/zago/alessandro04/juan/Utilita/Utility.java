package com.gmail.zago.alessandro04.juan.Utilita;

import com.gmail.zago.alessandro04.juan.Main;
import org.bukkit.ChatColor;

public class Utility {
    private Main plugin;
    public Utility(Main plugin){
        this.plugin = plugin;
    }

    public String chatcolor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
