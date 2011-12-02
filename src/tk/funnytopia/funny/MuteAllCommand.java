package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteAllCommand implements CommandExecutor {
	
private Admin admin;
	
	public MuteAllCommand(Admin instance) {
		this.admin = instance;
	}
	
	ChatColor white = ChatColor.WHITE;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor gold = ChatColor.GOLD;
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			//player
			if(sender.hasPermission("adminassistor.muteall") || sender.isOp()) {
				//perms
				if(admin.misc.contains("muteall")) {
					//unmute
					admin.misc.remove("muteall");
					admin.tellAll(gold + "Someone unmuted all players!", gold + sender.getName() + " unmuted all players!");
					admin.logInfo(sender.getName() + " unmuted all players!");
					admin.chatLog("ServerBot", "The server was unmuted by " + sender.getName(), "INFO");
				} else {
					//mute
					admin.misc.add("muteall");
					admin.tellAll(gold + "Someone muted all players!", gold + sender.getName() + " muted all players!");
					admin.logInfo(sender.getName() + " muted all players!");
					admin.chatLog("ServerBot", "The server was muted by " + sender.getName(), "INFO");
				}
			} else {
				//no perms
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			}
		} else {
			//console
			if(admin.misc.contains("muteall")) {
				//unmute
				admin.misc.remove("muteall");
				admin.tellAll(gold + "Someone unmuted all players!", gold + "CONSOLE unmuted all players!");
			} else {
				//mute
				admin.misc.add("muteall");
				admin.tellAll(gold + "Someone muted all players!", gold + "CONSOLE muted all players!");
			}
		}
		return true;
	}

}
