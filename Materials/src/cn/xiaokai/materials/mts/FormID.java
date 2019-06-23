package cn.xiaokai.materials.mts;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.nukkit.utils.Config;
import cn.xiaokai.materials.Materials;
import cn.xiaokai.mis.prettylegs.PrettyLegs;
import cn.xiaokai.tool.Tool;

/**
 * @author Winfxk
 */
public class FormID {
	private Materials mis;
	private Map<String, Object> config;

	public FormID(Materials mis) {
		this.mis = mis;
		config = isSafety();
	}

	public static int getID(String key) {
		return Materials.mis.MakeForm.formID.getFormID(key);
	}

	public int getFormID(String key) {
		if (config.containsKey(key))
			return Tool.isInteger(config.get(key).toString()) ? Float.valueOf(config.get(key).toString()).intValue()
					: Tool.getRand(0, 809714630);
		return Tool.getRand(0, 809714630);
	}

	public Map<String, Object> getConfig() {
		return config;
	}

	private Map<String, Object> isSafety() {
		Map<String, Object> St = (new Config(new File(mis.getDataFolder(), PrettyLegs.FormID), Config.YAML)).getAll();
		HashMap<String, Integer> FormIDList = PrettyLegs.getFormIDMap();
		for (String key : St.keySet())
			if (Tool.isInteger(St.get(key).toString())) {
				if (FormIDList.containsKey(key))
					FormIDList.remove(key);
				else
					St.remove(key);
			} else {
				St.put(key, FormIDList.containsKey(key) ? FormIDList.get(key) : Tool.getRand(0, 809714630));
				if (FormIDList.containsKey(key))
					FormIDList.remove(key);
			}
		if (FormIDList.size() > 0)
			for (String key : FormIDList.keySet())
				St.put(key, FormIDList.get(key));
		return St;
	}
}
