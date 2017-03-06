<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>机柜信息</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
    </head> 
  <body style="padding:5px;">
  	<div class="easyui-layout" fit="true" border="false">
        <div region="center" border="false">
					<div id="tbar" class="SearchCondition">
						<!--查找条件：这里到时可以替换 -->
						<jsp:include page="../../../jspRef/divSearch.jsp"></jsp:include>
						 <!--查找条件：这里到时可以替换 end -->
							<form id="ff" action="">
								<table class="SearchCondition"  >
						    	<tr>
										<td>局站：<input class="easyui-combobox" id="juZhan" name="juZhan" style="width:150px"></input></td>
										<td>机房：<input class="easyui-combobox" id="jiFang" name="jiFang"  style="width:150px"></td>
										<td>专业：<input class="easyui-combobox" id="zhuanYe" name="zhuanYe"  style="width:150px"></input></td>
										<td>机柜编号/机柜名称：<input id="bianOrMc" placeHolder="请输入编号或名称.." name="bianOrMc"  style="width:150px"></input></td>
						    	</tr>
									<tr>
										<td colspan="8">
											<div style="text-align:center;padding:5px">
										    	<input type="button" class="button" id="btnSearch" value="查询" onclick="javascript:jgxxQuery();"/>
										    	<input type="button" class="button" id="btnClearForm" value="重置" onclick="javascript:resert();"/>
							    				<input type="button" class="button" id="btnClearForm" value="批量导入" onclick="javascript:inserts();"/>
							    		</div>
										</td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
						    	</table>
				      </form>
					</div>
					<table fit="true" title="机柜-列表" iconCls="icon-comment_edit" id="lstResult" class="easyui-datagrid" title="" pageSize="20"  rownumbers="true" 
						 remoteSort="true" fitColumns="true" toolbar="#tbar"  pagination="true"  title="机柜信息" singleSelect="false" >
					</table>
			</div>
    </div>
    <!-- 导入文件对话框 -->
            <div id="addDialog" class="easyui-dialog" style="width: 450px; height: 300px; padding: 10px 20px;"
      				 closed="true" buttons="#dlg-buttons" data-options="modal: true" title="机柜--导入"> 
            	 <form id="uploadFileForm" action="<%=path%>/jgxx/cabinetinfo_import.do?roomId=" enctype="multipart/form-data" class="form-horizontal" method="post" >
						<!-- <input type="hidden" id="myRoomId"/> -->
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">选择要上传的文件</label>
							<div class="col-sm-10">
								<input id="uploadFile" name="uploadFile" id="uploadFile" type="file" class="form-control">
							</div>
						</div>
						<div class="form-group">
						  <a href="javascript:void(0);" onclick="exc();">模板导出</a>
						</div>
	    				<div style="padding:5px;text-align:center;">
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:subimtBtn();" icon="icon-ok">确认导入</a>
				            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#addDialog').dialog('close')" icon="icon-cancel">取消</a>
       					</div>
				</form>
			</div>
    <!-- 导入文件对话框 结束！ -->
    
  </body>
  
 <script type="text/javascript">
	function exc(){
		<%-- var rows = $('#lstResult').datagrid('getRows');
 		if (!rows || rows.length == 0) {
			$.messager.alert('提示信息','没有可供导出的数据');
			return false; 
		}
		var strUrl = '<%=path%>/jgxx/initJgxxListPage.do?';
		window.location.href=strUrl; --%>
		var strUrl = '<%=path%>/jgxx/doExport.action?filename='+encodeURI(encodeURI("机柜模板"));
		window.location.href=strUrl;
	}
    
	function subimtBtn() {  
		//判断是否选择了文件
		if($("#uploadFile").val() == ""){
			alert("请选择文件!");
		}else{
			//alert("daoru");
			var filename=$("#uploadFile").val();
			var xlss = filename.toLowerCase().substr(filename.lastIndexOf(".")); 
			if(xlss!=".xls"&&xlss!=".xlsx"){
				alert("请选择以.xls和.xlsx结尾的表格文件导入！");
				return;
			}else{
				//alert(filename+"   "+xlss);
				var myRoomId=$("#jiFang").combobox("getValue");
				$("#uploadFileForm").attr("action","<%=path%>/jgxx/cabinetinfo_import.action?roomId="+myRoomId);
				$("#uploadFileForm").submit();
						
			}
			
		}
    }
	$('#juZhan').combobox({
		url:'<%=path%>/jgxx/juzhan_list_search.action',
	    valueField:'REGION_ID',
	    textField:'REGION_NAME',
	    method:'get',
	    
	    onChange : function(){//局站添加onChange事件。
            var regionId = $("#juZhan").combobox("getValue") ;           
            //1.清空原来的机房combobox中的选项
            $("#jiFang").combobox("clear");     
            
            //2.动态添加"机房"的下拉列表框的option                    
            if( regionId != null && regionId != '' ){//2
            	//alert("rek====="+regionId);
            	$('#jiFang').combobox({
            		url:"<%=path%>/jgxx/jifang_list_search.action?regionId="+regionId,
            	    valueField:'ROOM_ID',
            	    textField:'ROOM_NO',
             	    method:'get'
            	});
            }//-2
		   }
	    });
	
	$('#zhuanYe').combobox({
		url:'<%=path%>/jgxx/zhuanye_list_search.action',
	    valueField:'SPEC_ID',
	    textField:'SPEC_NAME',
	   // value:'--请选择机柜专业--',
	    method:'get'
	});
	
	function resert(){
			
		$("#jiFang").combobox("clear");    
		$("#juZhan").combobox("setValue","");
		$("#jiFang").combobox("setValue","");
		$("#zhuanYe").combobox("setValue","");
		$("#bianOrMc").val("");
	}
	
	//批量导入进入选 择文件页面
	function inserts(){
		var juZhan=$("#juZhan").combobox("getValue");
		var roomId=$("#jiFang").combobox("getValue");
		if(juZhan==null||juZhan==""){
			alert("请选择一个局站和任意一个机房");
			return ;
		}
		if(roomId==null||roomId==""){
			alert("请选择任意一个机房为其批量添加机柜");
			return ;
		}
		$("#addDialog").dialog("open") ;
		//window.open('<%=path%>/WEB-INF/back_page/cproom/exportCabinetList.jsp?roomId='+roomId,'newwindow','resizable=yes,scrollbars=yes,height=650,width=600,top=200,left=200');
	}
	
	
	function jgxxQuery(){
		$('#lstResult').datagrid('loadData', { total: 0, rows: [] });
		
		var juZhan=$("#juZhan").combobox("getValue");
		var jiFang=$("#jiFang").combobox("getValue");
		var zhuanYe=$("#zhuanYe").combobox("getValue");
		var bianOrMc=document.getElementById("bianOrMc").value; 
		//alert(jiFang);
		//alert("juzhan "+juZhan+" jifang:"+jiFang+" zhuanye:"+zhuanYe+" bianormc:"+bianOrMc);
		//return false;//====test!!
		$('#lstResult').datagrid({
				pageNumber:1,
				pageSize: 100,//每页显示的记录条数，默认为10  
                pageList: [50, 100, 200],//可以设置每页记录条数的列表 //   ywgl/ziChanGrid_list_seach.do
		        url:'<%=path%>/jgxx/JgxxListQuery.action', 
		         queryParams: {  		
		        	 juZhan:juZhan, 	
		        	 jiFang:jiFang,
		        	 zhuanYe:zhuanYe,
		        	 bianOrMc:bianOrMc
				  },
		        columns:[[
		             {title:'机柜ID',field:'ID',width:'200',align:'left',hidden:true}, 
		             {title:'机房名称',field:'ROOM_NO',width:'200',align:'left'},
		             {title:'机柜编号',field:'CABINET_NUM',width:'200',align:'left'},
		             {title:'机柜名称',field:'CABINET_NAME',width:'250',align:'left'},
		             {title:'机柜面',field:'CABINET_SURFACE',width:'200',align:'left'},
		             {title:'机柜二维码',field:'UPDATE',width:'200',align:'left',formatter:formatQrcode},
		             
				   	]],
		        method:'post'
		    });
	}
	//生成二维码
	function formatQrcode(value,rowData,rowIndex){
    	var link="<a href='javascript:doQrcode(\"" + rowData.CABINET_SURFACE + "\","+rowData.ID+")'>"+'生成二维码'+"</a>";
    	return link;
    }
	//
	function doQrcode(cabinet_surface,id)
    {
		window.open("<%=path%>/jspRef/cabinetshowQRCode.jsp?cabinet_surface="+cabinet_surface+"&id="+id);
    }
    
  </script>
</html>
