<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var tool = [{
            iconCls: 'icon-add',
            text:"添加",
            handler: function(){
                //弹出添加对话框
                $("#add").dialog("open")
            }
        },'-',{
            iconCls: 'icon-remove',
            text:"删除",
            handler: function(){
                //确认删除吗
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //指定行
                    //$.messager.confirm("提示","确认删除吗",function(fn){
                        //if(fn){
                            /*$.get(
                                "/banner/delBanner",
                                "id="+row.id,
                                function(result){
                                    //成功之后右下提示
                                    $.messager.show({
                                        title:"温馨提示",
                                        msg:"删除成功"
                                    });
                                    //刷新页面--当前页面
                                    $("#dg").datagrid("reload");
                                })*/
                            // 销毁指定的行
                            var index1 = $("#dg").edatagrid("getRowIndex", row);
                            $('#dg').edatagrid('destroyRow', index1.id);
                        //}
                    //});
                } else {
                    alert("请先选中行");
                }
            }
        },'-',{
            iconCls: 'icon-edit',
            text:"修改",
            handler: function(){
                //修改状态选项
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行");
                }

            }
        },'-',{
            iconCls: 'icon-remove',
            text:"导出",
            handler: function(){
                $.messager.confirm("提示","确认导出吗？",function (fn) {
                    if(fn){
                        $.ajax({
                            type:"POST",
                            url:"${pageContext.request.contextPath}/banner/export",
                            dataType:"json",
                            success:function (result) {
                                $.messager.show({
                                    title:"系统提示",
                                    msg:"导出成功"
                                })
                            }
                        })
                    }
                });

            }
        },'-',{
            iconCls: 'icon-add',
            text:"导入",
            handler: function(){
                $.messager.confirm("提示","确认导入吗？",function (fn) {
                    if(fn){
                        $.ajax({
                            type:"POST",
                            url:"${pageContext.request.contextPath}/banner/importance",
                            dataType:"json",
                            success:function (result) {
                                $.messager.show({
                                    title:"系统提示",
                                    msg:"导入成功"
                                });
                                $("#dg").datagrid("load");
                            }
                        })
                    }
                });

            }
        },'-',{
            iconCls: 'icon-search',
            text:"搜索",
            handler: function(){
               //打开搜索对话框
                $("#query").dialog("open")
            }
        },'-',{
            iconCls: 'icon-save',
            text:"保存",
            handler: function(){
                //发异步请求确认修改
                $("#dg").edatagrid("saveRow");
                //刷新
                $.messager.show({
                    title:"温馨提示",
                    msg:"修改成功"
                });
                $("#dg").datagrid("reload");

            }
        }]

        $('#dg').edatagrid({
            method:"POST",
            destroyUrl:"${pageContext.request.contextPath}/banner/delBanner",
            updateUrl:"${pageContext.request.contextPath}/banner/updateBanner",
            url:"${pageContext.request.contextPath}/banner/queryAllByPage",
            columns:[[
                {field:'id',title:'编号',width:100},
                {field:'title',title:'名称',width:100},
                {field:'imgPath',title:'图片',width:100,align:'right'},
                {field:'status',title:'状态',width:100,editor: {
                        type: "text",
                        options: {required:true}
                    }
                },
                {field:'pubDate',title:'上传时间',width:100},
                {field:'description',title:'描述',width:100}
            ]],
            fitColumns:true,
            //fit: true,
            pagination:true,
            pageSize:3,
            pageList:[3,6,9],
            toolbar:tool,
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="http://192.168.85.136/'+rowData.imgPath+'" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });

        //初始化添加对话框
        $("#add").dialog({
            title:"请添加",
            width: 400,
            height: 200,
            closed: true,
            cache: false,
            href: '${pageContext.request.contextPath}/banner/addBanner.jsp',
            modal: true
        })
        //初始化搜索对话框
        $("#query").dialog({
            title:"请搜索",
            width: 500,
            height: 400,
            closed: true,
            cache: false,
            href: '${pageContext.request.contextPath}/banner/query.jsp'
        })
    })
</script>

<table id="dg">

</table>

<div id="add"></div>

<div id="query"></div>