package hellwig.daz.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hellwig.daz.plugin.DaZPlugin;

public class AdminSetLobbyLocation implements CommandExecutor {

	public DaZPlugin plugin;
	
	public AdminSetLobbyLocation(DaZPlugin plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("setlobbylocation").setExecutor(this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) return false;
		if(!(sender.hasPermission("daz.commands.admin"))) return false;
		
		Player player = (Player) sender;
		
		if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("LobbySpawn") || args[0].equalsIgnoreCase("Entity_Villager_1") || args[0].equalsIgnoreCase("Entity_Villager_2")
					|| args[0].equalsIgnoreCase("Entity_Villager_3") || args[0].equalsIgnoreCase("Entity_Help") || 
					args[0].equalsIgnoreCase("Entity_Unknown")) {
				
				getPlugin().getLobbyLocationHandler().saveLocation(args[0], player.getLocation());
				player.sendMessage("§aLocation §e" + args[0] + " §aset");
				
			} else {
				
				player.sendMessage("§e< LobbySpawn | Entity_Villager_1 | Entity_Villager_2 | Entity_Villager_3 >");
				return false;
				
			}
			
		} else {
			
			player.sendMessage("§cWrong Usage. §eUse /setlobbylocation <Location>");
			return false;
			
		}
		
		return true;
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
