package com.guanghua.edms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guanghua.edms.domain.UserInfo;

@Repository
public class UserDaoImpl  implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	public UserInfo getUserByUserName(String userName) {
		System.out.println("dao--userId------------"+userName);
		//User u= (User) sessionFactory.getCurrentSession().get(User.class, userId);
		//User u =(User) sessionFactory.openSession().get(User.class, userId);
		//System.out.println("u.getUserId"+u.getPassword());
		List<Object[]> list=sessionFactory.openSession().createSQLQuery("select USER_ID,USER_NAME,PASSWORD from user_info where USER_NAME='"+userName+"'").list();
		System.out.println(list.size()+"------------");
		UserInfo u=new UserInfo();
		List<UserInfo> users=new ArrayList<UserInfo>();
		for(int i=0;i<list.size();i++){
			u.setUserId(Integer.parseInt(list.get(i)[0].toString()));
			u.setUserName(list.get(i)[1].toString());
			u.setPassword(list.get(i)[2].toString());
			users.add(u);
		}
		if(list.size()!=0){
			return users.get(0);
		}
		
		return null;
		//return (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, userName);
	}
}
