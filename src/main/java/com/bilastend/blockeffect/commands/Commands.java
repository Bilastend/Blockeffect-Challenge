package com.bilastend.blockeffect.commands;

import com.bilastend.blockeffect.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(args.length == 0){
            commandSender.sendMessage(getHelp());
            return true;
        }

        switch (args[0]){
            case "start"->{ return startCh(commandSender);}
            case "stop"->{return stopCh(commandSender);}
            case "reload"->{return reload(commandSender);}
            case "shuffle"->{return shuffle(commandSender);}
            default -> {commandSender.sendMessage(getHelp()); return true;}
        }
    }

     public boolean startCh(CommandSender sender){
        Main.getInstance().getManager().timer();
        sender.sendMessage("Challenge gestartet");
        return true;
    }

    public boolean stopCh(CommandSender sender){
        int taskID = Main.getInstance().getManager().getTaskID();
        if(taskID == -1) return true;
        Bukkit.getScheduler().cancelTask(taskID);
        sender.sendMessage("Challenge gestoppt");
        return true;
    }

    public boolean reload(CommandSender sender){
        Main.getInstance().getManager().setBlockEffects();
        sender.sendMessage("Potion Effekte wurden neu geladen");
        return true;
    }

    public boolean shuffle(CommandSender sender){
        boolean set = Main.getInstance().getManager().isShuffleOnDamage();
        Main.getInstance().getManager().setShuffleOnDamage(!set);
        if(set){
            sender.sendMessage("Schaden durch Mobs vertauscht §ckeine §fEffekte mehr");
        }else {
            sender.sendMessage("Schaden durch Mobs vertauscht jetzt Effekte");
        }
        return true;
    }

    public String getHelp(){
        return "/blockeffect start - Startet die challenge\n/blockeffect stop - Stoppt die challenge\n/blockeffect reload - Weißt jedem Block neue Effekte zu\n/blockeffect shuffle - Vertausche Effekte bei Schaden durch Mobs (Standardmäßig §aan§f)";
    }

}
