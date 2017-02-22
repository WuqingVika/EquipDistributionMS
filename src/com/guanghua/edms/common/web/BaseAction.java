package com.guanghua.edms.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * BaseAction
 * @author wuqingvika
 *
 */
public class BaseAction extends ActionSupport implements
ServletRequestAware{
	public BaseAction(){

	}
	
	// 操作结果
	protected static final String RESULT_KEY_RESULT = "result";
	
	// 错误信息
	protected static final String RESULT_KEY_MSG = "exceptionMsg";
	
	// 操作成功
	protected static final String RESULT_SUCCESS = "0";
	
	// 系统异常
	protected static final String RESULT_EXCEPTION = "1";
	
	// 业务异常
	protected static final String RESULT_CUSTOMEXCEPTION = "2";
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	// 返回操作成功结果
		protected Map<String, String> getSuccessResult(){
			Map<String, String>  map = new HashMap<String, String>();
			map.put(RESULT_KEY_RESULT, RESULT_SUCCESS);
			return map;
		}
		
		// 返回系统异常结果
		protected Map<String, String> getExceptionResult(Exception e){
			Map<String, String>  map = new HashMap<String, String>();
			map.put(RESULT_KEY_RESULT, RESULT_EXCEPTION);
			map.put(RESULT_KEY_MSG, e.getMessage());
			return map;
		}
		
		// 返回业务异常结果
		protected Map<String, String> getCustomExceptionResult(CustomException e){
			Map<String, String>  map = new HashMap<String, String>();
			map.put(RESULT_KEY_RESULT, RESULT_CUSTOMEXCEPTION);
			map.put(RESULT_KEY_MSG, e.getMessage());
			return map;
		}
		
		// 返回画面错误信息
		protected Map<String, String> getErrMsgResult(String errMsg){
			Map<String, String>  map = new HashMap<String, String>();
			map.put(RESULT_KEY_RESULT, RESULT_CUSTOMEXCEPTION);
			map.put(RESULT_KEY_MSG, errMsg);
			return map;
		}
}
