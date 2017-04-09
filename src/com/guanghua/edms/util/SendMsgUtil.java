/**
  * 文件说明
  * @Description:扩展说明
  * @Copyright: 2015 dreamtech.com.cn Inc. All right reserved
  * @Version: V6.0
  */
package com.guanghua.edms.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**  
 * @Author: wuqingvika
 * @Date: 2015年4月17日 上午9:24:48 
 * @ModifyUser: wuqingvika
 * @ModifyDate: 2015年4月17日 上午9:24:48 
 * @Version:V6.0
 */
public class SendMsgUtil {
	
	/**
	 * 发送短信消息
	  * 方法说明
	  * @Discription:扩展说明
	  * @param phones
	  * @param content
	  * @return
	  * @return String
	  * @Author: wuqingvika
	  * @Date: 2015年4月17日 下午7:18:08
	  * @ModifyUser：wuqingvika
	  * @ModifyDate: 2015年4月17日 下午7:18:08
	 */
	@SuppressWarnings("deprecation")
	public static String sendMsg(String phones,String content){
		//短信接口URL提交地址
		String url = "短信接口URL提交地址";
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("zh", "用户账号");
		params.put("mm", "用户密码");
		params.put("dxlbid", "短信类别编号");
		params.put("extno", "扩展编号");
		
		//手机号码，多个号码使用英文逗号进行分割
		params.put("hm", phones);
		//将短信内容进行URLEncoder编码
		params.put("nr", URLEncoder.encode(content));
		
		return HttpRequestUtil.getRequest(url, params);
	}
	
	/**
	 * 随机生成6位随机验证码
	  * 方法说明
	  * @Discription:扩展说明
	  * @return
	  * @return String
	  * @Author: wuqingvika
	  * @Date: 2015年4月17日 下午7:19:02
	  * @ModifyUser：wuqingvika
	  * @ModifyDate: 2015年4月17日 下午7:19:02
	 */
	public static String createRandomVcode(){
		//验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int)(Math.random() * 9);
		}
		return vcode;
	}
	
	/**
	 * 测试
	  * 方法说明
	  * @Discription:扩展说明
	  * @param args
	  * @return void
	  * @Author: wuqingvika
	  * @Date: 2015年4月17日 下午7:26:36
	  * @ModifyUser：wuqingvika
	  * @ModifyDate: 2015年4月17日 下午7:26:36
	 */
	public static void main(String[] args) {
//		System.out.println(SendMsgUtil.createRandomVcode());
//		System.out.println("&ecb=12".substring(1));
		System.out.println(sendMsg("18123456789,15123456789", "尊敬的用户，您的验证码为" + SendMsgUtil.createRandomVcode() + "，有效期为60秒，如有疑虑请详询400-069-2886（客服电话）【XXX中心】"));
	}
}
