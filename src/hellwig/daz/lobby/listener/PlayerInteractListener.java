package hellwig.daz.lobby.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import hellwig.daz.arena.Arena;
import hellwig.daz.arena.ArenaState;
import hellwig.daz.game.objects.DwarvType;
import hellwig.daz.plugin.DaZPlugin;
import hellwig.daz.plugin.ItemBuilder;

public class PlayerInteractListener implements Listener {

	public DaZPlugin plugin;

	public PlayerInteractListener(DaZPlugin plugin) {

		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);

	}

	@SuppressWarnings("incomplete-switch")
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {

		if (event.getView().getTitle().equalsIgnoreCase("§f§lAchievements")) {

			event.setCancelled(true);

		}

		if (event.getInventory().getType() == InventoryType.WORKBENCH) {

			if (event.getClick() == ClickType.SHIFT_LEFT) {

				switch (getPlugin().getDwarvTypeHandler().getDwarvType((Player) event.getWhoClicked())) {

				case TAILOR:

					if (event.getCurrentItem().getType() == Material.BREAD) {

						if (getPlugin().getGameHandler().getTransmuteBooks().get((Player) event.getWhoClicked())
								.getItems_to_craft() != 0) {

							event.setCancelled(true);

						}

					}

					break;

				case BLACKSMITH:

					if (event.getCurrentItem().getType() == Material.CLOCK) {

						if (getPlugin().getGameHandler().getTransmuteBooks().get((Player) event.getWhoClicked())
								.getItems_to_craft() != 0) {

							event.setCancelled(true);

						}

					}

					break;

				}

			}

		}

	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {

		if (getPlugin().getArenaManager().getArena(event.getPlayer()) == null) {

			event.setCancelled(true);

		} else {

			if (getPlugin().getArenaManager().getArena(event.getPlayer()).getState() == ArenaState.WAITING) {

				event.setCancelled(true);

			}

		}

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		try {

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

					if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§9Alchemist")) {

						event.setCancelled(true);
						event.getPlayer().getInventory().setItemInMainHand(null);
						getPlugin().getDwarvTypeHandler().player(event.getPlayer(), DwarvType.ALCHEMIST);
						event.getPlayer().sendMessage("§aYou are now an §9alchemist");
						clearDiscs(event.getPlayer());

					}

					if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§7Builder")) {

						event.setCancelled(true);
						event.getPlayer().getInventory().setItemInMainHand(null);
						getPlugin().getDwarvTypeHandler().player(event.getPlayer(), DwarvType.BUILDER);
						event.getPlayer().sendMessage("§aYou are now an §7builder");
						clearDiscs(event.getPlayer());

					}

					if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§aTailor")) {

						event.setCancelled(true);
						event.getPlayer().getInventory().setItemInMainHand(null);
						getPlugin().getDwarvTypeHandler().player(event.getPlayer(), DwarvType.TAILOR);
						event.getPlayer().sendMessage("§aYou are now an §atailor");
						clearDiscs(event.getPlayer());

					}

					if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§cBlacksmith")) {

						event.setCancelled(true);
						event.getPlayer().getInventory().setItemInMainHand(null);
						getPlugin().getDwarvTypeHandler().player(event.getPlayer(), DwarvType.BLACKSMITH);
						event.getPlayer().sendMessage("§aYou are now an §cblacksmith");
						clearDiscs(event.getPlayer());

					}
					
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§f§lTransmute")) {
						
						event.setCancelled(true);
						getPlugin().getGameHandler().transmute(event.getPlayer(), getPlugin().getGameHandler().getTransmuteBooks().get(event.getPlayer()));
						
						
					}

				}

			}

		} catch (Exception e) {
		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteractVillager(PlayerInteractEntityEvent event) {

		try {
			if (event.getRightClicked() != null) {

				if (event.getRightClicked().getType() == EntityType.PANDA) {

					if (getPlugin().getArenaManager().getArena(event.getPlayer()) == null) {

						event.setCancelled(true);
						getPlugin().achivInventory.achievementInventory(event.getPlayer());

					}

				}

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
		} catch (Exception e) {
		}

	}

	private void clearDiscs(Player player) {

		player.getInventory().clear();
		player.getInventory().setItem(8, new ItemBuilder(Material.OAK_DOOR).setName("§c§LLeave arena §7§o(RIGHTCLICK)").toItemStack());
		

	}

	public DaZPlugin getPlugin() {
		return plugin;
	}

}
