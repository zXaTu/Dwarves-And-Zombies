package hellwig.daz.arena.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hellwig.daz.plugin.DaZPlugin;

public class StartArenaCommand implements CommandExecutor {

	public DaZPlugin plugin;
	
	public StartArenaCommand(DaZPlugin plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("start").setExecutor(this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) return false;
		if(!(sender.hasPermission("daz.startarena"))) return false;
		if(!(args.length == 0)) return false;
		
		Player player = (Player) sender;
		
		if(getPlugin().getArenaManager().getArena(player) != null) {
			
			getPlugin().getArenaManager().getArena(player).startCountdown();
			
			for(Player all : getPlugin().getArenaManager().getArena(player).getPlayers()) {
				
				all.sendMessage("§f" + player.getName() + " §astarted the game!");
				
			}
			
		}
		
		return true;
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
