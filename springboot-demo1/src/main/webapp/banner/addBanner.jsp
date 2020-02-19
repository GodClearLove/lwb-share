<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<script type="text/javascript">
    $(function(){
        //初始化各组件
        $("#addFormTitle").validatebox({
            required:true,
        })
        $("#addFormPubDate").datebox({
            editable:false,
        })
        $("#addFormDescription").validatebox({
            required:true,
        })

        //单价确认按钮提交--
        $("#addFormOkBtn").linkbutton({
            iconCls:"icon-ok",
            //单击按钮
            onClick:function(){
                $("#addForm").form("submit",{
                    //跳转地址资源
                    url:"${pageContext.request.contextPath}/banner/addBanner",
                    //表单验证---调用form的validate方法
                    onSubmit:function(){
                        return $("#addForm").form("validate");
                    },
                    success:function(){
                        //对话框关闭
                        $("#add").dialog("close");
                        //右下角提示成功
                        $.messager.show({
                            title:"温馨提示",
                            msg:"添加成功"
                        });
                        //刷新页面--主页面
                        $("#dg").datagrid("load");
                    }
                });

            }
        });
    })
</script>


<form id="addForm" method="post" enctype="multipart/form-data">
    名称：<input id="addFormTitle" name="title"/><br/>
    时间：<input id="addFormPubDate" name="pubDate"/><br/>
    描述：<input id="addFormDescription" name="description"/><br/>
    图片：<input type="file" name="file"/><br/>
    <a id="addFormOkBtn">确认</a>
</form>