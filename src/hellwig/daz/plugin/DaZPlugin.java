package hellwig.daz.plugin;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import hellwig.daz.arena.ArenaManager;
import hellwig.daz.game.GameHandler;
import hellwig.daz.lobby.commands.AdminSetLobbyLocation;
import hellwig.daz.lobby.listener.PlayerInteractListener;
import hellwig.daz.lobby.listener.PlayerJoinListener;
import hellwig.daz.lobby.managment.LobbyHandler;
import hellwig.daz.lobby.managment.LobbyLocationHandler;
import hellwig.daz.scoreboard.LobbyScoreboard;

public class DaZPlugin extends JavaPlugin {

	// ###########
	// ## Lobby ##
	// ###########
	
	// [LobbyLocationHandler]
	public LobbyLocationHandler lobbyLocationHandler;
	// [LobbyHandler]
	public LobbyHandler lobbyHandler;
	
	// ###########
	// ## Arena ##
	// ###########
	
	// [ArenaManager]
	public ArenaManager arenaManager;
	
	// ##########
	// ## Game ##
	// ##########
	public GameHandler gameHandler;
	
	// #################
	// ## Scoreboards ##
	// #################
	public HashMap<Player, LobbyScoreboard> lobbyscoreboards;
	
	// # Enable
	@Override
	public void onEnable() {
		
		// # General
		
		saveDefaultConfig();
		
		// # Classes
		
		lobbyLocationHandler = new LobbyLocationHandler(this);
		lobbyHandler = new LobbyHandler(this);
		arenaManager = new ArenaManager(this);
		gameHandler = new GameHandler(this);
		
		// # Listener
		
		new PlayerJoinListener(this);
		new PlayerInteractListener(this);
		
		// # Commands
		
		new AdminSetLobbyLocation(this);
		
		// # Scoreboards
		lobbyscoreboards = new HashMap<Player, LobbyScoreboard>();

		
		Bukkit.getWorld("world").setGameRule(GameRule.DO_WEATHER_CYCLE, false);
		Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		Bukkit.getWorld("world").setTime(2500);
		Bukkit.getWorld("world").setPVP(false);
		Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
		
	}
	
	// # Disable
	@Override
	public void onDisable() {
		
		getLobbyHandler().entitys.values().forEach(entity -> entity.remove());
		
		getArenaManager().deleteWorld(Bukkit.getWorld("Arena1"));
		getArenaManager().deleteWorld(Bukkit.getWorld("Arena2"));
		getArenaManager().deleteWorld(Bukkit.getWorld("Arena3"));
		
		
	}
	
	public Integer getRandom(int min, int max) {

		Random random = new Random();
		int amount = random.nextInt(min, max);
		
		return amount;
	}
	
	public GameHandler getGameHandler() {
		return gameHandler;
	}
	
	public LobbyLocationHandler getLobbyLocationHandler() {
		return lobbyLocationHandler;
	}
	
	public ArenaManager getArenaManager() {
		return arenaManager;
	}
	
	public LobbyHandler getLobbyHandler() {
		return lobbyHandler;
	}
	
}
