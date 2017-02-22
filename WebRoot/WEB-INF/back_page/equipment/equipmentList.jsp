<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <!-- <base href="<%=basePath%>">-->
    <title>璁惧淇℃伅</title>
    <link rel="stylesheet" type="text/css" href="../../css/ywglCommon.css"/>
    <link rel="stylesheet" type="text/css" href="../../js/easyui/themes/default/easyui.css">   
		<link rel="stylesheet" type="text/css" href="../../js/easyui/themes/icon.css">  
    <script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>   
		<script type="text/javascript" src="../../js/easyui/jquery.easyui.min.js"></script> 
  	<script type="text/javascript" src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
  </head>
  
  <body style="padding:5px;">
  	<div class="easyui-layout" fit="true" border="false">
		    <div region="center" border="false">
					<div class="SearchCondition" id="tbar">
								<!--鏌ユ壘鏉′欢锛氳繖閲屽埌鏃跺彲浠ユ浛鎹� -->
								  <table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tbody>
											<tr>
												<td style="width: 5px; border-bottom: 2px solid #C0C0C0;">&nbsp;</td>
												<td style="border-bottom: 2px solid #F3F2F2;" valign="bottom">
													<div style="background: #F3F2F2; border-top: 1px solid #C0C0C0; border-right: 1px solid #C0C0C0; border-left: 1px solid #C0C0C0;">
														<div style="border-top: 1px solid #FFFFFF; border-right: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF; background: transparent url(../../css/TabGradientA.xhtml) repeat-x; padding: 3px 5px;">
																<table border="0" cellpadding="0" cellspacing="0">
																	<tbody>
																		<tr>
																				<td nowrap="nowrap"><div style="text-align: left; -webkit-appearance: none; border: 0; background: none; padding: 0; background-image: url(../../img/binocle.png); background-repeat: no-repeat; text-indent: 18px;">
																						<span>鏌ヨ鏉′欢</span></div>
																				</td>
																				<td>&nbsp;</td>
																				<td> &nbsp;&nbsp;&nbsp;</td>
																		</tr>
																	</tbody>
																</table>
														</div>
													</div>
												</td>
												<td style="width: 95%; border-bottom: 2px solid #C0C0C0; text-align:right; padding-right:20px;"></td>
											</tr>
										</tbody>
								  </table>
							<!--鏌ユ壘鏉′欢锛氳繖閲屽埌鏃跺彲浠ユ浛鎹� end -->
							<form id="ff" action="">
										<table class="SearchCondition" >
								    		<tr>
													<td>灞�绔欙細<input class="easyui-combobox" id="juZhan" name="juZhan" style="width:175px"></input></td>
													<td>鏈烘埧锛�<input class="easyui-combobox" id="jiFang" name="jiFang"  style="width:175px"></td>
													<td>鏈烘煖锛�<input class="easyui-combobox" id="cabinetId" name="cabinetId"  style="width:150px"></input></td>
								    		</tr>
								   		 	<tr>
								   		 	 	<td>缃戝厓缂栧彿锛�<input id="gridId" placeHolder="璇疯緭鍏ョ綉鍏冪紪鍙�.." name="gridId"  style="width:150px"></input></td>
													<td>璁惧鍚嶇О锛�<input id="equipmentName" placeHolder="璇疯緭鍏ヨ澶囧悕绉�.." name="equipmentName"  style="width:150px"></input></td>
								    		</tr>
												<tr>
													<td colspan="8">
														<div style="text-align:center;padding:5px">
													    	<input type="button" class="button" id="btnSearch" value="鏌ヨ" onclick="javascript:sbxxQuery();"/>
													    	<input type="button" class="button" id="btnClearForm" value="閲嶇疆" onclick="javascript:resert();"/>
										    				<input type="button" class="button" id="btnClearForm" value="鎵归噺瀵煎叆" onclick="javascript:inserts();"/>
										    			</div>
													</td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
						    		</table>
						  </form>
					</div>
					<table fit="true" style="width:1050px;" id="lstResult" class="easyui-datagrid" title="璁惧-鍒楄〃" iconCls="icon-comment_edit" pageSize="20" url="" rownumbers="true" 
						striped="true" remoteSort="true" pagination="true" singleSelect="false" toolbar="#tbar">
					</table>
			
					<div id="cardInfo" class="easyui-window" closed="true" title="鏉垮崱淇℃伅"  
				 	 	style="width:1100px;padding:5px;">
						<table style="width:1060px;" id="lstCardInfos" class="easyui-datagrid" pageSize="20" url="" rownumbers="true" 
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
			$.messager.alert('鎻愮ず淇℃伅','娌℃湁鍙緵瀵煎嚭鐨勬暟鎹�');
			return false; 
		}
		var strUrl = '<%=path%>/jgxx/initJgxxListPage.do?';
		window.location.href=strUrl;
	}
    /* $.messager.progress({
  		title: '',
		msg: '',
		text: '鍔犺浇涓紝璇风◢鍚�',
		interval:790
  	});
  	setTimeout(function(){$.messager.progress('close');},2000); */
  	
	$('#juZhan').combobox({
		url:'<%=path%>/jgxx/juzhan_list_search.do',
	    valueField:'REGION_ID',
	    textField:'REGION_NAME',
	    method:'get',
	    
	    onChange : function(){//灞�绔欐坊鍔爋nChange浜嬩欢銆�
            var regionId = $("#juZhan").combobox("getValue") ;           
            //1.娓呯┖鍘熸潵鐨勬満鎴縞ombobox涓殑閫夐」
            $("#jiFang").combobox("clear");     
            
            //2.鍔ㄦ�佹坊鍔�"鏈烘埧"鐨勪笅鎷夊垪琛ㄦ鐨刼ption                    
            if( regionId != null && regionId != '' ){//2
            	//alert("rek====="+regionId);
            	$('#jiFang').combobox({
            		url:"<%=path%>/jgxx/jifang_list_search.do?regionId="+regionId,
            		
            	    valueField:'ROOM_ID',
            	    textField:'ROOM_NO',
             	    method:'get',
             	    //Onchange
             	    	onChange : function(){//灞�绔欐坊鍔爋nChange浜嬩欢銆�
             	            var roomId = $("#jiFang").combobox("getValue") ;           
             	            //1.娓呯┖鍘熸潵鐨勬満鏌渃ombobox涓殑閫夐」
             	            $("#cabinetId").combobox("clear");     
             	            
             	            //2.鍔ㄦ�佹坊鍔�"鏈烘煖"鐨勪笅鎷夊垪琛ㄦ鐨刼ption                    
             	            if( roomId != null && roomId != '' ){//2
             	            	//alert("rek====="+regionId);
             	            	$('#cabinetId').combobox({
             	            		url:"<%=path%>/sbxx/cabinet_list_search.do?roomId="+roomId,
             	            		
             	            	    valueField:'CABINET_ID',
             	            	    textField:'CABINET_NAME',
             	             	    method:'get'
             	            	});
             	            }//-2
             			   }//onchange 缁撴潫
             	    //onchange end
            	});
            }//-2
		   }//onchange 缁撴潫
	    });
	
//	<%-- $('#zhuanYe').combobox({
//		url:'<%=path%>/jgxx/zhuanye_list_search.do',
//	    valueField:'SPEC_ID',
//	    textField:'SPEC_NAME',
//	   // value:'--璇烽�夋嫨鏈烘煖涓撲笟--',
//	    method:'get'
//	}); --%>
	
	var a="";
	function batchUpdateGrid(){
	 	a="";
		var datas=$('#lstResult').datagrid('getSelections');
		if(datas.length==0){
			alert("璇疯嚦灏戦�夋嫨涓�琛�");
			return;
		}
		for(var i=0;i<datas.length;i++){
			a+=datas[i].ASSETS_NUMBER;
			a+="-";
		}
		$("#batchUpdateGridDiv").dialog({
			title:'閫夋嫨缃戞牸',
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
			data: {assetNum:a,flag:"璧勪骇",gridNum:gridNum},
			success:(function(data){
				if(data==1){
					$('#win').window({ 
						title:"鏁版嵁鍚屾鎻愮ず",
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
				             {title:'缃戝厓id',field:'NU_ID',align:'left',hidden:true},
				             {title:'缃戞牸鍚嶇О',field:'GRID_NAME',width:'100',align:'left'},
				             {title:'缃戝厓鍚嶇О',field:'NU_NAME',width:'200',align:'left'},
				             {title:'缃戝厓鍦板潃',field:'NU_ADDRESS',width:'200',align:'left'},
				             {title:'绔欑偣',field:'SITE_NAME',width:'100',align:'left'},
				             {title:'鏈烘埧',field:'ROOM_NAME',width:'100',align:'left'},
						     {title:'涓撲笟',field:'NU_SPEC_NAME',width:'100',align:'left'},
				             {title:'缃戝厓鎵�鍦ㄥ尯灞�',field:'YW_REGION',width:'100',align:'left'},
						     {title:'鍘傚晢',field:'MANUFACTURER_NAME',width:'200',align:'left'}
						]],
				        method:'post'
				    });
				}else{
					alert("淇濆瓨鎴愬姛");
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
			data:{assetNum:assetNum,gridNum:gridNum,isSyn:isSyn,nuId:nuId,flag:"璧勪骇"},
			success:(function(data){
				$('#win').window({ 
					closed:true
				}); 
				alert("淇濆瓨鎴愬姛");
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
	    data:[{ROOM_ID:"",ROOM_NO:"" }]
		});
	}
	function resetCabinet(){
		//var data=;
		$('#cabinetId').combobox({
		valueField:'CABINET_ID',
	    textField:'CABINET_NAME',
	    data:[{"CABINET_ID":"","CABINET_NAME":"" }]
		});
	}
	function resert(){
		
		$("#jiFang").combobox("clear"); 
		$("#cabinetId").combobox("clear"); 
		$("#juZhan").combobox("setValue","");
		$("#jiFang").combobox("setValue","");
		/* //alert("aja");
		resetJiFang(); */
		$("#cabinetId").combobox("setValue","");
		/* resetCabinet();
		//$("#cabinetId").empty(); */
		$("#gridId").val("");
		$("#equipmentName").val("");
	}
	
	//鎵归噺瀵煎叆杩涘叆閫� 鎷╂枃浠堕〉闈�
	function inserts(){
		var cabinetId=$("#cabinetId").combobox("getValue");
		
		if(cabinetId==null||cabinetId==""){
			alert("璇烽�夋嫨浠绘剰涓�涓満鏌滀负鍏舵壒閲忔坊鍔犺澶囷紒");
			return ;
		}
		window.open('<%=path%>/ywgl/showRoom/importEquipmentList.jsp?cabinetId='+cabinetId,'newwindow','resizable=yes,scrollbars=yes,height=650,width=600,top=200,left=200');
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
				pageSize: 20,//姣忛〉鏄剧ず鐨勮褰曟潯鏁帮紝榛樿涓�10  
                pageList: [10, 20,50,100, 200],//鍙互璁剧疆姣忛〉璁板綍鏉℃暟鐨勫垪琛� //   ywgl/ziChanGrid_list_seach.do
		        url:'<%=path%>/sbxx/SbxxListQuery.do', 
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
		             //{title:'搴忓彿',field:'ID',width:'200',align:'left',hidden:true},
		             {title:'璁惧ID',field:'EQUIP_ID',width:'60',align:'left',hidden:true}, 
		             {title:'鏈烘埧鍚嶇О',field:'ROOM_NO',width:'200',align:'left'},
		             {title:'鏈烘煖缂栧彿',field:'CABINET_NUM',width:'60',align:'left'},
		             {title:'鏈烘煖闈�',field:'CABINET_SURFACE',width:'100',align:'left'},
		             {title:'缃戝厓缂栧彿',field:'NU_NUM',width:'200',align:'left'},
		             
		             {title:'璁惧鍚嶇О',field:'EQUIP_NAME',width:'250',align:'left'},
		             {title:'璁惧涓撲笟',field:'SPEC_NAME',width:'80',align:'left'},
		             {title:'璁惧鍘傚晢',field:'MANUFACTURER',width:'150',align:'left'},
		             {title:'璁惧绫诲瀷',field:'CATEGORY',width:'100',align:'left'},
		             
		             {title:'璁惧鍨嬪彿',field:'MODEL',width:'100',align:'left'},
		             {title:'瀛愭鏁伴噺',field:'SUB_RACK_COUNT',width:'100',align:'left'},
		             {title:'鏉垮崱淇℃伅',field:'UPDATE',width:'150',align:'left',formatter:CardInfo},
		             
				   	]],
		        method:'post'
		    });
	}
	//鏉垮崱淇℃伅鎸夐挳
	function CardInfo(value,rowData,rowIndex){
    	var link="<a href='javascript:showCard(" + rowData.EQUIP_ID + ")'>"+'鏉垮崱淇℃伅'+"</a>";
    	return link;
    }
	//寮瑰嚭鏉垮崱淇℃伅璇︽儏
	function showCard(equipId)
    {
		//$("#cardInfo").empty();	
		$('#lstCardInfos').datagrid('loadData', { total: 0, rows: [] });
		$('#lstCardInfos').datagrid({
				pageNumber:1,
				pageSize: 10,//姣忛〉鏄剧ず鐨勮褰曟潯鏁帮紝榛樿涓�10  
                pageList: [10,20,50],//鍙互璁剧疆姣忛〉璁板綍鏉℃暟鐨勫垪琛� //   ywgl/ziChanGrid_list_seach.do
		        url:'<%=path%>/sbxx/gotoCardInfo.do', 
		         queryParams: {  		
		        	 equipId:equipId
				  },
		        columns:[[
		             {title:'鏉垮崱ID',field:'CARD_ID',width:'60',align:'left',hidden:true}, 
		             {title:'瀛愭ID',field:'SUB_RACK_ID',width:'60',align:'left',hidden:true},
		             {title:'鎵�鍗犳Ы浣嶆暟',field:'OCCUPY_SLOT_NUM',width:'100',align:'left'},
		             {title:'鏉垮崱鍘傚晢',field:'MANUFACTURER',width:'150',align:'left'},
		             {title:'鏉垮崱鐢ㄩ��',field:'PURPOSE',width:'150',align:'left'},
		             
		             {title:'鏉垮崱绫诲瀷',field:'CATEGORY',width:'250',align:'left'},
		             {title:'鏉垮崱鍨嬪彿',field:'MODEL',width:'100',align:'left'},
		             {title:'鍥哄畾璧勪骇缂栧彿',field:'ASSET_NO',width:'150',align:'left'},
		             {title:'浣嶇疆鏁�',field:'POS_IDX',width:'60',align:'left'},
		             {title:'鏉垮崱鏇存崲鏃ユ湡',field:'CHANGE_DATE',width:'100',align:'left'},
		            
				   	]],
		        method:'post'
		    });
		$('#cardInfo').window({
			modal:true});
		$('#cardInfo').window('open');
		
    }
    
  </script>
</html>
