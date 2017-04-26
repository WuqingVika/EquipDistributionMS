package com.guanghua.edms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.edms.common.web.CustomException;
import com.guanghua.edms.common.web.session.SessionProvider;
import com.guanghua.brick.biz.BizUtil;
import com.guanghua.edms.common.web.BaseAction;
import com.guanghua.edms.service.CabinetService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("jgxxAction")
@Scope("prototype")
public class JgxxAction extends BaseAction implements ServletRequestAware{
	@Resource
	private CabinetService cabinetService;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private HttpServletRequest request;
	private int total; // 记录数
	private List<Object> rows; // datagrid数据
	private int pageSize; // 换页：单页最大显示数
	private int pageNumber; // 换页：页码
	private Map<String, String> results;
	@Autowired
	private SessionProvider sessionProvider;
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
	/**
	 * 1-1.查询局站
	 */
	public void selJiguiJuZhan(){
		System.out.println("wq--------局站请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		//CabinetDao jiGuiBean=(CabinetDao)BizUtil.getBizBean("jigui_xinxi");
		//List<Map<String, String>> list =jiGuiBean.selJuZhan();
		List<Map<String, String>> list =cabinetService.selJuZhan();
		System.out.println("jgxxaction list size juzhan----"+list.size());
		JSONArray jsonArray = JSONArray.fromObject( list );
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
	 * 1-2.查询机房
	 */
	public void selJifangByJuId(){
		System.out.println("wq--------一个局站>>多个机房请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		//JiGuiXinxiGuanLi jiGuiBean=(JiGuiXinxiGuanLi)BizUtil.getBizBean("jigui_xinxi");
		int regionId = Integer.parseInt(req.getParameter("regionId"));
		List<Map<String, String>> list =cabinetService.selJiFangByJuZhanId(regionId);
		JSONArray jsonArray = JSONArray.fromObject( list );
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
	 * 1-3.查询机柜专业
	 */
	public void selJiguiZhuanYe(){
		System.out.println("wq--------专业请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		//JiGuiXinxiGuanLi jiGuiBean=(JiGuiXinxiGuanLi)BizUtil.getBizBean("jigui_xinxi");
		List<Map<String, String>> list =cabinetService.selZhuanYes();
		JSONArray jsonArray = JSONArray.fromObject( list );
		try {
			PrintWriter out=res.getWriter();
			jsonArray.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//selJiguiToGrid
	public void selJiguiToGrid() throws CustomException {
		System.out.println("wq------查询机柜--信息请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		try {
			int pageSize = Integer.parseInt(req.getParameter("page"));
		    int rows = Integer.parseInt(req.getParameter("rows"));
		    /**
		     *  juZhan:juZhan, 	
		        	 jiFang:jiFang,
		        	 zhuanYe:zhuanYe,
		        	 bianOrMc:bianOrMc
		     */
			String juZhan = req.getParameter("juZhan").trim();
			String jiFang = req.getParameter("jiFang").trim();
			String zhuanYe = req.getParameter("zhuanYe").trim();
			String bianOrMc = req.getParameter("bianOrMc").trim();
			
			//JiGuiXinxiGuanLi jiGuiBean=(JiGuiXinxiGuanLi)BizUtil.getBizBean("jigui_xinxi");
			JSONObject result=cabinetService.selJiGuiByQuery(pageSize, rows,juZhan,jiFang,zhuanYe,bianOrMc);
			String assSql=(String)result.get("assSql");
			req.getSession().setAttribute("assSql", assSql);
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
	 * 2-1.下拉框显示机柜信息
	 */
	public void selCabinet(){
		System.out.println("wq--------下拉框机柜请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		int roomId = Integer.parseInt(req.getParameter("roomId"));
		List<Map<String, String>> list =cabinetService.selCabinetByJiFangId(roomId);
		JSONArray jsonArray = JSONArray.fromObject( list );
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
	 * 2-2.显示设备列表
	 */
	public void selEquipmentsTogrid() throws CustomException {
		System.out.println("wq------查询设备--信息请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		System.out.println("action2req--------"+sessionProvider.getSessionId(req));
		
		res.setCharacterEncoding("utf-8");
		try {
			int pageSize = Integer.parseInt(req.getParameter("page"));
		    int rows = Integer.parseInt(req.getParameter("rows"));
			String juZhan = req.getParameter("juZhan").trim();
			String jiFang = req.getParameter("jiFang").trim();
			String cabinetId = req.getParameter("cabinetId").trim();
			String gridId = req.getParameter("gridId").trim();
			String equipmentName= req.getParameter("equipmentName").trim();
			JSONObject result=cabinetService.selEquipmentsByQuery(pageSize, rows,juZhan,jiFang,cabinetId,gridId,equipmentName);
			String assSql=(String)result.get("assSql");
			req.getSession().setAttribute("assSql", assSql);
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
	 * 2-3.根据设备ID查询板卡信息
	 */
	public void selCardInfo(){
		System.out.println("wq--------根据设备ID查询板卡信息请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		try {
			int equipId = Integer.parseInt(req.getParameter("equipId"));
			int mypageSize = Integer.parseInt(req.getParameter("page"));
		    int myrows = Integer.parseInt(req.getParameter("rows"));
			JSONObject result =cabinetService.selCardByequipID(mypageSize,myrows,equipId);
			String assSql=(String)result.get("myassSql");
			req.getSession().setAttribute("myassSql", assSql);
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
	 * 3-1.板卡信息 
	 * @throws CustomException
	 */
	public void selCardsTogrid() throws CustomException {
		System.out.println("wq--3-1.----批量查询板卡--信息请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		try {
			int pageSize = Integer.parseInt(req.getParameter("page"));
		    int rows = Integer.parseInt(req.getParameter("rows"));
		    /**
		     *  juZhan:juZhan, 	
	        	 jiFang:jiFang,
	        	 cabinetId:cabinetId,
	        	 gridId:gridId,
	        	 equipmentName:equipmentName
		     */
			String juZhan = req.getParameter("juZhan").trim();
			String jiFang = req.getParameter("jiFang").trim();
			String cabinetId = req.getParameter("cabinetId").trim();
			String gridId = req.getParameter("gridId").trim();
			String equipmentName= req.getParameter("equipmentName").trim();
			
			//JiGuiXinxiGuanLi jiGuiBean=(JiGuiXinxiGuanLi)BizUtil.getBizBean("jigui_xinxi");
			JSONObject result=cabinetService.selCardsByQuery(pageSize, rows,juZhan,jiFang,cabinetId,gridId,equipmentName);
			String assSql=(String)result.get("assSql");
			req.getSession().setAttribute("assSql", assSql);
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
	 * 3-2.下拉框显示设备信息 根据机柜查询设备
	 */
	public void selEquipment(){
		System.out.println("wq------3-2.--下拉框设备请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		int cabinetId = Integer.parseInt(req.getParameter("cabinetId"));
		List<Map<String, String>> list =cabinetService.selEquipmentByCabinetId(cabinetId);
		JSONArray jsonArray = JSONArray.fromObject( list );
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
	 * 3-3.判断机柜里是否有设备
	 */
	public void judgeEquipment(){
		System.out.println("wq--------3-3.设备请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		int cabinetId = Integer.parseInt(req.getParameter("cabinetId"));
		List<Map<String, String>> list =cabinetService.selEquipmentByCabinetId(cabinetId);
		Map<String, String> msg=new HashMap<String, String>();
		if(list.size()>0){
			msg.put("msg","yes");
		}else{
			msg.put("msg","no");
		}
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
	 * 4-1.查询专业列表
	 * @throws CustomException
	 */
	public void selSpecsTogrid() throws CustomException {
		System.out.println("wq--4-1.---查询专业列表--信息请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		try {
			int pageSize = Integer.parseInt(req.getParameter("page"));
		    int rows = Integer.parseInt(req.getParameter("rows"));
		    String specName = req.getParameter("specName").trim();
			JSONObject result=cabinetService.selSpecsByQuery(pageSize, rows,specName);
			String specSql=(String)result.get("specSql");
			req.getSession().setAttribute("specSql", specSql);
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
	
	public void getDistribution(){
		System.out.println("wq------饼图数据查询请求");
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		res.setCharacterEncoding("utf-8");
		List<Map<String, String>> list =cabinetService.getDistribution();
		JSONArray jsonArray = JSONArray.fromObject( list );
		try {
			PrintWriter out=res.getWriter();
			jsonArray.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
