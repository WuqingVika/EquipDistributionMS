# EquipDistributionMS 《那些年屎香香走过的坑》
屎香香的毕业设计-机房设备分布管理系统

##2017年2月25号跳的如下哈哈哈
+ spring3.x不支持Java1.8,得用spring4.x
+ commons-lang-xx和commons-lang3-xx 本项目里有用到的Jar包是用前者，所以要用StringUtils得再导入个Lang3-xx.
+ 项目启动老卡在trying to resolve system-id[..dtd:hibernate3.0mapping.. 网慢就导到各种报错 这是因为它老跑hibernate官网连接dtd文件了，可以选择在本地配置。（用压缩包方式打开hibernate3.jar 可以找到hibernate-mapping-3.0！参考链接：http://blog.csdn.net/huweijian5/article/details/21863161
+ <result type="json"></result>问题需要struts-json-plugin.jar还有json-lib.jar(反正这个也加上吧)最后记得包名继承 Json-default!

##2017年3月2号跳的如下哈哈哈
+ struts和servlet共用时，我把Struts 过滤从/*变为*.action,这样使用servlet就能优先被拦截到啦。

##2017年3月18号跳的如下哈哈哈
+ getCurrentSession与 openSession区别。