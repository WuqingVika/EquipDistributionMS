package com.guanghua.edms.dao.user;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.guanghua.edms.domain.User;
@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	
	public User getUserByUserName(String userName) {
		return (User)this.getHibernateTemplate().get(User.class, userName);
	}
}
