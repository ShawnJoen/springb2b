<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/login_head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/j_spring_security_check" var="loginUrl"/>
<form:form method="post" modelAttribute="adminUser" action="${loginUrl}" name="f" id="f">
<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

	<c:if test="${param.error != null}">
	login error<br>
	</c:if>

	<h3>登入页</h3>
	<form:input type="text" path="username" disableFromBinding="false"/><br>
	<form:input type="password" path="password" disableFromBinding="false"/><br>
	<div class="btn btn-default" onclick="login()">登 入</div>
	<a class="btn btn-default" href="./password.do">密码修改</a>

</form:form>

<script type="text/javascript">
function login() {
	var params = {},
		usernameInput = $('[name=username]'), 
		passwordInput = $('[name=password]');
	
	params.username = $.trim(usernameInput.val());
	if (!params.username) {
		alert('请输入账号');
		usernameInput.select();
		return;
	}
	
	params.password = $.trim(passwordInput.val()); 
	if (!params.password) {
		alert('请输入密码');
		passwordInput.select();
		return;
	}
	$('#f').submit();
    /*$("#form1").ajaxSubmit({
        url: './jlogin.do',
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
    });*/
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/base_tail.htm"%>