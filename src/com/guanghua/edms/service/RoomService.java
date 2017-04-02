package com.guanghua.edms.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.domain.Region;
import com.guanghua.edms.domain.Room;

import net.sf.json.JSONObject;

public interface RoomService {
	/**
	 * 0.查询机房列表
	 * @param pageSize
	 * @param rows
	 * @param regionName
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject listRooms(int pageSize, int rows,String regionId,String roomName);
	
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
