<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/loginHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/j_spring_security_check" var="loginUrl"/>
<form:form method="post" action="${loginUrl}" name="f" id="f">
<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>

	<c:if test="${param.error != null}">
	login error<br>
	</c:if>

	<h3>登入页</h3>
	<input type="text" name="username" placeholder="아이디를 입력하세요."/><br>
	<input type="password" name="password" placeholder="비밀번호를 입력하세요."/><br>
	<div class="btn btn-default" onclick="login()">登 入</div>
	<a class="btn btn-default" href="./modifyPasswordNotLoggedIn.do">密码修改</a>

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
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>