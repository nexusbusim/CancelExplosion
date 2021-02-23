package main;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Commands implements CommandExecutor{
	
	FileConfiguration config = Main.getPlugin(Main.class).getConfig(); // Main 클래스에서 불러온 getConfig를 사용함.
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("ac")) {
			if(args.length == 1) { // 명령어 길이가 1개
				if(args[0].equalsIgnoreCase("info")) {
					sender.sendMessage("============\n");
					sender.sendMessage("버전 : 0.0.1 폭발방지\n");
					if(config.getBoolean("state")) {
						sender.sendMessage("상태 :" + ChatColor.BLUE + " 켜짐\n"); // 켜짐 == BLUE
					} else {
						sender.sendMessage("상태 :" + ChatColor.RED + " 꺼짐\n"); // 꺼짐 == RED
					}
					sender.sendMessage("============\n");
					
					return true;
				} else if (args[0].equalsIgnoreCase("on")) {
						config.set("state", true);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("폭발방지"+ ChatColor.BLUE+ " 켜짐\n");
						
						return true;
				} else if (args[0].equalsIgnoreCase("off")) {
						config.set("state", false);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("폭발방지"+ ChatColor.RED + " 꺼짐\n");
					
					return true;
				}else {
					sender.sendMessage("잘못된 커맨드를 입력했습니다.");
				}
			} else {
				sender.sendMessage("잘못된 커맨드를 입력했습니다.");
			}
		}
		
		return false;
	}
}
