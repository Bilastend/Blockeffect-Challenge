package com.bilastend.blockeffect.manager;

import com.bilastend.blockeffect.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Predicate;

public class Manager {

    private HashMap<Material, PotionEffect> map;
    private boolean shuffleOnDamage;
    private int taskID;
    PotionEffectType[] effectTypes;


    public Manager(){
        taskID = -1;
        shuffleOnDamage = true;
        effectTypes = Arrays.stream(PotionEffectType.values())
                .filter(potionEffectType -> !potionEffectType.equals(PotionEffectType.CONFUSION))
                .filter(potionEffectType -> !potionEffectType.equals(PotionEffectType.HEALTH_BOOST))
                .toArray(PotionEffectType[]::new);
        setBlockEffects();
    }

    public HashMap<Material, PotionEffect> getMap() {
        return map;
    }

    public void setBlockEffects(){
        map = new HashMap<>();
        for (Material block: Arrays.stream(Material.values())
                .filter(Material::isBlock)
                .filter(Predicate.not(Material::isAir)).toArray(Material[]::new)
             ) {
            map.put(block,new PotionEffect(effectTypes[new Random().nextInt(effectTypes.length)],60,new Random().nextInt(3)));
        }
    }

    public void setPlayerEffect(Player player){
        Block block = player.getLocation().subtract(0,0,0).getBlock();
        if(block.getType().isAir()){
            if(player.getLocation().subtract(0,1,0).getBlock().getType().isAir())return;
            block = player.getLocation().subtract(0,1,0).getBlock();
        }
        player.addPotionEffect(map.get(block.getType()));
    }

    public void timer(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            for (Player player: Bukkit.getOnlinePlayers()
                 ) {
                setPlayerEffect(player);
            }
        }, 0, 21);
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setShuffleOnDamage(boolean shuffleOnDamage) {
        this.shuffleOnDamage = shuffleOnDamage;
    }

    public boolean isShuffleOnDamage() {
        return shuffleOnDamage;
    }

    public int getTaskID() {
        return taskID;
    }
}
