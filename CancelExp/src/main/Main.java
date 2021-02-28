package main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	
	FileConfiguration config = getConfig(); // config 파일 설정
	
	@Override 
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("폭발 방지 플러그인 실행, 버전 : 0.0.1");
		this.getCommand("ac").setExecutor(new Commands()); // ac 커맨드의 class를 불러옴.
		
		config.addDefault("state", true); // 적용할지를 결정하는 config
		config.options().copyDefaults(true); // config 저장
		saveConfig();
		
		getServer().getPluginManager().registerEvents(new ExpListener(), this); // 폭발 관련 리스너 추가해주기.
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("폭발 방지 플러그인 종료");
		reloadConfig();
	}

}
