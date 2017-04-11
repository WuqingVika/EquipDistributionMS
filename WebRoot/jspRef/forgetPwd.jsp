<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>忘记密码</title>
    <jsp:include page="../jspRef/MainFrameworkRef.jsp"></jsp:include>
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
	<script type="text/javascript">
		 var  email="";
		 function formSub() {
			 var userName=$("#userName").val();
			 var captcha=$("#captcha").val();
			 if(userName==""||userName.trim()==""){
				 $.messager.alert("提示信息", "登录账号不能为空！");
				 return false;
			 }
			 if(captcha==""||captcha.trim()==""){
				 $.messager.alert("提示信息", "验证码不能为空！");
				 return false;
			 }
			 //判断验证码是否正确
				 $.post('<%=path%>/user/verifyCaptcha.action', { captcha: captcha }, function (flag) {
                     if (flag[0].msg=="0") {
                    	 $.messager.alert("提示信息", "验证码错误！");
                   		return false;
                      }else if(flag[0].msg=="1"){
                   		//获得该用户的邮箱
						 $.post('<%=path%>/user/getEmail.action', { userName: userName }, function (result) {
		                     if (result[0].msg=="-1") {
		                    	 $.messager.alert("提示信息", "请输入正确的登录账号！");
		                   		return false;
		                      }else if(result[0].msg=="0"||result[0].msg==""){
		                   		$.messager.alert("提示信息", "输入账号还没绑定邮箱，请及时联系管理员添加邮箱！");
		                   		return false;
		                      }else{
		                    	  email=result[0].msg;
		                    	  var myemail=email.substring(0,2);
		                    	  myemail+='****';
		                    	  myemail+=email.substring(email.length-5,email.length);
		                    	  //存在邮箱
		                    	  $.messager.confirm('发送邮件确认', '您确定要向邮箱['+myemail+']发送激活链接？', function (r) {
		      	                    if (r) {
		      	                    	$("#forgetform").submit();
		      	                    }
		      	                });
		                      }
		                 }, 'json');
                      }else{
                    	  $.messager.alert("提示信息", "系统错误,请稍后再试！");
                     	  return false;
                      }
                 }, 'json');
			 //验证结束
				 
			} 
			
			function changeCode(){
				$('.code').attr("src","<%=path %>/getSecurityCode.action?timestap="+new Date().getTime());
			}
			
	</script>

  </head>
  
 
  <body class="easyui-layout" fit="true" >
    <div data-options="region:'north'" class="header" style="height:150px" border="false">
    
    </div>
    <div data-options="region:'center'" border="false">
		<div class="easyui-layout" data-options="fit:true" >
			<div data-options="region:'west'" style="width:480px" border="false"></div>
			<div data-options="region:'center',title:'忘记密码', iconCls:'icon-textfield_key'" >
					<div class="link-info" style="margin-left:50px;">
		                        <h3 style="font-size: 12px;  color: #b5b9bc;">通过登录账号关联的邮箱重设密码</h3>
		                        <a class="right-back" href="<%=path %>/welcome/userLogin.jsp"> 返回立即登录</a>
		             </div>
					<div class="js-forgotpwd-form-wrap" style="margin-left:30px;">
	                    <form id="forgetform" action="<%=path %>/user/sendMail.action"  method="post">
	                        <div>
                               <input class="easyui-textbox" id="userName" name="userName" required="true" 
                                      data-options="iconCls:'icon-man',prompt:'请输入账号'"
                                      style="width:240px;height:30px;"/>
                          	 </div>
                           <div style="margin-top: 10px;">
                               <input class="easyui-textbox" type="text" id="captcha" name="captcha" required="true" 
                                      data-options="iconCls:'icon-application_lightning',prompt:'请输入难证码'"
                                      style="width:240px;height:30px;"/>
                                <img src="<%=path %>/getSecurityCode.action" onclick="changeCode()" 
	                                class="code" alt="换一张" />
	                                <a href="javascript:void(0)" onclick="changeCode()" class="easyui-linkbutton" iconCls="icon-reload" title="换一张"></a>
                           </div>
                           <!-- <div style="margin-top: 10px;">
                       		<ul class="uls form">
							<li id="errorName" class="errorTip" >嘿嘿</li>
							</ul>
                            </div> -->
                            <div style="margin-top: 20px;">
                                <p>
                                    <a href="javascript:void(0)" onclick="formSub()" style="width:240px;height:40px;" class="easyui-linkbutton" iconCls="icon-email_link">发送邮箱激活链接</a>
                                </p>
                            </div>
	                    </form>
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
