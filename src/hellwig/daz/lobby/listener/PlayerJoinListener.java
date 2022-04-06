package hellwig.daz.lobby.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import hellwig.daz.arena.ArenaState;
import hellwig.daz.plugin.DaZPlugin;
import hellwig.daz.plugin.ItemBuilder;
import hellwig.daz.scoreboard.LobbyScoreboard;

public class PlayerJoinListener implements Listener {

	public DaZPlugin plugin;
	
	public PlayerJoinListener(DaZPlugin plugin) {
		
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		event.setJoinMessage(null);
		player(event.getPlayer());
		
	}
	
	private void player(Player player) {
		
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getActivePotionEffects().clear();
		player.setHealth(6);
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(6);
		player.setFoodLevel(20);
		
		player.getInventory().setItem(8, new ItemBuilder(Material.BOOK).setName("§f§lHOW TO PLAY §7§o(RIGHTCLICK)").toItemStack());
		
		player.teleport(getPlugin().getLobbyLocationHandler().locations.get("LobbySpawn"));
		
		player.setWalkSpeed( (float) 0.5);
		
		LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player, "§c§lDaZ §8| §fLobby", getPlugin());
		getPlugin().lobbyscoreboards.put(player, lobbyScoreboard);
		
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		
		Player player = (Player) event.getEntity();
		
		if(getPlugin().getArenaManager().getArena(player) == null) {
			
			event.setCancelled(true);
			
		} else {
			
			if(getPlugin().getArenaManager().getArena(player).getState() == ArenaState.WAITING) {
				
				event.setCancelled(true);
				
			}
			
		}
		
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
