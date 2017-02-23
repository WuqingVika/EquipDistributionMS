package com.guanghua.edms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.UserDao;
import com.guanghua.edms.domain.UserInfo;
/**
 * 
 * @author wuqingvika
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired  
	private UserDao userDao;

	public UserInfo getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

}
