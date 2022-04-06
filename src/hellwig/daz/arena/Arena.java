package hellwig.daz.arena;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import hellwig.daz.plugin.DaZPlugin;
import hellwig.daz.plugin.ItemBuilder;
import hellwig.daz.scoreboard.LobbyScoreboard;

public class Arena {

	public DaZPlugin plugin;
	
	private String name;
	private Set<Player> players;
	
	private ArenaState state;
	
	private Location location;
	
	private BossBar bossbar;
	
	private ArmorStand armorStand;
	
	public Arena(String name, Location locations, DaZPlugin plugin, ArmorStand armorStand) {
		
		this.name = name;
		this.players = new HashSet<Player>();
		this.state = ArenaState.WAITING;
		this.location = locations;
		
		this.bossbar = Bukkit.createBossBar("§cWaiting for §e" + (25 - players.size()) + " §cmore players", BarColor.WHITE, BarStyle.SEGMENTED_20);
		
		this.plugin = plugin;
		
		this.armorStand = armorStand;
		
		this.armorStand.setCustomName("§6§lWAITING §8| §a" + this.players.size() + " / 25");
		
	}
	
	public void player(Player player) {
		
		player.setGameMode(GameMode.SURVIVAL);
		
		player.getInventory().clear();
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		player.setHealth(20);
		
		player.setWalkSpeed( (float) 0.2);
		
		player.getInventory().setItem(8, new ItemBuilder(Material.OAK_DOOR).setName("§c§LLeave arena §7§o(RIGHTCLICK)").toItemStack());
		player.getInventory().setItem(4, new ItemBuilder(Material.MAGMA_CREAM).setName("§f§lGet some discs").toItemStack());
		
		player.teleport(this.location);
		
		this.bossbar.addPlayer(player);
		this.bossbar.setTitle("§cWaiting for §e" + (25 - players.size()) + " §cmore players");
		
		getPlugin().lobbyscoreboards.get(player).cancel();
		getPlugin().lobbyscoreboards.get(player).delete();
		getPlugin().lobbyscoreboards.remove(player);
		
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
		
		LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player, "§c§lDaZ §8| §fLobby", getPlugin());
		getPlugin().lobbyscoreboards.put(player, lobbyScoreboard);
		
		
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
	
}
