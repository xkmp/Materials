package cn.xiaokai.materials;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import cn.xiaokai.materials.mts.Message;
import cn.xiaokai.materials.mts.CheckLegality;
import cn.xiaokai.materials.py.Monitor;
import cn.xiaokai.materials.py.PlayerEvent;
import cn.xiaokai.mis.cmd.MainCommand;
import cn.xiaokai.mis.form.MakeForm;
import cn.xiaokai.mis.prettylegs.Condom;
import cn.xiaokai.mis.prettylegs.PrettyLegs;
import cn.xiaokai.tool.Tool;
import cn.xiaokai.tool.up.Update;

/**
 * @author Winfxk
 */
public class Materials extends PluginBase {
	private Instant loadTime = Instant.now();
	public static Materials mis;
	public Config config, MoneyList;
	public MainCommand mcd;
	public MakeForm MakeForm;
	public Message Msg;
	public LinkedHashMap<String, Condom> PlayerCondom = new LinkedHashMap<String, Condom>();

	/**
	 * 明人不说暗话！这就是插件启动事件
	 */
	@Override
	public void onEnable() {
		你是傻逼();
		super.onEnable();
		this.getServer().getLogger().info(Tool.getColorFont(this.getName() + "启动！") + "§6耗时：§9"
				+ ((float) (Duration.between(loadTime, Instant.now()).toMillis()) / 1000));
	}

	/**
	 * ????这都看不懂？？这是插件关闭事件
	 */
	@Override
	public void onDisable() {
		this.getServer().getLogger()
				.info(Tool.getColorFont(this.getName() + "关闭！") + TextFormat.GREEN + "本次运行时长" + TextFormat.BLUE
						+ Tool.getTimeBy(((float) (Duration.between(loadTime, Instant.now()).toMillis()) / 1000))
						+ TextFormat.GREEN + "s");
		super.onDisable();
	}

	/**
	 * PY已准备好！插件加载事件
	 */
	@Override
	public void onLoad() {
		this.getServer().getLogger().info(Tool.getColorFont(this.getName() + "正在加载..."));
		if (!getDataFolder().exists())
			getDataFolder().mkdirs();
		mis = this;
	}

	/**
	 * 返回货币的名称，如“金币”
	 * 
	 * @return
	 */
	public String getMoneyName() {
		return config.getString("货币单位");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return mcd.onCommand(sender, label, args);
	}

	public void 你是傻逼() {
		(new CheckLegality(this)).start();
		MakeForm = new MakeForm();
		config = new Config(new File(getDataFolder(), PrettyLegs.ConfigName), 2);
		MoneyList = new Config(new File(getDataFolder(), PrettyLegs.BreastName), 2);
		Msg = new Message(this);
		PluginManager pm = this.getServer().getPluginManager();
		if (config.getBoolean("检测更新"))
			(new Update(this)).start();
		pm.registerEvents(new PlayerEvent(this), this);
		pm.registerEvents(new Monitor(this), this);
		mcd = new MainCommand(this);
	}
}
