package com.guanghua.edms.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.domain.Region;

import net.sf.json.JSONObject;
public interface RegionDao {
	/**
	 * 0.查询局站列表
	 * @param pageSize
	 * @param rows
	 * @param regionName
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject listRegion(int pageSize, int rows,String regionName);
	
	/**
	 * 1.添加局站
	 * @param Region
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int addRegion(Region region);
	/**
	 * 2.修改局站
	 * @param Region
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int editRegion(Region region);
	/**
	 * 3.批量移除局站
	 * @param Regions
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int removeRegions(List<Region> regions);
}
