package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GCommand implements CommandExecutor {
	
private Admin admin;
	
	public GCommand(Admin instance) {
		this.admin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			//player
			if(sender.isOp() || sender.hasPermission("adminassistance.g")) {
				//perms
				if(args.length > 0) {
					//correct args
					final String combined = admin.combineSplit(0, args, " ");
					admin.tellAll("<" + "ADMIN" + ChatColor.WHITE + "> " + ChatColor.LIGHT_PURPLE + combined, "<" + sender.getName() + "> " + ChatColor.LIGHT_PURPLE + combined);
					admin.logInfo("<ADMIN>-chat by " + sender.getName() + ": " + combined);
				} else {
					//incorrect args
					sender.sendMessage(ChatColor.RED + "Incorrect usage! /g <message>");
				}
			} else {
				//no perms
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			}
		} else {
			//console
			if(args.length > 0) {
				//correct everything, console, do stuff
				final String combined = admin.combineSplit(0, args, " ");
				admin.tellAll("<" + "ADMIN" + ChatColor.WHITE + "> " + ChatColor.LIGHT_PURPLE + combined, "<*CONSOLE*> " + ChatColor.LIGHT_PURPLE + combined);
			} else {
				//incorrect args, console
				sender.sendMessage("Usage: /g <message>");
			}
		}
		
		return true;
	}

}
