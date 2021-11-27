package me.perplexed.coordinates;

import me.perplexed.coordinates.coordinate.Coordinate;
import me.perplexed.coordinates.coordinate.CoordinateSaveFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private final List<Coordinate> coords  =new ArrayList<>();
    private static Main inst;
    private final CoordinateSaveFile saveFile = new CoordinateSaveFile(this);
    public final boolean leoLychee = false; // ignore this in public releases


    @Override
    public void onEnable() {
        inst = this;

        getCommand("coordinates").setExecutor(new MegaCommand());
        getCommand("coordinates").setExecutor(new MegaCommand());
    }


    public static Main getInstance() {
        return inst;
    }

    @Override
    public void onDisable() {
        coords.forEach(Coordinate::save);
    }
}
