package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PermCommand implements CommandExecutor {
	
private Admin admin;
	
	public PermCommand(Admin instance) {
		this.admin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		String perm=args[0];
		
		if(sender instanceof Player) {
			//player
			if(args.length == 1) {
				//check self
				if(sender.hasPermission(perm)) {
					//yep, has perms
					sender.sendMessage(ChatColor.GOLD + "Yep. You have the permission " + ChatColor.DARK_GREEN + perm);
				} else {
					//nope, no perms
					sender.sendMessage(ChatColor.GOLD + "Nope. You don't have the permission " + ChatColor.DARK_GREEN + perm);
				}
			} 
			else if(args.length == 2) {
				//check other
				Player otherplayer = admin.getServer().getPlayer(args[1]);
				if(otherplayer.hasPermission(perm)) {
					//yep, otherplayer has perm
					sender.sendMessage(ChatColor.GOLD + "Yep. " + otherplayer.getName() + " has the permission " + ChatColor.DARK_GREEN + perm);
				} else {
					//nope, other player doesn't have perms
					sender.sendMessage(ChatColor.GOLD + "Nope. " + otherplayer.getName() + "doesn't have the permission " + ChatColor.DARK_GREEN + perm);
				}
			} else {
				//invalid args
				sender.sendMessage(ChatColor.RED + "Usage: /perm <perm_node> [player]");
			}
		}
		else if(sender instanceof ConsoleCommandSender) {
			//console
			if(args.length == 1) {
				//nope, chuck testa
				sender.sendMessage("I'm afraid you can't check your perms as the console, try using /perm <perm_node> <player>");
			}
			else if(args.length == 2) {
				//check other
				Player otherplayer = admin.getServer().getPlayer(args[1]);
				if(otherplayer.hasPermission(perm)) {
					//yep, otherplayer has perm
					sender.sendMessage(ChatColor.GOLD + "Yep. " + otherplayer.getName() + " has the permission " + ChatColor.DARK_GREEN + perm);
				} else {
					//nope, other player doesn't have perms
					sender.sendMessage(ChatColor.GOLD + "Nope. " + otherplayer.getName() + "doesn't have the permission " + ChatColor.DARK_GREEN + perm);
				}
			} else {
				//invalid args
				sender.sendMessage("Usage: /perm <perm_node> [player]");
			}
		} else {
			//unknown commandsender
			sender.sendMessage("Unknown commandsender. Aborted.");
		}
		return true;
	}

}
