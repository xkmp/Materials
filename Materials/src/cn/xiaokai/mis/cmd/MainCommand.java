package cn.xiaokai.mis.cmd;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.xiaokai.materials.Materials;

/**
 * @author Winfxk
 */
public class MainCommand {
	private Materials mis;

	public MainCommand(Materials mis) {
		this.mis = mis;
	}

	public boolean onCommand(CommandSender player, String label, String[] a) {
		if (a.length < 1)
			if (player.isPlayer()) {
				mis.MakeForm.Main((Player) player);
				return true;
			} else
				return onCommand(player, label, new String[] { "help" });
		switch (a[0].toLowerCase()) {
		case "help":
		default:

			return true;
		}
	}

}
