package com.gmail.zago.alessandro04.juan.events;

import com.gmail.zago.alessandro04.juan.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;

public class HorseEvent implements Listener {

    private Main plugin;
    public HorseEvent(Main plugin){ this.plugin = plugin; }


    /** This event will be execute when you dismount the Horse
     *  The event will de-spawns the Horse
     * */
    @EventHandler
    public void onPLayerDismount(VehicleExitEvent e) {
        if(e.getExited() instanceof Player) {
            if(e.getVehicle() instanceof Horse) {
                Horse horse = (Horse) e.getVehicle();
                if(horse.getCustomName() != null) {
                    if(horse.getCustomName().equals(plugin.ut.chatcolor(plugin.getConfig().getString("First-Horse-Name")).replace("%first-horse-name%",e.getExited().getName()))) {
                        horse.remove();
                        String MessageDismount = plugin.ConfigMessage.getString("OnDismountMessage");
                        e.getExited().sendMessage(ChatColor.translateAlternateColorCodes('&', MessageDismount + ""));
                    }
                }
            }
        }
    }
}
