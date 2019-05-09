package me.Viscar.ArmorDrop.Listeners;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArmorDropListener implements Listener {
	
	FileConfiguration config;
	public ArmorDropListener(FileConfiguration config) {
		this.config = config;
	}
	
	private HashMap<String, ItemStack[]> toBeKept = new HashMap<>();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			PlayerInventory inv = p.getInventory();
			List<ItemStack> d = e.getDrops();
			ItemStack[] armor = inv.getArmorContents();
			ItemStack[] armorToKeep = new ItemStack[4];
			
			int index = 0;
			int maxDropCounter = 0;
			for(ItemStack i: armor) {
				double k = Math.random();
				if(k < config.getDouble("drop-chance") && maxDropCounter < config.getInt("max-num-drop")) {
					armorToKeep[index] = i;
					index++;
					d.remove(i);
					maxDropCounter++;
				} else index++;
		}
			toBeKept.put(p.getName(), armorToKeep);
		}
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if(toBeKept.containsKey(p.getName())) {
				p.getInventory().setArmorContents(toBeKept.get(p.getName()));
				toBeKept.remove(p.getName());
				p.sendMessage(ChatColor.GREEN + "Enjoy whatever armor ya got left, nerd. Don't die next time.");
		}
	}
}