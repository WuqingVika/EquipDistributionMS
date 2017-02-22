package com.guanghua.edms.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guanghua.edms.domain.User;
@Repository
public class UserDaoImpl  implements UserDao{//extends HibernateDaoSupport
	@Autowired
	private SessionFactory sessionFactory;
	public User getUserByUserName(String userId) {
		//return (User)this.getHibernateTemplate().get(User.class, userName);
		System.out.println("dao--userId------------"+userId);
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}
}
