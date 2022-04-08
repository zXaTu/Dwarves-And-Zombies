package hellwig.daz.arena;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import hellwig.daz.game.objects.DwarfShrine;
import hellwig.daz.plugin.DaZPlugin;

public class ArenaManager {
	
	public DaZPlugin plugin;
	
	public Set<Arena> activeArenas;
	public Set<World> activeArenaWorlds;
	
	public Set<Player> nofalldamage;
	
	public Map<Arena, DwarfShrine> shrines;
	
	public ArenaManager(DaZPlugin plugin) {
		
		this.plugin = plugin;
		
		activeArenas = new HashSet<Arena>();
		activeArenaWorlds = new HashSet<World>();
		nofalldamage = new HashSet<Player>();
		shrines = new HashMap<Arena, DwarfShrine>();
		
		createWorldForArena("Arena1");
		createWorldForArena("Arena2");
		createWorldForArena("Arena3");
		
		cylinder(Bukkit.getWorld("Arena1").getSpawnLocation().add(0, 50, 0), Material.WHITE_STAINED_GLASS, 12);
		cylinder(Bukkit.getWorld("Arena2").getSpawnLocation().add(0, 50, 0), Material.WHITE_STAINED_GLASS, 12);
		cylinder(Bukkit.getWorld("Arena3").getSpawnLocation().add(0, 50, 0), Material.WHITE_STAINED_GLASS, 12);
		
	}
	
	public void newArena(Arena arena) { 
		
		String name = arena.getName();
		
		removeWorld(Bukkit.getWorld(name));
		deleteWorld(Bukkit.getWorld(name));
		removeArena(arena);
		
		createWorldForArena(name);
		cylinder(Bukkit.getWorld(name).getSpawnLocation().add(0, 50, 0), Material.WHITE_STAINED_GLASS, 12);
		addWorld(Bukkit.getWorld(name));
		
	}
	
	public void cylinder(Location loc, Material mat, int r) {
	    int cx = loc.getBlockX();
	    int cy = loc.getBlockY();
	    int cz = loc.getBlockZ();
	    World w = loc.getWorld();
	    int rSquared = r * r;
	    for (int x = cx - r; x <= cx +r; x++) {
	        for (int z = cz - r; z <= cz +r; z++) {
	            if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared) {
	                w.getBlockAt(x, cy, z).setType(mat);
	                getPlugin().getServer().getLogger().info("[DaZ] Cyl : " + x + ", " + cy + ", " + z + " changed");
	            }
	        }
	    }
	    getPlugin().getServer().getLogger().info("[DaZ] Generated Circle for " + loc.getWorld().getName());
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
    
    public Map<Arena, DwarfShrine> getShrines() {
		return shrines;
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}

}
