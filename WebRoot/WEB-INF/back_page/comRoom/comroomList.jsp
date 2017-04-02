<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>机房信息</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
  </head>
</head>
<body style="padding:5px;">
    <div class="easyui-layout" fit="true" border="false">
        <div region="center" border="false">
            <table fit="true" id="dg" class="easyui-datagrid" title="机房-列表" iconCls="icon-comment_edit"
                   toolbar="#tb" idField="roomId" pagination="true" 
                   rownumbers="true" fitColumns="true" singleSelect="false">
               
            </table>
            <div id="tb" style="padding:5px">
               <span>局站名称:&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input class="easyui-combobox" id="regionNo" name="regionNo" style="width:170px"></input>
                &nbsp;&nbsp;<span>机房名称:&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input id="roomName" name="roomName" class="easyui-textbox" prompt="请输入机房名称..."
                       style="width:170px;height:26px;">
                <a href="#" iconCls="icon-search" onclick="loadWqData()" class="easyui-linkbutton">查询</a>
                &nbsp;&nbsp;<br /> 
                  <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="newroom()"
            		plain="true">添加</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit"
                onclick="editroom()" plain="true">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
                  onclick="delroom()"  iconcls="icon-remove" plain="true">删除</a>
            </div>
            
           	<!-- 机房编辑模态框---editregion -->
            <div id="myDialog" class="easyui-dialog" style="width: 450px; height: 400px; padding: 10px 20px;"
      				 closed="true" buttons="#dlg-buttons" data-options="modal: true" title="机房--修改"> 
            	<form id="jvForm"  method="post">
            		<input id="roomId"  name="roomId" hidden="true" />
            		  	<table cellpadding="5">
            		  		<tr>
				    			<td>机房名称:</td>
				    			<td><input id="roomNo" class="easyui-textbox" type="text" name="roomNo" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>局站名称:</td>
				    			<td><input id="regionId" class="easyui-combobox"  name="regionId" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>所在楼层:</td>
				    			<td><input id="roFloor" class="easyui-textbox" type="text" name="roFloor" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>用途:</td>
				    			<td><input id="roUsage" class="easyui-textbox" type="text" name="roUsage" data-options="required:true"></input></td>
				    		</tr>
				    			<tr>
				    			<td>使用权限:</td>
				    			<td><input id="propertyRight" class="easyui-textbox" type="text" name="propertyRight" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>使用状态:</td>
				    			<td><input id="roState" class="easyui-textbox" type="text" name="roState" data-options="required:true"></input></td>
				    		</tr>
				    		
	    				</table>
	    				<div style="padding:5px;text-align:center;">
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveroom()" icon="icon-ok">保存</a>
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#myDialog').dialog('close')" icon="icon-cancel">取消</a>
       					</div>
				</form>
			</div>
            <!-- 机房编辑模态框---end -->
        </div>
        <div region="south" style="height:10px;" border="false">

        </div>
    </div>
</body>
<script type="text/javascript">
function loadWqData(){
	var roomName=document.getElementById("roomName").value; 
	var regionNo=$("#regionNo").combobox("getValue");
	loadSearchData(roomName,regionNo);
}

$('#regionNo').combobox({
	url:'<%=path%>/jgxx/juzhan_list_search.action',
    valueField:'REGION_ID',
    textField:'REGION_NAME',
    method:'get'
});

function loadSearchData(roomName,regionNo){
	$('#dg').datagrid('loadData', { total: 0, rows: [] });
	$("#dg").datagrid({
		pageNumber:1,
		pageSize: 100,
		pageList: [50, 100, 200],
		url:'<%=path%>/myRoom/roomListQuery.action',
		 queryParams: {  		
			 roomName:roomName,
			 regionNo:regionNo
		  },
            columns:[[
            		{field:'ck',width:50,align:'center',checkbox:'true'},
                    {field:'roomId',width:60,align:'center',sortable:true,title:'机房ID',hidden:true},
                    {field:'regionName',width:100,align:'center',sortable:true,title:'局站名称'},
                    {field:'roomNo',width:60,align:'center',sortable:true,title:'机房名称'},
                    {field:'roFloor',width:100,align:'center',sortable:true,title:'所在楼层'},
                    {field:'roUsage',width:60,align:'center',sortable:true,title:'用途'},
                    {field:'propertyRight',width:100,align:'center',sortable:true,title:'使用权限'},
                    {field:'roState',width:100,align:'center',sortable:true,title:'使用状态'}
                ]],
        method:'post'
        });
}
        //增删改
      var myurl='';
      function newroom() {//弹出添加窗口
    	//变更url
    	myurl = '<%=path%>/myRoom/addMyroom.action';
      	// 重置表单
 		$("#jvForm").form("reset");
 		$('#regionId').combobox({
 			url:'<%=path%>/jgxx/juzhan_list_search.action',
 		    valueField:'REGION_ID',
 		    textField:'REGION_NAME',
 		    method:'get'
 		});
        $("#myDialog").dialog("open").dialog('setTitle','添加机房'); 
         // document.getElementById("hidtype").value="submit";
      }
       
     function editroom() {//弹出修改窗口
       	var rows=$("#dg").datagrid("getSelections");
    	 //var rows=$('#dg').datagrid('getSelected');
       	var len=rows.length;
       //alert("len:"+len);
       	if(len==0){
       		alert('请选择任一机房进行修改!');
       		return false;
       	}else if(len>1){
       		alert('您选择机房数过多,请选择任一机房进行修改!');
       		return false;
       	}else{
       		var row = rows[0];
            if (row) {
               $("#myDialog").dialog("open").dialog('setTitle','修改机房');
            	 //变更url
            	myurl = '<%=path%>/myRoom/editMyroom.action';
            	//加载局站
	            	$.ajax({
		   			dataType: 'json',
		   			url:'<%=path%>/jgxx/juzhan_list_search.action',
		   			success:function(aa){
		   				//console.info(aa);
		   				//aa = JSON.parse(aa);
		   				$('#regionId').combobox({
		   					data:aa,
		   				    valueField:'REGION_ID',
		   				    textField:'REGION_NAME'
		   				});
		                     for(var k=0;k<aa.length;k++){
		                   	  if(aa[k].REGION_NAME==row.regionName){
		                   		  $('#regionId').combobox('setValue', aa[k].REGION_ID);
		                   	  }
		                     }
		   				 
		   			}
	   				}); //ajax end
            	//加载局站end
                $("#jvForm").form("load", row);
            }
       	}
       }
  function saveroom() {//保存修改记录
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
       function delroom() {
       	var rows=$("#dg").datagrid("getSelections");
       	var len=rows.length;
       	var ids='';//要删除的id列表;
       	if(len==0){
       		alert('请选择任一机房进行删除!');
       		return false;
       	}else if(len>1){
       		$.messager.confirm('Confirm', '您确定进行批量删除？', function (r) {
                   if (r) {
                	   for(var x=0;x<len;x++){
                		   ids+=rows[x].roomId+",";
                	   }
                       $.post('<%=path%>/myRoom/delMyrooms.action', { ids: ids }, function (result) {
                          if (result[0].msg=="1") {//JSON.parse(result)[0]  result[0].msg
                              $('#dg').datagrid('reload');   
                          } else if(result[0].msg=="2"){
                        		$.messager.alert("提示信息", "本次操作已为您过滤正在使用的机房项,仅删除未使用机房！");
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
                $.messager.confirm('Confirm', '您确定要删除'+row.roomNo+'？', function (r) {
                    if (r) {
                    	ids=row.roomId;
                    	$.post('<%=path%>/myRoom/delMyrooms.action', { ids: ids }, function (result) {
                            if (result[0].msg=="1") {
                                $('#dg').datagrid('reload');   
                            } else if(result[0].msg=="2"){
                          		$.messager.alert("提示信息", "本次操作已为您过滤正在使用的机房项,仅删除未使用机房！");
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