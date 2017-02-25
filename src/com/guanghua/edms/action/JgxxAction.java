package com.guanghua.edms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.edms.common.web.BaseAction;
import com.guanghua.edms.dao.CabinetDao;
import com.guanghua.edms.service.CabinetService;

import net.sf.json.JSONArray;

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
	 * 1.查询局站
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
}
