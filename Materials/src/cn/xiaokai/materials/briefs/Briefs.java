package cn.xiaokai.materials.briefs;

import cn.nukkit.Player;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.mis.prettylegs.Condom;

/**
 * @author Winfxk
 */
public class Briefs {
	private Materials mis;
	private Player player;
	private Condom condom;

	public Briefs(Materials mis, Player player) {
		this.mis = mis;
		this.player = player;
		condom = mis.PlayerCondom.get(player.getName());
	}

	public boolean sendMoney(int Money, int Count, String Key, String Type) {
		
		return true;
	}
}
