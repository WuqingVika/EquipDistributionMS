<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>上海电信机房设备分布管理系统</title>
    <link rel="stylesheet" type="text/css" href="../js/easyui/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="../js/easyui/themes/icon.css">  
    <script type="text/javascript" src="../js/easyui/jquery.min.js"></script>   
	<script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script> 
    <script type="text/javascript" src="../js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <style>
        .linka{
            color:white;
        }
        /*网页首部*/
        .header{
            height:80px;color:white;overflow:hidden;
            background:url(../img/maintopbg.jpg)
        }

        .menuItem li{
            width:140px;
            height:30px;
            margin-top:10px;
        }

        /* 改写tree node 的样式*/
        .tree-node{
            height:18px;
            padding-top:8px;
            line-height: 18px;
            margin-top:5px;
            font-size:17px;
            padding-left:5px;
            padding-bottom:6px;
            border:solid 1px #e6e6e6;
        }

        .border-orange{
            border:solid 1px orange;
            color: #4766ff;
        }

        .tr{
            padding:8px;
        }
    </style>
    
</head>
<body class="easyui-layout" fit="true" >
<!-- 网站首部 -->
<div region="north" class="header" border="false">
    <span style="display:inline-block;float:left;width:120px;">
        <!--<img src="../img/logo.jpg" style="width:150px;height:110px;margin-top: -5px;"/>-->
    </span>
    <div style="float:left;width:320px;">
         <p style="color:white;font-family:'微软雅黑';margin-top:18px;">
            <span style="font-size:21px;font-weight: 800;">上海电信机房设备分布管理系统</span>
            <br>
            <span style="font-size:9px;"><i>Shanghai Telecom Room Equipment Distribution Management System</i></span>
        </p>
    </div>
    <div>

    </div>
    <div style="float:right;color:white;font-size:13px;width:260px;height:40px;margin-top:38px;line-height: 40px;">
         欢迎&nbsp;<span style="font-weight:700;">${user.workNo }</span>&nbsp;&nbsp;
        &nbsp;&nbsp;<a href="#" class="linka">退出登录</a>
    </div>
</div>
<!-- 菜单面板 -->
<div region="west" style="width:250px;" split="true" title="功能菜单" iconCls="icon-application_view_icons">
    <div id="aa" class="easyui-accordion" fit="true" border="false" class="menuItem">
        <div title="&nbsp;&nbsp;&nbsp;&nbsp;机房设备管理" style="">
            <ul class="easyui-tree tr" data-options="data:[
            
                {
                    'id':101,
                    'text':'机柜管理',
                    'iconCls':'icon-application_form_edit',
                    'attributes':{
                        'url':'<%=path %>/user/toCabinetList.action'
                    }
                },
             
                {
                    'id':102,
                    'text':'设备管理',
                    'iconCls':'icon-ruby_gear',
                    'attributes':{
                        'url':'<%=path %>/user/toEquipmentList.action'
                    }
                },
                {
                    'id':103,
                    'text':'板卡管理',
                    'iconCls':'icon-application_form_edit',
                    'attributes':{
                        'url':'<%=path %>/user/toCardList.action'
                    }
                }
                
                
            ]">
            </ul>
        </div>
        
        <div title="&nbsp;&nbsp;&nbsp;&nbsp;基础数据管理">
            <ul class="easyui-tree tr" data-options="data:[
                {
                    'id':201,
                    'text':'专业管理',
                    'iconCls':'icon-search',
                    'attributes':{
                        'url':'<%=path %>/user/toSpecList.action'
                    }
                },
                {
                    'id':202,
                    'text':'局站管理',
                    'iconCls':'icon-page_word',
                    'attributes':{
                        'url':'<%=path %>/user/toRegionList.action'
                    }
                },
                {
                    'id':203,
                    'text':'机房管理',
                    'iconCls':'icon-application_form_edit',
                    'attributes':{
                        'url':'<%=path %>/user/toRoomList.action'
                    }
                }
            ]">
            </ul>
        </div>
        
        <div id="userManage" title="&nbsp;&nbsp;&nbsp;&nbsp;系统维护">
            <ul id="userAdmission" class="easyui-tree tr" data-options="data:[
            <c:if test="${user.sp=='1' }">
                {
                    'id':301,
                    'text':'系统用户管理',
                    'iconCls':'icon-user',
                    'attributes':{
                        'url':'<%=path %>/user/toVipList.action'
                    }
                },
               </c:if>
                {
                    'id':302,
                    'text':'修改密码',
                    'iconCls':'icon-textfield_key',
                    'attributes':{
                        'url':'<%=path %>/user/toCommonPwd.action?userName=${user.userName }'
                    }
                },
                {
                    'id':303,
                    'text':'开发者介绍',
                    'iconCls':'icon-tip',
                    'attributes':{
                        'url':'<%=path %>/user/toIntro.action'
                    }
                }
            ]">
            </ul>
        </div>
    </div>
</div>
<!-- 主要内容 -->
<div region="center"  border="false">
    <div class="easyui-tabs" fit="true" id="tt">
        <div title="首页" data-options="iconCls:'icon-application_home'" >
			<span style="font-size:32px;font-weight:400;">欢迎来到上海电信机房设备分布管理系统</span><br/>
        </div>
    </div>
</div>
<!-- 版权部分 -->
<div region="south" style="text-align: center;height:40px;
background:url(../img/mainbottombg.jpg);line-height: 40px;overflow:hidden;color:white;" border="false">
    版权所有@上海电信机房设备分布管理系统  Copyright 2016-2017
</div>
<!-- 创建菜单 -->
<div id="mm" class="easyui-menu" style="width:120px;">
    <div id="closeCurrent" name="closeCurrent">关闭当前</div>
    <div id="closeOther" name="closeOther">关闭其他</div>
    <div id="closeAll" name="closeAll">关闭所有</div>
</div>
<script>
    $(function(){
        // 改变tree的样式效果
        $(".tr").tree({
//            onLoadSuccess:function(node, data){
//                console.log(node,data);
//                var id = data[0].children[0].children[0].id;
//                var n = $(this).tree('find',id);
//                $(this).tree('select', n.target);
//            },
            onSelect:function(node){
                $(node.target).addClass("border-orange").parent().siblings().find("div").removeClass('border-orange');
                // 动态添加tab标签
                addTab(node.iconCls,node.text,node.attributes.url);
            },
        });


        // 动态添加标签
        function addTab(iconCls,title, url){
            if ($('#tt').tabs('exists', title)){
                $('#tt').tabs('select', title);
            } else {
                var content = '<iframe scrolling="auto"  frameborder="0"  src="'+url+'" style="width:100%;height:100%;margin-bottom:-3px;"></iframe>';
                $('#tt').tabs('add',{
                    title:title,
                    content:content,
                    closable:true,
                    iconCls:iconCls
                });
            }
        }

        // 绑定右键事件
        $("#tt").tabs({
            onContextMenu:function(e, title,index){
                e.preventDefault();
                console.log(e, title,index);
                // 选中标签
                $("#tt").tabs('select',index);
                // 显示右键菜单
                $("#mm").menu('show',{
                    left:e.pageX,
                    top:e.pageY
                });
                // 绑定点击事件
                $("#mm").menu({
                    onClick:function(item){
                        closeTab(this,item.name);
                    }
                });
            }
        });

        // 定义关闭标签的方法(第一个不能关闭)
        var closeTab = function(type,menuName){
            // 获取所有的tabs
            var $tabs = $("#tt").tabs("tabs");
            var length = $tabs.length;
            // 获取选中的tab
            var $selected = $("#tt").tabs('getSelected');
            var index = $("#tt").tabs('getTabIndex',$selected);
            // 执行关闭操作
            if(menuName=="closeCurrent"){// 关闭当前
                $("#tt").tabs('close',index);
            }else if(menuName=="closeOther"){// 关闭其他
                // 关闭之前
                for(var i = 0;i<index-1;i++){
                    $("#tt").tabs('close',1);
                }
                // 关闭之后
                for(var i=0;i<length-index-1;i++){
                    $("#tt").tabs('close',2);
                }
                // 设置选中
                $('#tt').tabs('select',1);
            }else if(menuName=="closeAll"){// 关闭所有
                for(var i = 0;i<length-1;i++){
                    $("#tt").tabs('close',1);
                }
            }
        }
    	
    });
</script>
</body>
</html>