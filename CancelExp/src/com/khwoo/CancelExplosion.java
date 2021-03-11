package com.khwoo;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class CancelExplosion extends JavaPlugin{
	
	FileConfiguration config = getConfig(); // initiate config object

	
	@Override 
	public void onEnable() {
		this.getCommand("ac").setExecutor(new Commands()); // get command from Commands() class
		
		config.addDefault("lang", "ko"); // ko : korean , en : english
		
		String version = "0.0.5"; // version
		
		String language = config.getString("lang"); // get language information AFTER SET LANGUAGE CONFIG
		
		Bukkit.getConsoleSender().sendMessage(config.getString(language.concat(".onEnable"))); // onEnable

		config.addDefault("state", true); // default config to true

		// language support for english
		config.addDefault("en.onEnable", "CancelExplosion Enabled, version : ".concat(version)); // .onEnable
		config.addDefault("en.onDisable", "CancelExplosion Disabled"); // .onDisable
		config.addDefault("en.version", "Current version : ".concat(version)); // version information
		config.addDefault("en.statusOn", "Current status : ON"); // on
		config.addDefault("en.statusOff", "Current status : OFF"); // off
		config.addDefault("en.reload", "reloading config file"); // reload
		
		
		// language support for korean
		config.addDefault("ko.onEnable", "���� ���� �÷����� ����, ���� : ".concat(version)); // .onEnable
		config.addDefault("ko.onDisable", "���� ���� �÷����� ����"); // .onDisable
		config.addDefault("ko.version", "���� : ".concat(version)); // version information
		config.addDefault("ko.statusOn", "���� ���� : ����"); // on
		config.addDefault("ko.statusOff", "���� ���� : ����"); // off
		config.addDefault("ko.reload", "�������� ���ε�"); // reload
		
		
		config.options().copyDefaults(true); // save config
		saveConfig();
		
		getServer().getPluginManager().registerEvents(new ExpListener(), this); // add listener to check explosion
	}
	
	@Override
	public void onDisable() {
		String language = config.getString("lang"); // get language information
		Bukkit.getConsoleSender().sendMessage(config.getString(language.concat(".onDisable"))); // onDisable
		reloadConfig(); // reload config file 
	}

}
