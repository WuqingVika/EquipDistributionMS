package com.guanghua.edms.service;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guanghua.edms.util.QRCodeUtil;



public class JiGuiServlet extends HttpServlet {
	public void init() {}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			showQRCode(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 生成二维码图片
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void showQRCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("wq-----------二维码servlet");
		//String path = getServletContext().getRealPath("/") ; 
		//path+="ywgl/images/zgdxlogo.jpg";//path+="/images/zgdxlogo.jpg";
		//String JIGUI_ID = String.valueOf(request.getParameter("id").split("id=")[1]);
		//String JIGUI_NAME = URLDecoder.decode(request.getParameter("name"),"utf-8");
		String JIGUI_ID=URLDecoder.decode(request.getParameter("id"),"utf-8");
		//String CABINET_SURFACE2 = URLDecoder.decode(request.getParameter("cabinet_surface"),"utf-8");
		String CABINET_SURFACE =new String(request.getParameter("cabinet_surface").getBytes("iso-8859-1"), "utf-8").trim();
		//String CABINET_SURFACE3 =request.getParameter("cabinet_surface");
		
		//String[] imgs = CABINET_SURFACE.split(",");
		//System.out.println("1---"+CABINET_SURFACE +"    3----"+CABINET_SURFACE3);
		//for(int i=0;i<imgs.length;i++){
			//System.out.println("img[i]:  -----"+imgs[i]);
		    String path=null;
			String title=CABINET_SURFACE;//imgs[i];
			System.out.println("标题："+title);
			QRCodeUtil.encodeForCabinet(JIGUI_ID, title, path, response.getOutputStream(), true);
		//}
		
		
	}
}
