package com.gmail.jameshealey1994.simplepvptoggle.localisation;

import com.gmail.jameshealey1994.simplepvptoggle.utils.ColorUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.logging.Level;
import org.bukkit.ChatColor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * Class to help with localisation and custom messages.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Localisation {

    /**
     * The default localisation filename.
     */
    public static final String DEFAULT_FILENAME = "localisation.yml";

    /**
     * The config path to the localisation filename.
     */
    public static final String LOCALISATION_FILENAME_PATH = "Localisation Filename";

    /**
     * Plugin associated with the localisation.
     */
    private final Plugin plugin;

    /**
     * Initialises plugin variable and localisations.
     *
     * @param plugin    plugin used for config file, used for filename
     */
    public Localisation(Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }
        this.plugin = plugin;
    }

    /**
     * Gets the corresponding localisation to the key passed.
     *
     * @param key   key used to obtain localisation value
     * @return      value obtained, or, if no valid value is found, the default
     *              message belonging to key
     */
    public String get(LocalisationEntry key) {
        final HashMap<String, Object> localisations = getLocalisations();

        if (localisations.containsKey(key.getName())) {
            return ColorUtils.addColor(String.valueOf(localisations.get(key.getName())));
        } else {
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + "Missing localisation: ''{0}''", key.getName());
            plugin.getLogger().log(Level.WARNING, "{0}Edit or update your localisation config to resolve", ChatColor.RED);
            return ColorUtils.addColor(key.getDefaultValue());
        }
    }

    /**
     * Gets the corresponding localisation to the key passed, then formats the
     * message using the objects passed.
     *
     * @param key               key used to obtain localisation value
     * @param formatObjects     arguments to format the string with
     * @return                  value obtained, or, if no valid value is found,
     *                          the default message belonging to key
     */
    public String get(LocalisationEntry key, Object[] formatObjects) {
        try {
            return String.format(get(key), formatObjects);
        } catch (IllegalFormatException  ex) {
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + "Error in localisation: ''{0}''", key.getName());
            plugin.getLogger().log(Level.WARNING, "{0}Edit or update your localisation config to resolve", ChatColor.RED);
            return ColorUtils.addColor(String.format(key.getDefaultValue(), formatObjects));
        }
    }

    /**
     * Returns either a custom or default HashMap of localisations.
     *
     * @return  custom HashMap of localisations if one has been provided, else a
     *          default is returned
     */
    private HashMap<String, Object> getLocalisations() {
        return (HashMap<String, Object>) getConfig().getValues(true);
    }

    /**
     * Returns the FileConfiguration of getFile().
     *
     * @return the FileConfiguration of getFile()
     */
    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    /**
     * Returns the file containing localisation values.
     * If the file with the filename specified in the config.yml does not exist,
     * a file is created with that name and filled with default values.
     *
     * @return  the file containing localisation values
     */
    private File getFile() {
        final File file = new File(plugin.getDataFolder(), getFilename());
        if (!(file.exists())) {
            createDefaultConfig();
        }
        return file;
    }

    /**
     * Fills the file specified in the config with default localisation values.
     */
    private void createDefaultConfig() {
        final String title = "# " + plugin.getName() + " localisation configuration\n";
        final String info = "# Generated with " + plugin.getDescription().getVersion() + " of the plugin\n";
        final File file = new File(plugin.getDataFolder(), getFilename());

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
            writer.write(title);
            writer.write(info);
            for (LocalisationEntry entry : LocalisationEntry.values()) {
                writer.write(entry.toString());
            }
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "IOException when creating new localisation file: {0}", title);
            plugin.getLogger().severe(ex.getMessage());
        }
    }

    /**
     * Returns the filename as specified in config.yml.
     *
     * @return  filename as specified in config.yml
     */
    public final String getFilename() {
        return plugin.getConfig().getString(LOCALISATION_FILENAME_PATH, DEFAULT_FILENAME);
    }
}