<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>管理员信息</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
  </head>
</head>
<body style="padding:5px;">
    <div class="easyui-layout" fit="true" border="false">
        <div region="center" border="false">
            <table fit="true" id="dg" class="easyui-datagrid" title="管理员-列表" iconCls="icon-comment_edit"
                   toolbar="#tb" idField="userId" pagination="true" 
                   rownumbers="true" fitColumns="true" singleSelect="false">
               
            </table>
            <div id="tb" style="padding:5px">
               <span>管理员:&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input id="userName" class="easyui-textbox" prompt="请输入姓名..."
                       style="width:170px;height:26px;">&nbsp;&nbsp;
                <a href="#" iconCls="icon-search" onclick="loadWqData()" class="easyui-linkbutton">查询</a>
                &nbsp;&nbsp;<br /> 
                  <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="newUser()"
            		plain="true">添加</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit"
                onclick="editUser()" plain="true">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
                  onclick="delUser()"  iconcls="icon-remove" plain="true">删除</a>
            </div>
            
           	<!-- 管理员编辑模态框---editUser -->
            <div id="myDialog" class="easyui-dialog" style="width: 450px; height: 300px; padding: 10px 20px;"
      				 closed="true" buttons="#dlg-buttons" data-options="modal: true" title="管理员--修改"> 
            	<form id="jvForm"  method="post">
            		<input  name="userId" hidden="true" />
            		  	<table cellpadding="5">
            		  		<tr>
				    			<td>管理员简称:</td>
				    			<td><input id="usernamewq" class="easyui-textbox"  name="userName" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>姓名:</td>
				    			<td><input  class="easyui-textbox" type="text" name="workNo" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>权限:</td>
				    			<td>
					    			<select  class="easyui-combobox"  name="sp" >
					    				<option value="0">普通管理员</option>
										<option value="1">超级管理员</option>
					    			</select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>邮箱:</td>
				    			<td><input  class="easyui-textbox" type="text" name="email" data-options="required:true"></input></td>
				    		</tr>
				    		
	    				</table>
	    				<div style="padding:5px;text-align:center;">
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveUser()" icon="icon-ok">保存</a>
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#myDialog').dialog('close')" icon="icon-cancel">取消</a>
       					</div>
				</form>
			</div>
            <!-- 管理员编辑模态框---end -->
        </div>
        <div region="south" style="height:10px;" border="false">
        </div>
    </div>
</body>
<script type="text/javascript">
function loadWqData(){
	var userName=document.getElementById("userName").value; 
	loadSearchData(userName);
	$('#dg').datagrid('clearSelections');
}

	
function loadSearchData(userName){
	$('#dg').datagrid('loadData', { total: 0, rows: [] });
	$("#dg").datagrid({
		pageNumber:1,
		pageSize: 100,
		pageList: [50, 100, 200],
		url:'<%=path%>/user/userListQuery.action',
		 queryParams: {  		
			 userName:userName
		  },
            columns:[[
            		{field:'ck',width:50,align:'center',checkbox:'true'},
                    {field:'userId',width:60,align:'center',sortable:true,title:'管理员ID'},
                    {field:'userName',width:100,align:'center',sortable:true,title:'管理员名称'},
                    {field:'workNo',width:100,align:'center',sortable:true,title:'工号'},
                    {field:'email',width:100,align:'center',sortable:true,title:'邮箱'},
                    {field:'sp',width:100,align:'center',sortable:true,title:'权限'  
                    	,formatter:function(value,row,index){
				    		if(value=="0"){
				    			return "普通";
				    		}else{
				    			return "超级管理员";
				    		}
				    	}  
                    }
                ]],
        method:'post'
        });
}
        //增删改
      var myurl='';
      var op=0;//看本次操作类型
      function newUser() {//弹出添加窗口
    	//变更url
    	myurl = '<%=path%>/user/addMyUser.action';
    	 $("#usernamewq").attr("readonly",false);
    	op=1;//添加
      	// 重置表单
 		$("#jvForm").form("reset");
        $("#myDialog").dialog("open").dialog('setTitle','添加管理员'); 
      }
       
     function editUser() {//弹出修改窗口
       	var rows=$("#dg").datagrid("getSelections");
	$('#usernamewq').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
     	op=2;
    	 //var rows=$('#dg').datagrid('getSelected');
       	var len=rows.length;
       //alert("len:"+len);
       	if(len==0){
       		alert('请选择任一管理员进行修改!');
       		return false;
       	}else if(len>1){
       		alert('您选择管理员数过多,请选择任一管理员进行修改!');
       		return false;
       	}else{
       		var row = rows[0];
            if (row) {
               $("#myDialog").dialog("open").dialog('setTitle','修改管理员');
            	 //变更url
            	myurl = '<%=path%>/user/editMyUser.action';
                $("#jvForm").form("load", row);
            }
       	}
       }
  function saveUser() {//保存修改记录
    $("#jvForm").form("submit", {
           url: myurl,
           onsubmit: function () {
               return $(this).form("validate");
           },
           success: function (result) {
              if (JSON.parse(result)[0].msg=="1") {
            	  if(op==1){
            		  //代表添加
            		  $.messager.alert("提示信息", "添加成功[注:新用户初始密码为:123456,请及时通知修改]!");
            	  }else{
            		  $.messager.alert("提示信息", "修改成功!");
            	  }
                   $("#myDialog").dialog("close");
                   refresh();
               }else if (JSON.parse(result)[0].msg=="4") {
            	   //代表重复
         		  $.messager.alert("提示信息", "该账号已经存在!");
               }
               else {
               	$("#myDialog").dialog("close");
                  	$.messager.alert("提示信息", "操作失败!");
              	} 
          	}
      });
  }
       function delUser() {
       	var rows=$("#dg").datagrid("getSelections");
       	var len=rows.length;
       	var ids='';//要删除的id列表;
       	if(len==0){
       		alert('请选择任一管理员进行删除!');
       		return false;
       	}else if(len>1){
       		$.messager.confirm('Confirm', '您确定进行批量删除？', function (r) {
                   if (r) {
                	   for(var x=0;x<len;x++){
                		   ids+=rows[x].userId+",";
                	   }
                       $.post('<%=path%>/user/delMyUsers.action', { ids: ids }, function (result) {
                          if (result[0].msg=="1") {//JSON.parse(result)[0]  result[0].msg
                        	  refresh();
                          } else if(result[0].msg=="2"){
                        		$.messager.alert("提示信息", "本次操作已为您过滤正在使用的管理员项,仅删除未使用管理员！");
                        		refresh();
                           }else{
                        		$.messager.alert("提示信息", "操作失败!");
                           }
                      }, 'json');
                    }
                });
        	}else{
        		var row = rows[0];
        		if (row) {
                $.messager.confirm('Confirm', '您确定要删除'+row.userName+'？', function (r) {
                    if (r) {
                    	ids=row.userId;
                    	$.post('<%=path%>/user/delMyUsers.action', { ids: ids }, function (result) {
                            if (result[0].msg=="1") {
                            	refresh();  
                            } else if(result[0].msg=="2"){
                          		$.messager.alert("提示信息", "本次操作已为您删除其他管理员，不能删除自己！");
                          		refresh();
                             }else{
                          		$.messager.alert("提示信息", "操作失败");
                             }
                        }, 'json');
                    }
                });
           	 }
        	}//else end
        }  
         //end 增删改
        
     $.extend($.fn.validatebox.defaults.rules, {
	    INTPlus:{
	    	validator:function(value){
	    		return /^[0-9]*[1-9][0-9]*$/.test(value);
	    	},
	    	message:"请输入正整数!"
	    }
	});
       // 刷新
  	 function refresh(){
  		 $("#dg").datagrid("reload");
  	 }

   </script>
</html>