package me.perplexed.coordinates;

import me.perplexed.coordinates.utils.Coordinate;
import me.perplexed.coordinates.utils.CoordinateSaveFile;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    private final List<Coordinate> coords  =new ArrayList<>();
    private static Main inst;
    private final CoordinateSaveFile saveFile = new CoordinateSaveFile(this);
    public boolean leoLychee = false; // ignore this in public releases


    @Override
    public void onEnable() {
        inst = this;

        getCommand("coordinates").setExecutor(new MegaCommand());
        getCommand("coordinates").setExecutor(new MegaCommand());
    }

    public CoordinateSaveFile getSaveFile() {
        return saveFile;
    }

    public static Main getInstance() {
        return inst;
    }

    public List<Coordinate> getCoords() {
        return coords;
    }

    @Override
    public void onDisable() {
        coords.forEach(Coordinate::save);
    }
}
