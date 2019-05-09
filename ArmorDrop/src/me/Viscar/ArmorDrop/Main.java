package me.Viscar.ArmorDrop;

import java.util.logging.Logger;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Viscar.ArmorDrop.Commands.Commands;
import me.Viscar.ArmorDrop.Listeners.ArmorDropListener;


public class Main extends JavaPlugin {
	
	Logger pluginLogger = Bukkit.getLogger();
	public void onEnable() 
	{
		pluginLogger.info("[!] ArmorDrop booting up");
		this.saveDefaultConfig();
		final FileConfiguration config = this.getConfig();
		this.getCommand("armordrop").setExecutor((CommandExecutor)new Commands(config));
		getServer().getPluginManager().registerEvents(new ArmorDropListener(config), this);
	}
	public void onDisable() 
	{
		pluginLogger.info("[!] ArmorDrop shutting down");
	}
}

