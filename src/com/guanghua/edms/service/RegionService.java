package com.guanghua.edms.service;

import java.util.List;

import com.guanghua.edms.domain.Region;

import net.sf.json.JSONObject;

public interface RegionService {
	/**
	 * 0.查询局站列表
	 * @param pageSize
	 * @param rows
	 * @param regionName
	 * @return
	 */
	public JSONObject listRegion(int pageSize, int rows,String regionName);
	
	/**
	 * 1.添加局站
	 * @param Region
	 * @return
	 */
	public int addRegion(Region region);
	/**
	 * 2.修改局站
	 * @param Region
	 * @return
	 */
	public int editRegion(Region region);
	/**
	 * 3.批量移除局站
	 * @param Regions
	 * @return
	 */
	public int removeRegions(List<Region> regions);
	
}
