package com.guanghua.edms.common.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
/**
 * Session 供应类
 * @author wuqingvika
 *
 */
public interface SessionProvider {

	//往Session设置值
		//name Constants  user_session
		//value   用户对象
		public void setAttribute(HttpServletRequest request, String name,Serializable value);
		
		//从Session中取值
		public Serializable getAttribute(HttpServletRequest request,String name);
		
		//退出登陆
		public void logout(HttpServletRequest request);
		
		//获取SessionID
		public String getSessionId(HttpServletRequest request);
		//退出登陆
}
