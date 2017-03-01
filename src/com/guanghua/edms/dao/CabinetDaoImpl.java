package com.guanghua.edms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.brick.db.SQLUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class CabinetDaoImpl implements CabinetDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selJuZhan() {
		//查询局站
		Connection conn = this.sessionFactory.openSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select region_id,region_name from  region where district=20");
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			System.out.println("list size ----dao---"+list.size());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selJiFangByJuZhanId(int regionId) {
		//根据局站查询机房
		Connection conn = this.sessionFactory.openSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select room_id,room_no from room where district=20 and region_id=").append(regionId);
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selZhuanYes() {
		//显示机柜专业
		Connection conn = this.sessionFactory.openSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select spec_id,spec_name from  jfzs_spec_manage");
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selJiGuiByQuery(int pageSize, int rows, String juZhan, String jiFang, String zhuanYe,
			String bianOrMc) {
		System.out.println("查询机柜-信息dao");
		System.out.println("psize:"+pageSize+"  rows:"+rows+"  juzhan:"+juZhan+" jiFang:"+jiFang+" zhuany:"+zhuanYe+" bOm:"+bianOrMc);
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select  a.cabinet_id,b.room_no,a.CABINET_NUM,a.CABINET_NAME,a.CABINET_SURFACE from jfzs_cabinet_manage a,room b where a.room_id=b.room_Id and b.district=20");
		countSql.append(" select count(*) from (select  a.cabinet_id,b.room_no,a.CABINET_NUM,a.CABINET_NAME,a.CABINET_SURFACE  from jfzs_cabinet_manage a,room b where a.room_id=b.room_Id and b.district=20 ");
		if(!"".equals(juZhan)){
			int regionId=Integer.parseInt(juZhan.trim());
			//
			//sql.append(" and remark like '%"+juZhan+"%'");
			sql.append(" and a.room_id in (select room_id from room where district=20 and region_id ="+regionId+")");
			countSql.append(" and a.room_id in (select room_id from room where district=20 and region_id ="+regionId+")");
		}
		if(!"".equals(jiFang)){
			int roomId=Integer.parseInt(jiFang.trim());
			sql.append(" and a.room_id="+roomId );
			//countSql.append(" and attachment like '%"+jiFang+"%'");
			countSql.append(" and a.room_id="+roomId);
		}
		if(!"".equals(zhuanYe)){
			int specId=Integer.parseInt(zhuanYe.trim());
			sql.append(" and a.spec_id="+specId);
			countSql.append(" and a.spec_id="+specId);
		}
		if(!"".equals(bianOrMc)){
			//and (a.CABINET_NUM like '%F0%' or a.CABINET_NAME like '%cc%')
			sql.append(" and (a.CABINET_NUM like '%"+bianOrMc+"%' or a.CABINET_NAME like '%"+bianOrMc+"%')");
			countSql.append("  and (a.CABINET_NUM like '%"+bianOrMc+"%' or a.CABINET_NAME like '%"+bianOrMc+"%')");
		}
		
		//org.hibernate.classic.Session session = this.sessionFactory.openSession();
		List<Object[]> list=sessionFactory.openSession().createSQLQuery(sql.toString()).setFirstResult((pageSize - 1) * rows).setMaxResults(rows).list();
		countSql.append(") aa");
		JSONObject result = new JSONObject();
		if(pageSize==1){
			System.out.println();
			String str=sessionFactory.openSession().createSQLQuery(countSql.toString()).uniqueResult().toString();
			int count=Integer.parseInt(str);
			result.put("total", count);
		}
		JSONArray jsonArray=new JSONArray();
		for (Object[] objects  : list) {
			//a.cabinet_id,b.room_no,a.CABINET_NUM,a.CABINET_NAME,a.CABINET_SURFACE,a.CABINET_SURFACE
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("ID", objects[0]);
			jsonObject.put("ROOM_NO", objects[1]);
			jsonObject.put("CABINET_NUM", objects[2]);
			jsonObject.put("CABINET_NAME", objects[3]);
			jsonObject.put("CABINET_SURFACE", objects[4]);
			jsonObject.put("CABINET_SURFACE", objects[4]);
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("assSql", sql.toString());
		return result;
	}

}
