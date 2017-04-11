package com.guanghua.edms.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.edms.common.SecurityCode;
import com.guanghua.edms.common.SecurityCode.SecurityCodeLevel;
import com.guanghua.edms.common.web.session.SessionProvider;
import com.guanghua.edms.util.Constants;
import com.guanghua.edms.common.SecurityImage;
import com.opensymphony.xwork2.ActionSupport;
@Component("captchaAction")
@Scope("prototype")
public class CaptchaAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// 图片流
	private ByteArrayInputStream imageStream;
	/*// session域
	private HttpSession session = ServletActionContext.getRequest().getSession();*/
	@Autowired
	private SessionProvider sessionProvider;
	
	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	/*public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}*/

	public String execute() {
		HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println("hahah");
		String securityCode = SecurityCode.getSecutrityCode(4, SecurityCodeLevel.Hard, false);
		imageStream = SecurityImage.getImageAsInputStream(securityCode);
		// 放入session中
		sessionProvider.setAttribute(request,Constants.SECTURITY_CODE, securityCode);
		return SUCCESS;
	}

}
