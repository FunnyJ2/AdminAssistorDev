package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {
	
private Admin admin;
	
	public MuteCommand(Admin instance) {
		this.admin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			//player
			if(sender.hasPermission("adminassistor.mute") || sender.isOp()) {
				//perms
				if(args.length == 1) {
					//correct args
					Player otherplayer = admin.getServer().getPlayer(args[0]);
					if(!(otherplayer.hasPermission("adminassistor.overridemute")) && !(otherplayer.isOp())) {
						//muteable
						if(admin.mute.contains(otherplayer.getName())) {
							//unmute
							admin.mute.remove(otherplayer.getName());
							admin.logInfo(sender.getName() + " unmuted " + otherplayer.getName());
							admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " unmuted " + otherplayer.getName());
							otherplayer.sendMessage(ChatColor.GOLD + "You were unmuted");
							admin.chatLog("ServerBot", otherplayer.getName() + " was unmuted by " + sender.getName(), "INFO");
						} else {
							//mute
							admin.mute.add(otherplayer.getName());
							admin.logInfo(sender.getName() + " muted " + otherplayer.getName());
							admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " muted " + otherplayer.getName());
							otherplayer.sendMessage(ChatColor.GOLD + "You were muted");
							admin.chatLog("ServerBot", otherplayer.getName() + " was muted by " + sender.getName(), "INFO");
						}
					} else {
						//cannot mute that player
						sender.sendMessage(ChatColor.RED + "You cannot mute that player!");
						otherplayer.sendMessage(ChatColor.GOLD + sender.getName() + " tried to mute you!");
					}
				} else {
					//nope, wrong args
					sender.sendMessage(ChatColor.RED + "Usage: /mute <player>");
				}
			} else {
				//no perms
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			}
		} else {
			//console
			sender.sendMessage("console support is yet to be added");
		}
		return true;
	}

}
