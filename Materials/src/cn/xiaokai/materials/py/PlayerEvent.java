package cn.xiaokai.materials.py;

import java.io.File;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.Config;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.mis.prettylegs.Condom;
import cn.xiaokai.mis.prettylegs.PrettyLegs;

/**
 * @author Winfxk
 */
public class PlayerEvent implements Listener {
	private Materials mis;

	public PlayerEvent(Materials mis) {
		this.mis = mis;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Condom condom = new Condom();
		Config config;
		File file = new File(mis.getDataFolder(), PrettyLegs.PlayerPath + player + ".yml");
		if (!file.exists()) {
			config = new Config(file, Config.YAML);
			config.setAll(PrettyLegs.getPlayerMap());
			config.save();
		} else
			config = new Config(file, Config.YAML);
		condom.player = player;
		condom.config = config;
		mis.PlayerCondom.put(player.getName(), condom);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (mis.PlayerCondom.containsKey(player.getName()))
			mis.PlayerCondom.remove(player.getName());
	}
}
