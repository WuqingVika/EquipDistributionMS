package com.guanghua.edms.service;


import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.domain.UserInfo;

import net.sf.json.JSONObject;

public interface UserService {
	/**
	 * 根据用户名获得User对象
	 * @param userName
	 * @return
	 */
	public UserInfo getUserByUserName(String userName);
	/**
	 * 查询会员列表信息
	 * @param pageSize
	 * @param rows
	 * @param userName 模态查询用户名
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject listVips(int pageSize, int rows,String userName);
	/**
	 * 添加会员信息
	 * @param userInfo
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
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
	/**
	 * 7.发送邮件更改状态 u_state,code
	 */
	public int updateState(UserInfo userInfo);
	//8.判断链接是否失效
	public UserInfo getUserByUserName(String userName,String code);
	
}
