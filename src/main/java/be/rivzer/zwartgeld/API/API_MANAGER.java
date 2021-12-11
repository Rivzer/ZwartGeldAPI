package be.rivzer.zwartgeld.API;

import be.rivzer.zwartgeld.Config.Config;
import be.rivzer.zwartgeld.Logger;
import be.rivzer.zwartgeld.Main;
import org.bukkit.entity.Player;

public class API_MANAGER {

    public Main plugin = Main.getPlugin(Main.class);

    public static String getZwartGeld(Player p){
        if(p == null) return Logger.color("&cEr ging iets fout!");
        if(Config.getCustomConfig2().getString("Players." + p.getUniqueId()) != null){
            return Config.getCustomConfig2().getString("Players." + p.getUniqueId() + ".Zwartgeld");
        }
        else{
            return "Deze speler staat niet in de config met zwart geld!";
        }
    }

    public static String addZwartGeld(Player p, Long bedrag){
        if(p == null) return Logger.color("&cEr ging iets fout!");
        Config.getCustomConfig2().set("Players." + p.getUniqueId() + ".Zwartgeld", (Config.getCustomConfig2().getLong("Players." + p.getUniqueId() + ".Zwartgeld")+bedrag));
        Config.saveConfig2();
        return Logger.color("&7€" + bedrag + " &aZwart geld bij speler &7" + p.getName() + " &ais erbij toegevoegd.");
    }

    public static String removeZwartGeld(Player p, Long bedrag){
        if(p == null) return Logger.color("&cEr ging iets fout!");
        Config.getCustomConfig2().set("Players." + p.getUniqueId() + ".Zwartgeld", (Config.getCustomConfig2().getLong("Players." + p.getUniqueId() + ".Zwartgeld")-bedrag));
        Config.saveConfig2();
        return Logger.color("&7€" + bedrag + " &aZwart geld bij speler &7" + p.getName() + " &ais eraf gehaald.");
    }

}
