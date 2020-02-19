<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="always" name="referrer">
<title>百度一下，你就知道</title>

<script type="text/javascript">

	$(function(){
        var desc = $("#kw").val();
        console.log(desc);
	    $("#su").click(function(){
                $("#form1").form("submit",{
                    url:"${pageContext.request.contextPath}/banner/query",
                    onSubmit:function(){
                        return true;
                    },
                    success:function (res) {
                        $.each(res,function (index, bann) {
                            console.log(bann);

                        })
                    }
                })
        })
	
	})
</script>
<body>
	<div id="head_wrapper" class="s-isindex-wrap head_wrapper s-title-img ">
		<div id="s_fm" class="s_form">
			<div class="s_form_wrapper" id="s_form_wrapper">
				<form id="form1" method="post" class="fm">
					<input type="text" class="s_ipt" name="keyWords" id="kw" maxlength="100" autocomplete="off">
					<input type="submit" value="搜索一下" id="su" class="btn self-btn bg s_btn">
				</form>
				
			</div>
		</div>
	</div>
	
	
	<hr>
	<div id="ff"></div>
	
</body>
</html>