/**
  * 文件说明
  * @Description:扩展说明
  * @Copyright: 2015 dreamtech.com.cn Inc. All right reserved
  * @Version: V6.0
  */
package com.guanghua.edms.util;

/*import java.io.IOException;*/
import java.util.Map;

/*import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;*/

/**  
 * @Author: feizi
 * @Date: 2015年4月17日 上午9:26:34 
 * @ModifyUser: feizi
 * @ModifyDate: 2015年4月17日 上午9:26:34 
 * @Version:V6.0
 */
public class HttpRequestUtil {

	/**
	 * HttpClient 模拟POST请求
	  * 方法说明
	  * @Discription:扩展说明
	  * @param url
	  * @param params
	  * @return String
	  * @Author: feizi
	  * @Date: 2015年4月17日 下午7:15:59
	  * @ModifyUser：feizi
	  * @ModifyDate: 2015年4月17日 下午7:15:59
	 */
	public static String postRequest(String url, Map<String, String> params) {
		/*//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		//创建POST方法的实例
		PostMethod postMethod = new PostMethod(url);
		
		//设置请求头信息
		postMethod.setRequestHeader("Connection", "close");
		
		//添加参数
		for (Map.Entry<String, String> entry : params.entrySet()) {
			postMethod.addParameter(entry.getKey(), entry.getValue());
		}
		
		//使用系统提供的默认的恢复策略,设置请求重试处理，用的是默认的重试处理：请求三次
		httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		
		//接收处理结果
		String result = null;
		try {
			//执行Http Post请求
			httpClient.executeMethod(postMethod);
			
			//返回处理结果
			result = postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
		    System.out.println("请检查输入的URL!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
		    System.out.println("发生网络异常!");
			e.printStackTrace();
		} finally {
			//释放链接
			postMethod.releaseConnection();
			
			//关闭HttpClient实例
			if (httpClient != null) {
				((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();
				httpClient = null;
			}
		}
		return result;*/
		return "";//乱改的---wq用的时候需要去掉
	}

	/**
	 *  HttpClient 模拟GET请求
	  * 方法说明
	  * @Discription:扩展说明
	  * @param url
	  * @param params
	  * @return String
	  * @Author: feizi
	  * @Date: 2015年4月17日 下午7:15:28
	  * @ModifyUser：feizi
	  * @ModifyDate: 2015年4月17日 下午7:15:28
	 */
	public static String getRequest(String url, Map<String, String> params) {
		/*//构造HttpClient实例
		HttpClient client = new HttpClient();
		
		//拼接参数
		String paramStr = "";
		for (String key : params.keySet()) {
			paramStr = paramStr + "&" + key + "=" + params.get(key);
		}
		paramStr = paramStr.substring(1);
		
		//创建GET方法的实例
		GetMethod method = new GetMethod(url + "?" + paramStr);
		
		//接收返回结果
		String result = null;
		try {
			//执行HTTP GET方法请求
			client.executeMethod(method);
			
			//返回处理结果
			result = method.getResponseBodyAsString();
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
		    System.out.println("请检查输入的URL!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
		    System.out.println("发生网络异常!");
			e.printStackTrace();
		} finally {
			//释放链接
			method.releaseConnection();
			
			//关闭HttpClient实例
			if (client != null) {
				((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
				client = null;
			}
		}
		return result;*/
		return "";//乱改的。
	}
}
