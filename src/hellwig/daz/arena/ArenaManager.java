package hellwig.daz.arena;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import hellwig.daz.plugin.DaZPlugin;

public class ArenaManager {
	
	public DaZPlugin plugin;
	
	public Set<Arena> activeArenas;
	public Set<World> activeArenaWorlds;
	
	public ArenaManager(DaZPlugin plugin) {
		
		this.plugin = plugin;
		
		activeArenas = new HashSet<Arena>();
		activeArenaWorlds = new HashSet<World>();
		
		createWorldForArena("Arena1");
		createWorldForArena("Arena2");
		createWorldForArena("Arena3");
		
	}
	
	public void createWorldForArena(String worldname) {
		
		getPlugin().getServer().getLogger().info("[DaZ] Creating World : " + worldname);
		WorldCreator creator = new WorldCreator(worldname);
		creator.type(WorldType.LARGE_BIOMES);
		creator.generateStructures(true);
		creator.createWorld();
		getPlugin().getServer().getLogger().info("[DaZ] Created World : " + worldname);
		
		ArmorStand stand = (ArmorStand) getPlugin().getLobbyHandler().entitys.get(worldname + "_armorstand"); 
		
		Arena arena = new Arena(worldname, Bukkit.getWorld(worldname).getSpawnLocation(), getPlugin(), stand);
		
		addArena(arena);
		getPlugin().getServer().getLogger().info("[DaZ] Created Arena for World : " + worldname + " | " + arena.getName());
		
		addWorld(Bukkit.getWorld(worldname));
		
	}
	
	public void deleteWorld(World world) {
		
		Bukkit.unloadWorld(world, true);
		File worldFolder = world.getWorldFolder();
		try {
		    FileUtils.deleteDirectory(worldFolder);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	public Arena getArena(Player player) {
        return activeArenas.stream().filter(arena -> arena.getPlayers().contains(player)).findFirst().orElse(null);
    }

    public Arena getArena(String key) {
        return activeArenas.stream().filter(arena -> arena.getName().equals(key)).findFirst().orElse(null);
    }

    public Set<Arena> getArenaList() {
        return Collections.unmodifiableSet(activeArenas);
    }
    
    public Set<World> getArenaWorlds() {
        return Collections.unmodifiableSet(activeArenaWorlds);
    }
    
    public void removeWorld(World world) {
    	
    	activeArenaWorlds.remove(world);
    	
    }
    
    public void addWorld(World world) {
    	
    	activeArenaWorlds.add(world);
    	
    }
    
    public void addArena(Arena arena) {
    	
    	activeArenas.add(arena);
    	
    }
    
    public void removeArena(Arena arena) {
    	
    	activeArenas.add(arena);
    	
    }
	
	public DaZPlugin getPlugin() {
		return plugin;
	}

}
