package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class StixCommand implements CommandExecutor{
	
private Admin admin;
	
	public StixCommand(Admin instance) {
		this.admin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player) {
			//player
			Player player = (Player) sender;
			String playerName = player.getName();
			if(sender.hasPermission("adminassistor.stix") || sender.isOp()) {
				//has perms
				if(admin.getConfig().getBoolean("enable-stix") == true) {
					//has perms, command enabled
					if(admin.activePlayers.contains(playerName)){
						//player already enabled, deactivate
						admin.activePlayers.remove(playerName);
						sender.sendMessage(ChatColor.DARK_PURPLE + "Stix disabled");
						admin.logInfo(sender.getName() + " disabled stix");
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " disabled stix");
					} else {
						//player disabled, enable here
						admin.activePlayers.add(playerName);
						sender.sendMessage(ChatColor.DARK_PURPLE + "Stix enabled, be careful");
						admin.logInfo(sender.getName() + " enabled stix");
						admin.tellAdmin(ChatColor.DARK_AQUA + sender.getName() + " enabled stix");
					}
				} else {
					//has perms, command disabled
					sender.sendMessage(ChatColor.RED + "no u(stix are disabled by the server owner)");
				}
			} else {
				//no perms
				sender.sendMessage(ChatColor.RED + "You don't have permission to do this");
			}
		} 
		else if(sender instanceof ConsoleCommandSender){
			//console
			sender.sendMessage("That command is not executable from the console!");
		} else {
			//unknown commandsender
			sender.sendMessage("Your monkey is hacked! if you see the turtles show them this code:");
			sender.sendMessage("trh85srth74s4673673srth673763srth63636s7rth63srth65367srt5h");
		}
		return true;
	}

}
