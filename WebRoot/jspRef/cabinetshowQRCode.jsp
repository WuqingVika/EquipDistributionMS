<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  --%>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		%>
<html>
	<head>
   	 <base href="<%=basePath%>">  
   	
	</head>
  <body >
  
  <div id="wq" style="width:1000px">
  	 <%
		
		//String cabinet_surface = request.getParameter("cabinet_surface");
		String cabinet_surface = new String(request.getParameter("cabinet_surface").getBytes("iso-8859-1"), "utf-8").trim();
		String id = request.getParameter("id");
		String[] imgs = cabinet_surface.split(",");
		int k=imgs.length;
		for(int i=0;i<k;i++){
		
     %>
     <div style="width:450px;height:460px;float:left">
     <div style="width:450px;height:430px;float:left">
        
          <img style="height:430px;width:300px" src="<%=request.getContextPath() %>/Cabinet_QRCodeServlet?id=<%=id%>&cabinet_surface=<%=imgs[i]%>" />
      </div>
      <div style="width:450px;height:30px;float:left;padding-left: 150px">
      	<a ><%=imgs[i] %></a>
      </div>
     </div>
     
   	  <%} %>  
  </div>
  </body>
</html>