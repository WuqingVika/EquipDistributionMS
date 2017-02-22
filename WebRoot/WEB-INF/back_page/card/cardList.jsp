<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ page language="java" import="com.guanghua.ywgl.util.SessionUtil"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>板卡信息</title>
    
    <h:header/>	
    <script language="javascript" src="/ywgl/ywgl/js/locale/easyui-lang-zh_CN.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  
    <wt:permission function="10051"/>
    <div class="selZiYuanDiv">
 	<wt:searchConditionTitle/>
	<div class="SearchCondition">
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
				    	<input type="button" class="button" id="btnSearch" value="查询" onclick="javascript:bkxxQuery();"/>
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
		<table style="width:1050px;" id="lstResult" class="easyui-datagrid" title="" pageSize="20" url="" rownumbers="true" 
			striped="true" remoteSort="true" pagination="true" singleSelect="false" toolbar="#tb">
		</table>
	</div>
	<!-- 
	<div id="cardInfo" class="easyui-window" closed="true" title="板卡信息"  
 	 	style="width:1100px;height:600px;padding:5px;">
		<table style="width:1020px;" id="lstCardInfos" class="easyui-datagrid" title="" pageSize="20" url="" rownumbers="true" 
			striped="true" remoteSort="true" pagination="true" singleSelect="false" toolbar="#tb">
		</table>
	</div> -->
	
	
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
		url:'<%=path%>/jgxx/juzhan_list_search.do',
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
            		url:"<%=path%>/jgxx/jifang_list_search.do?regionId="+regionId,
            		
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
             	            		url:"<%=path%>/sbxx/cabinet_list_search.do?roomId="+roomId,
             	            		
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
	
	<%-- $('#zhuanYe').combobox({
		url:'<%=path%>/jgxx/zhuanye_list_search.do',
	    valueField:'SPEC_ID',
	    textField:'SPEC_NAME',
	   // value:'--请选择机柜专业--',
	    method:'get'
	}); --%>
	
	var a="";
	function batchUpdateGrid(){
	 	a="";
		var datas=$('#lstResult').datagrid('getSelections');
		if(datas.length==0){
			alert("请至少选择一行");
			return;
		}
		for(var i=0;i<datas.length;i++){
			a+=datas[i].ASSETS_NUMBER;
			a+="-";
		}
		$("#batchUpdateGridDiv").dialog({
			title:'选择网格',
			width:300,
			height:150,
			closed:false,
			modal:true
		});
	}
	function batchSubUpdateGrid(){
		var gridNum=$("#roadAddGridName1").combobox("getValue");
		$.ajax({
			async:false,
			url:'<%=path%>/ywgl/NuGridbatchAddAssGrid.do',
			dataType:"text",
			data: {assetNum:a,flag:"资产",gridNum:gridNum},
			success:(function(data){
				if(data==1){
					$('#win').window({ 
						title:"数据同步提示",
						modal:true,
						closed:false
						//maximized:true
					}); 
					$("#yesBut").attr("onclick","javascript:subBatchSynAddGrid(\""+a+"\",\""+gridNum+"\",1);");
					$("#noBut").attr("onclick","javascript:subBatchSynAddGrid(\""+a+"\",\""+gridNum+"\",0);");
					$('#nuResult').datagrid({
				        url:'<%=path%>/ywgl/NuGridselMoreSynNu.do',
						queryParams: {  		
							assetNum:a,
							gridNum:gridNum
						},
				        columns:[[
				             {title:'网元id',field:'NU_ID',align:'left',hidden:true},
				             {title:'网格名称',field:'GRID_NAME',width:'100',align:'left'},
				             {title:'网元名称',field:'NU_NAME',width:'200',align:'left'},
				             {title:'网元地址',field:'NU_ADDRESS',width:'200',align:'left'},
				             {title:'站点',field:'SITE_NAME',width:'100',align:'left'},
				             {title:'机房',field:'ROOM_NAME',width:'100',align:'left'},
						     {title:'专业',field:'NU_SPEC_NAME',width:'100',align:'left'},
				             {title:'网元所在区局',field:'YW_REGION',width:'100',align:'left'},
						     {title:'厂商',field:'MANUFACTURER_NAME',width:'200',align:'left'}
						]],
				        method:'post'
				    });
				}else{
					alert("保存成功");
					$('#lstResult').datagrid("reload");
					$("#batchUpdateGridDiv").dialog({
						closed:true
					});
				}
			}),
			method:"post"
		});
	}
	function subBatchSynAddGrid(assetNum,gridNum,isSyn){
		var datas=$('#nuResult').datagrid("getRows");		
		var nuId="";
		for(var i=0;i<datas.length;i++){
			nuId+=datas[i].NU_ID;
			nuId+="-";
		}
		$.ajax({
			async:false,
			dataType:"text",
			url:'<%=path%>/ywgl/NuGridbatchAddAssGrid.do',
			data:{assetNum:assetNum,gridNum:gridNum,isSyn:isSyn,nuId:nuId,flag:"资产"},
			success:(function(data){
				$('#win').window({ 
					closed:true
				}); 
				alert("保存成功");
				$('#lstResult').datagrid("reload");
				$("#batchUpdateGridDiv").dialog({
					closed:true
				});
			})
		});
	}
	
	function resert(){
		
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
			alert("请选择一个机柜！");
			return ;
		}
		$.ajax({
			async:false,
			dataType:"json",
			url:'<%=path%>/bkxx/equipment_list_judge.do?cabinetId='+cabinetId,
			success:(function(data){
				//var flag=eval(data.d);
				if(data[0].msg=="yes"){
					window.open('<%=path%>/ywgl/showRoom/importCardList.jsp?cabinetId='+cabinetId,'newwindow','resizable=yes,scrollbars=yes,height=650,width=600,top=200,left=200');
				}else{
					alert('此机柜无设备无法导入！');
					return;

				}
				
				
			})
		});
			}
	
	
	function bkxxQuery(){
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
		        url:'<%=path%>/bkxx/bkxxListQuery.do', 
		         queryParams: {  		
		        	 juZhan:juZhan, 	
		        	 jiFang:jiFang,
		        	 cabinetId:cabinetId,
		        	 gridId:gridId,
		        	 equipmentName:equipmentName
				  },
		        columns:[[
		             {title:'板卡ID',field:'CARD_ID',width:'60',align:'left',hidden:true}, 
		             {title:'机房名称',field:'ROOM_NO',width:'160',align:'left'},
		             {title:'机柜编号',field:'CABINET_NUM',width:'60',align:'left'},
		             {title:'机柜面',field:'CABINET_SURFACE',width:'80',align:'left'},
		             {title:'网元编号',field:'NU_NUM',width:'180',align:'left'},
		             
		             {title:'设备名称',field:'EQUIP_NAME',width:'160',align:'left'},
		             {title:'板卡生产厂商',field:'MANUFACTURER',width:'130',align:'left'},
		             {title:'板卡类型',field:'CATEGORY',width:'150',align:'left'},
		             
		             {title:'板卡型号',field:'MODEL',width:'100',align:'left'},
		             {title:'板卡用途',field:'PURPOSE',width:'100',align:'left'},
		             {title:'板卡位置数',field:'POS_X',width:'80',align:'left'},
		             {title:'子框标志',field:'LABEL',width:'70',align:'left'},
		             
		             {title:'子框数量',field:'SUB_RACK_COUNT',width:'100',align:'left'}
				   	]],
		        method:'post'
		    });
	}
    
  </script>
</html>
