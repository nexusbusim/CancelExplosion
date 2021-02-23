package main;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Commands implements CommandExecutor{
	
	FileConfiguration config = Main.getPlugin(Main.class).getConfig(); // Main Ŭ�������� �ҷ��� getConfig�� �����.
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("ac")) {
			if(args.length == 1) { // ��ɾ� ���̰� 1��
				if(args[0].equalsIgnoreCase("info")) {
					sender.sendMessage("============\n");
					sender.sendMessage("���� : 0.0.1 ���߹���\n");
					if(config.getBoolean("state")) {
						sender.sendMessage("���� :" + ChatColor.BLUE + " ����\n"); // ���� == BLUE
					} else {
						sender.sendMessage("���� :" + ChatColor.RED + " ����\n"); // ���� == RED
					}
					sender.sendMessage("============\n");
					
					return true;
				} else if (args[0].equalsIgnoreCase("on")) {
						config.set("state", true);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("���߹���"+ ChatColor.BLUE+ " ����\n");
						
						return true;
				} else if (args[0].equalsIgnoreCase("off")) {
						config.set("state", false);
						Main.getPlugin(Main.class).saveConfig();
						sender.sendMessage("���߹���"+ ChatColor.RED + " ����\n");
					
					return true;
				}else {
					sender.sendMessage("�߸��� Ŀ�ǵ带 �Է��߽��ϴ�.");
				}
			} else {
				sender.sendMessage("�߸��� Ŀ�ǵ带 �Է��߽��ϴ�.");
			}
		}
		
		return false;
	}
}
