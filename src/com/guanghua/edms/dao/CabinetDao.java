package com.guanghua.edms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.domain.AddCabinet;

import net.sf.json.JSONObject;
/**
 * 2017/2/25 15:17
 * @author wuqingvika
 *
 */
public interface CabinetDao {
	/**
	 * 1-1.显示局站
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selJuZhan();
	/**
	 * 1-2.根据局站显示机房列表
	 * @return 
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selJiFangByJuZhanId(int regionId);
	/**
	 * 1-3.显示机柜专业
	 * @return 
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selZhuanYes();
	/**
	 * 
	 * 1-4.查询机柜信息
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selJiGuiByQuery(int pageSize,int rows,String juZhan,String jiFang,String zhuanYe,String bianOrMc);
	/**
	 * 1-5.批量添加机柜信息
	 * @param cbs
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int addCabinetList(List<AddCabinet> cbs);
	/**
	 * 2-1.根据机房显示机柜列表
	 * @param roomId 机房ID
	 * @return 
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selCabinetByJiFangId(int roomId);
	/**
	 * 
	 * 2-2.查询设备信息
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selEquipmentsByQuery(int pageSize,int rows,String juZhan,String jiFang,String cabinetId,String gridId,String equipmentName);
	/**
	 * 2-3.设备关联板卡
	 * @param mypageSize
	 * @param myrows
	 * @param equipId
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selCardByequipID(int mypageSize, int myrows,int equipId);
	/**
	 * 2-4.批量添加设备信息
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addEquipmentList(List<String[]> equips,int cabinetId);
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
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selCardsByQuery(int pageSize,int rows,String juZhan,String jiFang,String cabinetId,String gridId,String equipmentName);

	/**
	 * 3-2.根据机柜显示设备列表
	 * @return 
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selEquipmentByCabinetId(int cabinetId);
}
