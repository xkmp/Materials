package cn.xiaokai.mis.form.sexyass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.nukkit.Player;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.materials.mts.FormID;
import cn.xiaokai.mis.form.MakeForm;
import cn.xiaokai.mis.prettylegs.Condom;
import cn.xiaokai.tool.Tool;
import cn.xiaokai.ui.CustomForm;
import cn.xiaokai.ui.SimpleForm;
import me.onebone.economyapi.EconomyAPI;

/**
 * @author Winfxk
 */
public class SexyAss {
	private Player player;
	private Materials mis;

	public SexyAss(Player player) {
		this.player = player;
		this.mis = Materials.mis;
	}

	public void sendMoney() {
		Condom condom = mis.PlayerCondom.get(player.getName());
		CustomForm form = new CustomForm(FormID.getID("发送金币红包页面ID"),
				mis.Msg.getSun("界面", "发送金币红包界面", "标题", new String[] { "{Player}" }, new String[] { player.getName() }));
		String 嫖娼价格 = mis.Msg.getSun("界面", "发送金币红包界面", "红包金额", new String[] { "{Player}" },
				new String[] { player.getName() });
		form.addInput(嫖娼价格, Tool.getRand(0, (int) EconomyAPI.getInstance().myMoney(player)) + "", 嫖娼价格);
		String 嫖娼名字 = mis.Msg.getSun("界面", "发送金币红包界面", "Key", new String[] { "{Player}" },
				new String[] { player.getName() });
		form.addInput(嫖娼名字, getKey(1), 嫖娼名字);
		String 要嫖几个女的 = mis.Msg.getSun("界面", "发送金币红包界面", "红包个数", new String[] { "{Player}" },
				new String[] { player.getName() });
		form.addInput(要嫖几个女的, "10", 要嫖几个女的);
		String 嫖前面后面 = mis.Msg.getSun("界面", "发送金币红包界面", "红包类型", new String[] { "{Player}" },
				new String[] { player.getName() });
		List<String> 洞列表 = Arrays.asList(new String[] { mis.Msg.getSun("界面", "发送金币红包界面", "拼手气红包"),
				mis.Msg.getSun("界面", "发送金币红包界面", "天梯红包"), mis.Msg.getSun("界面", "发送金币红包界面", "人均红包") });
		form.addDropdown(嫖前面后面, 洞列表, 0);
		condom.formMainItemArrayList = 洞列表;
		mis.PlayerCondom.put(player.getName(), condom);
		form.sendPlayer(player);
	}

	public void sendItem() {
		Condom condom = mis.PlayerCondom.get(player.getName());
		CustomForm form = new CustomForm(FormID.getID("发送物品红包页面ID"),
				mis.Msg.getSun("界面", "发送物品红包界面", "标题", new String[] { "{Player}" }, new String[] { player.getName() }));
		form.addInput(
				mis.Msg.getSun("界面", "发送物品红包界面", "物品ID", new String[] { "{Player}" },
						new String[] { player.getName() }),
				"", mis.Msg.getSun("界面", "发送物品红包界面", "物品ID", new String[] { "{Player}" },
						new String[] { player.getName() }));
		String 你是傻逼 = mis.Msg.getSun("界面", "发送金币红包界面", "Key", new String[] { "{Player}" },
				new String[] { player.getName() });
		form.addInput(你是傻逼, getKey(1), 你是傻逼);
		String 要嫖几个女的 = mis.Msg.getSun("界面", "发送金币红包界面", "红包个数", new String[] { "{Player}" },
				new String[] { player.getName() });
		form.addInput(要嫖几个女的, "10", 要嫖几个女的);
		String 嫖前面后面 = mis.Msg.getSun("界面", "发送金币红包界面", "红包类型", new String[] { "{Player}" },
				new String[] { player.getName() });
		List<String> 洞列表 = Arrays.asList(new String[] { mis.Msg.getSun("界面", "发送金币红包界面", "拼手气红包"),
				mis.Msg.getSun("界面", "发送金币红包界面", "天梯红包"), mis.Msg.getSun("界面", "发送金币红包界面", "人均红包") });
		form.addDropdown(嫖前面后面, 洞列表, 0);
		condom.formMainItemArrayList = 洞列表;
		mis.PlayerCondom.put(player.getName(), condom);
		form.sendPlayer(player);
	}

	public boolean PickAss() {
		if (!player.isOp())
			return MakeForm.MakeTip(player, mis.Msg.getSon("提示", "权限不足"));
		SimpleForm form = new SimpleForm(FormID.getID("新建活动页面ID"),
				mis.Msg.getSun("界面", "选择要开始的活动", "标题", new String[] { "{Player}" }, new Object[] { player.getName() }));
		form.addButton(mis.Msg.getSun("界面", "选择要开始的活动", "金币万中无一"));
		form.addButton(mis.Msg.getSun("界面", "选择要开始的活动", "物品万中无一"));
		form.addButton(mis.Msg.getSun("界面", "选择要开始的活动", "我命不由我"));
		Condom condom = mis.PlayerCondom.get(player.getName());
		List<String> list = new ArrayList<String>();
		list.add("M");
		list.add("I");
		list.add("S");
		condom.formMainItemArrayList = list;
		mis.PlayerCondom.put(player.getName(), condom);
		form.sendPlayer(player);
		return true;
	}

	public static String getKey(int JJLength) {
		String JJName = "";
		for (int JJSize = 0; JJSize < JJLength; JJSize++)
			JJName += Tool.getRandString();
		if (Materials.mis.MoneyList.exists(JJName))
			return getKey(JJLength++);
		return JJName;
	}
}
