<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/icon.css">

    <script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script type="text/javascript">
	<!--菜单处理-->
    $(function () {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/menu/queryAll",
            dataType:"json",
            success:function (data) {
                //遍历创建
                $.each(data,function (index,f) {
                    var a="";
                    $.each(f.list,function(index1,s){
                        a+="<p style='text-align: center'><a id=\"btn\" href=\"#\" class=\"easyui-linkbutton\" onclick=\"addTabs('"+s.title+"','"+s.iconcls+"','"+s.url+"')\" data-options=\"iconCls:'"+s.iconcls+"'\">"+s.title+"</a></p>";
                    })
                    $('#aa').accordion("add", {
                        title: f.title,
                        iconCls:f.iconcls,
                        content: a,
                        selected: false
                    });
                })
            }
        })

    })
    function addTabs(title,iconcls,url) {
        //判断--
        var v = $("#tt").tabs("exists",title)
        if(v){
            //存在即打开
            $("#tt").tabs("select",title)
        }else{
            //不存在添加选项卡
            $("#tt").tabs("add",{
                title:title,
                iconCls:iconcls,
                href:"${pageContext.request.contextPath}/"+url,
                closable:true
            })
        }

    }
</script>

</head>
<body class="easyui-layout">
    <shiro:authenticated>
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
        <%--${sessionScope.admin.name}--%>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 500px;float:right;padding-top:15px">
            欢迎您:<shiro:principal></shiro:principal> &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/logout" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
            <shiro:hasPermission name="admin:delete">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">删除管理员</a>
            </shiro:hasPermission>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">查看管理员</a>
        </div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   

    <%-- 导航模块 --%>
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
        <div id="aa" class="easyui-accordion" data-options="fit:true">

        </div>
    </div>


    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>
    </shiro:authenticated>
    <shiro:notAuthenticated>
        <a href="${pageContext.request.contextPath}/login.jsp">您还未登录，请登录</a>
    </shiro:notAuthenticated>
</body> 
</html>