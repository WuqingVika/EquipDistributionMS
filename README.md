# EquipDistributionMS 《那些年:smirk:屎香香填过的坑》
屎香香的毕业设计-机房设备分布管理系统   

## :blush: 2017年2月25号跳的如下哈哈哈
+ spring3.x不支持Java1.8,得用spring4.x
+ commons-lang-xx和commons-lang3-xx 本项目里有用到的Jar包是用前者，所以要用StringUtils得再导入个Lang3-xx.
+ 项目启动老卡在trying to resolve system-id[..dtd:hibernate3.0mapping.. 网慢就导到各种报错 这是因为它老跑hibernate官网连接dtd文件了，可以选择在本地配置。（用压缩包方式打开hibernate3.jar 可以找到hibernate-mapping-3.0！参考链接：http://blog.csdn.net/huweijian5/article/details/21863161
+ <result type="json"></result>问题需要struts-json-plugin.jar还有json-lib.jar(反正这个也加上吧)最后记得包名继承 Json-default!

## :blush: 2017年3月2号跳的如下哈哈哈
+ struts和servlet共用时，我把Struts 过滤从/*变为*.action,这样使用servlet就能优先被拦截到啦。

## :blush: 2017年3月18号跳的如下哈哈哈
+ getCurrentSession与 openSession区别。
+ 【Field 'CARD_ID' doesn't have a default value】mysql注意主键设为自动递增，我把decimal改成bigint就ok.

## :blush: 2017年3月25号跳的如下哈哈哈
+ 【一个很坑的低级的 就是datagrid判断获取行数时，当前页面如果选勾，再刷新数据就会把之前勾上的条数累加】 idField="specId"是这个字段。
+ 修改已有记录的mysql数据表的主键为自动增长时,报出以下错误
【ALTER TABLE causes auto_increment resequencing, resulting in duplicate entry ’1′ for key ‘PRIMARY’】
 ```  
 第1步：将主键字段值为0的那条记录值改为其他大于0且不重复的任意数
 第2步：修改主键字段为auto_increment
第3步：把刚才修改过的那条记录的值还原
 ``` 
 
## :blush: 2017年3月28号跳的如下哈哈哈
+ 修改mysql递增的id

![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/helpOne.jpg)

+ 删除sql未执行
记得加上.executeUpdate();

## :blush: 2017年4月9号跳的如下哈哈哈
+ easyui复选框刷新累计问题
```  
 //datagrid用了idField,建议google easyui idField作用;
 $('#dg').datagrid('clearSelections');//每次加载datagrid需要清空
 原文参考：http://blog.csdn.net/isea533/article/details/50929752
 ``` 
+ 发送邮件验证码授权码问题
```  
 MailUtil中的return new PasswordAuthentication("aa@qq.com", "abcxxxxxxxxxx"); // 发件人邮箱账号、授权码
	//注：授权码不是邮箱密码，可以看如何获取授权码，复制粘贴就Ok.			
 ``` 
 
## :blush: 2017年4月12号跳的如下哈哈哈
+ struts重定向问题
```
	window.location.href='';//把它改成top.location.href;
	我就是把问题想得太复杂了,才走了这么多坑，所以冷静下来好好想毕竟 万变不离其宗。
	
 ``` 
---

## 系统截图 
+ 1登录界面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showLogin.jpg)
+ 2系统主页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showMain.jpg)
+ 3机柜管理页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showCabinet.jpg)
+ 4设备管理页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showEqu.jpg)
+ 5板卡管理页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showCard.jpg)
+ 6专业管理页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showSpec.jpg)
+ 7局站管理页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showRegion.jpg)
+ 8机房管理页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/comroomManage.png)
+ 9用户管理页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showUser.png)
+ 10忘记密码页面
![image](https://github.com/WuqingVika/EquipDistributionMS/blob/master/WebRoot/img/showsendEmail.png)
---
【功能还在拼命完善中！:blush:】