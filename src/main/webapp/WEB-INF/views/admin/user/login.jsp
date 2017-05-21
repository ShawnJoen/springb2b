<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/login_head.jsp"%>

<form method="post" enctype="multipart/form-data" name="form1" id="form1">
<h3>登入页</h3>
<input type="text" name="login_account"><br>
<input type="password" name="pw"><br>
<div class="btn btn-default" onclick="login()">登 入</div>
</form>

<script type="text/javascript">
function login() {
	var params = {},
		login_input = $('[name=login_account]'), 
		pw_input = $('[name=pw]');
	
	params.login_account = $.trim(login_input.val());
	if (!params.login_account) {
		alert('请输入账号');
		login_input.select();
		return;
	}
	
	params.pw = $.trim(pw_input.val()); 
	if (!params.pw) {
		alert('请输入密码');
		pw_input.select();
		return;
	}
	
    $("#form1").ajaxSubmit({
        url: '/admin/user/jlogin.do',
        type: "post",
        dataType: "json",
        data: params,
        success:function(result) {
            if (result.msg)
            {
                alert(result.msg);
            }
            if (result.code === 0)
            {
                location.replace('/admin/index.do');
            }
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/base_tail.htm"%>