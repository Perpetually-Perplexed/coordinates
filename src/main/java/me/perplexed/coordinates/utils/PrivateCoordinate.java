package me.perplexed.coordinates.utils;

import me.perplexed.coordinates.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PrivateCoordinate extends Coordinate{

    private final Player plyr;

    public PrivateCoordinate(Location loc, String name, Player plyr) {
        super(loc, name);
        this.plyr = plyr;
    }

    public Player getPlyr() {
        return plyr;
    }

    @Override
    public void save() {
        FileConfiguration config = Main.getInstance().getSaveFile().getDataConfig();

        //introduced new vars cuz im lazy lol
        String name = getName();
        Location loc = getLoc();
        config.set("private." + plyr.getName() + "." + name + ".x",loc.getX());
        config.set("private." + plyr.getName() + "." + name + ".y",loc.getY());
        config.set("private." + plyr.getName() + "." + name + ".z",loc.getZ());
        config.set("private." + plyr.getName() + "." + name + ".world",loc.getWorld().getUID());
        Main.getInstance().getSaveFile().saveConfig();
    }
}
