package com.guanghua.edms.dao;

import java.util.List;

import com.guanghua.edms.domain.UserInfo;
import net.sf.json.JSONObject;
public interface UserDao {
	
	public UserInfo getUserByUserName(String userName);
	//1.查询所有的用户
	public JSONObject listUsers(int pageSize, int rows,String username);
	//修改密码
	//2.添加会员信息
	public int addUser(UserInfo userInfo);
	//3.修改会员信息
	public int editMyUser(UserInfo userInfo);
	/**
	 * 
	 * 4.重置密码，
	 * @param userName
	 * @return
	 */
	public int resetPwd(String userName);
	//5.修改密码
	public int updatePwd(UserInfo userInfo);
	//6.批量删除(除了本身）
	public int delUsers(List<UserInfo> userInfos,UserInfo bs);
	
}
