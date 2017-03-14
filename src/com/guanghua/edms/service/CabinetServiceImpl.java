package com.guanghua.edms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.CabinetDao;
import com.guanghua.edms.domain.AddCabinet;

import net.sf.json.JSONObject;
@Service
@Transactional
public class CabinetServiceImpl implements CabinetService {
	@Autowired  
	private CabinetDao cabinetDao;
	@Override
	public List<Map<String, String>> selJuZhan() {
		return cabinetDao.selJuZhan();
	}
	@Override
	public List<Map<String, String>> selJiFangByJuZhanId(int regionId) {
		return cabinetDao.selJiFangByJuZhanId(regionId);
	}
	@Override
	public List<Map<String, String>> selZhuanYes() {
		return cabinetDao.selZhuanYes();
	}
	@Override
	public JSONObject selJiGuiByQuery(int pageSize, int rows, String juZhan, String jiFang, String zhuanYe,
			String bianOrMc) {
		return  cabinetDao.selJiGuiByQuery(pageSize, rows,juZhan,jiFang,zhuanYe,bianOrMc);
		
	}
	@Override
	public int addCabinetList(List<AddCabinet> cbs) {
		return cabinetDao.addCabinetList(cbs);
		
	}
	@Override
	public List<Map<String, String>> selCabinetByJiFangId(int roomId) {
		// 1设备管理中：根据机房显示当前的机柜
		return cabinetDao.selCabinetByJiFangId(roomId);
	}
	@Override
	public JSONObject selEquipmentsByQuery(int pageSize, int rows, String juZhan, String jiFang, String cabinetId,
			String gridId, String equipmentName) {
		// 2设备管理中：显示设备列表
		return cabinetDao.selEquipmentsByQuery(pageSize, rows,juZhan,jiFang,cabinetId,gridId,equipmentName);
	}
	@Override
	public JSONObject selCardByequipID(int mypageSize, int myrows, int equipId) {
		//3 设备管理中：显示某一设备关联板卡
		return cabinetDao.selCardByequipID(mypageSize,myrows,equipId);
	}
	@Override
	public void addEquipmentList(List<String[]> stringList, int cabinetId) {
		//3 设备管理中：批量添加设备信息
		 cabinetDao.addEquipmentList(stringList,cabinetId);
	}

}
