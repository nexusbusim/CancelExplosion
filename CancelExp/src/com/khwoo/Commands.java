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
					sender.sendMessage("���� : 0.0.4 ���߹���\n");
					if(config.getBoolean("state")) {
						sender.sendMessage("���� :" + ChatColor.BLUE + " ����\n"); // Enabled == Blue
					} else {
						sender.sendMessage("���� :" + ChatColor.RED + " ����\n"); // Disabled == Red
					}
					sender.sendMessage("============\n");
					
					return true;
				} else if (args[0].equalsIgnoreCase("on") && sender.hasPermission(acSwitch)) { //  /ac on
						config.set("state", true);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("���߹���"+ ChatColor.BLUE+ " ����\n");
						
						return true;
				} else if (args[0].equalsIgnoreCase("off") && sender.hasPermission(acSwitch)) { // /ac off
						config.set("state", false);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("���߹���"+ ChatColor.RED + " ����\n");
					
					return true;
				}else if (args[0].equalsIgnoreCase("reload")  && sender.hasPermission(acReload)) { // /ac reload
					Bukkit.getPluginManager().disablePlugin(plugin);
					Bukkit.getPluginManager().enablePlugin(plugin);
										
					sender.sendMessage("���߹��� �������� ���ε�\n");

					
					return true;
				} else {
					sender.sendMessage("�߸��� Ŀ�ǵ带 �Է��߰ų�, ������ �����ϴ�.");
				}
			} else {
				sender.sendMessage("�߸��� Ŀ�ǵ带 �Է��߽��ϴ�.");
			}
		}
		
		return false;
	}
}
