package hellwig.daz.lobby.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import hellwig.daz.arena.Arena;
import hellwig.daz.arena.ArenaState;
import hellwig.daz.plugin.DaZPlugin;

public class PlayerInteractListener implements Listener {

	public DaZPlugin plugin;

	public PlayerInteractListener(DaZPlugin plugin) {

		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);

	}
	
	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {
		
		if(getPlugin().getArenaManager().getArena(event.getPlayer()) == null) {
			
			event.setCancelled(true);
			
		} else {
			
			if(getPlugin().getArenaManager().getArena(event.getPlayer()).getState() == ArenaState.WAITING) {
				
				event.setCancelled(true);
				
			}
			
		}
		
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		if (getPlugin().getArenaManager().getArena(event.getPlayer()) == null) {

			if (!event.getPlayer().isOp()) {
				event.setCancelled(true);
			} else {
				event.setCancelled(false);
			}

		} else {

			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {

				if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
						.equalsIgnoreCase("§c§lLeave arena §7§o(RIGHTCLICK)")) {

					event.setCancelled(true);
					getPlugin().getArenaManager().getArena(event.getPlayer()).removePlayer(event.getPlayer());

				}

				if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
						.equalsIgnoreCase("§f§lGet some discs")) {

					event.setCancelled(true);
					event.getPlayer().getInventory().setItemInMainHand(null);
					getPlugin().getGameHandler().discs(event.getPlayer());

				}
				
			}
			

		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteractVillager(PlayerInteractEntityEvent event) {

		if (event.getRightClicked() != null) {

			if (event.getRightClicked().getCustomName().equalsIgnoreCase("§f§lArena 1")) {

				event.setCancelled(true);

				for (Arena arena : getPlugin().getArenaManager().getArenaList()) {

					if (arena.getName().equalsIgnoreCase("Arena1")) {

						if (arena.getState() == ArenaState.WAITING) {

							arena.addPlayer(event.getPlayer());
							event.getPlayer().sendTitle(" ", "§aYou joined §fArena 1");

							new BukkitRunnable() {

								@Override
								public void run() {

									arena.player(event.getPlayer());

								}
							}.runTaskLater(getPlugin(), 15);

						}

					}

				}

			}

			if (event.getRightClicked().getCustomName().equalsIgnoreCase("§f§lArena 2")) {

				event.setCancelled(true);

				for (Arena arena : getPlugin().getArenaManager().getArenaList()) {

					if (arena.getName().equalsIgnoreCase("Arena2")) {

						if (arena.getState() == ArenaState.WAITING) {

							arena.addPlayer(event.getPlayer());
							event.getPlayer().sendTitle(" ", "§aYou joined §fArena 2");

							new BukkitRunnable() {

								@Override
								public void run() {

									arena.player(event.getPlayer());

								}
							}.runTaskLater(getPlugin(), 15);

						}

					}

				}

			}

			if (event.getRightClicked().getCustomName().equalsIgnoreCase("§f§lArena 3")) {

				event.setCancelled(true);

				for (Arena arena : getPlugin().getArenaManager().getArenaList()) {

					if (arena.getName().equalsIgnoreCase("Arena3")) {

						if (arena.getState() == ArenaState.WAITING) {

							arena.addPlayer(event.getPlayer());
							event.getPlayer().sendTitle(" ", "§aYou joined §fArena 3");

							new BukkitRunnable() {

								@Override
								public void run() {

									arena.player(event.getPlayer());

								}
							}.runTaskLater(getPlugin(), 15);

						}

					}

				}

			}

		}

	}

	public DaZPlugin getPlugin() {
		return plugin;
	}

}
