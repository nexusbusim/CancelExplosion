package com.khwoo;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;

public class Commands implements CommandExecutor{
	
	FileConfiguration config = Main.getPlugin(Main.class).getConfig(); // get config from Main class
	Plugin plugin = Bukkit.getPluginManager().getPlugin("CancelExplosion");

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String language = config.getString("lang"); // get language information
		
		if(cmd.getName().equalsIgnoreCase("ac")){
			if(args.length == 1) { 
				switch (args[0]) {
					case "info": // ac info | show information 
						if(sender.hasPermission(new Permission("ac.info"))) {
							sender.sendMessage("============\n");
							sender.sendMessage(config.getString(language.concat(".version"))); // show version information
							sender.sendMessage("\n");
							if(config.getBoolean("state")) {
								sender.sendMessage(config.getString(language.concat(".statusOn"))); // Enabled == Blue
							} else {
								sender.sendMessage(config.getString(language.concat(".statusOff"))); // Disabled == Red
							}
							sender.sendMessage("============\n");
							return true;
						}
						break;
						
					case "on": // ac on | enable plugin
						if(sender.hasPermission(new Permission("ac.switch"))) {
							config.set("state", true);
							Main.getPlugin(Main.class).saveConfig();
							sender.sendMessage(config.getString(language.concat(".statusOn"))); // switch ON
							return true;
						}
						break;
						
					case "off": // ac off | disable plugin
						if(sender.hasPermission(new Permission("ac.switch"))) {
							config.set("state", false);
							Main.getPlugin(Main.class).saveConfig();
							sender.sendMessage(config.getString(language.concat(".statusOff"))); // switch OFF
							return true;
						}
						break;
						
					case "reload": // ac reload | reload config file
						if(sender.hasPermission(new Permission("ac.reload"))) {
							Bukkit.getPluginManager().disablePlugin(plugin);
							Bukkit.getPluginManager().enablePlugin(plugin);	
							sender.sendMessage(config.getString(language.concat(".reload")));
							return true;
						}
						break;
						
					default:
						sender.sendMessage("YOU DONT HAVE PERMISSION FOR THIS");
						break;
				}
			} else {
				sender.sendMessage("WRONG COMMAND");
			}
		}
		
		return false;
	}
}
