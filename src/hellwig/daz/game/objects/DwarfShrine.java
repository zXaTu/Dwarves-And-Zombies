package hellwig.daz.game.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import hellwig.daz.arena.Arena;
import hellwig.daz.plugin.DaZPlugin;

public class DwarfShrine {

	public DaZPlugin plugin;
	
	public Arena arena;
	public Location location;

	public List<Block> shrineBlocks;
	
	public ArmorStand title;

	public DwarfShrine(Location location, Arena arena, DaZPlugin plugin) {

		this.plugin = plugin;
		this.location = location;
		this.arena = arena;

		this.shrineBlocks = new ArrayList<Block>();
		
		generateDwarfShrine(location, arena);

	}

	public void generateDwarfShrine(Location location, Arena arena) {

		// [1] [2] [3]
		// [4] [5] [6]
		// [7] [8] [9]

		location.getBlock().setType(Material.CHISELED_STONE_BRICKS); // [5]
		location.clone().add(0, 0, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [2]
		location.clone().add(1, 0, 0).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [4]
		location.clone().add(1, 0, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [1]

		location.clone().subtract(0, 0, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [8]
		location.clone().subtract(1, 0, 0).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [6]
		location.clone().subtract(1, 0, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [9]

		location.clone().add(0, 0, 1).subtract(1, 0, 0).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [3]
		location.clone().add(1, 0, 0).subtract(0, 0, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [7]
		// -------------------------------------------------------------------------------------------------------
		location.clone().add(0, 1, 0).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [5]
		location.clone().add(0, 1, 1).getBlock().setType(Material.STONE_BRICK_SLAB); // [2]
		location.clone().add(1, 1, 0).getBlock().setType(Material.STONE_BRICK_SLAB); // [4]
		location.clone().add(1, 1, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [1]

		location.clone().add(0, 1, 0).subtract(0, 0, 1).getBlock().setType(Material.STONE_BRICK_SLAB); // [8]
		location.clone().add(0, 1, 0).subtract(1, 0, 0).getBlock().setType(Material.STONE_BRICK_SLAB); // [6]
		location.clone().add(0, 1, 0).subtract(1, 0, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [9]

		location.clone().add(0, 1, 1).subtract(1, 0, 0).getBlock().setType(Material.STONE_BRICK_WALL); // [3]
		location.clone().add(1, 1, 0).subtract(0, 0, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [7]
		// --------------------------------------------------------------------------------------------------------
		location.clone().add(0, 2, 0).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [5]
		location.clone().add(1, 2, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [1]
		location.clone().add(0, 2, 0).subtract(1, 0, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [9]
		location.clone().add(0, 2, 1).subtract(1, 0, 0).getBlock().setType(Material.STONE_BRICK_WALL); // [3]
		location.clone().add(1, 2, 0).subtract(0, 0, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [7]
		// --------------------------------------------------------------------------------------------------------
		location.clone().add(0, 3, 0).getBlock().setType(Material.ENCHANTING_TABLE); // [5]
		shrineBlocks.add(location.clone().add(0, 3, 0).getBlock());
		getPlugin().getServer().getLogger().info("shrineBlocks add " + location.clone().add(0, 3, 0).getBlock().getType().toString());
		location.clone().add(0, 3, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [2]
		location.clone().add(1, 3, 0).getBlock().setType(Material.STONE_BRICK_WALL); // [4]
		location.clone().add(1, 3, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [1]

		location.clone().add(0, 3, 0).subtract(0, 0, 1).getBlock().setType(Material.STONE_BRICK_WALL); // [8]
		location.clone().add(0, 3, 0).subtract(1, 0, 0).getBlock().setType(Material.STONE_BRICK_WALL); // [6]
		location.clone().add(0, 3, 0).subtract(1, 0, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [9]

		location.clone().add(0, 3, 1).subtract(1, 0, 0).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [3]
		location.clone().add(1, 3, 0).subtract(0, 0, 1).getBlock().setType(Material.CHISELED_STONE_BRICKS); // [7]
		// --------------------------------------------------------------------------------------------------------
		location.clone().add(0, 4, 1).getBlock().setType(Material.STONE_BRICK_SLAB); // [2]
		location.clone().add(1, 4, 0).getBlock().setType(Material.STONE_BRICK_SLAB); // [4]
		location.clone().add(1, 4, 1).getBlock().setType(Material.ENCHANTING_TABLE); // [1]
		shrineBlocks.add(location.clone().add(1, 4, 1).getBlock());
		getPlugin().getServer().getLogger().info("shrineBlocks add " + location.clone().add(0, 3, 0).getBlock().getType().toString());

		location.clone().add(0, 4, 0).subtract(0, 0, 1).getBlock().setType(Material.STONE_BRICK_SLAB); // [8]
		location.clone().add(0, 4, 0).subtract(1, 0, 0).getBlock().setType(Material.STONE_BRICK_SLAB); // [6]
		location.clone().add(0, 4, 0).subtract(1, 0, 1).getBlock().setType(Material.ENCHANTING_TABLE); // [9]
		shrineBlocks.add(location.clone().add(0, 4, 0).subtract(1, 0, 1).getBlock());
		getPlugin().getServer().getLogger().info("shrineBlocks add " + location.clone().add(0, 4, 0).subtract(1, 0, 1).getBlock().getType().toString());
		
		location.clone().add(0, 4, 1).subtract(1, 0, 0).getBlock().setType(Material.ENCHANTING_TABLE); // [3]
		shrineBlocks.add(location.clone().add(0, 4, 1).subtract(1, 0, 0).getBlock());
		getPlugin().getServer().getLogger().info("shrineBlocks add " + location.clone().add(0, 4, 1).subtract(1, 0, 0).getBlock().getType().toString());
		location.clone().add(1, 4, 0).subtract(0, 0, 1).getBlock().setType(Material.ENCHANTING_TABLE); // [7]
		shrineBlocks.add(location.clone().add(1, 4, 0).subtract(0, 0, 1).getBlock());
		getPlugin().getServer().getLogger().info("shrineBlocks add " + location.clone().add(1, 4, 0).subtract(0, 0, 1).getBlock().getType().toString());

	}
	
	public void armorStand() {
		
		title = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0.5, 4, 0.5), EntityType.ARMOR_STAND); 
		title.setVisible(false);
		title.setInvulnerable(true);
		title.setCustomNameVisible(true);
		title.teleport(location.clone().add(1, 4, 1));
		title.setGravity(false);
		
	}
	
	public void checkShrine() {
		
		this.title.setCustomName("§d§lDwarfen Shrine §8| " + getShrineProcess());
		
	}
	
	public String getShrineProcess() {
		
		int i = getShrineBlocks().size();
		
		switch(i) {
		
		case 1:
			return "§a§l█ §c§l█ █ █ █";
		case 2:
			return "§a§l█ █ §c§l█ █ █";
		case 3:
			return "§a§l█ █ █ §c§l█ █";
		case 4:
			return "§a§l█ █ █ █ §c§l█";
		case 5:
			return "§a§l█ █ █ █ █";
		
		}
		
		return "";
		
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
	public List<Block> getShrineBlocks() {
		return shrineBlocks;
	}

}
