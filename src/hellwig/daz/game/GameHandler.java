package hellwig.daz.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import hellwig.daz.arena.Arena;
import hellwig.daz.game.objects.DwarfShrine;
import hellwig.daz.game.objects.DwarvType;
import hellwig.daz.game.objects.TransmuteBook;
import hellwig.daz.plugin.DaZPlugin;
import hellwig.daz.plugin.ItemBuilder;

public class GameHandler {
	
	public DaZPlugin plugin;
	
	public List<ItemStack> discs;
	public List<ItemStack> builderTransmute;
	public List<ItemStack> tailorTransmute;
	public List<ItemStack> blackSmithTransmute;
	
	public Map<Player, TransmuteBook> transmuteBooks;
	public Map<Arena, DwarfShrine> shrines;
	
	public GameHandler(DaZPlugin plugin) {
		
		this.plugin = plugin;
		
		discs = new ArrayList<ItemStack>();
		discs.add(new ItemBuilder(Material.MUSIC_DISC_11).setName("§9Alchemist").setItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_CAT).setName("§7Builder").setItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_CHIRP).setName("§cBlacksmith").setItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
		discs.add(new ItemBuilder(Material.MUSIC_DISC_STRAD).setName("§aTailor").setItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
	
		transmuteBooks = new HashMap<Player, TransmuteBook>();
		shrines = new HashMap<Arena, DwarfShrine>();
		
		builderTransmute = new ArrayList<ItemStack>();
		builderTransmute.add(new ItemBuilder(Material.STONE_BRICKS, 64).toItemStack());
		builderTransmute.add(new ItemBuilder(Material.CHISELED_STONE_BRICKS, 64).toItemStack());
		builderTransmute.add(new ItemBuilder(Material.MOSSY_STONE_BRICKS, 64).toItemStack());
		builderTransmute.add(new ItemBuilder(Material.STONE_BRICKS, 64).toItemStack());
		builderTransmute.add(new ItemBuilder(Material.CRACKED_STONE_BRICKS, 64).toItemStack());
		
		tailorTransmute = new ArrayList<ItemStack>();
		tailorTransmute.add(new ItemBuilder(Material.IRON_HELMET).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.IRON_LEGGINGS).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.IRON_BOOTS).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.GOLDEN_HELMET).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.GOLDEN_CHESTPLATE).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.GOLDEN_LEGGINGS).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.GOLDEN_BOOTS).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.DIAMOND_HELMET).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.NETHERITE_HELMET).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.NETHERITE_CHESTPLATE).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.NETHERITE_LEGGINGS).toItemStack());
		tailorTransmute.add(new ItemBuilder(Material.NETHERITE_BOOTS).toItemStack());
		
		blackSmithTransmute = new ArrayList<ItemStack>();
		blackSmithTransmute.add(new ItemBuilder(Material.STONE_SWORD).toItemStack());
		blackSmithTransmute.add(new ItemBuilder(Material.GOLDEN_SWORD).toItemStack());
		blackSmithTransmute.add(new ItemBuilder(Material.IRON_SWORD).toItemStack());
		blackSmithTransmute.add(new ItemBuilder(Material.DIAMOND_SWORD).toItemStack());
		blackSmithTransmute.add(new ItemBuilder(Material.BOW).toItemStack());
		blackSmithTransmute.add(new ItemBuilder(Material.ARROW, 8).toItemStack());
		
	}
	
	public void transmute(Player player, TransmuteBook book) {
		
		if(book.isUse()) {
			
			if(book.isCooleddown()) {
				
				DwarvType type = getPlugin().getDwarvTypeHandler().getDwarvType(player);
				Vector v = player.getLocation().getDirection();
				
				switch(type) {
				case ALCHEMIST:
					player.giveExp(getPlugin().getRandom(1, 5));
					book.cooldown(30);
					break;
				case BLACKSMITH:
					
					player.getLocation().getWorld().dropItemNaturally(player.getLocation().add(v),
							blackSmithTransmute.get(getPlugin().getRandom(0, 5)));
					
					player.giveExp(getPlugin().getRandom(1, 5));
					book.cooldown(30);
					break;
				case BUILDER:
					player.getLocation().getWorld().dropItemNaturally(player.getLocation().add(v),
							builderTransmute.get(getPlugin().getRandom(0, 4)));
					player.getLocation().getWorld().dropItemNaturally(player.getLocation().add(v),
							new ItemBuilder(Material.GLOWSTONE_DUST, 3).toItemStack());
					player.giveExp(getPlugin().getRandom(1, 5));
					book.cooldown(30);
					
					break;
				case TAILOR:
					
					for(int i = 0; i < getPlugin().getRandom(1, 3); i++) {
						
						player.getLocation().getWorld().dropItemNaturally(player.getLocation().add(v),
								tailorTransmute.get(getPlugin().getRandom(0, 15)));
						
					}
					
					player.giveExp(getPlugin().getRandom(1, 5));
					book.cooldown(30);
					break;
				default:
					break;
				}
				
			}
			
		}
		
	}
	
	public void discs(Player player) {
		
		int discamount = getPlugin().getRandom(1, 4);
		
		for(int i = 0; i < discamount; i++) {
			
			player.getInventory().addItem(discs.get(getPlugin().getRandom(0, 4)));
			
		}
	
	}
	
	public void startItems(Player player) {
		
		DwarvType type = getPlugin().getDwarvTypeHandler().getDwarvType(player);
		
		switch(type) {
		case ALCHEMIST:
			
			player.getInventory().addItem(new ItemBuilder(Material.BOOK).setName("§f§lTransmute").toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.BREWING_STAND, 2).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.CAULDRON, 2).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.CHEST, 2).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.REDSTONE, 5).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.LAPIS_BLOCK, 64).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.GLASS, 64).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.OAK_SIGN, 3).toItemStack());
			
			break;
		case BLACKSMITH:
			
			player.getInventory().addItem(new ItemBuilder(Material.BOOK).setName("§f§lTransmute").toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.FURNACE, 2).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.CHEST, 2).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.COAL, 10).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.GOLD_ORE, 24).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.REDSTONE_ORE, 24).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.NETHER_BRICKS, 64).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.COD, 10).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.OAK_SIGN, 2).toItemStack());
			
			break;
		case BUILDER:
			
			player.getInventory().addItem(new ItemBuilder(Material.BOOK).setName("§f§lTransmute").toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE, 5).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.IRON_SHOVEL, 5).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE, 5).toItemStack());
			
			player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setLeatherArmorColor(Color.GRAY).toItemStack());
			player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.GRAY).toItemStack());
			player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherArmorColor(Color.GRAY).toItemStack());
			player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GRAY).toItemStack());
			
			break;
		case TAILOR:
			
			player.getInventory().addItem(new ItemBuilder(Material.BOOK).setName("§f§lTransmute").toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.GRASS_BLOCK, 36).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.BONE, 3).toItemStack());
			player.getInventory().addItem(new ItemBuilder(Material.BROWN_MUSHROOM, 64).toItemStack());
			
			break;
		}
		
	}
	
	public Map<Arena, DwarfShrine> getShrines() {
		return shrines;
	}
	
	public Map<Player, TransmuteBook> getTransmuteBooks() {
		return transmuteBooks;
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}

}
