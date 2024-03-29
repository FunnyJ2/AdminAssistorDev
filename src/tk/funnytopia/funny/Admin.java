package tk.funnytopia.funny;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Admin extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	
	private StixPlayerListener stixplayerListener = new StixPlayerListener(this);
	private FreezePlayerListener freezeplayerListener = new FreezePlayerListener(this);
	private MutePlayerListener muteplayerListener = new MutePlayerListener(this);
	private MuteAllListener muteallListener = new MuteAllListener(this);
	private ChatLogListener chatlogListener = new ChatLogListener(this);
	
	public ArrayList<String> activePlayers = new ArrayList<String>();
	public ArrayList<String> freeze = new ArrayList<String>();
	public ArrayList<String> mute = new ArrayList<String>();
	public ArrayList<String> misc = new ArrayList<String>();
	
	public String combineSplit(int startIndex, String[] string, String seperator) {
        final StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - seperator.length());
        return builder.toString();
    }
	
	public void chatLog(String playername, String message, String tag){
		final File chatlog;
		chatlog = new File("plugins/AdminAssistor/chatlog.txt");
		try {
			String newline = System.getProperty("line.separator");
			FileWriter wrt = new FileWriter(chatlog, true);
			String towrite = tag + "<" + playername + "> " + message;
			wrt.write(towrite + newline);
			wrt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//(tag + "<" + playername + "> " + message)
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
	
	public void fakeChat(String msg, Player player) {
		player.chat(msg);
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
		String end = "enable-end-command";
		String stix = "enable-stix";
		
		getConfig().addDefault(end, false);
		getConfig().addDefault(shutdown, false);
		getConfig().addDefault(stix, true);
		
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
		this.getCommand("stix").setExecutor(new StixCommand(this));
		this.getCommand("freeze").setExecutor(new FreezeCommand(this));
		this.getCommand("mute").setExecutor(new MuteCommand(this));
		this.getCommand("muteall").setExecutor(new MuteAllCommand(this));
		//note to self: write down perm nodes you lazy bitch!
		
		//chatlog stuff
		final File chatlog;
		chatlog = new File("plugins/AdminAssistor/chatlog.txt");
		if (!chatlog.exists()) {
			try {
				chatlog.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.logInfo("Chatlog is ready to log");
		}
		
		PluginManager pm = getServer().getPluginManager();
		
		//stix
		pm.registerEvent(Event.Type.PLAYER_INTERACT, this.stixplayerListener, Priority.High, this);
		
		//freeze
		pm.registerEvent(Event.Type.PLAYER_INTERACT, this.freezeplayerListener, Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_DROP_ITEM, this.freezeplayerListener, Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_CHAT, this.freezeplayerListener, Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, this.freezeplayerListener, Priority.High, this);
		
		//mute
		pm.registerEvent(Event.Type.PLAYER_CHAT, this.muteplayerListener, Priority.High, this);
		
		//muteall
		pm.registerEvent(Event.Type.PLAYER_CHAT, this.muteallListener, Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, muteallListener, Priority.High, this);
		
		//chatlog
		pm.registerEvent(Event.Type.PLAYER_CHAT, chatlogListener, Priority.Highest, this);
	}

}
