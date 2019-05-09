package me.Viscar.ArmorDrop.Commands;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import me.Viscar.ArmorDrop.Main;

import org.bukkit.Bukkit;


public class Commands implements CommandExecutor {
	
	FileConfiguration config;
	public Commands(FileConfiguration config) {
		this.config = config;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			
			
			    if(label.equalsIgnoreCase("armordrop")) {
				Player p = (Player) sender;
				p.sendMessage(ChatColor.GREEN + "Armor drop chance: " + (config.getDouble("drop-chance") * 100) + "%");
				p.sendMessage(ChatColor.GREEN + "Max armor drop per death: " + config.getInt("max-num-drop"));

			}
		}
		return true;
	}
}
