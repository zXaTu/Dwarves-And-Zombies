package hellwig.daz.lobby.managment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import hellwig.daz.plugin.DaZPlugin;

public class LobbyLocationHandler {

	public DaZPlugin plugin;

	public File file;
	public FileConfiguration configuration;
	
	public HashMap<String, Location> locations;

	public LobbyLocationHandler(DaZPlugin plugin) {

		this.plugin = plugin;

		file = new File(plugin.getDataFolder(), "lobbylocations.yml");
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		configuration = YamlConfiguration.loadConfiguration(file);
		
		locations = new HashMap<String, Location>();
		
		loadLocations();

	}
	
	private void loadLocations() {
		
		if(configuration.get("LobbySpawn") != null) {
			
			locations.put("LobbySpawn", getLocation("LobbySpawn"));
			locations.put("Entity_Villager_1", getLocation("Entity_Villager_1"));
			locations.put("Entity_Villager_2", getLocation("Entity_Villager_2"));
			locations.put("Entity_Villager_3", getLocation("Entity_Villager_3"));
			locations.put("Entity_Help", getLocation("Entity_Help"));
			locations.put("Entity_Unknown", getLocation("Entity_Unknown"));
			
		}
		
	}

	public void saveLocation(String locName, Location location) {
		String locationString = String.valueOf(location.getWorld().getName()) + ":" + location.getX() + ":"
				+ location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
		configuration.set(locName, locationString);
		saveConfiguration();
	}

	public Location getLocation(String locName) {
		String locationString = configuration.getString(locName);
		String[] lSB = locationString.split(":");
		Location location = new Location(Bukkit.getWorld(lSB[0]), Double.valueOf(lSB[1]).doubleValue(),
				Double.valueOf(lSB[2]).doubleValue(), Double.valueOf(lSB[3]).doubleValue(),
				Float.valueOf(lSB[4]).floatValue(), Float.valueOf(lSB[5]).floatValue());
		return location;
	}

	public void saveConfiguration() {

		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public File getFile() {
		return file;
	}

	public FileConfiguration getConfiguration() {
		return configuration;
	}

	public DaZPlugin getPlugin() {
		return plugin;
	}

}
