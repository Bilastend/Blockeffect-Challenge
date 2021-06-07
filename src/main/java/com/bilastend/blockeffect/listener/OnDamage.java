package com.bilastend.blockeffect.listener;

import com.bilastend.blockeffect.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnDamage implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(!Main.getInstance().getManager().isShuffleOnDamage())return;
        if(!(event.getEntity() instanceof Player)) return;
        Main.getInstance().getManager().setBlockEffects();
    }
}

