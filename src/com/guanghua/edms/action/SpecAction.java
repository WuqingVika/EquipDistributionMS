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
import com.guanghua.edms.service.SpecService;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

import com.guanghua.edms.common.web.BaseAction;
import com.guanghua.edms.domain.Spec;

@Component("specAction")
@Scope("prototype")
public class SpecAction extends BaseAction implements ServletRequestAware, ModelDriven<Spec>{
	@Resource
	private SpecService specService;
	//private Logger logger = Logger.getLogger(this.getClass().getName());
	private HttpServletRequest request;
	private int total; // 记录数
	private List<Object> rows; // datagrid数据
	private int pageSize; // 换页：单页最大显示数
	private int pageNumber; // 换页：页码
	private Map<String, String> results;
	
	private Spec spec = new Spec();
	/**
	 * 1.添加专业
	 */
	public void addMySpec(){
		System.out.println("wq--------1.添加专业请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		int addFlag=0;
		if(spec!=null){
			addFlag=specService.addSpec(spec);
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
	 * 2.修改专业
	 */
	public void editMySpec(){
		System.out.println("wq--------2.修改专业请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		
		int editFlag=0;
		if(spec!=null){
			editFlag=specService.editSpec(spec);
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
	 * 3.移除专业
	 */
	public void delMySpecs(){
		System.out.println("wq--------3.删除专业请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		List<Spec> specs=new ArrayList<Spec>();
		/**
		 * 分割 ids
		 */
		String ids=req.getParameter("ids");
		String[] idArray = ids.split(","); 
		if (idArray != null && idArray.length > 0) {  
             //List<Long> uploadIds = new LinkedList<Long>(); 
             for (String id : idArray) {  
            	 Spec newSpec=new Spec();
            	 newSpec.setSpecId(Long.valueOf(id));
            	 specs.add(newSpec);
             }
         }  
		/* 分割 end*/
		
		int delFlag=0;
		if(specs!=null&&specs.size()!=0){
			System.out.println("4-1**---------------ids:"+ids+"-----------sepcsize:"+specs.size());
			delFlag=specService.removeSpecs(specs);
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
	public Spec getModel() {
		return spec;
	}
	
}
