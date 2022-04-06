package hellwig.daz.scoreboard;

import org.bukkit.entity.Player;

import hellwig.daz.plugin.DaZPlugin;

public class GameScoreboard extends ScoreboardBuilder {

	public DaZPlugin plugin;
	
	public GameScoreboard(Player player, String displayName, DaZPlugin plugin) {
		super(player, displayName, plugin);
		this.plugin = plugin;
	}

	@Override
	public void createScoreboard() {
		
		
		
	}

	@Override
	public void update() {
		
		
		
	}

	
	
}
