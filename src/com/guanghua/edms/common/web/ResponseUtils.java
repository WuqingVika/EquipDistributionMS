package com.guanghua.edms.common.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
/**
 * json
 * xml
 * text
* @ClassName: ResponseUtils 
* @Description: (专门处理 异步返回各种格式 ) 
* @author (wuqingvika)  
* @date 2016年10月13日 下午2:19:24 
* @version V1.0
 */
public class ResponseUtils {
	//发送内容"application/json;charset=UTF-8"
	public static void render(HttpServletResponse response,String contentType,String text){
		response.setContentType(contentType);//
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//发送的是JSon
	public static void renderJson(HttpServletResponse response,String text){
		render(response,"application/json;charset=UTF-8",text);
	}
	//发送的是xml
	public static void renderXml(HttpServletResponse response,String text){
		render(response,"text/xml;charset=UTF-8",text);
	}
	//发送的是Text
	public static void renderText(HttpServletResponse response,String text){
		render(response,"text/plain;charset=UTF-8",text);
	}	
}
