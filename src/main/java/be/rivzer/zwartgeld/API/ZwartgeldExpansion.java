package be.rivzer.zwartgeld.API;

import be.rivzer.zwartgeld.Config.Config;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ZwartgeldExpansion extends PlaceholderExpansion {


    @Override
    public @NotNull String getIdentifier() {
        return "zwartgeld";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Rivzer";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, @NotNull String params) {
        if(p == null) return "0";

        if(params.equals("geld")){
            if(Config.getCustomConfig2().getString("Players." + p.getUniqueId() + ".Zwartgeld") == null) return "0";
            return Config.getCustomConfig2().getString("Players." + p.getUniqueId() + ".Zwartgeld");
        }

        return "0";
    }
}
