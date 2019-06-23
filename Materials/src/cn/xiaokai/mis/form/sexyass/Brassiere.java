package cn.xiaokai.mis.form.sexyass;

import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseSimple;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.materials.briefs.Briefs;
import cn.xiaokai.mis.form.MakeForm;
import cn.xiaokai.mis.prettylegs.Condom;
import cn.xiaokai.tool.Tool;

/**
 * @author Winfxk
 */
public class Brassiere {
	private Materials mis;
	private Player player;

	public Brassiere(Materials mis, Player player) {
		this.player = player;
		this.mis = mis;
	}

	public boolean sendMoney(FormResponseCustom data) {
		String moneyString = String.valueOf(data.getResponse(0));
		if (moneyString == null || moneyString.isEmpty())
			return MakeForm.MakeTip(player, mis.Msg.getSun("提示", "界面", "不能为空", new String[] { "{Player}", "{Item}" },
					new Object[] { player.getName(), mis.getMoneyName() + "数" }));
		if (!Tool.isInteger(moneyString))
			return MakeForm.MakeTip(player, mis.Msg.getSun("提示", "界面", "仅支持纯数字", new String[] { "{Player}", "{Item}" },
					new Object[] { player.getName(), mis.getMoneyName() + "数" }));
		int Money = Float.valueOf(moneyString).intValue();
		if (Money < 1)
			return MakeForm.MakeTip(player, mis.Msg.getSun("提示", "界面", "仅支持正整数", new String[] { "{Player}", "{Item}" },
					new Object[] { player.getName(), mis.getMoneyName() + "数" }));
		String Key = String.valueOf(data.getResponse(1));
		if (Key == null || Key.isEmpty())
			Key = SexyAss.getKey(1);
		String brsString = String.valueOf(data.getResponse(2));
		if (brsString == null || brsString.isEmpty())
			return MakeForm.MakeTip(player, mis.Msg.getSun("提示", "界面", "不能为空", new String[] { "{Player}", "{Item}" },
					new Object[] { player.getName(), "红包数" }));
		if (!Tool.isInteger(brsString))
			return MakeForm.MakeTip(player, mis.Msg.getSun("提示", "界面", "仅支持纯数字", new String[] { "{Player}", "{Item}" },
					new Object[] { player.getName(), "红包数" }));
		int Brs = Float.valueOf(brsString).intValue();
		if (Brs < 1)
			return MakeForm.MakeTip(player, mis.Msg.getSun("提示", "界面", "仅支持正整数", new String[] { "{Player}", "{Item}" },
					new Object[] { player.getName(), "红包数" }));
		if (Money < Brs)
			return MakeForm.MakeTip(player, mis.Msg.getSun("提示", "界面", "红包数过大", new String[] { "{Player}", "{Item}" },
					new Object[] { player.getName(), mis.getMoneyName() + "数" }));
		String braTypeString = String.valueOf(data.getResponse(3));
		Condom condom = mis.PlayerCondom.get(player.getName());
		List<String> list = condom.formMainItemArrayList;
		String BraType = list.get(2).equals(braTypeString) ? "人均红包"
				: list.get(1).equals(braTypeString) ? "天梯红包" : "拼手气红包";
		(new Briefs(mis, player)).sendMoney(Money, Brs, Key, BraType);
		return true;
	}

	public boolean sendItem(FormResponseCustom data) {

		return true;
	}

	public boolean Lucency(FormResponseSimple data) {

		return true;
	}
}
