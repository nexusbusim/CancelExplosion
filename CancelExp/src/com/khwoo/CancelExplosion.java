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
		config.addDefault("ko.onEnable", "폭발 방지 플러그인 실행, 버전 : ".concat(version)); // .onEnable
		config.addDefault("ko.onDisable", "폭발 방지 플러그인 종료"); // .onDisable
		config.addDefault("ko.version", "버전 : ".concat(version)); // version information
		config.addDefault("ko.statusOn", "현재 상태 : 켜짐"); // on
		config.addDefault("ko.statusOff", "현재 상태 : 꺼짐"); // off
		config.addDefault("ko.reload", "설정파일 리로딩"); // reload
		
		
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
