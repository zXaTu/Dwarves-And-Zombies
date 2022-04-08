package hellwig.daz.game.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import hellwig.daz.arena.Arena;
import hellwig.daz.game.objects.DwarfShrine;
import hellwig.daz.plugin.DaZPlugin;

public class PlayerBreakBlockListener implements Listener {

	public DaZPlugin plugin;
	
	public PlayerBreakBlockListener(DaZPlugin plugin) {
		
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		Player player = (Player) event.getPlayer();
		
		if(getPlugin().getArenaManager().getArena(player) != null) {
			
			Arena arena = getPlugin().getArenaManager().getArena(player);
			DwarfShrine shrine = getPlugin().getArenaManager().getShrines().get(arena);
			
			if(shrine.getShrineBlocks().contains(event.getBlock())) {
				
				shrine.getShrineBlocks().remove(event.getBlock());
				event.setDropItems(false);
				
				for(Player all : arena.getPlayers()) {
					
					getPlugin().gamescoreboards.get(all).update();
					
					if(shrine.getShrineBlocks().size() >= 1) {
						
						all.sendTitle("§c§lThe shrine is attacked", "§fA monster has destroyed a shrine block");
						all.playSound(all.getEyeLocation(), Sound.ENTITY_GHAST_SCREAM, 1, 1);
						
					}
					
					if(shrine.getShrineBlocks().size() == 0) {
						
						all.getInventory().clear();
						all.playSound(all.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
						all.setGameMode(GameMode.SPECTATOR);
						all.sendTitle("§a§lThe monsters have won", "§fThe Dwarf Shrine was destroyed");
						
						new BukkitRunnable() {
							
							@Override
							public void run() {
								
								arena.finish();
								
							}
						}.runTaskLater(getPlugin(), 100);
						
						
					}
					
					
				}
				
				
			}
			
			
		}
		
	}
	
	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
