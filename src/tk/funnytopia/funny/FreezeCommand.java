package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {
	
private Admin admin;
	
	public FreezeCommand(Admin instance) {
		this.admin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			//player
			Player otherplayer = admin.getServer().getPlayer(args[0]);
			String otherpn = otherplayer.getName();
			if(sender.hasPermission("adminassistor.freeze") || sender.isOp()) {
				//perms
				if(args.length == 1) {
					//right args
					
					if(!(otherpn == sender.getName())) {
					
						if(admin.freeze.contains(otherpn)) {
							//delete from hash
							admin.freeze.remove(otherpn);
							admin.tellAdmin(ChatColor.DARK_AQUA + otherpn + " was unfrozen by " + sender.getName());
							admin.logInfo(otherpn + " was unfrozen by " + sender.getName());
							otherplayer.sendMessage(ChatColor.RED + "You were unfrozen!");
						} else {
							//add to hash
							admin.freeze.add(otherpn);
							admin.tellAdmin(ChatColor.DARK_AQUA + otherpn + " was frozen by " + sender.getName());
							admin.logInfo(otherpn + " was frozen by " + sender.getName());
							otherplayer.sendMessage(ChatColor.RED + "You were frozen!");
						}
					} else {
						//can't do to yourself
						sender.sendMessage(ChatColor.RED + "You can't freeze yourself!");
					}
				} else {
					//incorrect args
					sender.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
					return true;
				}
			} else {
				//no perms
				sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			}
		} 
		else if(sender instanceof ConsoleCommandSender) {
			//console
			sender.sendMessage("Can't do that from console");
		} else {
			//unknown commandsender
			return false;
		}
		
		return true;
	}

}
