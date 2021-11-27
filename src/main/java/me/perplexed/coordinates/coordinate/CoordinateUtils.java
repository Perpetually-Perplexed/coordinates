package me.perplexed.coordinates.coordinate;

import me.perplexed.coordinates.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoordinateUtils {
    private final static List<Coordinate> coords  =new ArrayList<>();
    private final static CoordinateSaveFile saveFile =  new CoordinateSaveFile(Main.getInstance());


    public static List<Coordinate> getCoords() {
        return coords;
    }

    public static CoordinateSaveFile getSaveFile() {
        return saveFile;
    }
}
