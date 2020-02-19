<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
        var id1;
        var aid1;
        var tool = [{
            iconCls: 'icon-tip',
            text:"专辑详情",
            handler: function(){
                //弹出详情对话框--需选择专辑栏
                var row = $("#tg").treegrid("getSelected");
                 id1=row.id;
                //选择专辑
                /*if(row.children!=null){
                    $("#albumMsg").dialog("open")
                }else{
                    alert("请选择专辑")
                }*/
                if (row != null) {
                    if (row.children != null) {
                        //打开dialog
                        $("#album_dialog").dialog("open");
                        $("#album_ff").form("load", row);
                        $("#album_img").prop("src", "http://192.168.85.136/"+row.coverImg);
                    }else{
                        alert("请选择专辑");
                    }
                }
            }
        },'-',{
            iconCls: 'icon-add',
            text:"添加专辑",
            handler: function(){
                $("#addAlbum").dialog("open")
            }
        },'-',{
            iconCls: 'icon-remove',
            text:"导出",
            handler: function(){
                $.messager.confirm("提示","确认导出吗？",function (fn) {
                    if(fn){
                        $.ajax({
                            type:"POST",
                            url:"${pageContext.request.contextPath}/album/export",
                            dataType:"json",
                            success:function (result) {
                                if(result==""){
                                    $.messager.show({
                                        title:"系统提示",
                                        msg:"导出成功"
                                    })
                                }

                            }
                        })
                    }
                });
            }
        },'-',{
            iconCls: 'icon-save',
            text:"添加章节",
            handler: function(){
                //需选择对应的专辑
                var row = $("#tg").treegrid("getSelected");
                if(row.children!=null){
                    aid1=row.id;
                    $("#addChapter").dialog("open");
                }else{
                    alert("请选择专辑")
                }
            }
        },'-',{
            iconCls: 'icon-back',
            text:"下载音频",
            handler: function(){
                var row = $("#tg").treegrid("getSelected");
                if(row.children==null){
                    //下载--row.url
                    location.href="${pageContext.request.contextPath}/chapter/downLoad?url="+row.url+"&title="+row.title
                }else{
                    alert("请选择音频");
                }

            }
        }]
        $(function () {
        $('#tg').treegrid({
            url:'${pageContext.request.contextPath}/album/queryAllByPage',
            idField:'id',
            treeField:'title',
            columns:[[
                {field:'title',title:'名称',width:180},
                {field:'url',title:'下载路径',width:60},
                {field:'size',title:'大小',width:80},
                {field:'duration',title:'时长',width:80},
                {field:'uploadDate',title:'更新时间',width:80}
            ]],
            fitColumns:true,
            fit:true,
            pagination:true,
            pageSize:20,
            pageList:[20,25,30],
            toolbar:tool,
            onDblClickRow:function(row){
                if(row.children==null){
                    $("#music").dialog({
                        title:"正在播放"+row.url,
                        iconCls:"icon-large-chart",
                        width:320,
                        height:100
                    });
                    $("#musicAudio").prop("src","http://192.168.85.136/"+row.url);
                }
            }
        });
        $("#albumMsg").dialog({
            title:"专辑详情",
            width: 400,
            height: 300,
            closed: true,
            cache: false,
            href: '${pageContext.request.contextPath}/album/albumMsg.jsp',
            modal: true
        })
        //添加专辑对话框
            $("#addAlbum").dialog({
                title:"请添加专辑",
                width: 400,
                height:300,
                closed: true,
                cache: false,
                href: '${pageContext.request.contextPath}/album/addAlbum.jsp',
                modal: true
            })

            $("#addChapter").dialog({
                title:"请添加章节",
                width: 400,
                height:300,
                closed: true,
                cache: false,
                href: '${pageContext.request.contextPath}/album/addChapter.jsp',
                modal: true
            })

    })
</script>

<table id="tg">

</table>

<div id="albumMsg"></div>

<div id="addAlbum"></div>

<div id="addChapter"></div>
<div id="music">
    <audio id="musicAudio" controls="controls"></audio>
</div>

<div id="album_dialog" class="easyui-dialog" title="专辑详情" style="width:400px;height:350px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <form id="album_ff" method="post">
        <div>
            专辑名称：<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            章节数量：<input class="easyui-validatebox" type="text" name="count" data-options="required:true"/>
        </div>
        <div>
            评分：<input class="easyui-validatebox" type="text" name="score" data-options="required:true"/>
        </div>
        <div>
            播音：<input class="easyui-validatebox" type="text" name="broadcast" data-options="required:true"/>
        </div>
        <div>
            简介：<input class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <div>
            更新日期：<input class="easyui-validatebox" type="text" name="pubDate" data-options="required:true"/>
        </div>
        <div>
            <img src="#" id="album_img">
        </div>
    </form>

</div>