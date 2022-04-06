package hellwig.daz.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import hellwig.daz.plugin.DaZPlugin;
import hellwig.daz.plugin.ItemBuilder;

public class GameHandler {
	
	public DaZPlugin plugin;
	
	public List<ItemStack> discs;
	
	public GameHandler(DaZPlugin plugin) {
		
		this.plugin = plugin;
		
		discs = new ArrayList<ItemStack>();
		discs.add(new ItemBuilder(Material.MUSIC_DISC_11).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_13).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_CAT).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_CHIRP).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_FAR).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_MALL).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_PIGSTEP).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_STRAD).setName(ChatColor.GRAY + "").toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_WARD).setName(ChatColor.GRAY + "").toItemStack());
		
	}
	
	public void discs(Player player) {
		
		int discamount = getPlugin().getRandom(1, 5);
		
		for(int i = 0; i < discamount; i++) {
			
			player.getInventory().addItem(discs.get(getPlugin().getRandom(0, 8)));
			
		}
	
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}

}
