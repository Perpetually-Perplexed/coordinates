package me.perplexed.coordinates.coordinate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public record Coordinate(Location loc, String name) {

    public Coordinate(Location loc, String name) {
        this.loc = loc;
        this.name = name.replaceAll(" ", "_");
        CoordinateUtils.getCoords().add(this);
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
            case NETHER -> append = ChatColor.RED + ". This is in the nether";
            case THE_END -> append = ChatColor.RED + ". This is in the end";
            case CUSTOM -> {
            }
            default -> Bukkit.getLogger().warning("Unexpected Dimension. Are you using mods??");
        }

        return ChatColor.RED + name + " " + ChatColor.WHITE + ": " +
                Math.floor(loc.getX()) + Math.floor(loc.getY()) + Math.floor(loc.getZ()) + append;
    }

    public void save() {
        FileConfiguration config = CoordinateUtils.getSaveFile().getDataConfig();
        config.set("public." + name + ".x", loc.getX());
        config.set("public." + name + ".y", loc.getY());
        config.set("public." + name + ".z", loc.getZ());
        config.set("public." + name + ".world", loc.getWorld().getUID());
        CoordinateUtils.getSaveFile()
                .saveConfig();
    }
}
