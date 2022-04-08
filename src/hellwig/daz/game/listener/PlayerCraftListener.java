package hellwig.daz.game.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import hellwig.daz.game.objects.DwarvType;
import hellwig.daz.game.objects.TransmuteBook;
import hellwig.daz.plugin.DaZPlugin;

public class PlayerCraftListener implements Listener {

	public DaZPlugin plugin;

	public PlayerCraftListener(DaZPlugin plugin) {

		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);

	}

	@EventHandler
	public void onInventoryClick(CraftItemEvent event) {

		if (getPlugin().getArenaManager().getArena((Player) event.getWhoClicked()) != null) {

			ItemStack result = event.getRecipe().getResult();

			if (result.getType() == Material.BREAD) {

				int amount = event.getRecipe().getResult().getAmount();

				if (getPlugin().getDwarvTypeHandler()
						.getDwarvType((Player) event.getWhoClicked()) == DwarvType.TAILOR) {

					TransmuteBook book = getPlugin().getGameHandler().getTransmuteBooks()
							.get((Player) event.getWhoClicked());
					book.setItems_to_craft(book.getItems_to_craft() - amount);
					book.checkForTransmute();

				}

			}

			if (result.getType() == Material.CLOCK) {

				int amount = event.getRecipe().getResult().getAmount();

				if (getPlugin().getDwarvTypeHandler()
						.getDwarvType((Player) event.getWhoClicked()) == DwarvType.BLACKSMITH) {

					TransmuteBook book = getPlugin().getGameHandler().getTransmuteBooks()
							.get((Player) event.getWhoClicked());
					book.setItems_to_craft(book.getItems_to_craft() - amount);
					book.checkForTransmute();

				}

			}

		}

	}

	public DaZPlugin getPlugin() {
		return plugin;
	}

}
