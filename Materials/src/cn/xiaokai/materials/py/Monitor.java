package cn.xiaokai.materials.py;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.materials.mts.FormID;
import cn.xiaokai.mis.form.Rape;
import cn.xiaokai.mis.form.sexyass.Brassiere;

/**
 * @author Winfxk
 */
public class Monitor implements Listener {
	private Materials mis;

	public Monitor(Materials mis) {
		this.mis = mis;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
	public void onPlayerForm(PlayerFormRespondedEvent e) {
		Player player = e.getPlayer();
		if (player == null || e.wasClosed() || e.getResponse() == null
				|| (!(e.getResponse() instanceof FormResponseCustom) && !(e.getResponse() instanceof FormResponseSimple)
						&& !(e.getResponse() instanceof FormResponseModal)))
			return;
		FormResponse data = e.getResponse();
		int ID = e.getFormID();
		FormID formID = mis.MakeForm.formID;
		if (ID == formID.getFormID("主页ID")) {
			(new Rape(mis)).Main(player, (FormResponseSimple) data);
		} else if (ID == formID.getFormID("选择红包类型页ID")) {
			(new Rape(mis)).SwitchSeme(player, (FormResponseSimple) data);
		} else if (ID == formID.getFormID("发送金币红包页面ID")) {
			(new Brassiere(mis, player)).sendMoney((FormResponseCustom) data);
		} else if (ID == formID.getFormID("发送物品红包页面ID")) {
			(new Brassiere(mis, player)).sendItem((FormResponseCustom) data);
		} else if (ID == formID.getFormID("新建活动页面ID")) {
			(new Brassiere(mis, player)).Lucency((FormResponseSimple) data);
		}
	}
}
