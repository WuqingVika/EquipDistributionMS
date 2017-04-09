package com.guanghua.edms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.UserDao;
import com.guanghua.edms.domain.UserInfo;

import net.sf.json.JSONObject;
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

	@Override
	public JSONObject listVips(int pageSize, int rows, String userName) {
		
		return userDao.listUsers(pageSize, rows, userName);
	}

	@Override
	public int addUser(UserInfo userInfo) {
		
		return userDao.addUser(userInfo);
	}

	@Override
	public int editMyUser(UserInfo userInfo) {
		return userDao.editMyUser(userInfo);
	}

	@Override
	public int resetPwd(String userName) {
		// TODO Auto-generated method stub
		return userDao.resetPwd(userName);
	}

	@Override
	public int updatePwd(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userDao.updatePwd(userInfo);
	}

	@Override
	public int delUsers(List<UserInfo> userInfos, UserInfo bs) {
		// TODO Auto-generated method stub
		return userDao.delUsers(userInfos, bs);
	}

}
