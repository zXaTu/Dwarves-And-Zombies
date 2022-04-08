package hellwig.daz.lobby.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import hellwig.daz.plugin.DaZPlugin;

public class PlayerChatListener implements Listener {

	public DaZPlugin plugin;
	
	public PlayerChatListener(DaZPlugin plugin) {
		
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		String message = event.getMessage();
		event.setCancelled(true);
		
		if(getPlugin().getArenaManager().getArena(player) == null) {
			
			for(Player all : player.getWorld().getPlayers()) {
				
				all.sendMessage("§f" + player.getName() + " §8→ §f" + message);
				
			}
			
		} else {
			
			if(getPlugin().getDwarvTypeHandler().getDwarvType(player) != null) {
				
				switch(getPlugin().getDwarvTypeHandler().getDwarvType(player)) {
				case ALCHEMIST:
					for(Player all : player.getWorld().getPlayers()) {
						
						all.sendMessage("§f" + player.getName() + " §9the Alchemist §8→ §f" + message);
						
					}
					break;
				case BLACKSMITH:
					for(Player all : player.getWorld().getPlayers()) {
						
						all.sendMessage("§f" + player.getName() + " §cthe Blacksmith §8→ §f" + message);
						
					}
					break;
				case BUILDER:
					for(Player all : player.getWorld().getPlayers()) {
						
						all.sendMessage("§f" + player.getName() + " §7the Builder §8→ §f" + message);
						
					}
					break;
				case TAILOR:
					for(Player all : player.getWorld().getPlayers()) {
						
						all.sendMessage("§f" + player.getName() + " §athe Tailor §8→ §f" + message);
						
					}
					break;
				}
				
			} else {
				
				for(Player all : player.getWorld().getPlayers()) {
					
					all.sendMessage("§f" + player.getName() + " §fthe Dwarf §8→ §f" + message);
					
				}
				
			}
			
		}
		
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
