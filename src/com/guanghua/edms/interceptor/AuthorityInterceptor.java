package com.guanghua.edms.interceptor;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.guanghua.edms.common.web.session.SessionProvider;
import com.guanghua.edms.domain.UserInfo;
import com.guanghua.edms.util.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * 定义拦截器 2017/04/20
 * @author wuqingvika
 *
 */
public class AuthorityInterceptor extends AbstractInterceptor{
	@Autowired
	private SessionProvider sessionProvider;
	//设置常量
	private static final String INTERCEPTOR_URL="/user/";//可以拦截带"/user的请求地址"
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//ActionContext ctx = invocation.getInvocationContext();
		/*Map<String,Object> session = ctx.getSession();
		Object object = session.get("name");*/
		HttpServletRequest request=ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) sessionProvider.getAttribute(request, Constants.USER_SESSION);
		if(userInfo != null){
			return invocation.invoke();
		}else{
			String requestURI = request.getRequestURI();
			//如果是验证码、找回密码发送邮件请求都不用拦截，直接放过
			if(requestURI.indexOf("getEmail")!=-1||requestURI.indexOf("login")!=-1||
					requestURI.indexOf("toResetPwd")!=-1||requestURI.indexOf("verifyCaptcha")!=-1
					||requestURI.indexOf("sendMail")!=-1||requestURI.indexOf("updateForgetPwd")!=-1){//忘记密码不用拦截
				System.out.println("你走吧----"+requestURI);
				return invocation.invoke();
			}
			System.out.println("我不让你走----"+requestURI);
			request.setAttribute("error", "请登录后操作！");
			return "toLogin";
		}
	}

}
