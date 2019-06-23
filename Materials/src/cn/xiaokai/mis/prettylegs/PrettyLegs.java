package cn.xiaokai.mis.prettylegs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import cn.xiaokai.tool.Tool;

/**
 * @author Winfxk
 */
public class PrettyLegs {
	public static final String PlayerPath = "/Players/";
	/**
	 * 红包数据存储位置
	 */
	public static final String BreastName = "Breast.yml";
	/**
	 * 文字消息存储内容
	 */
	public static final String MsgName = "JiaoBreathe.yml";
	public static final String FormID = "FormID.yml";
	/**
	 * 主配置文件名称
	 */
	public static final String ConfigName = "Config.yml";
	/**
	 * 要预加载的配置文件名列表
	 */
	public static final String[] loadConfigList = { MsgName, ConfigName };
	/**
	 * 要检查数据是否匹配的文件列表
	 */
	public static final String[] ExamineConfigNameList = { ConfigName, MsgName };

	/**
	 * 构建默认的表单ID
	 * 
	 * @return
	 */
	public static HashMap<String, Integer> getFormIDMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("主页ID", Tool.getRand(0, 809714630));
		map.put("选择红包类型页ID", Tool.getRand(0, 809714630));
		map.put("发送金币红包页面ID", Tool.getRand(0, 809714630));
		map.put("发送物品红包页面ID", Tool.getRand(0, 809714630));
		map.put("新建活动页面ID", Tool.getRand(0, 809714630));
		return map;
	}

	public static LinkedHashMap<String, Object> getPlayerMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("Msg", new ArrayList<String>());
		map.put("未完成红包", 0);
		map.put("发送红包总数", 0);
		map.put("领取红包总数", 0);
		return map;
	}
}
