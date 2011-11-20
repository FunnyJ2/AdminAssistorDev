package tk.funnytopia.funny;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class StixPlayerListener extends PlayerListener {
	
private Admin admin;
	
	public StixPlayerListener(Admin instance) {
		this.admin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		String playername = event.getPlayer().getName();
		Material material = event.getMaterial();
		
		if(admin.activePlayers.contains(playername)) {
			//player in hashmap
			if((event.getAction() == Action.RIGHT_CLICK_BLOCK) && material.equals(Material.STICK)) {
				//right click with stick, do stuff
				Block block = event.getClickedBlock();
				block.setType(Material.AIR);
			} else {
				//normal interaction, do absolutely nuthin'
			}
		} else {
			//player not in hashmap
		}
	}

}
