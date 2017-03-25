<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>专业信息</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
  </head>
</head>
<body style="padding:5px;">
    <div class="easyui-layout" fit="true" border="false">
        <div region="center" border="false">
            <table fit="true" id="dg" class="easyui-datagrid" title="专业-列表" iconCls="icon-comment_edit"
                   toolbar="#tb" idField="specId" pagination="true" 
                   rownumbers="true" fitColumns="true" singleSelect="false">
               
            </table>
            <div id="tb" style="padding:5px">
               <span>专业名称:&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input id="specName" class="easyui-textbox" prompt="请输入专业名称..."
                       style="width:170px;height:26px;">&nbsp;&nbsp;
              	<!-- 
                <a href="#" iconCls="icon-search" class="easyui-linkbutton" onclick="doSearch()">条件查询</a>
                &nbsp;&nbsp;-->
                <a href="#" iconCls="icon-search" onclick="loadWqData()" class="easyui-linkbutton">查询</a>
                &nbsp;&nbsp;<br /> 
                  <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="newSpec()"
            		plain="true">添加</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit"
                onclick="editSpec()" plain="true">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
                  onclick="delSpec()"  iconcls="icon-remove" plain="true">删除</a>
            </div>
            
           	<!-- 专业编辑模态框---editSpec -->
            <div id="myDialog" class="easyui-dialog" style="width: 450px; height: 300px; padding: 10px 20px;"
      				 closed="true" buttons="#dlg-buttons" data-options="modal: true" title="专业--修改"> 
            	<form id="jvForm"  method="post">
            		<input id="specId"  name="specId" hidden="true"/>
            		  	<table cellpadding="5">
            		  		<tr>
				    			<td>专业名称:</td>
				    			<td><input id="specName" class="easyui-textbox" type="text" name="specName" data-options="required:true"></input></td>
				    		</tr>
	    				</table>
	    				<div style="padding:5px;text-align:center;">
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveSpec()" icon="icon-ok">确认修改</a>
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#myDialog').dialog('close')" icon="icon-cancel">取消</a>
       					</div>
				</form>
			</div>
            <!-- 专业编辑模态框---end -->
            
            <!-- 专业添加模态框---addSpec -->
            <!-- <div id="addDialog" class="easyui-dialog" style="width: 450px; height: 300px; padding: 10px 20px;"
      				 closed="true" buttons="#dlg-buttons" data-options="modal: true" title="机房设备专业--添加"> 
            	<form id="jaForm" action="add.do" method="post" enctype="multipart/form-data">
            		  	<table cellpadding="5">
            		  		<tr>
				    			<td>专业名称:</td>
				    			<td><input class="easyui-textbox" type="text" name="specName" data-options="required:true"></input></td>
				    		</tr>
	    				</table>
	    				<div style="padding:5px;text-align:center;">
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addSpec()" icon="icon-ok">确认添加</a>
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#addDialog').dialog('close')" icon="icon-cancel">取消</a>
       					</div>
				</form>
			</div> -->
            <!-- 添加end -->
				
        </div>
        <div region="south" style="height:10px;" border="false">

        </div>
    </div>
</body>
<script type="text/javascript">
/* $(function(){
	var s="";
	loadSearchData(s);
}); */
function loadWqData(){
	var specName=document.getElementById("specName").value; 
	loadSearchData(specName);
}
function loadSearchData(specName){
	$('#dg').datagrid('loadData', { total: 0, rows: [] });
	$("#dg").datagrid({
		pageNumber:1,
		pageSize: 100,
		pageList: [50, 100, 200],
		url:'<%=path%>/spec/SpecListQuery.action',
		 queryParams: {  		
        	 specName:specName
		  },
            columns:[[
            		{field:'ck',width:50,align:'center',checkbox:'true'},
                    {field:'specId',width:60,align:'center',sortable:true,title:'专业ID'},
                    {field:'specName',width:100,align:'center',sortable:true,title:'专业名称'}
                ]],
        method:'post'
        });
}
        //增删改
      var myurl='';
      function newSpec() {//弹出添加窗口
    	//变更url
    	myurl = '<%=path%>/spec/SpecAdd.action';
      	// 重置表单
 		$("#jvForm").form("reset");
        $("#myDialog").dialog("open").dialog('setTitle','添加专业'); 
          //;
         // document.getElementById("hidtype").value="submit";
      }
       
     function editSpec() {//弹出修改窗口
       	var rows=$("#dg").datagrid("getSelections");
    	 //var rows=$('#dg').datagrid('getSelected');
       	var len=rows.length;
       //alert("len:"+len);
       	if(len==0){
       		alert('请选择任一专业进行修改!');
       		return false;
       	}else if(len>1){
       		alert('您选择专业数过多,请选择任一专业进行修改!');
       		return false;
       	}else{
       		var row = rows[0];
            if (row) {
               $("#myDialog").dialog("open").dialog('setTitle','修改专业');
            	 //变更url
            	myurl = '<%=path%>/spec/SpecAdd.action?specId='+ row.specId;
                $("#jvForm").form("load", row);
            }
       	}
       }
      	  function saveSpec() {//保存修改记录
				        	alert("保存成功！");
				        	$("#myDialog").dialog("close");
//				            $("#fm").form("submit", {
//				                url: url,
//				                onsubmit: function () {
//				                    return $(this).form("validate");
//				                },
//				                success: function (result) {
//				                    if (result == "1") {
//				                        $.messager.alert("提示信息", "操作成功");
//				                        $("#dlg").dialog("close");
//				                        $("#dg").datagrid("load");
//				                    }
//				                    else {
//				                        $.messager.alert("提示信息", "操作失败");
//				                    }
//				                }
//				            });
				        }
       function delSpec() {
       	var rows=$("#dg").datagrid("getSelections");
       	var len=rows.length;
       	if(len==0){
       		alert('请选择任一专业进行删除!');
       		return false;
       	}else if(len>1){
       		$.messager.confirm('Confirm', '您确定进行批量删除？', function (r) {
                   if (r) {
				                    	alert('dels success!');
//				                        $.post('destroy_user.php', { id: row.id }, function (result) {
//				                            if (result.success) {
//				                                $('#dg').datagrid('reload');    // reload the user data  
//				                            } else {
//				                                $.messager.show({   // show error message  
//				                                    title: 'Error',
//				                                    msg: result.errorMsg
//				                                });
//				                            }
//				                        }, 'json');
                    }
                });
        	}else{
        		var row = rows[0];
        		if (row) {
                $.messager.confirm('Confirm', '您确定要删除'+row.specName+'？', function (r) {
                    if (r) {
                    	alert('del success!');
//				                        $.post('destroy_user.php', { id: row.id }, function (result) {
//				                            if (result.success) {
//				                                $('#dg').datagrid('reload');    // reload the user data  
//				                            } else {
//				                                $.messager.show({   // show error message  
//				                                    title: 'Error',
//				                                    msg: result.errorMsg
//				                                });
//				                            }
//				                        }, 'json');
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