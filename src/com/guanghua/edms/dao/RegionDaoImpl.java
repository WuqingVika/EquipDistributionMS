package com.guanghua.edms.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.domain.Region;
import com.guanghua.edms.domain.Spec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Repository
public class RegionDaoImpl implements RegionDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public JSONObject listRegion(int pageSize, int rows, String regionName) {
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select  region_Id,region_name,address,property_right,RE_floor,RE_usage,RE_state,re_addrress from region ");
		countSql.append(" select count(*) from (select  region_Id,region_name,address,property_right,RE_floor,RE_usage,RE_state,re_addrress from region");
		
		if(!"".equals(regionName)){
			sql.append(" where  region_name  like '%"+regionName+"%' ");
			countSql.append(" where region_name like '%"+regionName+"%' ");
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
			jsonObject.put("regionId", objects[0]);
			jsonObject.put("regionName", objects[1]);
			
			jsonObject.put("address", objects[2]);
			jsonObject.put("propertyRight", objects[3]);
			jsonObject.put("reFloor", objects[4]);
			jsonObject.put("reUsage", objects[5]);
			jsonObject.put("reState", objects[6]);
			jsonObject.put("reAddrress", objects[7]);
			
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("regionSql", sql.toString());
		return result;
	}

	@Override
	public int addRegion(Region region) {
		Long regionId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(region_id)+1 from region").uniqueResult().toString());
		region.setRegionId(regionId);
		//插入数据库
		int res = sessionFactory.getCurrentSession().createSQLQuery("insert into region (district,region_id,region_name,address,property_right,re_floor,re_usage,re_state,re_addrress) values (20,"+region.getRegionId()+",'"+region.getRegionName()+"','"
		+region.getAddress()+"','"+region.getPropertyRight()+"',"+region.getReFloor()+",'"+region.getReUsage()+
				"',"+region.getReState()+",'"+region.getReAddress()+"')").executeUpdate();
		return res;
	}

	@Override
	public int editRegion(Region region) {
		int res=sessionFactory.getCurrentSession().createSQLQuery("update region set region_name='"+region.getRegionName()+"',address='"+region.getAddress()+"',property_right='"
				+ region.getPropertyRight()+"',re_floor="+region.getReFloor()
				+",re_usage='"+region.getReUsage()+"',re_state='"+region.getReState()
				+"',re_addrress='"+region.getReAddress()
				+"' where region_id="+region.getRegionId()).executeUpdate();
		return res;
	}

	@Override
	public int removeRegions(List<Region> regions) {
		//
		Session currentSession = sessionFactory.getCurrentSession();
		int res=0;
		for(int i=0;i<regions.size();i++){
			//判断每个专业下是否有数据。如果有不能删除，如果没有则可以删除
			List<Object[]> list=currentSession.createSQLQuery("select * from room where region_id="+regions.get(i).getRegionId()).list();
			if(list.size()==0){
				//可以删
				System.out.println("-------------可以删！");
			  try {//saveOrUpdate
				 currentSession.createSQLQuery("delete from region where region_id="+regions.get(i).getRegionId()).executeUpdate();
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
