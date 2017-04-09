package com.guanghua.edms.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.edms.common.encode.Md5Pwd;
import com.guanghua.edms.common.web.CustomException;
import com.guanghua.edms.common.web.session.SessionProvider;
import com.guanghua.edms.domain.UserInfo;
import com.guanghua.edms.service.UserService;
import com.guanghua.edms.util.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;




@Component("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport  implements ModelDriven<UserInfo>{
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private Md5Pwd md5Pwd;
	@Resource
	private UserService userService;
	private UserInfo user = new UserInfo();
	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println("username----"+user.getUserName());
		if(null!=user && StringUtils.isNotBlank(user.getUserName())){
			if(StringUtils.isNotBlank(user.getPassword())){
				//判断用户密码
				UserInfo b = userService.getUserByUserName(user.getUserName());//用户名作Key的
				System.out.println("haha----"+b.toString());
				if(b!=null){//md5Pwd.encode(userInfo.getPassword())
					if(b.getPassword().equals(md5Pwd.encode(user.getPassword()))){
						//登录成功！！-------把用户对象放入session中--------
						sessionProvider.setAttribute(request, Constants.USER_SESSION, b);
						//跳转到个人中心
						System.out.println("登录成功---跳转到后台");
						return SUCCESS;
					}else{
						 //ActionContext.getContext().put("error","密码输入错误");
						//登陆失败
			            //this.addActionError("用户名或密码错误！");
						request.setAttribute("error", "用户名或密码错误！");
					}
				}
			}else{
				//ActionContext.getContext().put("error", "请输入密码");
				 //this.addActionError("请输入密码！");
				 request.setAttribute("error", "请输入密码！");
			}
		}else{//如果用户名为空
			//this.addActionError("请输入用户名！");
			request.setAttribute("error", "请输入用户名！");
		}
		return "loginAgain";
	}
	
	/*
	 * 跳转到机柜列表
	 */
	public String toCabinetList() throws Exception {
		return SUCCESS;
	}
	/*
	 * 跳转到设备列表
	 */
	public String toEquipmentList() throws Exception {
		return SUCCESS;
	}
	/*
	 * 跳转到板卡列表
	 */
	public String toCardList() throws Exception {
		return SUCCESS;
	}
	/*
	 * 跳转到专业列表
	 */
	public String toSpecList() throws Exception {
		return SUCCESS;
	}
	/*
	 * 跳转到局站列表
	 */
	public String toRegionList() throws Exception {
		return SUCCESS;
	}
	/*
	 * 跳转到机房列表
	 */
	public String toRoomList() throws Exception {
		return SUCCESS;
	}
	/*
	 * 跳转到管理员列表
	 */
	public String toVipList() throws Exception {
		return SUCCESS;
	}
	
	
	
	
	/*private static final Log logger = LogFactory.getLog(LoginAction.class);
	private List<Object> rows;			// datagrid数据	
	private Map<String, String> results;
	
	public List<Object> getRows() {
		return rows;
	}	

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	
	public void setResults(Map<String, String> results) {
		this.results = results;
	}

	public Map<String, String> getResults() {
		return results;
	}
	
	**/
	@Override
	public UserInfo getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
