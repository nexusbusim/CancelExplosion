package main;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Commands implements CommandExecutor{
	
	FileConfiguration config = Main.getPlugin(Main.class).getConfig(); // Loading config from the Main class.
	Plugin plugin = Bukkit.getPluginManager().getPlugin("CancelExplosion");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Permission acInfo = new Permission("ac.info");
		Permission acSwitch = new Permission("ac.switch");
		Permission acReload = new Permission("ac.reload");
		
		if(cmd.getName().equalsIgnoreCase("ac")){
			if(args.length == 1) { // Check if only 1 parameter is given
				if(args[0].equalsIgnoreCase("info") && sender.hasPermission(acInfo)) {  // /ac info
					sender.sendMessage("============\n");
					sender.sendMessage("CancelExplosion, Version 0.0.1\n");
					if(config.getBoolean("state")) {
						sender.sendMessage("Status: " + ChatColor.BLUE + " ON\n"); // ON == BLUE
					} else {
						sender.sendMessage("Status: " + ChatColor.RED + " OFF\n"); // OFF == RED
					}
					sender.sendMessage("============\n");
					
					return true;
				} else if (args[0].equalsIgnoreCase("on") && sender.hasPermission(acSwitch)) { //  /ac on
						config.set("state", true);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("CancelExplosion turned "+ ChatColor.BLUE+ " ON\n");
						
						return true;
				} else if (args[0].equalsIgnoreCase("off") && sender.hasPermission(acSwitch)) { // /ac off
						config.set("state", false);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("CancelExplosion turned "+ ChatColor.RED + " OFF\n");
					
					return true;
				}else if (args[0].equalsIgnoreCase("reload")  && sender.hasPermission(acReload)) { // /ac reload
					Bukkit.getPluginManager().disablePlugin(plugin);
					Bukkit.getPluginManager().enablePlugin(plugin);
										
					sender.sendMessage("Reload CancelExplosion config\n");

					
					return true;
				} else {
					sender.sendMessage("You either entered the wrong command or your don't have enough permissions!");
				}
			} else {
				sender.sendMessage("You entered an incorrect command!");
			}
		}
		
		return false;
	}
}
