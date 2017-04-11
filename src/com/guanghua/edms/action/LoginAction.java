package com.guanghua.edms.action;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.edms.common.encode.Md5Pwd;
import com.guanghua.edms.common.web.session.SessionProvider;
import com.guanghua.edms.domain.UserInfo;
import com.guanghua.edms.service.UserService;
import com.guanghua.edms.util.Constants;
import com.octo.captcha.service.CaptchaService;
import com.opensymphony.xwork2.ActionContext;
/*import com.octo.captcha.service.image.ImageCaptchaService;*/
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

@Component("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport  implements  ModelDriven<UserInfo>{
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private Md5Pwd md5Pwd;
	/*private HttpServletRequest request;*/
	/*@Autowired
	private CustomGenericManageableCaptchaService captchaService;*/
	/*@Autowired
	private CaptchaService imageCaptchaService;*/
	@Resource
	private UserService userService;
	private UserInfo user = new UserInfo();
	//验证码
	private String captcha;
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println("action1req--------"+sessionProvider.getSessionId(request));
		//验证码是否为null
		if(StringUtils.isNotBlank(captcha)){
			//1.JsessionId 2.验证码
			//imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request), captcha)
			if(sessionProvider.getAttribute(request,"secturity_code").toString().toLowerCase().equals(this.captcha.toLowerCase())){
				//captchaService.removeCaptcha(sessionProvider.getSessionId(request));
				if(null!=user && StringUtils.isNotBlank(user.getUserName())){
					if(StringUtils.isNotBlank(user.getPassword())){
						//判断用户密码
						UserInfo b = userService.getUserByUserName(user.getUserName());//用户名作Key的
						//System.out.println("haha----"+b.toString());
						if(b!=null){//md5Pwd.encode(userInfo.getPassword())
							if(b.getPassword().equals(md5Pwd.encode(user.getPassword()))){
								//登录成功！！-------把用户对象放入session中--------
								sessionProvider.setAttribute(request, Constants.USER_SESSION, b);
								request.setAttribute("user", b);
								//跳转到个人中心
								System.out.println("登录成功---跳转到后台");
								return SUCCESS;
							}else{
							 //ActionContext.getContext().put("error","密码输入错误");
							//登陆失败
				            //this.addActionError("用户名或密码错误！");
							request.setAttribute("error", "用户名或密码错误！");
							}
						}else{
							request.setAttribute("error", "用户不存在！");
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
			}else{
				request.setAttribute("error", "验证码输入错误！");
			}
		}else{
			request.setAttribute("error", "请输入验证码！");
		}
		return "loginAgain";
	}
	
	/**
	 * 1.5查询邮箱
	 */
	public void verifyCaptcha(){
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="0";
		if(sessionProvider.getAttribute(req,"secturity_code").toString().toLowerCase().equals(this.captcha.toLowerCase())){
			editFlag="1";
		}else{
			editFlag="0";
		}
		//将标记传给前台
		Map<String, String> msg=new HashMap<String, String>();
		msg.put("msg",editFlag+"");
		List<Map<String, String>> flags=new ArrayList<Map<String,String>>();
		flags.add(msg);
		JSONArray jsonArray = JSONArray.fromObject( flags );
		try {
			PrintWriter out=res.getWriter();
			jsonArray.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	/*@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}*/
	
}
