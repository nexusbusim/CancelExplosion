package main;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;


public class ExpListener implements Listener{
	FileConfiguration config = Main.getPlugin(Main.class).getConfig();

	
	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		if(config.getBoolean("state")) {
			event.blockList().clear();
		}
	}
	
	@EventHandler
	public void onExplode(BlockExplodeEvent event) {
		if(config.getBoolean("state")) {
			event.blockList().clear();
		}
	}
}
