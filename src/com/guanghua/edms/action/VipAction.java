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
import com.guanghua.edms.util.Constants;
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
