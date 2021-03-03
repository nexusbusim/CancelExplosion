package main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	
	FileConfiguration config = getConfig(); // Gets the config
	
	@Override 
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("CancelExplosion running, version: 0.0.2");
		this.getCommand("ac").setExecutor(new Commands()); // Call class of ac command.
		
		config.addDefault("state", true); // Sets the default value of state to 'true'
		config.options().copyDefaults(true); // Saves config
		saveConfig();
		
		getServer().getPluginManager().registerEvents(new ExpListener(), this); // Adding explosion listener, to listen for explosions.
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("CancelExplosion shutting down");
		reloadConfig(); // Saving data to config file, that will be applied for next time
	}

}
