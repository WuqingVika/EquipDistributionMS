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

}
