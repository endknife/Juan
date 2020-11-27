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

    @EventHandler
    public void horse(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            ItemStack item = e.getItem();

            if(item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Brown Horse")) {
                Horse horsebrown = (Horse) p.getWorld().spawn(p.getLocation(), Horse.class);
                horsebrown.setAdult();
                horsebrown.setTamed(true);
                horsebrown.setOwner(p);
                horsebrown.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                horsebrown.setCustomName(ChatColor.RED + "Horse");
                horsebrown.setPassenger(p);
            }

            if(item.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "Black Horse")) {
                Horse horseblack = (Horse) p.getWorld().spawn(p.getLocation(), Horse.class);
                horseblack.setAdult();
                horseblack.setTamed(true);
                horseblack.setOwner(p);
                horseblack.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                horseblack.setCustomName(ChatColor.DARK_GRAY + "Horse");
                horseblack.setPassenger(p);
            }

            if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "White Horse")) {
                Horse horsewhite = (Horse) p.getWorld().spawn(p.getLocation(), Horse.class);
                horsewhite.setAdult();
                horsewhite.setTamed(true);
                horsewhite.setOwner(p);
                horsewhite.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                horsewhite.setCustomName(ChatColor.WHITE + "Horse");
                horsewhite.setPassenger(p);
            }
        }
   }

    @EventHandler
    public void onPLayerDismount(VehicleExitEvent e) {
        if(e.getExited() instanceof Player) {
            e.getVehicle().remove();
            if(e.getVehicle() instanceof Horse) {
                Horse donkey = (Horse) e.getVehicle();
                if(donkey.getCustomName() != null) {
                    if(donkey.getCustomName().equals(ChatColor.GRAY + "Donkey")) {
                        donkey.remove();
                    }
                }
            }
            if(e.getVehicle() instanceof Horse) {
                Horse horse = (Horse) e.getVehicle();
                if(horse.getCustomName() != null) {
                    if(horse.getCustomName().equals(ChatColor.RED + "Horse")) {
                        horse.remove();
                    }
                    if(horse.getCustomName().equals(ChatColor.DARK_GRAY + "Horse")) {
                        horse.remove();
                    }
                    if(horse.getCustomName().equals(ChatColor.WHITE + "Horse")) {
                        horse.remove();
                    }
                }
            }
        }
    }
}
