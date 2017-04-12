<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>局站信息</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
  </head>
<body style="padding:5px;">
    <div class="easyui-layout" fit="true" border="false">
        <div region="center" border="false">
            <table fit="true" id="dg" class="easyui-datagrid" title="局站-列表" iconCls="icon-comment_edit"
                   toolbar="#tb" idField="regionId" pagination="true" 
                   rownumbers="true" fitColumns="true" singleSelect="false">
               
            </table>
            <div id="tb" style="padding:5px">
               <span>局站名称:&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input id="regionName" class="easyui-textbox" prompt="请输入局站名称..."
                       style="width:170px;height:26px;">&nbsp;&nbsp;
                <a href="#" iconCls="icon-search" onclick="loadWqData()" class="easyui-linkbutton">查询</a>
                &nbsp;&nbsp;<br /> 
                  <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="newregion()"
            		plain="true">添加</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit"
                onclick="editregion()" plain="true">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
                  onclick="delregion()"  iconcls="icon-remove" plain="true">删除</a>
            </div>
            
           	<!-- 局站编辑模态框---editregion -->
            <div id="myDialog" class="easyui-dialog" style="width: 450px; height: 300px; padding: 10px 20px;"
      				 closed="true" buttons="#dlg-buttons" data-options="modal: true" title="局站--修改"> 
            	<form id="jvForm"  method="post">
            		<input id="regionId"  name="regionId" hidden="true" />
            		  	<table cellpadding="5">
            		  		<tr>
				    			<td>局站名称:</td>
				    			<td><input id="regionName" class="easyui-textbox" type="text" name="regionName" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>地址:</td>
				    			<td><input id="address" class="easyui-textbox" type="text" name="address" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>使用权限:</td>
				    			<td><input id="propertyRight" class="easyui-textbox" type="text" name="propertyRight" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>楼层:</td>
				    			<td><input id="reFloor" class="easyui-textbox" type="text" name="reFloor" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>用途:</td>
				    			<td><input id="reUsage" class="easyui-textbox" type="text" name="reUsage" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>使用状态:</td>
				    			<td><input id="reState" class="easyui-textbox" type="text" name="reState" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>详细地址:</td>
				    			<td><input id="reAddress" class="easyui-textbox" type="text" name="reAddress" data-options="required:true"></input></td>
				    		</tr>
	    				</table>
	    				<div style="padding:5px;text-align:center;">
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveregion()" icon="icon-ok">保存</a>
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#myDialog').dialog('close')" icon="icon-cancel">取消</a>
       					</div>
				</form>
			</div>
            <!-- 局站编辑模态框---end -->
        </div>
        <div region="south" style="height:10px;" border="false">

        </div>
    </div>
</body>
<script type="text/javascript">
function loadWqData(){
	var regionName=document.getElementById("regionName").value; 
	loadSearchData(regionName);
}
function loadSearchData(regionName){
	$('#dg').datagrid('loadData', { total: 0, rows: [] });
	$("#dg").datagrid({
		pageNumber:1,
		pageSize: 100,
		pageList: [50, 100, 200],
		url:'<%=path%>/myRegion/regionListQuery.action',
		 queryParams: {  		
        	 regionName:regionName
		  },
            columns:[[
            		{field:'ck',width:50,align:'center',checkbox:'true'},
                    {field:'regionId',width:60,align:'center',sortable:true,title:'局站ID',hidden:true},
                    {field:'regionName',width:100,align:'center',sortable:true,title:'局站名称'},
                    {field:'address',width:60,align:'center',sortable:true,title:'地址'},
                    {field:'propertyRight',width:100,align:'center',sortable:true,title:'使用权限'},
                    {field:'reFloor',width:100,align:'center',sortable:true,title:'楼层'},
                    {field:'reUsage',width:60,align:'center',sortable:true,title:'用途'},
                    {field:'reState',width:100,align:'center',sortable:true,title:'使用状态'},
                    {field:'reAddrress',width:100,align:'center',sortable:true,title:'说细地址'}
                ]],
        method:'post'
        });
}
        //增删改
      var myurl='';
      function newregion() {//弹出添加窗口
    	//变更url
    	myurl = '<%=path%>/myRegion/addMyregion.action';
      	// 重置表单
 		$("#jvForm").form("reset");
        $("#myDialog").dialog("open").dialog('setTitle','添加局站'); 
         // document.getElementById("hidtype").value="submit";
         
      }
       
     function editregion() {//弹出修改窗口
       	var rows=$("#dg").datagrid("getSelections");
    	 //var rows=$('#dg').datagrid('getSelected');
       	var len=rows.length;
       //alert("len:"+len);
       	if(len==0){
       		alert('请选择任一局站进行修改!');
       		return false;
       	}else if(len>1){
       		alert('您选择局站数过多,请选择任一局站进行修改!');
       		return false;
       	}else{
       		var row = rows[0];
            if (row) {
               $("#myDialog").dialog("open").dialog('setTitle','修改局站');
            	 //变更url
            	myurl = '<%=path%>/myRegion/editMyregion.action';//
                $("#jvForm").form("load", row);
            }
       	}
       }
  function saveregion() {//保存修改记录
    $("#jvForm").form("submit", {
           url: myurl,
           onsubmit: function () {
               return $(this).form("validate");
           },
           success: function (result) {
              if (JSON.parse(result)[0].msg=="1") {
                   $.messager.alert("提示信息", "操作成功!");
                   $("#myDialog").dialog("close");
                   $("#dg").datagrid("load");
               }
               else {
               	$("#myDialog").dialog("close");
                  	$.messager.alert("提示信息", "操作失败!");
              	} 
          	}
      });
  }
       function delregion() {
       	var rows=$("#dg").datagrid("getSelections");
       	var len=rows.length;
       	var ids='';//要删除的id列表;
       	if(len==0){
       		alert('请选择任一局站进行删除!');
       		return false;
       	}else if(len>1){
       		$.messager.confirm('Confirm', '您确定进行批量删除？', function (r) {
                   if (r) {
                	   for(var x=0;x<len;x++){
                		   ids+=rows[x].regionId+",";
                	   }
                       $.post('<%=path%>/myRegion/delMyregions.action', { ids: ids }, function (result) {
                          if (result[0].msg=="1") {//JSON.parse(result)[0]  result[0].msg
                              $('#dg').datagrid('reload');   
                          } else if(result[0].msg=="2"){
                        		$.messager.alert("提示信息", "本次操作已为您过滤正在使用的局站项,仅删除未使用局站！");
                        		$('#dg').datagrid('reload');
                           }else{
                        		$.messager.alert("提示信息", "操作失败!");
                           }
                      }, 'json');
                    }
                });
        	}else{
        		var row = rows[0];
        		if (row) {
                $.messager.confirm('Confirm', '您确定要删除'+row.regionName+'？', function (r) {
                    if (r) {
                    	ids=row.regionId;
                    	$.post('<%=path%>/myRegion/delMyregions.action', { ids: ids }, function (result) {
                            if (result[0].msg=="1") {
                                $('#dg').datagrid('reload');   
                            } else if(result[0].msg=="2"){
                          		$.messager.alert("提示信息", "本次操作已为您过滤正在使用的局站项,仅删除未使用局站！");
                          		$('#dg').datagrid('reload');
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