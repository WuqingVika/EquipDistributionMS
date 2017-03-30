package com.guanghua.edms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.RegionDao;
import com.guanghua.edms.domain.Region;
import com.guanghua.edms.domain.Spec;

import net.sf.json.JSONObject;
/**
 * 2017/03/30 20:36
 * @author wuqingvika
 *
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService {
	@Autowired  
	private RegionDao regionDao;

	@Override
	public JSONObject listRegion(int pageSize, int rows, String regionName) {
		// TODO Auto-generated method stub
		return regionDao.listRegion(pageSize, rows, regionName);
	}

	@Override
	public int addRegion(Region region) {
		// TODO Auto-generated method stub
		return regionDao.addRegion(region);
	}

	@Override
	public int editRegion(Region region) {
		// TODO Auto-generated method stub
		return regionDao.editRegion(region);
	}

	@Override
	public int removeRegions(List<Region> regions) {
		// TODO Auto-generated method stub
		return regionDao.removeRegions(regions);
	}

}
