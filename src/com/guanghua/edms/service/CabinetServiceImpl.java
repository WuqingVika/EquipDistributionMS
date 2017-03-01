package com.guanghua.edms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.CabinetDao;

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

}
