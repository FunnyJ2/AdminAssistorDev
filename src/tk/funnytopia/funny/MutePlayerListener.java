package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class MutePlayerListener extends PlayerListener {
	
private Admin admin;
	
	public MutePlayerListener(Admin instance) {
		this.admin = instance;
	}
	
	ChatColor white = ChatColor.WHITE;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor gold = ChatColor.GOLD;
	
	public void onPlayerChat(PlayerChatEvent event) {
		String eventplayername = event.getPlayer().getName();
		if(admin.mute.contains(eventplayername)) {
			event.setCancelled(true);
			String message = event.getMessage();
			admin.tellAdmin(gold + "[Muted]" + white + "<" + yellow + eventplayername + white + "> " + message);
			event.getPlayer().sendMessage(gold + "Can't do that while muted");
			admin.chatLog(eventplayername, message, "[Muted]");
		} else {
			//nothing
		}
	}

}
