package me.perplexed.coordinates.utils;

import me.perplexed.coordinates.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Coordinate {

    private final Location loc;
    private final String name;

    public Coordinate(Location loc, String name) {
        this.loc = loc;
        this.name = name;
        Main.getInstance().getCoords().add(this);
    }

    public Location getLoc() {
        return loc;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        String append = "";

        switch (loc.getWorld().getEnvironment()) {
            case NETHER -> append = ". This is in the nether";
            case THE_END -> append = ". Thius is in the end";
            case CUSTOM -> {}
            default -> Bukkit.getLogger().warning("Unexpected Dimension. Are youu sing mods??");
        }

        return ChatColor.RED + name + " " + ChatColor.WHITE + ": " +
                Math.floor(loc.getX()) + Math.floor(loc.getY()) +Math.floor(loc.getZ()) + append;
    }
}
