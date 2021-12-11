package be.rivzer.zwartgeld;

import be.rivzer.zwartgeld.API.API_MANAGER;
import be.rivzer.zwartgeld.API.ZwartgeldExpansion;
import be.rivzer.zwartgeld.Commands.ZwartGeldAPI;
import be.rivzer.zwartgeld.CommandsConsole.ZwartGeldConsoleApi;
import be.rivzer.zwartgeld.Config.Config;
import be.rivzer.zwartgeld.Listeners.OnJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public API_MANAGER api;

    @Override
    public void onEnable() {

        Config.createCustomConfig1();
        Config.createCustomConfig2();

        api = new API_MANAGER();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new ZwartgeldExpansion().register();
            Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        } else {
            System.out.print("Plugin word uitgezet! PlaceHolderAPI is niet gevonden");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        //Custom api's
        new ZwartGeldAPI(this);
        new ZwartGeldConsoleApi(this);

        System.out.print("Plugin is opgestart! \n");

    }
}
