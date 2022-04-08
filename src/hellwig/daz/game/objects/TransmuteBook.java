package hellwig.daz.game.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import hellwig.daz.plugin.DaZPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TransmuteBook {

	public DaZPlugin plugin;
	
	public Player player;
	public DwarvType type;
	
	public BukkitTask task;
	public BukkitTask cooldown;
	
	public String actionbar_text;
	
	public int items_to_craft;
	
	public boolean use;
	public boolean cooleddown;
	
	public TransmuteBook(Player player, DaZPlugin plugin) {
		
		this.player = player;
		this.plugin = plugin;
		
		type = getPlugin().getDwarvTypeHandler().getDwarvType(player);
		
		use = false;
		cooleddown = false;
		
		items_to_craft = 3;
		
	}
	
	public void run() {
		
		task = Bukkit.getScheduler().runTaskTimerAsynchronously(getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				if(!use) {
					
					player.spigot()
					.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionbar_text
							.replace("%a", String.valueOf(items_to_craft))));
					
				} 
				
				if(cooleddown) {
					
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aYou can use the transmute spell"));
					
				}
				
			}
		}, 0, 10);
		
	}
	
	public void cooldown(int cdown) {
		
		cooleddown = false;
		
		cooldown = Bukkit.getScheduler().runTaskTimerAsynchronously(getPlugin(), new Runnable() {
			
			int coun = cdown;
			
			@Override
			public void run() {
				
				coun--;
				player.spigot()
				.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7You can use the transmute spell again in §e" +
				coun + " §7second(s)"));
				
				if(coun == 0) {
					
					cooldown.cancel();
					cooleddown = true;
					
					
				}
				
				
			}
		}, 0, 20);
		
	}
	
	public void checkForTransmute() {
		
		if(items_to_craft == 0) {
			
			use = true;
			cooleddown = true;
			
		}
		
	}

	public int getItems_to_craft() {
		return items_to_craft;
	}

	public void setItems_to_craft(int items_to_craft) {
		this.items_to_craft = items_to_craft;
	}

	public boolean isCooleddown() {
		return cooleddown;
	}

	public void setCooleddown(boolean cooleddown) {
		this.cooleddown = cooleddown;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public DwarvType getType() {
		return type;
	}

	public void setType(DwarvType type) {
		this.type = type;
	}

	public BukkitTask getTask() {
		return task;
	}

	public void setTask(BukkitTask task) {
		this.task = task;
	}

	public BukkitTask getCooldown() {
		return cooldown;
	}

	public void setCooldown(BukkitTask cooldown) {
		this.cooldown = cooldown;
	}

	public String getActionbar_text() {
		return actionbar_text;
	}

	public void setActionbar_text(String actionbar_text) {
		this.actionbar_text = actionbar_text;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public void setPlugin(DaZPlugin plugin) {
		this.plugin = plugin;
	}

	public DaZPlugin getPlugin() {
		return plugin;
	}
	
}
