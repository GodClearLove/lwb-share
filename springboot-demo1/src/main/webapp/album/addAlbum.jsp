<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function(){
        $("#albumFormPubDate").datebox({
            editable:false
        })
        $("#albumFormBtn").linkbutton({
            iconCls:"icon-ok",
            onClick:function(){
                $("#albumForm").form("submit",{
                    url:"${pageContext.request.contextPath}/album/addAlbum",
                    onSubmit:function () {
                        return $("#albumForm").form("validate");
                    },
                    success:function () {
                        $("#addAlbum").dialog("close");
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


<form id="albumForm" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="title"/><br/>
    章数：<input type="text"name="count"/><br/>
    图片：<input type="file" name="file" /><br/>
    评分：<input type="text" name="score"/><br/>
    作者：<input type="text" name="author"/><br/>
    播音：<input type="text" name="broadcast"/><br/>
    简介：<input type="text" name="brief"/><br/>
    出版时间：<input id="albumFormPubDate" name="pubDate"/><br/>
    <a id="albumFormBtn">确认</a>
</form>