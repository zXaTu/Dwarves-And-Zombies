package hellwig.daz.game.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import hellwig.daz.plugin.DaZPlugin;

public class PlayerListener implements Listener {

	public DaZPlugin plugin;
	
	public PlayerListener(DaZPlugin plugin) {
		
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {
			
			Player player = (Player) event.getEntity();
			
			if(getPlugin().getArenaManager().getArena(player) == null) {
				
				event.setCancelled(true);
				
			} else {
				
				if(event.getCause() == DamageCause.FALL) {
					
					if(getPlugin().getArenaManager().nofalldamage.contains(player)) {
						
						event.setCancelled(true);
						
					} else {
						
						event.setCancelled(false);
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
