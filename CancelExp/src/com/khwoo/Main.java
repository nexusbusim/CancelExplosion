package com.khwoo;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	
	FileConfiguration config = getConfig(); // initiate config object
	
	@Override 
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("���� ���� �÷����� ����, ���� : 0.0.4");
		this.getCommand("ac").setExecutor(new Commands()); // get command from Commands() class
		
		config.addDefault("state", true); // default config to true
		config.options().copyDefaults(true); // save config
		saveConfig();
		
		getServer().getPluginManager().registerEvents(new ExpListener(), this); // add listener to check explosion
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("���� ���� �÷����� ����");
		reloadConfig(); // reload config file 
	}

}
