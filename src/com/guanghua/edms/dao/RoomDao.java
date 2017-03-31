package com.guanghua.edms.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.domain.Region;
import com.guanghua.edms.domain.Room;

import net.sf.json.JSONObject;

/**
 * 2017/03/30 21:26
 * @author wuqingvika
 * 1.查询列表（带条件：机房ID+机房名称）
 * 2.增加机房（可选机房）
 * 3.删除机房（判断下面有机柜）
 * 4.编辑机房（绑定当前机房，加载可选机房）
 */
public interface RoomDao {
	/**
	 * 0.查询机房列表
	 * @param pageSize
	 * @param rows
	 * @param regionName
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject listRooms(int pageSize, int rows,Long regionId,String roomName);
	
	/**
	 * 1.添加机房
	 * @param Region
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int addRoom(Room room);
	/**
	 * 2.修改机房
	 * @param Region
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int editRoom(Room room);
	/**
	 * 3.批量移除机房
	 * @param Regions
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int removeRooms(List<Room> rooms);
}
