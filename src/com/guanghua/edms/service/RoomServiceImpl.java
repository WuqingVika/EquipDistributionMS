package com.guanghua.edms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.RoomDao;
import com.guanghua.edms.domain.Room;

import net.sf.json.JSONObject;
/**
 * 2017/03/30 20:36
 * @author wuqingvika
 *
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {
	@Autowired  
	private RoomDao roomDao;

	@Override
	public JSONObject listRooms(int pageSize, int rows, String regionId, String roomName) {
		return roomDao.listRooms(pageSize, rows, regionId, roomName);
	}

	@Override
	public int addRoom(Room room) {
		// TODO Auto-generated method stub
		return roomDao.addRoom(room);
	}

	@Override
	public int editRoom(Room room) {
		// TODO Auto-generated method stub
		return roomDao.editRoom(room);
	}

	@Override
	public int removeRooms(List<Room> rooms) {
		// TODO Auto-generated method stub
		return roomDao.removeRooms(rooms);
	}

	

}
