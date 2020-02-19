<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function(){
        $("#albumForm").form("load", "${pageContext.request.contextPath}/album/queryOne?id="+id1);
        var v1 = $("#cc").val();
        alert(v1);
        $("#p").prop("src","http://192.168.85.136/"+v1);
    })

</script>


<form id="albumForm" method="post">
    <%--编号：--%><input type="text" name="id" hidden="true" /><br/>
    名称：<input type="text" name="title" readonly=true/><br/>
    章数：<input type="text" name="count" readonly=true/><br/>
    图片：<img id="p" src="#"><br/>
    <input id="cc" type="hidden" name="coverImg" />
    评分：<input type="text" name="score" readonly=true/><br/>
    作者：<input type="text" name="author" readonly=true/><br/>
    播音：<input type="text" name="broadcast" readonly=true/><br/>
    简介：<input type="text" name="brief" readonly=true/><br/>
    出版时间：<input type="text" name="pubDate" readonly=true/><br/>
</form>