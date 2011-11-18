package tk.funnytopia.funny;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class EndCommand implements CommandExecutor {
	
private Admin admin;
	
	public EndCommand(Admin instance) {
		this.admin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		Player otherplayer = admin.getServer().getPlayer(args[0]);
		
		if(sender instanceof Player) {
			//player
			if(sender.hasPermission("adminassistor.end") || sender.isOp()) {
				//player with perms
				if(args.length == 1) {
					//right args, do stuff
					Random rand;
					rand = new Random();
					int number;
					number = 1+rand.nextInt(3);
					
					if(number == 1) {
						//killer 1
						otherplayer.sendMessage(ChatColor.GOLD + "I WILL END YOU!");
						((Player) otherplayer).setHealth(1);
						((Player) otherplayer).setFoodLevel(1);
						((Player) otherplayer).setFireTicks(1000);
						otherplayer.getWorld().strikeLightningEffect(otherplayer.getLocation());
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " ended " + otherplayer.getName());
						admin.logInfo(sender.getName() + " ended " + otherplayer.getName());
					}
					else if(number == 2) {
						//killer 2
						otherplayer.sendMessage("*Stab*");
						otherplayer.sendMessage(ChatColor.GRAY + "Whisper: " + ChatColor.RED + "The liberation of Roma has begun");
						((Player) otherplayer).setHealth(1);
						((Player) otherplayer).setFireTicks(1000);
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " ended " + otherplayer.getName());
						admin.logInfo(sender.getName() + " ended " + otherplayer.getName());
					}
					else if(number == 3) {
						//killer 3
						sender.sendMessage("burp");
					}
				} else {
					//wrong args
					sender.sendMessage(ChatColor.RED + "Usage: /end <player>");
				}
			} else {
				//no perms - player
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			}
		} 
		else if(sender instanceof ConsoleCommandSender) {
			//console
			if(args.length == 1) {
				//right args, do stuff
				Random rand;
				rand = new Random();
				int number;
				number = 1 + rand.nextInt(3);
				
				if(number == 1) {
					//killer 1
					otherplayer.sendMessage(ChatColor.GOLD + "I WILL END YOU!");
					((Player) otherplayer).setHealth(1);
					((Player) otherplayer).setFoodLevel(1);
					((Player) otherplayer).setFireTicks(1000);
					otherplayer.getWorld().strikeLightningEffect(otherplayer.getLocation());
					admin.tellAdmin(ChatColor.DARK_AQUA + "The Console ended " + otherplayer.getName());
				}
				else if(number == 2) {
					//killer 2
					otherplayer.sendMessage("*Stab*");
					otherplayer.sendMessage(ChatColor.GRAY + "Whisper: " + ChatColor.RED + "The liberation of Roma has begun");
					((Player) otherplayer).setHealth(1);
					((Player) otherplayer).setFireTicks(1000);
					admin.tellAdmin(ChatColor.DARK_AQUA + "The Console ended " + otherplayer.getName());
				}
				else if(number == 3) {
					//killer 3
					
				}
			}else{
				//wrong args
				sender.sendMessage("Usage: /end <player>");
			}
		} else {
			//unknown commandsender
			sender.sendMessage("Your monkey is hacked! if you see the turtles show them this code:");
			sender.sendMessage("trh85srth74s4673673srth673763srth63636s7rth63srth65367srt5h");
		}
		
		
		
		return true;
	}

}
