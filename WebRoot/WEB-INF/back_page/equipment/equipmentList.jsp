<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>设备信息</title>
    <jsp:include page="../../../jspRef/MainFrameworkRef.jsp"></jsp:include>
  </head>
  
  <body style="padding:5px;">
  	<div class="easyui-layout" fit="true" border="false">
		    <div region="center" border="false">
					<div class="SearchCondition" id="tbar">
						<!--查找条件：这里到时可以替换 -->
						 	<jsp:include page="../../../jspRef/divSearch.jsp"></jsp:include>
						 <!--查找条件：这里到时可以替换 end -->
							<form id="ff" action="">
										<table class="SearchCondition" >
								    		<tr>
													<td>局站：<input class="easyui-combobox" id="juZhan" name="juZhan" style="width:175px"></input></td>
													<td>机房：<input class="easyui-combobox" id="jiFang" name="jiFang"  style="width:175px"></td>
													<td>机柜：<input class="easyui-combobox" id="cabinetId" name="cabinetId"  style="width:150px"></input></td>
								    		</tr>
								   		 	<tr>
								   		 	 	<td>网元编号：<input id="gridId" placeHolder="请输入网元编号.." name="gridId"  style="width:150px"></input></td>
													<td>设备名称：<input id="equipmentName" placeHolder="请输入设备名称.." name="equipmentName"  style="width:150px"></input></td>
								    		</tr>
												<tr>
													<td colspan="8">
														<div style="text-align:center;padding:5px">
													    	<input type="button" class="button" id="btnSearch" value="查询" onclick="javascript:sbxxQuery();"/>
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
					<table fit="true" style="width:1050px;" id="lstResult" class="easyui-datagrid" title="设备-列表" iconCls="icon-comment_edit" pageSize="20" url="" rownumbers="true" 
						striped="true" remoteSort="true" pagination="true" singleSelect="false" toolbar="#tbar">
					</table>
			
					<div id="cardInfo" class="easyui-window" closed="true" title="板卡信息"  
				 	 	style="width:1100px;padding:5px;">
						<table style="width:1060px;" id="lstCardInfos" class="easyui-datagrid"  pageSize="20" url="" rownumbers="true" 
							striped="true" remoteSort="true"  pagination="true" singleSelect="false" >
						</table>
					</div>
			
			</div>
   </div>
 </body>
  
  <script type="text/javascript">
	function exc(){
		var rows = $('#lstResult').datagrid('getRows');
 		if (!rows || rows.length == 0) {
			$.messager.alert('提示信息','没有可供导出的数据');
			return false; 
		}
		var strUrl = '<%=path%>/jgxx/initJgxxListPage.do?';
		window.location.href=strUrl;
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
            	$('#jiFang').combobox({
            		url:"<%=path%>/jgxx/jifang_list_search.action?regionId="+regionId,
            	    valueField:'ROOM_ID',
            	    textField:'ROOM_NO',
             	    method:'get',
             	    //Onchange
             	    	onChange : function(){//局站添加onChange事件。
             	            var roomId = $("#jiFang").combobox("getValue") ;           
             	            //1.清空原来的机柜combobox中的选项
             	            $("#cabinetId").combobox("clear");     
             	            
             	            //2.动态添加"机柜"的下拉列表框的option                    
             	            if( roomId != null && roomId != '' ){//2
             	            	//alert("rek====="+regionId);
             	            	$('#cabinetId').combobox({
             	            		url:"<%=path%>/sbxx/cabinet_list_search.action?roomId="+roomId,
             	            	    valueField:'CABINET_ID',
             	            	    textField:'CABINET_NAME',
             	             	    method:'get'
             	            	});
             	            }//-2
             			   }//onchange 结束
             	    //onchange end
            	});
            }//-2
		   }//onchange 结束
	    });
	
	function resetJiFang(){
		$('#jiFang').combobox({
		valueField:'ROOM_ID',
	    textField:'ROOM_NO',
	    data:[{ROOM_ID:"",ROOM_NO:"" }]
		});
	}
	function resetCabinet(){
		$('#cabinetId').combobox({
		valueField:'CABINET_ID',
	    textField:'CABINET_NAME',
	    data:[{"CABINET_ID":"","CABINET_NAME":"" }]
		});
	}
	function resert(){//重置
		$("#jiFang").combobox("clear"); 
		$("#cabinetId").combobox("clear"); 
		$("#juZhan").combobox("setValue","");
		$("#jiFang").combobox("setValue","");
		$("#cabinetId").combobox("setValue","");
		$("#gridId").val("");
		$("#equipmentName").val("");
	}
	
	//批量导入进入选 择文件页面
	function inserts(){
		var cabinetId=$("#cabinetId").combobox("getValue");
		
		if(cabinetId==null||cabinetId==""){
			alert("请选择任意一个机柜为其批量添加设备！");
			return ;
		}
		window.open('<%=path%>/jspRef/importEquipmentList.jsp?cabinetId='+cabinetId,'newwindow','resizable=yes,scrollbars=yes,height=650,width=600,top=200,left=200');
	}
	
	
	function sbxxQuery(){
		$('#lstResult').datagrid('loadData', { total: 0, rows: [] });
		
		var juZhan=$("#juZhan").combobox("getValue");
		var jiFang=$("#jiFang").combobox("getValue");
		var cabinetId=$("#cabinetId").combobox("getValue");
		
		var gridId=document.getElementById("gridId").value; 
		var equipmentName=document.getElementById("equipmentName").value;
		
		$('#lstResult').datagrid({
				pageNumber:1,
				pageSize: 20,//每页显示的记录条数，默认为10  
                pageList: [10, 20,50,100, 200],//可以设置每页记录条数的列表 //   ywgl/ziChanGrid_list_seach.do
		        url:'<%=path%>/sbxx/SbxxListQuery.action', 
		         queryParams: {  		
		        		
		        	 juZhan:juZhan, 	
		        	 jiFang:jiFang,
		        	 cabinetId:cabinetId,
		        	 gridId:gridId,
		        	 equipmentName:equipmentName
				  },
		        columns:[[
		        	//ASSETS_NUMBER, ASSETS_DESCRIBE, ASSETS_MANUFACTURER, ADDRESS, OBJ_DEPT_DESCRIBE, DEPT_DESCRIBE
		             //{field:'flag',checkbox:true},
		             //{title:'序号',field:'ID',width:'200',align:'left',hidden:true},
		             {title:'设备ID',field:'EQUIP_ID',width:'60',align:'left',hidden:true}, 
		             {title:'机房名称',field:'ROOM_NO',width:'200',align:'left'},
		             {title:'机柜编号',field:'CABINET_NUM',width:'60',align:'left'},
		             {title:'机柜面',field:'CABINET_SURFACE',width:'100',align:'left'},
		             {title:'网元编号',field:'NU_NUM',width:'200',align:'left'},
		             
		             {title:'设备名称',field:'EQUIP_NAME',width:'250',align:'left'},
		             {title:'设备专业',field:'SPEC_NAME',width:'80',align:'left'},
		             {title:'设备厂商',field:'MANUFACTURER',width:'150',align:'left'},
		             {title:'设备类型',field:'CATEGORY',width:'100',align:'left'},
		             
		             {title:'设备型号',field:'MODEL',width:'100',align:'left'},
		             {title:'子框数量',field:'SUB_RACK_COUNT',width:'100',align:'left'},
		             {title:'板卡信息',field:'UPDATE',width:'150',align:'left',formatter:CardInfo},
		             
				   	]],
		        method:'post'
		    });
	}
	//板卡信息按钮
	function CardInfo(value,rowData,rowIndex){
    	var link="<a href='javascript:showCard(" + rowData.EQUIP_ID + ")'>"+'板卡信息'+"</a>";
    	return link;
    }
	//弹出板卡信息详情
	function showCard(equipId)
    {
		//$("#cardInfo").empty();	
		$('#lstCardInfos').datagrid('loadData', { total: 0, rows: [] });
		$('#lstCardInfos').datagrid({
				pageNumber:1,
				pageSize: 10,//每页显示的记录条数，默认为10  
                pageList: [10,20,50],//可以设置每页记录条数的列表 //   ywgl/ziChanGrid_list_seach.do
		        url:'<%=path%>/sbxx/gotoCardInfo.action', 
		         queryParams: {  		
		        	 equipId:equipId
				  },
		        columns:[[
		             {title:'板卡ID',field:'CARD_ID',width:'60',align:'left',hidden:true}, 
		             {title:'子框ID',field:'SUB_RACK_ID',width:'60',align:'left',hidden:true},
		             {title:'所占槽位数',field:'OCCUPY_SLOT_NUM',width:'100',align:'left'},
		             {title:'板卡厂商',field:'MANUFACTURER',width:'150',align:'left'},
		             {title:'板卡用途',field:'PURPOSE',width:'150',align:'left'},
		             
		             {title:'板卡类型',field:'CATEGORY',width:'250',align:'left'},
		             {title:'板卡型号',field:'MODEL',width:'100',align:'left'},
		             {title:'固定资产编号',field:'ASSET_NO',width:'150',align:'left'},
		             {title:'位置数',field:'POS_IDX',width:'60',align:'left'},
		             {title:'板卡更换日期',field:'CHANGE_DATE',width:'100',align:'left'},
		            
				   	]],
		        method:'post'
		    });
		$('#cardInfo').window({
			modal:true});
		$('#cardInfo').window('open');
		
    }
    
  </script>
</html>
