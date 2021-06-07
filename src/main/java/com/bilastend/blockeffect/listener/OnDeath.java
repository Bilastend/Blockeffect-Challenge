package com.bilastend.blockeffect.listener;

import com.bilastend.blockeffect.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnDeath implements Listener {


    @EventHandler(ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        if(!(event.getEntity() instanceof Player))return;
        Main.getInstance().getManager().setBlockEffects();

        int taskID = Main.getInstance().getManager().getTaskID();
        if(taskID != -1){
            Bukkit.getScheduler().cancelTask(taskID);
            Main.getInstance().getManager().setTaskID(-1);
        }
    }
}
