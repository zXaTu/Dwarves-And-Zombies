package hellwig.daz.game;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import hellwig.daz.game.objects.DwarvType;
import hellwig.daz.plugin.DaZPlugin;

public class DwarvTypeHandler {

	public DaZPlugin plugin;
	
	public Map<Player, DwarvType> dwarvtypes;
	
	public DwarvTypeHandler(DaZPlugin plugin) {
		
		this.plugin = plugin;
		
		dwarvtypes = new HashMap<Player, DwarvType>();
		
	}
	
	public void removePlayer(Player player) {
		
		if(getDwarvtypes().containsKey(player)) {
			
			getDwarvtypes().remove(player);
			
		}
		
	}
	
	public void player(Player player, DwarvType type) {
		
		if(getDwarvtypes().containsKey(player)) {
			
			getDwarvtypes().replace(player, type);
			getPlugin().gamescoreboards.get(player).update();
			
		} else {
			
			getDwarvtypes().put(player, type);
			getPlugin().gamescoreboards.get(player).update();
			
		}
		
	}
	
	public DwarvType getDwarvType(Player player) {
		
		return getDwarvtypes().get(player);
		
	}
	
	public Map<Player, DwarvType> getDwarvtypes() {
		return dwarvtypes;
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
