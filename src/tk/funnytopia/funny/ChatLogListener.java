package tk.funnytopia.funny;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ChatLogListener extends PlayerListener	{
	
private Admin admin;
	
	public ChatLogListener(Admin instance) {
		this.admin = instance;
	}
	
	public void onPlayerChat(PlayerChatEvent event){
		admin.chatLog(event.getPlayer().getName(), event.getMessage(), "");
	}

}
