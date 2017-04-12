<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>结果</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
	<style type="text/css">
		 /*网页首部*/
        .header{
            height:80px;color:white;overflow:hidden;
            /* background:url(img/maintopbg.jpg) */
        } 
        .right-back{/*返回重新登录*/
	        transition: all .3s;
		    font-size: 14px;
		    color: #f01400;
		    position: absolute;
		    right: 10;
		    top: 38px;
        }
        .tips {
			    font-size: 12px;
			    clear: both;
			     height: 20px;
			    line-height: 20px;
		}
		.errorTip{color:#e59700;border:1px solid #e59700;background-color:#fff2f2;background-position:4px 4px;
		width:70%;margin-top:4px;margin-left:-40px;padding:4px 0%;line-height:20px;text-align:center;background-color:#ffc;list-style-type:none;}
		.code{width:72px;height:24px;margin-right:6px;cursor:pointer;border:1px solid #e3e3e3}
	</style>

  </head>
  
 
  <body class="easyui-layout" fit="true" >
    <div data-options="region:'north'" class="header" style="height:150px" border="false">
    
    </div>
    <div data-options="region:'center'" border="false">
		<div class="easyui-layout" data-options="fit:true" >
			<div data-options="region:'west'" style="width:480px" border="false"></div>
			<div data-options="region:'center',title:'${mytitle }', iconCls:'icon-textfield_key'" >
					<div class="link-info" style="margin-left:50px;">
		                        <h3 style="font-size: 12px;  color: #b5b9bc;"></h3>
		                        <a class="right-back" href="<%=path %>/welcome/userLogin.jsp"> 返回立即登录</a>
		             </div>
					<div class="js-forgotpwd-form-wrap" style="margin-left:30px;">
                            <div style="margin-top: 40px;">
                       		<ul class="uls form">
							<li id="errorName" class="errorTip" >${msg }</li>
							</ul>
                            </div>
                            
	                </div>
				<!-- end div1 -->
			</div>
			<div data-options="region:'east'" style="width:480px" border="false"></div>
		</div>
    </div>
    <div region="south" style="text-align: center;height:150px;
		line-height: 40px;overflow:hidden;color:black;" border="false">
	    	版权所有@上海电信机房设备分布管理系统  Copyright 2016-2017
	</div>
</body>
</html>
