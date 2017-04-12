<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改密码</title>
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
	<script type="text/javascript">
			 function formSub() {
				 var userName=$("#userName").val().trim();
				 var password=$("#password").val().trim();
				 var passwordAgain=$("#passwordAgain").val().trim();
				 var passwordOld=$("#passwordOld").val().trim();
				 if(password==""||passwordAgain==""||passwordOld==""){
					 $.messager.alert("提示信息", "密码输入框不得为空！");
					 return false;
				 }
				 if(password!=passwordAgain){
					 $.messager.alert("提示信息", "两次密码不一致！");
					 return false;
				 }
				//判断新密码是否和旧密码一致
				 $.post('<%=path%>/user/checkPwdOld.action', { userName: userName,passwordOld:passwordOld }, function (result) {
                  if (result[0].msg=="0") {
                 	 $.messager.alert("提示信息", "旧密码输入错误！");
                		return false;
                   }else if(result[0].msg=="1"){
                	   $.messager.confirm('修改密码确认', '您确定要修改密码？', function (r) {
                           if (r) {
                           	//$("#pwdform").submit(); 
			                  $.post('<%=path%>/user/updateCommonPwd.action', { userName: userName,password:password}, function (myflag) {
			                  if (myflag[0].msg=="0") {
			                 	 $.messager.alert("提示信息", "修改失败,请稍后重试！");
			                		return false;
			                   }else if(myflag[0].msg=="1"){
			                	   top.location.href='<%=path%>/welcome/userLogin.jsp';
			                   }else{
			                	   $.messager.alert("提示信息", "系统错误,请稍后重试！");
			               			return false;
			                   }
			              }, 'json'); 
                           	/**/
                           }
                       });
                   }else{
                	   $.messager.alert("提示信息", "系统错误,请稍后重试！");
               			return false;
                   }
              }, 'json');
			 
			}
			
			
	</script>

  </head>
  
 
  <body class="easyui-layout" fit="true" >
    <div data-options="region:'north'" class="header" style="height:10px" border="false">
    
    </div>
    <div data-options="region:'center'" border="false">
		<div class="easyui-layout" data-options="fit:true" >
			<div data-options="region:'west'" style="width:0px" border="false"></div>
			<div data-options="region:'center',title:'修改密码', iconCls:'icon-textfield_key'" >
					<div class="link-info" style="margin-left:50px;">
		                        <h3 style="font-size: 12px;  color: #b5b9bc;"></h3>
		                      
		             </div>
					<div class="js-forgotpwd-form-wrap" style="margin-left:30px;">
					 <c:if test="${!empty user}">
	                    <form id="pwdform"   method="post">
	                        
	                        <div>
                               <input class="easyui-textbox" id="userName" name="userName" value="${user.userName }"
                                      data-options="iconCls:'icon-man'" readonly="true"
                                      style="width:240px;height:30px;"/>
                          	 </div>
                           <div style="margin-top: 20px;">
                                <input class="easyui-textbox" type="password" id="passwordOld" 
                                       data-options="iconCls:'icon-lock',prompt:'请输入旧密码'"
                                       style="width:240px;height:30px;"/>
                            </div>
                            <div style="margin-top: 20px;">
                                <input class="easyui-textbox" type="password" id="password" name="password"
                                       data-options="iconCls:'icon-lock',prompt:'请输入新密码'"
                                       style="width:240px;height:30px;"/>
                            </div>
                             <div style="margin-top: 20px;">
                                  <input class="easyui-textbox" type="password" id="passwordAgain" 
                                         data-options="iconCls:'icon-lock',prompt:'再次确认密码'"
                                         style="width:240px;height:30px;"/>
                             </div>
                            <div style="margin-top: 20px;">
                                <p>
                                    <a href="javascript:void(0)" onclick="formSub()" style="width:240px;height:40px;" class="easyui-linkbutton" iconCls="icon-accept">修改</a>
                                </p>
                            </div>
	                    </form>
	                    </c:if>
	                    <c:if test="${!empty error}">
	                    <div style="margin-top: 10px;">
                       		<ul class="uls form">
							<li id="errorName" class="errorTip" >${error}</li>
							</ul>
                         </div>
	                    </c:if>
	                </div>
				<!-- end div1 -->
			</div>
			<div data-options="region:'east'" style="width:10px" border="false"></div>
		</div>
    </div>
    
</body>
</html>
