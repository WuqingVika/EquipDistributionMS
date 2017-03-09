<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String roomId=request.getParameter("roomId");//roomId
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <script  type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
    <title>export cabinet list</title>
	<style type="text/css">
	.btn {
		border-right: #7b9ebd 1px solid;
		padding-right: 2px;
		border-top: #7b9ebd 1px solid;
		padding-top: 2px;
		border-left: #7b9ebd 1px solid;
		padding-left: 2px;
		border-bottom: #7b9ebd 1px solid;
		font-size: 12px;
		color: black;
		filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde);
		cursor: expression((this.type == 'file')?'':'hand');
	}
	.fi {
		border-right: #7b9ebd 1px solid;
		padding-right: 2px;
		border-top: #7b9ebd 1px solid;
		padding-top: 2px;
		border-left: #7b9ebd 1px solid;
		padding-left: 2px;
		border-bottom: #7b9ebd 1px solid;
		font-size: 12px;
		color: black;
	}
	</style>

  </head>
  
  <body>
  <SCRIPT type="text/javascript">
  
 	
	function addFileInput(obj) {
		obj.insertAdjacentHTML("beforeBegin", "<input type='file' name='file' size='36' class='fi' onchange='javascript:validFileName(this)'/><input type='button' style='width:40px;height:20px' value='去除' class='btn' onclick='javascript:removeFileInput(this)'/><br>");
	}

	function removeFileInput(obj) {
		obj.previousSibling.removeNode();
		obj.nextSibling.removeNode();
		obj.removeNode();
	}
	
	function validFileName(obj) {
		var v = obj.value;
		var os = document.getElementsByName('file');
		for (var i = 0; i < os.length; i ++) {
			if (obj != os[i] && v == os[i].value) {
				//alert("您选择的文件重复,请确认是否一定要上传两个相同的文件");
				break;
			}
		}
	}
	function resetFile(){ 
        	var html=document.getElementById('uploadFileTd').innerHTML; 
			document.getElementById('uploadFileTd').innerHTML=html; 
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
				$("#uploadFileForm").submit();
			}
			
		}
    }
	//导出模板
	function exc(){
		
		var strUrl = '<%=path%>/jgxx/doExport.action?filename='+encodeURI(encodeURI("机柜模板"));
		window.location.href=strUrl;
		//window.location.href=strUrl;///ywgl20161209/WebRoot/ywgl/platform/机柜信息导入模板.xls
		//window.location='${pageContext.request.contextPath}/ywgl/platform/cabinetResourceInfo.xls';
	}
	
</SCRIPT>
    <center>
			<table border="0" cellspacing="0" cellpadding="0" style="border:1px solid #CCCCCC;" width="350">
				<tr><td height="10"></td></tr>
				<tr>
					<td align="center">
					 <form id="uploadFileForm" action="<%=path%>/jgxx/cabinetinfo_import.action?roomId=<%=roomId %>" enctype="multipart/form-data" class="form-horizontal" method="post" >
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">选择要上传的文件</label>
							<div class="col-sm-10">
								<input id="uploadFile" name="uploadFile" id="uploadFile" type="file" class="form-control">
							</div>
						</div>
					</form>
					<input type="button" class="btn" value="导入" style="width:60px" onclick="javascript:subimtBtn();"/>
					
					</td>
				</tr>
				<tr>
					<td align="left">
					  <a href="javascript:void(0);" onclick="exc();">模板导出</a>
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</center>
  </body>
</html>
