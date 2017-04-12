package com.guanghua.edms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.edms.service.RegionService;
import com.guanghua.edms.service.RoomService;
import com.guanghua.edms.service.UserService;
import com.guanghua.edms.util.CodeUtil;
import com.guanghua.edms.util.Constants;
import com.guanghua.edms.util.MailUtil;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.guanghua.edms.common.encode.Md5Pwd;
import com.guanghua.edms.common.web.BaseAction;
import com.guanghua.edms.common.web.CustomException;
import com.guanghua.edms.common.web.session.SessionProvider;
import com.guanghua.edms.domain.Room;
import com.guanghua.edms.domain.UserInfo;

@Component("vipAction")
@Scope("prototype")
public class VipAction extends BaseAction implements ServletRequestAware, ModelDriven<UserInfo>{
	@Resource
	private UserService userService;
	
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private Md5Pwd md5Pwd;
	//private Logger logger = Logger.getLogger(this.getClass().getName());
	private HttpServletRequest request;
	private int total; // 记录数
	private List<Object> rows; // datagrid数据
	private int pageSize; // 换页：单页最大显示数
	private int pageNumber; // 换页：页码
	private Map<String, String> results;
	
	private UserInfo userInfo = new UserInfo();
	
	/**
	 * 1.查询会员列表
	 */
	public void userListQuery() throws CustomException{
		System.out.println("wq--5-1.---查询会员列表--信息请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		try {
			int pageSize = Integer.parseInt(req.getParameter("page"));
		    int rows = Integer.parseInt(req.getParameter("rows"));
		    String userName = req.getParameter("userName").trim();
			JSONObject result=userService.listVips(pageSize, rows, userName);
			String regionSql=(String)result.get("vipSql");
			req.getSession().setAttribute("vipSql", regionSql);
			PrintWriter out=res.getWriter();
			out.println(result.toString());
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 2.添加会员
	 */
	public void addMyvip(){
		System.out.println("wq--------1.添加机房请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		int addFlag=0;
		if(userInfo!=null){
			if(userInfo.getPassword()==null){
				userInfo.setPassword("123456");
			}
			//MD5加密
			String encodePwd = md5Pwd.encode(userInfo.getPassword());
			userInfo.setPassword(encodePwd);
			addFlag=userService.addUser(userInfo);
		}
		//将标记传给前台
		Map<String, String> msg=new HashMap<String, String>();
		msg.put("msg",addFlag+"");
		System.out.println("----------------"+msg.get("msg").toString());
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
	/**
	 * 3.修改会员
	 */
	public void editMyvip(){
		System.out.println("wq--------2.修改会员请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		
		int editFlag=0;
		if(userInfo!=null){
			editFlag=userService.editMyUser(userInfo);
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
	/**
	 * 3.移除管理员
	 */
	public void delMyvips(){
		System.out.println("wq--------删除管理员请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		List<UserInfo> userinfos=new ArrayList<UserInfo>();
		/**
		 * 分割 ids
		 */
		String ids=req.getParameter("ids");
		String[] idArray = ids.split(","); 
		if (idArray != null && idArray.length > 0) {  
             //List<Long> uploadIds = new LinkedList<Long>(); 
             for (String id : idArray) {  
            	 UserInfo newUser=new UserInfo();
            	 newUser.setUserId((Long.valueOf(id)));
            	 userinfos.add(newUser);
             }
         }  
		/* 分割 end*/
		
		int delFlag=0;
		if(userinfos!=null&&userinfos.size()!=0){
			System.out.println("4-1**---------------ids:"+ids+"-----------userinfos size:"+userinfos.size());
			UserInfo bs=(UserInfo) sessionProvider.getAttribute(req,Constants.USER_SESSION);
					//Buyer buyer=(Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
			delFlag=userService.delUsers(userinfos, bs);
		}
		//将标记传给前台
		Map<String, String> msg=new HashMap<String, String>();
		msg.put("msg",delFlag+"");
		System.out.println("del----------------"+msg.get("msg").toString());
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
	/**
	 * 1.5查询邮箱
	 */
	public void getEmailByUserName(){
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="0";
		if(userInfo!=null){
			userInfo=userService.getUserByUserName(userInfo.getUserName());
		}
		if(userInfo==null){
			editFlag="-1";//不存在该账号
		}else if(userInfo.getEmail()!=null){
			if(userInfo.getuState()!=2){//2代表已经发过修改密码请求了
				editFlag=userInfo.getEmail();
			}else{
				editFlag="2";//已经发过链接了
			}
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
	/**
	 * 1.6发送邮箱激活链接
	 */
	public String sendMail(){
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="发送失败,请稍后再试！";
		if(userInfo!=null&&userInfo.getUserName()!=null){//接收到userName
			//判断是否已经发送过了
			userInfo=userService.getUserByUserName(userInfo.getUserName());
			if(userInfo.getuState()==2){
				//已经发过，不要再发了。
				editFlag="系统已经发送过重置密码链接,请及时去验证！";
			}else{
				
				/**前端传来两个参数,userName;
				 * 1.生成重置码
				 * 2.修改数据库当前user的状态为2,code为生成的重置码
				 * 3.发送邮件
				 */
				//生成激活码(32位)
				String code=CodeUtil.generateUniqueCode();
				userInfo.setCode(code);
				userInfo.setuState(2);
				if(userService.updateState(userInfo)>0){
					//userInfo=userService.getUserByUserName(userInfo.getUserName());
					//修改成功，发送邮件
					new Thread(new MailUtil(userInfo.getEmail(),code,userInfo.getWorkNo(),userInfo.getUserName())).start();
					editFlag="发送成功，请及时前往邮箱点击重置链接修改密码！";
					
				}
			}
		}
		req.setAttribute("msg", editFlag);
		req.setAttribute("mytitle", "发送邮件结果");
		return "resultMsg";
	}
	/**
	 * 1.7 邮箱前往修改密码页面
	 */
	public String toResetPwd(){
		/**1.判断是邮件修改还是普通修改
		 *   邮件：判断code是否为空----u_state是否为2,2就是邮件修改，其他则为普通
		 * 2.一点击将状态置为正常
		 */
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="";
		if(userInfo!=null){//说明有信息传来
			String myUserName=userInfo.getUserName();
			if(userInfo.getCode()!=null){
				/*判断链接是否失效----code+userName查询有无结果
				 *   失效：返回error提示信息
				 *   有效：返回用户信息
				 */
				String mycode=userInfo.getCode();
				userInfo=userService.getUserByUserName(myUserName, mycode);
				if(userInfo!=null){
					//允许修改
					userInfo.setCode(null);
					userInfo.setuState(userInfo.getSp()==0?0:1);
					if(userService.updateState(userInfo)>0){
						request.setAttribute("user", userInfo);
						request.setAttribute("flag", 0);//代表是邮箱 这样页面可以不用确认旧密码
					}else{
						editFlag="系统错误！";
					}
				}else{
					if(userService.getUserByUserName(myUserName).getuState()!=2){
						editFlag="重置修改密码链接已经失效！您可重新获取";
					}else{
						editFlag="请确认你的邮箱重置码是否正确！";
					}
						
				}
			}else{
				editFlag="激活重置码不得为空！";
			}
		}else{
			editFlag="请确认你的邮箱重置链接是否正确！";
		}
		if(editFlag!=""){
			request.setAttribute("error", editFlag);
		}
		
		return "updatePwd";
	}
	/**
	 * 1.8重置密码
	 */
	public String updateForgetPwd(){
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="";
		if(userInfo!=null&&userInfo.getPassword()!=null&&userInfo.getUserName()!=null){
			//Md5加密
			userInfo.setPassword(md5Pwd.encode(userInfo.getPassword()));
			if(userService.updatePwd(userInfo)>0){
				//修改成功
				editFlag="您的密码修改成功！";
			}else{
				editFlag="操作失败，请稍后重试！";
			}
		}
		req.setAttribute("msg", editFlag);
		req.setAttribute("mytitle", "重置密码结果");
		return "resultMsg";
	}
	
	/**
	 * 1.9 普通页面前往修改密码页面
	 */
	public String toCommonPwd(){
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="";
		if(userInfo!=null&&userInfo.getUserName()!=null){
			userInfo=userService.getUserByUserName(userInfo.getUserName());
			request.setAttribute("user", userInfo);
		}else{
			editFlag="系统错误，请稍后重试！";
		}
		if(editFlag!=""){
			request.setAttribute("error", editFlag);
		}
		return "updatePwd";
	}
	/**
	 * 2.0修改密码时：判断旧密码输入是否正确。
	 */
	public void checkPwdOld(){
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="0";
		if(userInfo!=null&&passwordOld!=null&&userInfo.getUserName()!=null){
			userInfo=userService.getUserByUserName(userInfo.getUserName());
		}
		
		if(md5Pwd.encode(passwordOld).equals(userInfo.getPassword())){
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
	/*旧密码*/
	private String passwordOld;
	
	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}
	/**
	 * 2.1普通修改密码
	 */
	public void updateCommonPwd(){
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		String editFlag="";
		if(userInfo!=null&&userInfo.getPassword()!=null&&userInfo.getUserName()!=null){
			//Md5加密
			userInfo.setPassword(md5Pwd.encode(userInfo.getPassword()));
			if(userService.updatePwd(userInfo)>0){
				//修改成功
				editFlag="1";
			}else{
				editFlag="0";
			}
		}
		try {
			res.sendRedirect(request.getContextPath() +"/WEB-INF/back_page/resultMsg/msgSendEmail.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*//将标记传给前台
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
		}*/
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Map<String, String> getResults() {
		return results;
	}

	public void setResults(Map<String, String> results) {
		this.results = results;
	}

	@Override
	public UserInfo getModel() {
		return userInfo;
	}
	
}
