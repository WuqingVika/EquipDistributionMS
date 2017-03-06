package com.guanghua.edms.service;

import java.util.List;
import java.util.Map;

import com.guanghua.edms.domain.AddCabinet;

import net.sf.json.JSONObject;

public interface CabinetService {
	/**
	 * 1-1.显示局站
	 * @return
	 */
	public List<Map<String, String>> selJuZhan();
	/**
	 * 1-2.根据局站显示机房
	 * @param regionId
	 * @return
	 */
	public List<Map<String, String>> selJiFangByJuZhanId(int regionId);
	/**
	 * 1-3.显示机柜专业
	 * @return
	 */
	public List<Map<String, String>> selZhuanYes();
	/**
	 * 1-4.查询机柜信息
	 * @return
	 */
	public JSONObject selJiGuiByQuery(int pageSize,int rows,String juZhan,String jiFang,String zhuanYe,String bianOrMc);
	/**
	 * 1-5.批量添加机柜信息
	 * @param cbs
	 */
	public void addCabinetList(List<AddCabinet> cbs);
	
}
