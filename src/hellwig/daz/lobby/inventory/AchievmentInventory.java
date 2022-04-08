package hellwig.daz.lobby.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import hellwig.daz.plugin.DaZPlugin;
import hellwig.daz.plugin.ItemBuilder;

public class AchievmentInventory {

	public DaZPlugin plugin;
	
	public AchievmentInventory(DaZPlugin plugin) {
		
		this.plugin = plugin;
		
	}
	
	public void achievementInventory(Player player) {
		
		Inventory inv = Bukkit.createInventory(player, 3*9, "§f§lAchievements");
		
		for(int i = 0; i < inv.getSize(); i++) {
			
			inv.setItem(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());
			
		}
		
		for(int i = 10; i < 17; i++) {
			
			inv.setItem(i, new ItemBuilder(Material.GRAY_DYE).setName("§c§l?").toItemStack());
			
		}
		
		player.openInventory(inv);
		
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
