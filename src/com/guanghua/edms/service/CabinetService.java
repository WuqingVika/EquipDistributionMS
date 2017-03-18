package com.guanghua.edms.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	public int addCabinetList(List<AddCabinet> cbs);
	/**
	 * 2-1.根据机房显示机柜列表
	 * @param roomId 机房ID
	 * @return 
	 */
	public List<Map<String, String>> selCabinetByJiFangId(int roomId);
	/**
	 * 
	 * 2-2.查询设备信息
	 * @return
	 */
	public JSONObject selEquipmentsByQuery(int pageSize, int rows, String juZhan, String jiFang, String cabinetId,
			String gridId, String equipmentName);
	/**
	 * 2-3.根据设备ID查询板卡信息
	 * @param mypageSize
	 * @param myrows
	 * @param equipId
	 * @return
	 */
	public JSONObject selCardByequipID(int mypageSize, int myrows, int equipId);
	/**
	 * 2-4.批量添加设备信息
	 * @param stringList
	 * @param cabinetId
	 */
	public void addEquipmentList(List<String[]> stringList, int cabinetId);
	/**
	 * 3-1.查询板卡信息
	 * @param pageSize
	 * @param rows
	 * @param juZhan
	 * @param jiFang
	 * @param cabinetId
	 * @param gridId
	 * @param equipmentName
	 * @return
	 */
	public JSONObject selCardsByQuery(int pageSize, int rows, String juZhan, String jiFang, String cabinetId,
			String gridId, String equipmentName);
	/**
	 * 3-2.下拉框显示设备信息 根据机柜查询设备
	 * @param cabinetId
	 * @return
	 */
	public List<Map<String, String>> selEquipmentByCabinetId(int cabinetId);
	
	/**
	 * 3-3.指量添加板卡信息
	 */
	public void addCardList(List<String[]> cards,int equipId);
	/**
	 * 3-4.根据设备Id和子框显示标志，查询子框Id是否存在 
	 * @param equipId 设备id
	 * @param label 子框显示标志
	 * @return
	 */
	public int getSubrackId(int equipId,String label);
	
}
