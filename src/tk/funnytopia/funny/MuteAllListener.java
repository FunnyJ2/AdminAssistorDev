package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class MuteAllListener extends PlayerListener {
	
private Admin admin;
	
	public MuteAllListener(Admin instance) {
		this.admin = instance;
	}
	
	ChatColor white = ChatColor.WHITE;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor gold = ChatColor.GOLD;
	
	public void onPlayerChat(PlayerChatEvent event){
		if(admin.misc.contains("muteall")){
			//heh
			if(!(event.getPlayer().hasPermission("adminassistor.overridemute")) && !(event.getPlayer().isOp())){
				//cancel
				event.setCancelled(true);
				String message = event.getMessage();
				admin.tellAdmin(gold + "[Muted]" + white + "<" + yellow + event.getPlayer().getName() + white + "> " + message);
				event.getPlayer().sendMessage(gold + "Can't do that while muted");
				admin.chatLog(event.getPlayer().getName(), message, "[Muted]");
			} else {
				//don't cancel
				event.getPlayer().sendMessage(gold + "Note: Regular users are muted");
			}
		} else {
			//noooothing
		}
	}
	
	public void onPlayerJoin(PlayerJoinEvent event){
		if(admin.misc.contains("muteall")) {
			if(!(event.getPlayer().hasPermission("adminassistor.overridemute"))){
				event.getPlayer().sendMessage(gold + "Note: All players are muted");
			} else {
				event.getPlayer().sendMessage(gold + "Note: Regular users are muted!");
			}
		} else {
			//nooooothing
		}
	}

}
