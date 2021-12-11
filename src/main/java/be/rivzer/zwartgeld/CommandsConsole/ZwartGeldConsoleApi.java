package be.rivzer.zwartgeld.CommandsConsole;

import be.rivzer.zwartgeld.Config.Config;
import be.rivzer.zwartgeld.Logger;
import be.rivzer.zwartgeld.Main;
import be.rivzer.zwartgeld.Utils.CheckInt;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ZwartGeldConsoleApi implements CommandExecutor {

    private Main plugin;

    public ZwartGeldConsoleApi(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("zwartgeldconsoleapi").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {

        if(sender instanceof ConsoleCommandSender){
            ConsoleCommandSender c = (ConsoleCommandSender) sender;

            if(args.length == 0){
                c.sendMessage(Logger.color("&f-----------------------"));
                c.sendMessage(Logger.color("&7/zwartgeldconsoleapi add <bedrag> <speler>"));
                c.sendMessage(Logger.color("&7/zwartgeldconsoleapi set <bedrag> <speler>"));
                c.sendMessage(Logger.color("&7/zwartgeldconsoleapi remove <bedrag> <speler>"));
                c.sendMessage(Logger.color("&7/zwartgeldconsoleapi ifhigher <bedrag> <speler>"));
                c.sendMessage(Logger.color("&7/zwartgeldconsoleapi iflower <bedrag> <speler>"));
                c.sendMessage(Logger.color("&7/zwartgeldconsoleapi get <speler>"));
                c.sendMessage(Logger.color("&7/zwartgeldconsoleapi clear <speler>"));
                c.sendMessage(Logger.color("&f-----------------------"));
            }
            else if(args[0].equalsIgnoreCase("ifhigher")){
                if(args.length == 1){
                    c.sendMessage(Logger.color("&cGeef een bedrag op!"));
                    return false;
                }
                else if(args.length == 2){
                    c.sendMessage(Logger.color("&cGeef een speler op!"));
                    return false;
                }

                Player t = Bukkit.getPlayerExact(args[2]);

                if(t == null){
                    c.sendMessage(Logger.color("&cDe speler was niet gevonden"));
                    return false;
                }

                if(Config.getCustomConfig2().getString("Players." + t.getUniqueId()) != null){
                    long m = Config.getCustomConfig2().getLong("Players." + t.getUniqueId() + ".Zwartgeld");
                    if(m > Long.parseLong(args[1])){
                        c.sendMessage("true");
                    }
                    else{
                        c.sendMessage("false");
                    }
                }
                else{
                    c.sendMessage(Logger.color("&cDe speler heeft geen zwartgeld!"));
                }

                return false;
            }
            else if(args[0].equalsIgnoreCase("iflower")){
                if(args.length == 1){
                    c.sendMessage(Logger.color("&cGeef een bedrag op!"));
                    return false;
                }
                else if(args.length == 2){
                    c.sendMessage(Logger.color("&cGeef een speler op!"));
                    return false;
                }

                Player t = Bukkit.getPlayerExact(args[2]);

                if(t == null){
                    c.sendMessage(Logger.color("&cDe speler was niet gevonden"));
                    return false;
                }

                if(Config.getCustomConfig2().getString("Players." + t.getUniqueId()) != null){
                    long m = Config.getCustomConfig2().getLong("Players." + t.getUniqueId() + ".Zwartgeld");
                    if(m < Long.parseLong(args[1])){
                        c.sendMessage("true");
                    }
                    else{
                        c.sendMessage("false");
                    }
                }
                else{
                    c.sendMessage(Logger.color("&cDe speler heeft geen zwartgeld!"));
                }

                return false;
            }
            else if(args[0].equalsIgnoreCase("add")){
                if(args.length == 1){
                    c.sendMessage(Logger.color("&cGeef een bedrag op!"));
                    return false;
                }
                else if(args.length == 2){
                    c.sendMessage(Logger.color("&cGeef een speler op!"));
                    return false;
                }

                Player t = Bukkit.getPlayerExact(args[2]);

                if(t == null){
                    c.sendMessage(Logger.color("&cDe speler was niet gevonden"));
                    return false;
                }

                if(!CheckInt.CheckInt(args[1])){
                    c.sendMessage(Logger.color("&cU moet een geldig getal opgeven!"));
                    return false;
                }

                try{
                    if(Config.getCustomConfig2().getString("Players." + t.getUniqueId()) != null){
                        long m = Config.getCustomConfig2().getLong("Players." + t.getUniqueId() + ".Zwartgeld");
                        Config.getCustomConfig2().set("Players." + t.getUniqueId() + ".Zwartgeld", (m + Long.parseLong(args[1])));
                        Config.saveConfig2();
                        c.sendMessage(Logger.color("&7Er is &b€" + args[1] + " &7zwartgeld toegevoegd bij speler &b" + t.getName() + " &7en heeft nu &b€" + (m + Long.parseLong(args[1])) + " &7zwartgeld"));
                    }
                    else{
                        Config.getCustomConfig2().set("Players." + t.getUniqueId() + ".Zwartgeld", Long.parseLong(args[1]));
                        Config.saveConfig2();
                        c.sendMessage(Logger.color("&7Er is &b€" + args[1] + " &7zwartgeld toegevoegd bij speler &b" + t.getName()));
                    }
                }catch (Exception e){
                    c.sendMessage(Logger.color("&cEr is iets fout gegaan!"));
                    e.printStackTrace();
                }
                return false;
            }
            else if(args[0].equalsIgnoreCase("clear")){
                if(args.length == 1){
                    c.sendMessage(Logger.color("&cGeef een speler op!"));
                    return false;
                }

                Player t = Bukkit.getPlayerExact(args[1]);

                if(t == null){
                    c.sendMessage(Logger.color("&cDe speler was niet gevonden"));
                    return false;
                }

                if(Config.getCustomConfig2().getString("Players." + t.getUniqueId()) != null){
                    Config.getCustomConfig2().set("Players." + t.getUniqueId() + ".Zwartgeld", 0);
                    Config.saveConfig2();
                    c.sendMessage(Logger.color("&b" + t.getName() + " &7zijn zwartgeld is nu gecleard!"));
                }
                else{
                    c.sendMessage(Logger.color("&cDeze speler heeft geen zwartgeld om te clearen!"));
                }
                return false;
            }
            else if(args[0].equalsIgnoreCase("set")){
                if(args.length == 1){
                    c.sendMessage(Logger.color("&cGeef een bedrag op!"));
                    return false;
                }
                else if(args.length == 2){
                    c.sendMessage(Logger.color("&cGeef een speler op!"));
                    return false;
                }

                Player t = Bukkit.getPlayerExact(args[2]);

                if(t == null){
                    c.sendMessage(Logger.color("&cDe speler was niet gevonden"));
                    return false;
                }

                if(!CheckInt.CheckInt(args[1])){
                    c.sendMessage(Logger.color("&cU moet een geldig getal opgeven!"));
                    return false;
                }

                try{
                    if(Config.getCustomConfig2().getString("Players." + t.getUniqueId()) != null){
                        Config.getCustomConfig2().set("Players." + t.getUniqueId() + ".Zwartgeld", Long.parseLong(args[1]));
                        Config.saveConfig2();
                        c.sendMessage(Logger.color("&b" + t.getName() + " &7zijn zwartgeld is gezet naar &b" + Long.parseLong(args[1])));
                    }
                    else{
                        c.sendMessage(Logger.color("&cDeze speler heeft geen zwartgeld om te removen!"));
                    }
                }catch (Exception e){
                    c.sendMessage(Logger.color("&cEr is iets fout gegaan!"));
                    e.printStackTrace();
                }
                return false;
            }
            else if(args[0].equalsIgnoreCase("remove")){
                if(args.length == 1){
                    c.sendMessage(Logger.color("&cGeef een bedrag op!"));
                    return false;
                }
                else if(args.length == 2){
                    c.sendMessage(Logger.color("&cGeef een speler op!"));
                    return false;
                }

                Player t = Bukkit.getPlayerExact(args[2]);

                if(t == null){
                    c.sendMessage(Logger.color("&cDe speler was niet gevonden"));
                    return false;
                }

                if(!CheckInt.CheckInt(args[1])){
                    c.sendMessage(Logger.color("&cU moet een geldig getal opgeven!"));
                    return false;
                }

                try{
                    if(Config.getCustomConfig2().getString("Players." + t.getUniqueId()) != null){
                        long m = Config.getCustomConfig2().getLong("Players." + t.getUniqueId() + ".Zwartgeld");
                        Config.getCustomConfig2().set("Players." + t.getUniqueId() + ".Zwartgeld", (m - Long.parseLong(args[1])));
                        Config.saveConfig2();
                        c.sendMessage(Logger.color("&7Er is &b€" + args[1] + " &7zwartgeld afgehaald bij speler &b" + t.getName()));
                    }
                    else{
                        c.sendMessage(Logger.color("&cDeze speler heeft geen zwartgeld om te removen!"));
                    }
                }catch (Exception e){
                    c.sendMessage(Logger.color("&cEr is iets fout gegaan!"));
                    e.printStackTrace();
                }
                return false;
            }
            else if(args[0].equalsIgnoreCase("get")){
                if(args.length == 1){
                    c.sendMessage(Logger.color("&cGeef een speler op!"));
                    return false;
                }

                Player t = Bukkit.getPlayerExact(args[1]);

                if(t == null){
                    c.sendMessage(Logger.color("&cDe speler was niet gevonden"));
                    return false;
                }

                if(Config.getCustomConfig2().getString("Players." + t.getUniqueId()) != null){
                    long zwartgeld = Config.getCustomConfig2().getLong("Players." + t.getUniqueId() + ".Zwartgeld");
                    c.sendMessage(Logger.color("&b" + t.getName() + " &7heeft &b€" + zwartgeld + " &7zwartgeld"));
                }
                else{
                    c.sendMessage(Logger.color("&cDeze speler heeft geen zwartgeld!"));
                }
                return false;
            }
        }

        return false;
    }
}
