package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShutDownCommand implements CommandExecutor {
	
private Admin admin;
	
	public ShutDownCommand(Admin instance) {
		this.admin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			//player
			if(sender.isOp() || sender.hasPermission("adminassister.shutdown")) {
				//perms
				if(args.length < 1) {
					//wrong args
					sender.sendMessage(ChatColor.RED + "Wrong usage! /shutdown <reason>");
				} else {
					//right args, do stuff
					final String combined = admin.combineSplit(0, args, " ");
					
					admin.tellAdmin(ChatColor.DARK_AQUA + "Server shutdown by " + sender.getName());
					admin.tellAdmin(ChatColor.DARK_AQUA + "Reason: " + combined);
					admin.logInfo("Server shutdown by " + sender.getName());
					admin.logInfo("Reason: " + combined);
					admin.getServer().broadcastMessage(ChatColor.DARK_PURPLE + "Server shutting down!");
					
					admin.getServer().savePlayers();
					admin.getServer().shutdown();
				}
			} else {
				//no perms
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			}
		} else {
			//console
			final String combined = admin.combineSplit(0, args, " ");
			
			admin.tellAdmin(ChatColor.DARK_AQUA + "Server shutdown by *CONSOLE*");
			admin.tellAdmin(ChatColor.DARK_AQUA + "Reason: " + combined);
			admin.getServer().broadcastMessage(ChatColor.DARK_PURPLE + "Server shutting down!");
			
			admin.getServer().savePlayers();
			admin.getServer().shutdown();
		}
		
		return true;
	}

}
