package tk.funnytopia.funny;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Admin extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	
	public String combineSplit(int startIndex, String[] string, String seperator) {
        final StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - seperator.length());
        return builder.toString();
    }
	
	public void adminChatMsg(String message, String playername) {
        for (final Player player : this.getServer().getOnlinePlayers()) {
            if (player.isOp() || player.hasPermission("adminassistor.chat")) {
                player.sendMessage(ChatColor.GOLD + "[Private]" + ChatColor.WHITE + "<" + ChatColor.LIGHT_PURPLE + playername + ChatColor.WHITE + "> " + ChatColor.WHITE + message);
                this.logInfo("[Private]" + playername + ": " + message);
            }
        }
    }
	
	public void tellAdmin(String message) {
        for (final Player player : this.getServer().getOnlinePlayers()) {
            if (player.isOp() || player.hasPermission("adminassistor.admin")) {
                player.sendMessage(message);
            }
        }
    }
	
	public void tellAll(String message, String amessage) {
		for (final Player player : this.getServer().getOnlinePlayers()) {
			if(player.isOp() || player.hasPermission("adminassistor.admin")){
				//admin
				player.sendMessage(amessage);
			} else {
				//normal user
				player.sendMessage(message);
			}
		}
	}
	
	public void loadConfiguration(){
		//config stuff
		String shutdown = "enable-shutdown-command";
		getConfig().addDefault(shutdown, false);
		
		String end = "enable-end-command";
		getConfig().addDefault(end, false);
		
		getConfig().options().copyDefaults(true); 
		saveConfig();
	}
	
	public void logInfo(String message) {
		log.info("[AdminAssistor] " + message);
	}
	
	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
		
		loadConfiguration();
		
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logInfo(pdfFile.getVersion() + " enabled");
		
		this.getCommand("a").setExecutor(new ACommand(this));
		this.getCommand("judge").setExecutor(new JudgeCommand(this));
		this.getCommand("g").setExecutor(new GCommand(this));
		this.getCommand("mode").setExecutor(new ModeCommand(this));
		this.getCommand("shutdown").setExecutor(new ShutDownCommand(this));
		this.getCommand("whois").setExecutor(new WhoisCommand(this));
		this.getCommand("perm").setExecutor(new PermCommand(this));
		this.getCommand("end").setExecutor(new EndCommand(this));
		//note to self: write down perm nodes you lazy bitch!
	}

}
