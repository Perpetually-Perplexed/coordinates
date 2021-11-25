package me.perplexed.coordinates.utils;

import me.perplexed.coordinates.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class CoordinateSaveFile {
    private final Main instance;
    private FileConfiguration dataFile = null;
    private File configFile = null;

    public CoordinateSaveFile(Main instance) {
        this.instance = instance;
        saveBasicConfig();
    }

    public void reloadFile() {
        if (this.configFile == null) this.configFile = new File(this.instance.getDataFolder(), "timers.yml");

        this.dataFile = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream basicStream = this.instance.getResource("timers.yml");
        if (basicStream !=null) {
            YamlConfiguration basicConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(basicStream));
            this.dataFile.setDefaults(basicConfig);
        }
    }

    public FileConfiguration getDataConfig() {
        if (this.dataFile == null) reloadFile();
        return this.dataFile;
    }

    public void saveConfig() {
        if (this.dataFile == null || this.configFile == null) return;
        try {
            this.getDataConfig().save(this.configFile);
        } catch (IOException e) {
            instance.getLogger().log(Level.SEVERE, "Could not save player Timers to " + this.configFile, e);
        }

    }

    public void saveBasicConfig() {
        if (this.configFile == null) this.configFile = new File(this.instance.getDataFolder(),"timers.yml");
        if (!this.configFile.exists()) {
            this.instance.saveResource("timers.yml",false);
        }
    }
}
