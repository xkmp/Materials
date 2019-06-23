package cn.xiaokai.mis.form;

import java.util.ArrayList;

import cn.nukkit.Player;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.materials.mts.FormID;
import cn.xiaokai.mis.prettylegs.Condom;
import cn.xiaokai.tool.Tool;
import cn.xiaokai.ui.SimpleForm;

/**
 * @author Winfxk
 */
public class MakeForm {
	private Materials mis;
	public FormID formID;

	public MakeForm() {
		mis = Materials.mis;
		formID = new FormID(mis);
	}

	public void Uke(Player player) {

	}

	public void SwitchSB(Player player) {

	}

	public void SwitchSeme(Player player) {
		SimpleForm form = new SimpleForm(formID.getFormID("选择红包类型页ID"), mis.Msg.getSun("界面", "选择要发送的红包类型", "标题"),
				mis.Msg.getSun("界面", "选择要发送的红包类型", "提示内容"));
		form.addButton(mis.Msg.getSun("界面", "选择要发送的红包类型", "金币红包"));
		form.addButton(mis.Msg.getSun("界面", "选择要发送的红包类型", "物品红包"));
		form.addButton(mis.Msg.getSun("界面", "选择要发送的红包类型", "开始活动"));
		ArrayList<String> FormMainItems = new ArrayList<String>();
		FormMainItems.add("M");
		FormMainItems.add("I");
		FormMainItems.add("S");
		form.sendPlayer(player);
		Condom condom = mis.PlayerCondom.get(player.getName());
		condom.formMainItemArrayList = FormMainItems;
		mis.PlayerCondom.put(player.getName(), condom);
	}

	public void Main(Player player) {
		ArrayList<String> FormMainItems = new ArrayList<String>();
		SimpleForm form = new SimpleForm(formID.getFormID("主页ID"), mis.Msg.getSun("界面", "主页", "标题"),
				mis.Msg.getSun("界面", "主页", "提示内容"));
		form.addButton(mis.Msg.getSun("界面", "主页", "发红包按钮"));
		FormMainItems.add("G");
		form.addButton(mis.Msg.getSun("界面", "主页", "大奖赛按钮"));
		FormMainItems.add("M");
		if (mis.config.getBoolean("通过界面领取红包")) {
			form.addButton(mis.Msg.getSun("界面", "主页", "领红包按钮"));
			FormMainItems.add("S");
		}
		if (player.isOp()) {
			form.addButton("配置数据");
			form.addButton("关于作者");
			FormMainItems.add("C");
			FormMainItems.add("X");
		}
		form.sendPlayer(player);
		Condom condom = mis.PlayerCondom.get(player.getName());
		condom.formMainItemArrayList = FormMainItems;
		mis.PlayerCondom.put(player.getName(), condom);
	}

	public void Winfxk(Player player) {

	}

	public void Setting(Player player) {

	}

	public static boolean MakeTip(Player player, String string) {
		return MakeTip(player, string, Tool.getRandColor() + Materials.mis.getName(), "确定", false);
	}

	public static boolean MakeTip(Player player, String string, boolean c) {
		return MakeTip(player, string, Tool.getRandColor() + Materials.mis.getName(), "确定", c);
	}

	public static boolean MakeTip(Player player, String string, String Title, String Bt, boolean c) {
		SimpleForm form = new SimpleForm(Tool.getRand(0, 100000), Title, string);
		form.addButton(Bt);
		form.sendPlayer(player);
		return c;
	}
}
