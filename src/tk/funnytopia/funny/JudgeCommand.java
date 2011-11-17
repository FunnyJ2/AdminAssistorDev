package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JudgeCommand extends Admin {
	
private Admin admin;
	
	public JudgeCommand(Admin instance) {
		this.admin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			//player
			if(args.length == 1) {
				//right args
				if(sender.isOp() || sender.hasPermission("adminassistor.judge")) {
					//right perms
					Player otherplayer = admin.getServer().getPlayer(args[0]);
					otherplayer.getWorld().strikeLightningEffect(otherplayer.getLocation());
					admin.tellAdmin(ChatColor.DARK_AQUA + otherplayer.getName() + " was judged by " + sender.getName());
					admin.logInfo(otherplayer.getName() + " was judged by " + sender.getName());
					otherplayer.sendMessage(ChatColor.DARK_GREEN + "You were judged!");
				} else {
					//no perms
					sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
				}
			} else {
				//wrong arguments
				sender.sendMessage(ChatColor.RED + "/judge <player>");
			}
			
		} else {
			//console start
			if(args.length == 1) {
				//correct args, console
				Player otherplayer = admin.getServer().getPlayer(args[0]);
				otherplayer.getWorld().strikeLightningEffect(otherplayer.getLocation());
				admin.tellAdmin(ChatColor.DARK_AQUA + otherplayer.getName() + " was judged by *CONSOLE*");
				otherplayer.sendMessage(ChatColor.DARK_GREEN + "You were judged!");
			} else {
				//wrong args, console
				sender.sendMessage("Too few arguments! /judge <player>");
			}
		}
		
		return true;
	}

}
