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

import com.guanghua.edms.domain.Spec;
@Repository
public class SpecDaoImpl implements SpecDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int addSpec(Spec spec) {
		Long specId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(spec_Id)+1 from Jfzs_spec_Manage").uniqueResult().toString());
		spec.setSpecId(specId);
		try {
			sessionFactory.getCurrentSession().save(spec);
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int editSpec(Spec spec) {
		try {
			sessionFactory.getCurrentSession().createSQLQuery("update Jfzs_spec_Manage set spec_name='"+spec.getSpecName()+"' where spec_id="+spec.getSpecId()).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
		//+region.getAddress()+"','"+
		//region.getPropertyRight()+"',
		//"+region.getReFloor()+",'
		//"+region.getReUsage()+
		//"',"+region.getReState()+",
		//'"+region.getReAddress()+"')").executeUpdate();
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int removeSpecs(List<Spec> specs) {
		//
		Session currentSession = sessionFactory.getCurrentSession();
		int res=0;
		for(int i=0;i<specs.size();i++){
			//判断每个专业下是否有数据。如果有不能删除，如果没有则可以删除
			List<Object[]> list=currentSession.createSQLQuery("select * from jfzs_cabinet_manage where spec_id="+specs.get(i).getSpecId()).list();;
			if(list.size()==0){
				//可以删
				System.out.println("-------------可以删！");
			  try {//saveOrUpdate
				 currentSession.createSQLQuery("delete  from Jfzs_spec_Manage where spec_id="+specs.get(i).getSpecId()).executeUpdate();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return res;
			}
				
			}else{
				System.out.println("不能删");
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
