package cn.xiaokai.mis.form;

import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.mis.form.sexyass.SexyAss;
import cn.xiaokai.mis.prettylegs.Condom;

/**
 * @author Winfxk
 */
public class Rape {
	private Materials mis;

	public Rape(Materials mis) {
		this.mis = mis;
	}

	public boolean SwitchSeme(Player player, FormResponseSimple data) {
		Condom condom = mis.PlayerCondom.get(player.getName());
		List<String> list = condom.formMainItemArrayList;
		condom.formMainItemArrayList = null;
		String sK = list.get(data.getClickedButtonId());
		SexyAss ass = new SexyAss(player);
		if (sK.equals("M")) {
			ass.sendMoney();
		} else if (sK.equals("I")) {
			ass.sendItem();
		} else if (!player.isOp())
			return MakeForm.MakeTip(player, mis.Msg.getSon("提示", "权限不足"));
		else
			ass.PickAss();
		return true;
	}

	public void Main(Player player, FormResponseSimple data) {
		Condom condom = mis.PlayerCondom.get(player.getName());
		List<String> list = condom.formMainItemArrayList;
		condom.formMainItemArrayList = null;
		String sK = list.get(data.getClickedButtonId());
		MakeForm form = mis.MakeForm;
		if (sK.equals("G")) {
			form.SwitchSeme(player);
		} else if (sK.equals("S")) {
			form.Uke(player);
		} else if (sK.equals("M"))
			form.SwitchSB(player);
		else if (player.isOp() && sK.equals("C")) {
			form.Setting(player);
		} else
			form.Winfxk(player);
	}
}
