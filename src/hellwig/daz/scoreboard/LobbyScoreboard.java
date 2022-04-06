package hellwig.daz.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import hellwig.daz.arena.Arena;
import hellwig.daz.arena.ArenaState;
import hellwig.daz.plugin.DaZPlugin;

public class LobbyScoreboard extends ScoreboardBuilder {

	public DaZPlugin plugin;
	
	public BukkitTask task;

	public LobbyScoreboard(Player player, String displayName, DaZPlugin plugin) {
		super(player, displayName, plugin);
		this.plugin = plugin;
		run();
	}

	@Override
	public void createScoreboard() {

		setScore("", 14);
		setScore("§c§l● §fArenas", 13);
		setScore("", 12);
		setScore("§a§l■ §f§lArena 1", 11);
		setScore("§a§l■ §7Status : §6§lWAITING", 10);
		setScore("§a§l■ §7Players : §fNaN", 9);
		setScore("", 8);
		setScore("§9§l■ §f§lArena 2", 7);
		setScore("§9§l■ §7Status : §6§lWAITING", 6);
		setScore("§9§l■ §7Players : §fNaN", 5);
		setScore("", 4);
		setScore("§d§l■ §f§lArena 3", 3);
		setScore("§d§l■ §7Status : §6§lWAITING", 2);
		setScore("§d§l■ §7Players : §fNaN", 1);
		setScore("", 0);

	}

	@Override
	public void update() {

		for (Arena arena : getPlugin().getArenaManager().getArenaList()) {

			if (arena.getName().equals("Arena1")) {

				int players = arena.getPlayers().size();
				ArenaState state = arena.getState();

				if (state == ArenaState.WAITING) {

					setScore("§a§l■ §7Status : §6§lWAITING", 10);
					setScore("§a§l■ §7Players : §f" + players + "/25", 9);

				}

				if (state == ArenaState.RUNNING) {

					setScore("§a§l■ §7Status : §a§lRUNNING", 10);
					setScore("§a§l■ §7Players : §f" + players + "/25", 9);

				}

				if (state == ArenaState.ENDING) {

					setScore("§a§l■ §7Status : §c§lENDING", 10);
					setScore("§a§l■ §7Players : §f" + players + "/25", 9);

				}

			}

			if (arena.getName().equals("Arena2")) {

				int players = arena.getPlayers().size();
				ArenaState state = arena.getState();

				if (state == ArenaState.WAITING) {

					setScore("§9§l■ §7Status : §6§lWAITING", 6);
					setScore("§9§l■ §7Players : §f" + players + "/25", 5);

				}

				if (state == ArenaState.RUNNING) {

					setScore("§9§l■ §7Status : §a§lRUNNING", 6);
					setScore("§9§l■ §7Players : §f" + players + "/25", 5);

				}

				if (state == ArenaState.ENDING) {

					setScore("§9§l■ §7Status : §c§lENDING", 6);
					setScore("§9§l■ §7Players : §f" + players + "/25", 5);

				}

			}

			if (arena.getName().equals("Arena3")) {

				int players = arena.getPlayers().size();
				ArenaState state = arena.getState();

				if (state == ArenaState.WAITING) {

					setScore("§d§l■ §7Status : §6§lWAITING", 2);
					setScore("§d§l■ §7Players : §f" + players + "/25", 1);

				}

				if (state == ArenaState.RUNNING) {

					setScore("§d§l■ §7Status : §a§lRUNNING", 2);
					setScore("§d§l■ §7Players : §f" + players + "/25", 1);

				}

				if (state == ArenaState.ENDING) {

					setScore("§d§l■ §7Status : §c§lENDING", 2);
					setScore("§d§l■ §7Players : §f" + players + "/25", 1);

				}

			}

		}

	}
	
	public void cancel() {
		
		this.task.cancel();
		
	}

	private void run() {

		task = Bukkit.getScheduler().runTaskTimerAsynchronously(getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				update();
				
				
			}
		}, 0, 5);

	}

}
