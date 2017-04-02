package com.guanghua.edms.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guanghua.edms.domain.Room;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Repository
public class RoomDaoImpl implements RoomDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public JSONObject listRooms(int pageSize, int rows, String regionId, String roomName) {
		//select room_id,room_name,room_no,ro_floor,ro_usage,a.property_right,ro_state from room a , room b
		//where  a.room_id=b.room_id
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select  room_id,region_name,room_no,ro_floor,ro_usage,a.property_right,ro_state from room a , region b where  a.region_id=b.region_id");
		countSql.append(" select count(*) from (select room_id,region_name,room_no,ro_floor,ro_usage,a.property_right,ro_state from room a , region b where  a.region_id=b.region_id");
		
		if(!"".equals(roomName)){
			sql.append(" and   room_no  like '%"+roomName+"%' ");
			countSql.append(" and  room_no like '%"+roomName+"%' ");
		}
		if(!"".equals(regionId)){
			Long regId=Long.parseLong(regionId);
			sql.append(" and   a.region_id  = "+regId);
			countSql.append(" and  a.region_id = "+regId);
		}
		countSql.append(" ) aa");
		
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setFirstResult((pageSize - 1) * rows).setMaxResults(rows).list();
		JSONObject result = new JSONObject();
		if(pageSize==1){
			System.out.println();
			String str=sessionFactory.getCurrentSession().createSQLQuery(countSql.toString()).uniqueResult().toString();
			int count=Integer.parseInt(str);
			result.put("total", count);
		}
		JSONArray jsonArray=new JSONArray();
		for (Object[] objects  : list) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("roomId", objects[0]);
			jsonObject.put("regionName", objects[1]);
			
			jsonObject.put("roomNo", objects[2]);
			jsonObject.put("roFloor", objects[3]);
			jsonObject.put("roUsage", objects[4]);
			jsonObject.put("propertyRight", objects[5]);
			jsonObject.put("roState", objects[6]);
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("roomSql", sql.toString());
		return result;
	}

	@Override
	public int addRoom(Room room) {
		Long roomId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(room_id)+1 from room").uniqueResult().toString());
		room.setRoomId(roomId);
		//插入数据库
		int res = sessionFactory.getCurrentSession().createSQLQuery("insert into room (district,room_id,region_id,room_no,ro_floor,ro_usage,property_right,ro_state) values (20,"+room.getRoomId()+","+room.getRegionId()+",'"+room.getRoomNo()
					+"',"+room.getRoFloor()+",'"+room.getRoUsage()+"','"+room.getPropertyRight()+"','"+room.getRoState()+"')").executeUpdate();
		return res;
	}

	@Override
	public int editRoom(Room room) {
		int res=sessionFactory.getCurrentSession().createSQLQuery("update room set region_id="+room.getRegionId()+",room_no='"+room.getRoomNo()+"',ro_floor="+room.getRoFloor()+",ro_usage='"
				+ room.getRoUsage()+"',property_right='"+room.getPropertyRight()
				+"',ro_state='"+room.getRoState()+"' where room_id="+room.getRoomId()).executeUpdate();
		return res;
	}

	@Override
	public int removeRooms(List<Room> rooms) {
		Session currentSession = sessionFactory.getCurrentSession();
		int res=0;
		for(int i=0;i<rooms.size();i++){
			//判断每个机房下是否有机柜。如果有不能删除，如果没有则可以删除
			List<Object[]> list=currentSession.createSQLQuery("select * from jfzs_cabinet_manage where room_id="+rooms.get(i).getRoomId()).list();
			if(list.size()==0){
				//可以删
				System.out.println("-------------可以删！");
			  try {//saveOrUpdate
				 currentSession.createSQLQuery("delete from room where room_id="+rooms.get(i).getRoomId()).executeUpdate();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return res;
			}
				
			}else{
				System.out.println("不能删!");
				res=2;//代表不能删的
				continue;//跳过
			}
		}
		if(res==2){
			//说明有不能删的
			return res;
		}
		res=1;
		return res;
	}

}
