<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>开发历程</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
    
</head>
<body style="padding:5px;">
<div class="easyui-layout" fit="true" border="false">
<!-- <iframe id="theframe"
	src="../../../jspRef/intro.html"
	 width="100%" height="100%">
<p>您的浏览器不支持iframe</p> -->
<!-- </iframe> -->
    <embed src="../../../jspRef/wuqingvika.pdf" width="100%" height="100%">
</div>
</body>
</html>