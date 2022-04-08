package hellwig.daz.lobby.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import hellwig.daz.arena.Arena;
import hellwig.daz.arena.ArenaState;
import hellwig.daz.plugin.DaZPlugin;

public class PlayerMoveListener implements Listener {

	public DaZPlugin plugin;
	
	public PlayerMoveListener(DaZPlugin plugin) {
		
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
		
	}
	
	@EventHandler
	public void onFall(PlayerMoveEvent event) {
		
		if(getPlugin().getArenaManager().getArena(event.getPlayer()) != null) {
			
			Arena arena = getPlugin().getArenaManager().getArena(event.getPlayer());
			
			if(arena.getState() != ArenaState.RUNNING) {
				
				int y = arena.getLocations().getBlockY() + 48;
				
				if(event.getPlayer().getLocation().getBlockY() <= y) {
					
					event.getPlayer().teleport(arena.getLocations().clone().add(0, 51, 0));
					
				}
				
			}
			
		}
		
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
