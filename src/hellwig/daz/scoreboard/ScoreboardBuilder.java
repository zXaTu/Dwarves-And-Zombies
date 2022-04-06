package hellwig.daz.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import hellwig.daz.plugin.DaZPlugin;

public abstract class ScoreboardBuilder {

	protected final Scoreboard scoreboard;

	protected final Objective objective;

	protected final Player player;

	public DaZPlugin plugin;

	public ScoreboardBuilder(Player player, String displayName, DaZPlugin plugin) {
		this.player = player;
		if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard()))
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		this.scoreboard = player.getScoreboard();
		if (this.scoreboard.getObjective("display") != null)
			this.scoreboard.getObjective("display").unregister();
		this.plugin = plugin;
		this.objective = this.scoreboard.registerNewObjective("display", "dummy", displayName);
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		createScoreboard();
	}

	public abstract void createScoreboard();

	public abstract void update();

	public void delete() {

		this.player.getScoreboard().getObjective("display").unregister();
		
	}

	public void setDisplayName(String displayName) {
		this.objective.setDisplayName(displayName);
	}

	public void setScore(String content, int score) {
		Team team = getTeamByScore(score);
		if (team == null)
			return;
		team.setPrefix(content);
		showScore(score);
	}

	public void removeScore(int score) {
		hideScore(score);
	}

	private EntryName getEntryNameByScore(int score) {
		byte b;
		int i;
		EntryName[] arrayOfEntryName;
		for (i = (arrayOfEntryName = EntryName.values()).length, b = 0; b < i;) {
			EntryName name = arrayOfEntryName[b];
			if (score == name.getEntry())
				return name;
			b++;
		}
		return null;
	}

	private Team getTeamByScore(int score) {
		EntryName name = getEntryNameByScore(score);
		if (name == null)
			return null;
		Team team = this.scoreboard.getEntryTeam(name.getEntryName());
		if (team != null)
			return team;
		team = this.scoreboard.registerNewTeam(name.name());
		team.addEntry(name.getEntryName());
		return team;
	}

	private void showScore(int score) {
		EntryName name = getEntryNameByScore(score);
		if (name == null)
			return;
		if (this.objective.getScore(name.getEntryName()).isScoreSet())
			return;
		this.objective.getScore(name.getEntryName()).setScore(score);
	}

	private void hideScore(int score) {
		EntryName name = getEntryNameByScore(score);
		if (name == null)
			return;
		if (!this.objective.getScore(name.getEntryName()).isScoreSet())
			return;
		this.scoreboard.resetScores(name.getEntryName());
	}

	public DaZPlugin getPlugin() {
		return plugin;
	}
}
