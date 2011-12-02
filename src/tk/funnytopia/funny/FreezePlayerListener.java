package tk.funnytopia.funny;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class FreezePlayerListener extends PlayerListener {
	
private Admin admin;
	
	public FreezePlayerListener(Admin instance) {
		this.admin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		String eventplayer = event.getPlayer().getName();
		if(admin.freeze.contains(eventplayer)) {
			//cancel here
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Can't do that while frozen!");
		}
	}
	
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		
		String eventplayer = event.getPlayer().getName();
		if(admin.freeze.contains(eventplayer)) {
			//cancel here
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Can't do that while frozen!");
		}
	}
	
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		String eventplayer = event.getPlayer().getName();
		if(admin.freeze.contains(eventplayer)) {
			//cancel here
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Can't do that while frozen!");
		}
	}
	
	public void onPlayerChat(PlayerChatEvent event) {
		String eventplayer = event.getPlayer().getName();
		if(admin.freeze.contains(eventplayer)) {
			//player in hashmap chats
			String msg = event.getMessage();
			admin.tellAdmin(ChatColor.DARK_AQUA + "Blocked message by " + eventplayer + ": " + msg);
			
			Random rand = new Random();
			int number;
			number = 1+rand.nextInt(10);
			
			if(number == 1) {
				event.setMessage("Lolz.");
			}
			else if(number == 2) {
				event.setMessage("This is funny!");
			}
			else if(number == 3) {
				event.setMessage("I wish i was a cool server-owner! :3");
			}
			else if(number == 4) {
				event.setMessage("Turtles are round");
			}
			else if(number == 5) {
				event.setMessage("I'm a kitty! purr :3");
			}
			else if(number == 6) {
				event.setMessage("This place is great!");
			}
			else if(number == 7) {
				event.setMessage("Hurr Durr");
			}
			else if(number == 8) {
				event.setMessage("How did i get here?");
			}
			else if(number == 9) {
				event.setMessage("My friends are lions");
			}
			else if(number == 10) {
				event.setMessage("<('_'<) (>'_')>");
			} else {
				//error lolz
				event.getPlayer().sendMessage("error lolz.");
				admin.logInfo("ZOMG THERE WAS A JAVA ERROR! ERROR: failed to generate random number(lolz)");
			}
		} else {
			//nothing.
		}
	}
	
}
