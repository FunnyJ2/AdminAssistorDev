package tk.funnytopia.funny;

import java.net.InetSocketAddress;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhoisCommand implements CommandExecutor {
	
private Admin admin;
	
	public WhoisCommand(Admin instance) {
		this.admin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			//player
			if(sender.hasPermission("adminassister.whois")) {
				//has perms
				if(args.length == 1) {
					//do stuff
					Player otherplayer = admin.getServer().getPlayer(args[0]);
					
					InetSocketAddress IP = otherplayer.getAddress();
					int X = (int) otherplayer.getLocation().getX();
					int Y = (int) otherplayer.getLocation().getY();
					int Z = (int) otherplayer.getLocation().getZ();
					World world = otherplayer.getWorld();
					
					admin.logInfo("Player: "+sender.getName()+" whois'd "+otherplayer.getName());
					admin.tellAdmin(ChatColor.DARK_AQUA+sender.getName()+" whois'd "+otherplayer.getName());
					
					sender.sendMessage(ChatColor.GREEN+"--"+otherplayer.getName()+"--");
					sender.sendMessage(ChatColor.GREEN+"IP adress: "+ IP);
					sender.sendMessage(ChatColor.GREEN+"Location: " + X + "," + Y + "," + Z);
					sender.sendMessage(ChatColor.GREEN+"World: "+ world.getName());
				} else {
					//wrong args
					sender.sendMessage(ChatColor.RED + "Wrong usage! /whois <player>");
				}
			} else {
				//no perms
				sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			}
		} else {
			//console
			if(args.length == 1) {
				//right args, do stuff
				Player otherplayer = admin.getServer().getPlayer(args[0]);
				
				InetSocketAddress IP = otherplayer.getAddress();
				int X = (int) otherplayer.getLocation().getX();
				int Y = (int) otherplayer.getLocation().getY();
				int Z = (int) otherplayer.getLocation().getZ();
				World world = otherplayer.getWorld();
				
				sender.sendMessage(ChatColor.GREEN+"--"+otherplayer.getName()+"--");
				sender.sendMessage(ChatColor.GREEN+"IP adress: "+ IP);
				sender.sendMessage(ChatColor.GREEN+"Location: " + X + "," + Y + "," + Z);
				sender.sendMessage(ChatColor.GREEN+"World: "+ world.getName());
			} else {
				//wrong args
				sender.sendMessage("usage: /whois <player>");
			}
		}
		
		return true;
	}

}
