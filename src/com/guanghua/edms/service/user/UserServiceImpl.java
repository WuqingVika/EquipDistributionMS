package com.guanghua.edms.service.user;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.user.UserDao;
import com.guanghua.edms.domain.User;
/**
 * 
 * @author wuqingvika
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired  
	private UserDao userDaoImpl;

	public User getUserByUserName(String userName) {
		return userDaoImpl.getUserByUserName(userName);
	}

}
