package cn.xiaokai.materials.mts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.Utils;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.mis.prettylegs.PrettyLegs;
import cn.xiaokai.tool.Tool;

/**
 * @author Winfxk
 */
@SuppressWarnings("unchecked")
public class CheckLegality {
	private Materials mis;

	public CheckLegality(Materials mis) {
		this.mis = mis;
	}

	public void start() {
		File file;
		for (String FileName : PrettyLegs.loadConfigList) {
			file = new File(mis.getDataFolder(), FileName);
			if (!file.exists())
				try {
					mis.getLogger().info("§6初始化资源：§c" + FileName);
					InputStream rand = this.getClass().getResourceAsStream("/resources/" + FileName);
					if (rand == null) {
						String QQName = "";
						try {
							QQName = Tool.doPost("http://tool.epicfx.cn/", "s=qs&qq=2508543202");
							if (QQName == null)
								QQName = "";
						} catch (Exception e) {
						}
						mis.getLogger().error("初始化资源包失败！可能是插件已经损坏或被人为修改！请联系作者！" + QQName + "QQ：2508543202 ");
					} else
						Utils.writeFile(file, rand);
				} catch (IOException e) {
					mis.getLogger().error("§4资源初始化失败！请检查！§f" + e.getMessage());
					mis.setEnabled(false);
				}
		}
		Config config = new Config(new File(mis.getDataFolder(), PrettyLegs.FormID), Config.YAML);
		Map<String, Object> map = config.getAll();
		Map<String, Object> cg = (new FormID(mis)).getConfig();
		boolean isOK = true;
		for (String ike : cg.keySet()) {
			if (!map.containsKey(ike)) {
				mis.getLogger().info("§4未找到§6" + ike + "§4!正在设置随机ID：§6" + cg.get(ike));
				map.put(ike, cg.get(ike));
				isOK = false;
			}
		}
		if (!isOK) {
			mis.getLogger().info("§6正在更正数据......");
			config.setAll((LinkedHashMap<String, Object>) map);
			config.save();
		}
		for (String FileNae : PrettyLegs.ExamineConfigNameList) {
			try {
				mis.getLogger().info("§6正在检查文件" + FileNae);
				String content = Utils.readFile(this.getClass().getResourceAsStream("/resources/" + FileNae));
				DumperOptions dumperOptions = new DumperOptions();
				dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
				Yaml yaml = new Yaml(dumperOptions);
				map = new ConfigSection(yaml.loadAs(content, LinkedHashMap.class));
				config = new Config(new File(mis.getDataFolder(), FileNae), Config.YAML);
				cg = config.getAll();
				isMap(map, cg, config);
			} catch (IOException e) {
				mis.getLogger().info("§4在检查数据中遇到错误！请尝试删除该文件§9[§d" + FileNae + "§9]\n§f" + e.getMessage());
			}
		}
	}

	public void isMap(Map<String, Object> map, Map<String, Object> cg, Config config) {
		for (String ike : map.keySet()) {
			if (!cg.containsKey(ike)) {
				cg.put(ike, map.get(ike));
				mis.getLogger().info("§6" + ike + "§4所属的数据错误！已回复默认");
				continue;
			}
			if (((cg.get(ike) instanceof Map) || (map.get(ike) instanceof Map))
					|| ((cg.get(ike) instanceof List) && (map.get(ike) instanceof List)
							|| ((cg.get(ike) instanceof String) && (map.get(ike) instanceof String)))
					|| ((map.get(ike) instanceof Integer) && (cg.get(ike) instanceof Integer))
					|| ((map.get(ike) instanceof Boolean) && (cg.get(ike) instanceof Boolean))
					|| ((map.get(ike) instanceof Float) && (cg.get(ike) instanceof Float))) {
			} else {
				cg.put(ike, map.get(ike));
				mis.getLogger().info("§6" + ike + "§4属性不匹配！已回复默认");
				continue;
			}
			if (map.get(ike) instanceof Map)
				cg.put(ike, icMap((Map<String, Object>) map.get(ike), (Map<String, Object>) cg.get(ike)));
		}
		config.setAll((LinkedHashMap<String, Object>) cg);
		config.save();
	}

	public Map<String, Object> icMap(Map<String, Object> map, Map<String, Object> cg) {
		for (String ike : map.keySet()) {
			if (!cg.containsKey(ike)) {
				cg.put(ike, map.get(ike));
				mis.getLogger().info("§6" + ike + "§4所属的数据错误！已回复默认");
				continue;
			}
			if (((cg.get(ike) instanceof Map) && (map.get(ike) instanceof Map))
					|| ((cg.get(ike) instanceof List) && (map.get(ike) instanceof List)
							|| ((cg.get(ike) instanceof String) && (map.get(ike) instanceof String)))) {
			} else {
				cg.put(ike, map.get(ike));
				mis.getLogger().info("§6" + ike + "§4属性不匹配！已回复默认");
				continue;
			}
			if (map.get(ike) instanceof Map)
				cg.put(ike, icMap((Map<String, Object>) map.get(ike), (Map<String, Object>) cg.get(ike)));
		}
		return cg;
	}
}
