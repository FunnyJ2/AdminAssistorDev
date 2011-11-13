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
		
		if(sender instanceof Player) {
			//player
			if(sender.hasPermission("adminassistor.end")) {
				//player with perms
				if(args.length == 1) {
					//right args, do stuff
					Random rand;
					rand = new Random();
					int min = 0;
					int max = 2;
					
					int randomNum = rand.nextInt(max - min + 1) + min;
					
					if(randomNum == 0) {
						//killer 1
					}
					else if(randomNum == 1) {
						//killer 2
					}
					else if(randomNum == 2) {
						//killer 3
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
				int min = 0;
				int max = 2;
				
				int randomNum = rand.nextInt(max - min + 1) + min;
				
				if(randomNum == 0) {
					//killer 1
				}
				else if(randomNum == 1) {
					//killer 2
				}
				else if(randomNum == 2) {
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
