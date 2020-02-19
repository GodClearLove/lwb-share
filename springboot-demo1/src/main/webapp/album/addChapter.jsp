<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">

    $(function(){
        $("#aaid").val(aid1);
        $("#chapterFormUploadDate").datebox({
            editable:false
        })
        $("#chapterFormBtn").linkbutton({
            iconCls:"icon-ok",
            onClick:function(){
                $("#chapterForm").form("submit",{
                    url:"${pageContext.request.contextPath}/chapter/addChapter",
                    onSubmit:function () {
                        return $("#chapterForm").form("validate");
                    },
                    success:function () {
                        $("#addChapter").dialog("close");
                        //右下角提示成功
                        $.messager.show({
                            title:"温馨提示",
                            msg:"添加成功"
                        });
                        $("#tg").treegrid("load");
                    }

                })
            }
        })
    })
</script>


<form id="chapterForm" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="title"/><br/>
    <%--大小：<input type="text" name="size"/><br/>--%>
    <%--时长：<input type="text" name="duration" /><br/>--%>
    音频：<input type="file" name="file1"/><br/>
    出版时间：<input id="chapterFormUploadDate" name="uploadDate"/><br/>
    <input id="aaid" type="text" name="aid" hidden=true />
    <a id="chapterFormBtn">确认</a>
</form>