package hellwig.daz.arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import hellwig.daz.game.objects.DwarfShrine;
import hellwig.daz.game.objects.TransmuteBook;
import hellwig.daz.plugin.DaZPlugin;
import hellwig.daz.plugin.ItemBuilder;
import hellwig.daz.scoreboard.GameScoreboard;
import hellwig.daz.scoreboard.LobbyScoreboard;

public class Arena {

	public DaZPlugin plugin;
	
	private String name;
	private Set<Player> players;
	
	private ArenaState state;
	
	private Location location;
	
	private BossBar bossbar;
	
	private ArmorStand armorStand;
	
	private BukkitTask countdown;
	
	private List<Block> shrineBlocks;
	
	public Arena(String name, Location locations, DaZPlugin plugin, ArmorStand armorStand) {
		
		this.name = name;
		this.players = new HashSet<Player>();
		this.state = ArenaState.WAITING;
		this.location = locations;
		
		this.bossbar = Bukkit.createBossBar("§cWaiting for §e" + (2 - players.size()) + " §cmore player(s)", BarColor.WHITE, BarStyle.SEGMENTED_20);
		
		this.plugin = plugin;
		
		this.armorStand = armorStand;
		
		this.armorStand.setCustomName("§6§lWAITING §8| §a" + this.players.size() + " / 25");
		
		this.shrineBlocks = new ArrayList<Block>();
		
	}
	
	public void player(Player player) {
		
		player.setGameMode(GameMode.SURVIVAL);
		
		player.getInventory().clear();
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		player.setHealth(20);
		
		player.setWalkSpeed( (float) 0.2);
		
		player.getInventory().setItem(8, new ItemBuilder(Material.OAK_DOOR).setName("§c§LLeave arena §7§o(RIGHTCLICK)").toItemStack());
		player.getInventory().setItem(4, new ItemBuilder(Material.MAGMA_CREAM).setName("§f§lGet some discs").toItemStack());
		
		player.teleport(this.location.clone().add(0, 51, 0));
		
		this.bossbar.addPlayer(player);
		this.bossbar.setTitle("§cWaiting for §e" + (2 - players.size()) + " §cmore player(s)");
		
		getPlugin().lobbyscoreboards.get(player).cancel();
		getPlugin().lobbyscoreboards.get(player).delete();
		getPlugin().lobbyscoreboards.remove(player);
		
		GameScoreboard scoreboard = new GameScoreboard(player, "§c§lDaZ §8| §fArena", getPlugin());
		getPlugin().gamescoreboards.put(player, scoreboard);
		
		if(players.size() >= 2) {
			
			if(countdown.isCancelled()) {
				
				startCountdown();
				
			}
			
		}
		
	}
	
	public void addPlayer(Player player) {
		
		this.players.add(player);
		this.armorStand.setCustomName("§6§lWAITING §8| §a" + this.players.size() + " / 25");
		
	}
	
	public void removePlayer(Player player) {
		
		this.players.remove(player);
		this.armorStand.setCustomName("§6§lWAITING §8| §a" + this.players.size() + " / 25");
		
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getActivePotionEffects().clear();
		player.setHealth(6);
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(6);
		player.setFoodLevel(20);
		
		player.getInventory().setItem(8, new ItemBuilder(Material.BOOK).setName("§f§lHOW TO PLAY §7§o(RIGHTCLICK)").toItemStack());
		
		player.teleport(getPlugin().getLobbyLocationHandler().locations.get("LobbySpawn"));
		
		player.setWalkSpeed( (float) 0.5);
		
		this.bossbar.removePlayer(player);
		
		getPlugin().gamescoreboards.get(player).delete();
		getPlugin().gamescoreboards.remove(player);
		
		LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player, "§c§lDaZ §8| §fLobby", getPlugin());
		getPlugin().lobbyscoreboards.put(player, lobbyScoreboard);
		
		getPlugin().getDwarvTypeHandler().removePlayer(player);
		
		if(players.size() <= 1) {
			
			countdown.cancel();
			armorStand.setCustomName("§6§lWAITING §8| §a" + this.players.size() + " / 25");
			bossbar.setTitle("§cWaiting for §e" + (2 - players.size()) + " §cmore player(s)");
			bossbar.setProgress(1);
			state = ArenaState.WAITING;
			
		}
		
		
	}
	
	public void finish() {
		
		for(Player player : players) {
			
			this.players.remove(player);
			
			player.setGameMode(GameMode.ADVENTURE);
			player.getInventory().clear();
			player.getActivePotionEffects().clear();
			player.setHealth(6);
			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(6);
			player.setFoodLevel(20);
			
			player.getInventory().setItem(8, new ItemBuilder(Material.BOOK).setName("§f§lHOW TO PLAY §7§o(RIGHTCLICK)").toItemStack());
			
			player.teleport(getPlugin().getLobbyLocationHandler().locations.get("LobbySpawn"));
			
			player.setWalkSpeed( (float) 0.5);
			
			this.bossbar.removePlayer(player);
			
			getPlugin().gamescoreboards.get(player).delete();
			getPlugin().gamescoreboards.remove(player);
			
			LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player, "§c§lDaZ §8| §fLobby", getPlugin());
			getPlugin().lobbyscoreboards.put(player, lobbyScoreboard);
			
			getPlugin().getDwarvTypeHandler().removePlayer(player);
			
			getPlugin().getGameHandler().getTransmuteBooks().get(player).getTask().cancel();
			getPlugin().getGameHandler().getTransmuteBooks().remove(player);
			
			armorStand.setCustomName("§c§lRESTARTING");
			setState(ArenaState.RESTARTING);
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					reset();
					
				}
			}.runTaskLater(getPlugin(), 40);
			
		}
		
	}
	
	public void reset() {
		
		getPlugin().getArenaManager().newArena(this);
		
	}
	
	public void startCountdown() {
		
		setState(ArenaState.STARTING);
		
		setCountdown(Bukkit.getScheduler().runTaskTimer(getPlugin(), new Runnable() {
			
			int count = 50;
			
			@Override
			public void run() {
				
				count--;
				bossbar.setTitle("§aGame starts in §e" + count + " §asecond(s)");
				bossbar.setProgress(bossbar.getProgress() - 0.02);
				armorStand.setCustomName("§d§lSTARTING in §e" + count + " §8| §a" + players.size() + " / 25");
				
				if(count == 0) {
					
					getCountdown().cancel();
					bossbar.setTitle("§a§lSTARTING GAME ...");
					bossbar.setColor(BarColor.GREEN);
					setState(ArenaState.RUNNING);
					shrine();
					
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							bossbar.removeAll();
							getPlugin().getArenaManager().cylinder(location.clone().add(0, 50, 0), Material.AIR, 12);
							
							for(Player all : Bukkit.getOnlinePlayers()) {
								
								all.getInventory().clear();
								all.setGameMode(GameMode.SURVIVAL);
								getPlugin().getArenaManager().nofalldamage.add(all);
								getPlugin().getGameHandler().startItems(all);
								
								TransmuteBook book = new TransmuteBook(all, getPlugin());
								
								switch(getPlugin().getDwarvTypeHandler().getDwarvType(all)) {
								case ALCHEMIST:
									book.setActionbar_text("§cCraft §e%a §cmore mundane potions to use transmute spell");
									book.run();
									break;
								case BLACKSMITH:
									book.setActionbar_text("§cCraft §e%a §cmore golden clocks to use transmute spell");
									book.run();
									break;
								case BUILDER:
									book.setCooleddown(true);
									book.setUse(true);
									book.run();
									break;
								case TAILOR:
									book.setActionbar_text("§cCraft §e%a §cmore bread to use transmute spell");
									book.run();
									break;
								}
								
								armorStand.setCustomName("§a§lRUNNING §8| §a" + players.size() + " / 25");
								
								getPlugin().getGameHandler().transmuteBooks.put(all, book);
								
								getPlugin().gamescoreboards.get(all).update();
								
								new BukkitRunnable() {
									
									@Override
									public void run() {
										
										getPlugin().getArenaManager().nofalldamage.remove(all);
										
									}
								}.runTaskLater(getPlugin(), 10*20);
								
							}
							
							
						}
					}.runTaskLater(getPlugin(), 15);
					
				}
				
			}
		}, 0, 20));
		
	}
	
	public void shrine() {
		
		DwarfShrine shrine = new DwarfShrine(location, this, getPlugin());
		getPlugin().getArenaManager().getShrines().put(this, shrine);
		
	}
	
	public List<Block> getShrineBlocks() {
		return shrineBlocks;
	}
	
	public Set<Player> getPlayers() {
		
		return Collections.unmodifiableSet(this.players);
		
	}

	public DaZPlugin getPlugin() {
		return plugin;
	}
	
	public ArmorStand getArmorStand() {
		return armorStand;
	}
	
	
	public void setArmorStand(ArmorStand armorStand) {
		this.armorStand = armorStand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArenaState getState() {
		return state;
	}

	public void setState(ArenaState state) {
		this.state = state;
	}

	public Location getLocations() {
		return location;
	}

	public void setLocation(Location locations) {
		this.location = locations;
	}

	public BossBar getBossbar() {
		return bossbar;
	}

	public void setBossbar(BossBar bossbar) {
		this.bossbar = bossbar;
	}

	public void setPlugin(DaZPlugin plugin) {
		this.plugin = plugin;
	}

	public BukkitTask getCountdown() {
		return countdown;
	}

	public void setCountdown(BukkitTask countdown) {
		this.countdown = countdown;
	}
	
}
