package com.guanghua.edms.service.user;


import com.guanghua.edms.domain.User;

public interface UserService {

	public static final String SERVICE_NAME = "com.guanghua.edms.service.user.UserServiceImpls";
	public User getUserByUserName(String userName);
	
}
