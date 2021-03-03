package com.khwoo;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Commands implements CommandExecutor{
	
	FileConfiguration config = Main.getPlugin(Main.class).getConfig(); // get config from Main class
	Plugin plugin = Bukkit.getPluginManager().getPlugin("CancelExplosion");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Permission acInfo = new Permission("ac.info");
		Permission acSwitch = new Permission("ac.switch");
		Permission acReload = new Permission("ac.reload");
		
		if(cmd.getName().equalsIgnoreCase("ac")){
			if(args.length == 1) { // check command argument length is 1
				if(args[0].equalsIgnoreCase("info") && sender.hasPermission(acInfo)) {  // /ac info
					sender.sendMessage("============\n");
					sender.sendMessage("버전 : 0.0.4 폭발방지\n");
					if(config.getBoolean("state")) {
						sender.sendMessage("상태 :" + ChatColor.BLUE + " 켜짐\n"); // Enabled == Blue
					} else {
						sender.sendMessage("상태 :" + ChatColor.RED + " 꺼짐\n"); // Disabled == Red
					}
					sender.sendMessage("============\n");
					
					return true;
				} else if (args[0].equalsIgnoreCase("on") && sender.hasPermission(acSwitch)) { //  /ac on
						config.set("state", true);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("폭발방지"+ ChatColor.BLUE+ " 켜짐\n");
						
						return true;
				} else if (args[0].equalsIgnoreCase("off") && sender.hasPermission(acSwitch)) { // /ac off
						config.set("state", false);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("폭발방지"+ ChatColor.RED + " 꺼짐\n");
					
					return true;
				}else if (args[0].equalsIgnoreCase("reload")  && sender.hasPermission(acReload)) { // /ac reload
					Bukkit.getPluginManager().disablePlugin(plugin);
					Bukkit.getPluginManager().enablePlugin(plugin);
										
					sender.sendMessage("폭발방지 설정파일 리로딩\n");

					
					return true;
				} else {
					sender.sendMessage("잘못된 커맨드를 입력했거나, 권한이 없습니다.");
				}
			} else {
				sender.sendMessage("잘못된 커맨드를 입력했습니다.");
			}
		}
		
		return false;
	}
}
