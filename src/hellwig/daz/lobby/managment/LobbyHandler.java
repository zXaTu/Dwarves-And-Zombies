package hellwig.daz.lobby.managment;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import hellwig.daz.plugin.DaZPlugin;

public class LobbyHandler {

	public DaZPlugin plugin;
	
	public Map<String, Entity> entitys;
	
	public LobbyHandler(DaZPlugin plugin) {
		
		this.plugin = plugin;
		entitys = new HashMap<String, Entity>();
		
		spawnEnts();
		
		spawnEntitys("help", getPlugin().getLobbyLocationHandler().locations.get("Entity_Help"), "§e§lHow to play Dwarves and Zombies");
		spawnEntitys("panda", getPlugin().getLobbyLocationHandler().locations.get("Entity_Unknown"), "§c§oUnknown Entity");
	}
	
	public void spawnEnts() {
		
		spawnVillager(getPlugin().getLobbyLocationHandler().locations.get("Entity_Villager_1"), Profession.FARMER, "§f§lArena 1", "Arena1");
		spawnVillager(getPlugin().getLobbyLocationHandler().locations.get("Entity_Villager_2"), Profession.CARTOGRAPHER, "§f§lArena 2", "Arena2");
		spawnVillager(getPlugin().getLobbyLocationHandler().locations.get("Entity_Villager_3"), Profession.LIBRARIAN, "§f§lArena 3", "Arena3");
		
	}
	
	public void spawnEntitys(String ent, Location location, String name) {
		
		if(ent.equalsIgnoreCase("help")) {
			
			IronGolem slime = (IronGolem) location.getWorld().spawnEntity(location, EntityType.IRON_GOLEM);
			slime.setAI(false);
			slime.setInvulnerable(true);
			slime.setAware(false);
			
			ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, 0.75, 0), EntityType.ARMOR_STAND);
			armorStand.setCustomNameVisible(true);
			armorStand.setCustomName(name);
			armorStand.setVisible(false);
			armorStand.setGravity(false);
			
			entitys.put("help_slime", slime);
			entitys.put("help_armorStand", armorStand);
			
		} else {
			
			Panda panda = (Panda) location.getWorld().spawnEntity(location, EntityType.PANDA);
			panda.setAI(false);
			panda.setInvulnerable(true);
			panda.setSilent(true);
			panda.setAware(false);
			
			ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location.clone().subtract(0, 0.25, 0), EntityType.ARMOR_STAND);
			armorStand.setCustomNameVisible(true);
			armorStand.setCustomName(name);
			armorStand.setVisible(false);
			armorStand.setGravity(false);
			
			entitys.put("unknown_panda", panda);
			entitys.put("unknown_armorStand", armorStand);
			
		}
		
	}
	
	public void spawnVillager(Location location, Profession proefession, String name, String arena) {
		
		Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
		villager.setProfession(proefession);
		villager.setCustomNameVisible(true);
		villager.setCustomName(name);
		villager.setAI(false);
		villager.setInvulnerable(true);
		villager.setSilent(true);
		villager.teleport(location);
		
		ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, 0.25, 0), EntityType.ARMOR_STAND);
		armorStand.setCustomNameVisible(true);
		armorStand.setGravity(false);
		armorStand.setVisible(false);
		
		entitys.put(arena + "_villager", villager);
		entitys.put(arena + "_armorstand", armorStand);
		
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
