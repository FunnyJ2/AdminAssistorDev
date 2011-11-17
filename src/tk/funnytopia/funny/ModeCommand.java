package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModeCommand implements CommandExecutor {
	
private Admin admin;
	
	public ModeCommand(Admin instance) {
		this.admin = instance;
	}
	
	ChatColor RED = ChatColor.RED;
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		String derp = " (by: " + sender.getName() + ")";
		
		if(sender instanceof Player) {
			
			if(args.length == 1) {
				//only for themself
				if(sender.isOp() || sender.hasPermission("adminassistor.mode") || sender.hasPermission("adminassistor.mode.other")) {
					//perms for themself
					if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
						//set creative for themself
						((Player) sender).setGameMode(GameMode.CREATIVE);
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " is now in creative gamemode!" + derp);
					}
					
					else if(args[0].equalsIgnoreCase("survival")|| args[0].equalsIgnoreCase("s")) {
						//set survival for themself
						((Player) sender).setGameMode(GameMode.SURVIVAL);
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " is now in survival gamemode!"+ derp);
					} else {
						//unknown gamemode
						sender.sendMessage(RED + "You supplied an unknown gamemode, use survival/creative!");
					}
				} else {
					//no perms
					sender.sendMessage(RED + "You don't have permission to do this!");
				}
			}
			
			else if(args.length == 2) {
				//for someone else
				if(sender.hasPermission("adminassistance.mode.other") || sender.isOp()) {
					//perms for other
					if(args[0].equalsIgnoreCase("creative")|| args[0].equalsIgnoreCase("c")) {
						//creative - still needs the other player
						Player otherplayer = admin.getServer().getPlayer(args[1]);
						otherplayer.setGameMode(GameMode.CREATIVE);
						otherplayer.sendMessage(ChatColor.GOLD + "You were put in gamemode: creative by an admin");
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " put " + otherplayer.getName() + " in creative mode");
						admin.logInfo(sender.getName() + " put " + otherplayer.getName() + " in creative mode"+ derp);
					}
					
					else if(args[0].equalsIgnoreCase("survival")|| args[0].equalsIgnoreCase("s")) {
						//survival - still needs the player
						Player otherplayer = admin.getServer().getPlayer(args[1]);
						otherplayer.setGameMode(GameMode.SURVIVAL);
						otherplayer.sendMessage(ChatColor.GOLD + "You were put in gamemode: survival by an admin");
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " put " + otherplayer.getName() + " in survival mode");
						admin.logInfo(sender.getName() + " put " + otherplayer.getName() + " in survival mode"+ derp);
					} else {
						//odd gamemode
						sender.sendMessage(RED + "You supplied an invalid gamemode!");
					}
				
				//note to self:
				//mega
				//stop
				} else {
					//no perms - other
					sender.sendMessage(RED + "You don't have permission to do that!");
				}
			} else {
				//odd args
				sender.sendMessage(RED + "Wrong usage! /mode <mode> [player]");
			}
		
		} else {
			//console
			if(args.length == 1) {
				//error - cannot change own gamemode
				sender.sendMessage("Error: you cannot change the console's gamemode");
			}
			else if(args.length == 2) {
				//change someones mode
				if(args[0].equalsIgnoreCase("creative")|| args[0].equalsIgnoreCase("c")) {
					//set someone to creative
					Player otherplayer = admin.getServer().getPlayer(args[1]);
					otherplayer.setGameMode(GameMode.CREATIVE);
					otherplayer.sendMessage(ChatColor.GOLD + "You were put in gamemode: creative by CONSOLE");
					admin.tellAdmin(ChatColor.DARK_AQUA + "CONSOLE put " + otherplayer.getName() + " in creative mode");
				}
				else if(args[0].equalsIgnoreCase("survival")|| args[0].equalsIgnoreCase("s")) {
					//set someone to survival
					Player otherplayer = admin.getServer().getPlayer(args[1]);
					otherplayer.setGameMode(GameMode.SURVIVAL);
					otherplayer.sendMessage(ChatColor.GOLD + "You were put in gamemode: survival by CONSOLE");
					admin.tellAdmin(ChatColor.DARK_AQUA + "CONSOLE put " + otherplayer.getName() + " in survival mode");
				} else {
					//unknown gamemode
					sender.sendMessage("You supplied an unknown gamemode");
				}
			} else {
				//invalid args
				sender.sendMessage("usage: /mode <mode> <player>");
			}
			
		}
		
		
		return true;
	}

}
