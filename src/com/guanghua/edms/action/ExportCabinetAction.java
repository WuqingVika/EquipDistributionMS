package com.guanghua.edms.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.guanghua.brick.biz.BizUtil;
import com.guanghua.brick.biz.inject.Inject;
import com.guanghua.edms.domain.AddCabinet;
import com.guanghua.edms.service.CabinetService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 2017/03/06 21：45
 * @author wuqingvika
 *
 */

@Component("exportCabinetAction")
@Scope("prototype")
public class ExportCabinetAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	/*@Inject
	Session session;*/
	@Resource
	private CabinetService cabinetService;
	//导入机柜信息
	private File uploadFile;
	private String uploadFileFileName;
	private String uploadFileContentType;
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	//0.导出模板信息-downTemplate
	public void downTemplate() throws IOException{
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		String name=req.getParameter("filename");
		name=URLDecoder.decode(name,"UTF-8");
		//String path=req.getContextPath();
		String path=req.getRealPath("/");
		path+="ywgl/platform/"+name+".xls";//
		System.out.println(path+"------------------wqkk");
		File file = new File(path);// path是根据日志路径和文件名拼接出来的
	    String filename = file.getName();// 获取日志文件名称
	    InputStream fis = new BufferedInputStream(new FileInputStream(path));
	    byte[] buffer = new byte[fis.available()];
	    fis.read(buffer);
	    fis.close();
	    res.reset();
	    // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
	    res.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
	    res.addHeader("Content-Length", "" + file.length());
	    OutputStream os = new BufferedOutputStream(res.getOutputStream());
	    res.setContentType("application/octet-stream");
	    os.write(buffer);// 输出文件
	    os.flush();
	    os.close();
		
	}
	//1.导入机柜信息
	public String importCabinet() {
		System.out.println("wq--导入机柜信息");
		int myres=0;
		String errMsg="";
		HttpServletResponse res=ServletActionContext.getResponse();
		HttpServletRequest req=ServletActionContext.getRequest();
		String roomIdmm=req.getParameter("roomId").trim();
		int roomId=Integer.parseInt(roomIdmm);
		System.out.println(roomId+" is roomID---文件名"+getUploadFileFileName()+"  文件类型"+getUploadFileContentType());
		try{
			// 获取到上传的文件
			FileInputStream fis = new FileInputStream(getUploadFile());
			if(fis!=null){
				System.out.println("fis ...");
			}
			String fileName=getUploadFileFileName();
			String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
			System.out.println(prefix);
			Workbook work =null;
			
			if(prefix.equals("xls")){
				 work= new HSSFWorkbook(fis);
			}else{
				work = new XSSFWorkbook(fis);
			}
			// 获取到Excel文档中的第一个表单
			Sheet sheet = work.getSheetAt(0);
			String company="上海NOC";
			//获取到Excel文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();//
			/*if(req.getParameter("roomId")!=null){
				roomId=Integer.parseInt(req.getParameter("roomId").trim());
			}else{
				return ERROR;
			}*/
			//直接从第2行开始读就好了
            int nullFlag=-1;
            
            int type=0;
            List<AddCabinet> cbs=new ArrayList<AddCabinet>();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				AddCabinet cb=new AddCabinet();
				// 获取第i行
				Row row = sheet.getRow(i);
				//遍历表格
				Cell cabinet_numCell = row.getCell(0,Row.CREATE_NULL_AS_BLANK);
				String cabinet_num=null;
				if(cabinet_numCell==null||cabinet_numCell.getCellType()==Cell.CELL_TYPE_BLANK){
					//String cabinet_num="";
					nullFlag=i;
					errMsg+="第"+i+"行,第1列 [机柜编号]不能为空！ ";
				}else{
					cabinet_numCell.setCellType(Cell.CELL_TYPE_STRING);
					
					cabinet_num=cabinet_numCell.getStringCellValue().trim();
					if(cabinet_num.equals("")){
						nullFlag=i;
						errMsg+="第"+i+"行,第1列 [机柜编号]不能为空！ ";
					}
				}
				cb.setCabinet_num(cabinet_num);//第一列 机柜编号
				
				Cell cabinet_nameCell = row.getCell(1,Row.CREATE_NULL_AS_BLANK);
				String cabinet_name="";
				if(cabinet_nameCell==null||cabinet_nameCell.getCellType()==Cell.CELL_TYPE_BLANK){
					
				}else{
					cabinet_nameCell.setCellType(Cell.CELL_TYPE_STRING);
					 cabinet_name=cabinet_nameCell.getStringCellValue();//第二列 机柜名称
				}
				cb.setCabinet_name(cabinet_name);//第二列 机柜名称
				
				String cabinet_surface=null;
				Cell cabinet_surfaceCell = row.getCell(2,Row.CREATE_NULL_AS_BLANK);
				if(cabinet_surfaceCell==null||cabinet_surfaceCell.getCellType()==Cell.CELL_TYPE_BLANK){
					nullFlag=i;
					errMsg+="第"+i+"行,第3列 [机柜面]不能为空！ ";
					
				}else{
					cabinet_surfaceCell.setCellType(Cell.CELL_TYPE_STRING);
					cabinet_surface=cabinet_surfaceCell.getStringCellValue().trim();//第三列 机柜面
					System.out.println("原："+cabinet_surface);
					cabinet_surface=cabinet_surface.replace('，',',');
					System.out.println("新："+cabinet_surface);
					if(cabinet_surface.equals("")){
						nullFlag=i;
						errMsg+="第"+i+"行,第3列 [机柜面]不能为空！ ";
					}
				}
				cb.setCabinet_surface(cabinet_surface);
				
				String spec_name=null;int spec_id=-1;
				Cell spec_idCell = row.getCell(3,Row.CREATE_NULL_AS_BLANK);
				if(spec_idCell==null||spec_idCell.getCellType()==Cell.CELL_TYPE_BLANK){
					nullFlag=i;
					errMsg+="第"+i+"行,第4列 [专业]不能为空！ ";
					
				}else{
					spec_idCell.setCellType(Cell.CELL_TYPE_STRING);
					
					spec_name=spec_idCell.getStringCellValue().trim();//第四列 专业
					if(spec_name.equals("")){
						nullFlag=i;
						errMsg+="第"+i+"行,第4列 [专业]不能为空！ ";
					}else{
						
						if(spec_name.equals("其他")){
							spec_id=0;
						}else if(spec_name.equals("传输")){
							spec_id=1;
						}else if(spec_name.equals("数据")){
							spec_id=2;
						}else if(spec_name.equals("电源")){
							spec_id=3;
						}
					}
				}
				cb.setSpec_id(spec_id);//4
				
				String assert_no="";
				Cell assert_noCell = row.getCell(4,Row.CREATE_NULL_AS_BLANK);
				if(assert_noCell==null||assert_noCell.getCellType()==Cell.CELL_TYPE_BLANK){
					
				}else{
					assert_noCell.setCellType(Cell.CELL_TYPE_STRING);
					assert_no=assert_noCell.getStringCellValue();//第五列 固定资产编号
				}
				cb.setAssert_no(assert_no);//5
				
				
				String power_a="";
				Cell power_aCell = row.getCell(5,Row.CREATE_NULL_AS_BLANK);
				if(power_aCell==null||power_aCell.getCellType()==Cell.CELL_TYPE_BLANK){
					//power_a="";
				}else{
				power_aCell.setCellType(Cell.CELL_TYPE_STRING);
				power_a=power_aCell.getStringCellValue();//第6列 A路电源
				}
				cb.setPower_a(power_a);//6
				
				
				String power_b="";
				Cell power_bCell = row.getCell(6,Row.CREATE_NULL_AS_BLANK);
				if(power_bCell==null||power_bCell.getCellType()==Cell.CELL_TYPE_BLANK){
					//power_a="";
				}else{
					power_bCell.setCellType(Cell.CELL_TYPE_STRING);
					power_b=power_bCell.getStringCellValue();//第7列 B路电源
				}
				cb.setPower_b(power_b);//7
				
				
				
				String layerCount=null;
				Cell layerCountCell = row.getCell(7,Row.CREATE_NULL_AS_BLANK);
				if(layerCountCell==null||layerCountCell.getCellType()==Cell.CELL_TYPE_BLANK||layerCountCell.getCellType()!=Cell.CELL_TYPE_NUMERIC){
					nullFlag=i;
					errMsg+="第"+i+"行,第8列 [机柜层数]不能为空或非数字类型！ ";
					
				}else{
					layerCountCell.setCellType(Cell.CELL_TYPE_STRING);
					layerCount=layerCountCell.getStringCellValue();//第8列 层数
				}
				cb.setLayerCount(layerCount);//8
				
				String pos_x=null;
				Cell pxCell = row.getCell(8,Row.CREATE_NULL_AS_BLANK);
				if(pxCell==null||pxCell.getCellType()==Cell.CELL_TYPE_BLANK||pxCell.getCellType()!=Cell.CELL_TYPE_NUMERIC){
					errMsg+="第"+i+"行,第9列 [机柜横向位置]不能为空或非数字类型！ ";
					nullFlag=i;
				}else{
					pxCell.setCellType(Cell.CELL_TYPE_STRING);
					pos_x=pxCell.getStringCellValue();//第9列 横向位置
				}
				cb.setPos_x(pos_x);//9
				
				String pos_y=null;
				Cell pYCell = row.getCell(9,Row.CREATE_NULL_AS_BLANK);
				if(pYCell==null||pYCell.getCellType()==Cell.CELL_TYPE_BLANK||pYCell.getCellType()!=Cell.CELL_TYPE_NUMERIC){
					nullFlag=i;
					errMsg+="第"+i+"行,第10列 [机柜纵向位置]不能为空或非数字类型！ ";
					
				}else{
					pYCell.setCellType(Cell.CELL_TYPE_STRING);
					pos_y=pYCell.getStringCellValue();//第10列 纵向位置
				}
				cb.setPos_y(pos_y);//10
				
				String label=cabinet_numCell.getStringCellValue();//label
				cb.setLabel(label);
				
				cb.setRoomId(roomId);
				cb.setCompany(company);
				cb.setType(type);
				
				cbs.add(cb);
				if(nullFlag==i){//当前行有错，加个换行。
					errMsg+=";\n";
				}
				System.out.println("机柜编号:"+cabinet_num+",机柜名称:"+cabinet_name+",机柜面:"+cabinet_surface+",专业:"+spec_name);
				System.out.println(",固定资产编号:"+assert_no+",A路电源:"+power_a+",B路电源:"+power_b+",层数:"+layerCount+",横向位置:"+pos_x);
				//jiGuiBean.addCabinetList(roomId, type, company, cabinet_num, cabinet_name, cabinet_surface, spec_name, assert_no, power_a, power_b, layerCount, pos_x, pos_y, label);
			}
			System.out.println("错误信息：\n"+errMsg );
			
			if(nullFlag!=-1){
				req.setAttribute("errMsg",errMsg);
				return ERROR;
			}else{
				//JiGuiXinxiGuanLi jiGuiBean=(JiGuiXinxiGuanLi)BizUtil.getBizBean("jigui_xinxi");
				myres=cabinetService.addCabinetList(cbs);
				
			}
		}catch(Exception e){
			return ERROR;
		}	
		if(myres==0){
			req.setAttribute("errMsg","请检查横纵向位置的重复机柜！");
			return ERROR;
		}
		return SUCCESS;
		/*PrintWriter out=ServletActionContext.getResponse().getWriter();
		out.print(myres);
		out.flush();
		out.close();*/
	}
}
