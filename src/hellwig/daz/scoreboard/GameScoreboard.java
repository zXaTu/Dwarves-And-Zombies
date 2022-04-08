package hellwig.daz.scoreboard;

import org.bukkit.entity.Player;

import hellwig.daz.arena.ArenaState;
import hellwig.daz.plugin.DaZPlugin;

public class GameScoreboard extends ScoreboardBuilder {

	public DaZPlugin plugin;
	
	public GameScoreboard(Player player, String displayName, DaZPlugin plugin) {
		super(player, displayName, plugin);
		this.plugin = plugin;
	}

	@Override
	public void createScoreboard() {
		
		setScore("", 3);
		setScore("§b§l■ §f§lDwarfType", 2);
		setScore("§b§l■ §7§oNone", 1);
		setScore("", 0);
		
		
	}

	@Override
	public void update() {

		if(getPlugin().getArenaManager().getArena(player).getState() == ArenaState.RUNNING) {
			
			setScore("", 6);
			setScore("§9§l■ §f§lShrine", 5);
			setScore("§9§l■ " + getPlugin().getArenaManager().getShrines().get(getPlugin().getArenaManager().getArena(player)).getShrineProcess(), 4);
			
		}
		
		if(getPlugin().getDwarvTypeHandler().getDwarvType(player) != null) {
			
			switch(getPlugin().getDwarvTypeHandler().getDwarvType(player)) {
			case ALCHEMIST:
				setScore("§b§l■ §9§l§oAlchemist", 1);
				break;
			case BLACKSMITH:
				setScore("§b§l■ §c§l§oBlacksmith", 1);
				break;
			case BUILDER:
				setScore("§b§l■ §7§l§oBuilder", 1);
				break;
			case TAILOR:
				setScore("§b§l■ §a§l§oTailor", 1);
				break;
			}
			
		} else {
			
			setScore("§a§l■ §7§oNone", 1);
			
		}
		
	}

	
	
}
