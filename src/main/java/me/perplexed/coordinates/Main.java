package me.perplexed.coordinates;

import me.perplexed.coordinates.utils.Coordinate;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    private final List<Coordinate> coords  =new ArrayList<>();
    private static Main inst;
    public boolean leoLychee = false; // ignore this in public releases

    @Override
    public void onEnable() {
        inst = this;

        getCommand("coordinates").setExecutor(new MegaCommand());
    }

    public static Main getInstance() {
        return inst;
    }

    public List<Coordinate> getCoords() {
        return coords;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
