package com.bilastend.blockeffect;

import com.bilastend.blockeffect.commands.Commands;
import com.bilastend.blockeffect.listener.OnDeath;
import com.bilastend.blockeffect.listener.OnDamage;
import com.bilastend.blockeffect.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Manager manager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new OnDeath(),this);
        pluginManager.registerEvents(new OnDamage(),this);
        getCommand("blockeffect").setExecutor(new Commands());

        manager = new Manager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    public Manager getManager() {
        return manager;
    }
}
