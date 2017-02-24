<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>机柜信息</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/ywglCommon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">   
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">  
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.min.js"></script>   
		<script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script> 
  	<script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
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
             	    method:'get'
            	});
            }//-2
		   }
	    });
	
	$('#zhuanYe').combobox({
		url:'<%=path%>/jgxx/zhuanye_list_search.do',
	    valueField:'SPEC_ID',
	    textField:'SPEC_NAME',
	   // value:'--请选择机柜专业--',
	    method:'get'
	});
	
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
	
	function resetJiFang(){
		//var data=;
		$('#jiFang').combobox({
		valueField:'ROOM_ID',
	    textField:'ROOM_NO',
	    data:[{ROOM_ID:-1,ROOM_NO:"" }]
		});
	}
	
	function resert(){
			
		$("#jiFang").combobox("clear");    
		$("#juZhan").combobox("setValue","");
		$("#jiFang").combobox("setValue","");
		resetJiFang();
		//$("#jiFang").combobox("clear");  
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
		window.open('<%=path%>/ywgl/selectroom/exportCabinetList.jsp?roomId='+roomId,'newwindow','resizable=yes,scrollbars=yes,height=650,width=600,top=200,left=200');
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
		        url:'<%=path%>/jgxx/JgxxListQuery.do', 
		         queryParams: {  		
		        		
		        	 juZhan:juZhan, 	
		        	 jiFang:jiFang,
		        	 zhuanYe:zhuanYe,
		        	 bianOrMc:bianOrMc
				  },
		        columns:[[
		        	//ASSETS_NUMBER, ASSETS_DESCRIBE, ASSETS_MANUFACTURER, ADDRESS, OBJ_DEPT_DESCRIBE, DEPT_DESCRIBE
		             //{field:'flag',checkbox:true},
		             //{title:'序号',field:'ID',width:'200',align:'left',hidden:true},
		              {title:'机柜ID',field:'ID',width:'200',align:'left',hidden:true}, 
		             {title:'机房名称',field:'ROOM_NO',width:'200',align:'left'},
		             {title:'机柜编号',field:'CABINET_NUM',width:'200',align:'left'},
		             {title:'机柜名称',field:'CABINET_NAME',width:'250',align:'left'},
		             {title:'机柜面',field:'CABINET_SURFACE',width:'200',align:'left'},
		             //{title:'机柜二维码',field:'CABINET_SURFACE',width:'200',align:'left'}	
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
		//frame.jsp?globe.target=/selectroom/cabinetshowQRCode.jsp?cabinet_surface
		//alert(cabinet_surface);
		window.open("<%=path%>/ywgl/selectroom/cabinetshowQRCode.jsp?cabinet_surface="+cabinet_surface+"&id="+id);
     	//window.top.openWindow("<%=path%>/jgxx/gotoQrcodePage.do?cabinet_surface="+cabinet_surface+"&id="+id);
    }
    
  </script>
	
</html>
