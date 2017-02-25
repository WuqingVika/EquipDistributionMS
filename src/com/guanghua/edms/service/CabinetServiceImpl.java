package com.guanghua.edms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.CabinetDao;
@Service
@Transactional
public class CabinetServiceImpl implements CabinetService {
	@Autowired  
	private CabinetDao cabinetDao;
	@Override
	public List<Map<String, String>> selJuZhan() {
		// TODO Auto-generated method stub
		return cabinetDao.selJuZhan();
	}

}
