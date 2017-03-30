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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.edms.service.RegionService;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.guanghua.edms.common.web.BaseAction;
import com.guanghua.edms.common.web.CustomException;
import com.guanghua.edms.domain.Region;

@Component("regionAction")
@Scope("prototype")
public class RegionAction extends BaseAction implements ServletRequestAware, ModelDriven<Region>{
	@Resource
	private RegionService regionService;
	//private Logger logger = Logger.getLogger(this.getClass().getName());
	private HttpServletRequest request;
	private int total; // 记录数
	private List<Object> rows; // datagrid数据
	private int pageSize; // 换页：单页最大显示数
	private int pageNumber; // 换页：页码
	private Map<String, String> results;
	
	private Region region = new Region();
	/**
	 * 1.添加局站
	 */
	public void addMyRegion(){
		System.out.println("wq--------1.添加局站请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		int addFlag=0;
		if(region!=null){
			addFlag=regionService.addRegion(region);
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
	 * 2.修改局站
	 */
	public void editMyRegion(){
		System.out.println("wq--------2.修改局站请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		
		int editFlag=0;
		if(region!=null){
			editFlag=regionService.editRegion(region);
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
	 * 3.移除局站
	 */
	public void delMyRegions(){
		System.out.println("wq--------3.删除局站请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		List<Region> regions=new ArrayList<Region>();
		/**
		 * 分割 ids
		 */
		String ids=req.getParameter("ids");
		String[] idArray = ids.split(","); 
		if (idArray != null && idArray.length > 0) {  
             //List<Long> uploadIds = new LinkedList<Long>(); 
             for (String id : idArray) {  
            	 Region newRegion=new Region();
            	 newRegion.setRegionId(Long.valueOf(id));
            	 regions.add(newRegion);
             }
         }  
		/* 分割 end*/
		
		int delFlag=0;
		if(regions!=null&&regions.size()!=0){
			System.out.println("4-1**---------------ids:"+ids+"-----------regionsize:"+regions.size());
			delFlag=regionService.removeRegions(regions);
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
	 * 4.查询局站列表
	 */
	public void listMyRegions() throws CustomException{
		System.out.println("wq--4-1.---查询局站列表--信息请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		try {
			int pageSize = Integer.parseInt(req.getParameter("page"));
		    int rows = Integer.parseInt(req.getParameter("rows"));
		    String regionName = req.getParameter("regionName").trim();
			JSONObject result=regionService.listRegion(pageSize, rows, regionName);
			String regionSql=(String)result.get("regionSql");
			req.getSession().setAttribute("regionSql", regionSql);
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
	public Region getModel() {
		return region;
	}
	
}
