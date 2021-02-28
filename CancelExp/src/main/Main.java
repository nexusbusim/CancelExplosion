package main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	
	FileConfiguration config = getConfig(); // config ���� ����
	
	@Override 
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("���� ���� �÷����� ����, ���� : 0.0.1");
		this.getCommand("ac").setExecutor(new Commands()); // ac Ŀ�ǵ��� class�� �ҷ���.
		
		config.addDefault("state", true); // ���������� �����ϴ� config
		config.options().copyDefaults(true); // config ����
		saveConfig();
		
		getServer().getPluginManager().registerEvents(new ExpListener(), this); // ���� ���� ������ �߰����ֱ�.
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("���� ���� �÷����� ����");
		reloadConfig();
	}

}
