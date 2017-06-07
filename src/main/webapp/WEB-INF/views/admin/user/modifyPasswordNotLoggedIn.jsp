<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/login_head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/admin/user/modifyPassword.do" var="modifyPasswordUrl"/>
<form:form method="post" modelAttribute="adminUserAuthentication" action="${findPasswordUrl}" name="f" id="f">
<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

	<c:if test="${param.error != null}">
	login error<br>
	</c:if>

	<h3>修改密码</h3>
	<form:input type="text" path="username" placeholder="아이디를 입력하세요."/><br>
	<form:input type="password" path="password" placeholder="비밀번호를 입력하세요."/><br>
	<form:input type="password" path="newPassword" placeholder="새 비밀번호를 입력하세요."/><br>
	<input type="password" name="rePassword" id="rePassword" placeholder="새 비밀번호 확인을 입력하세요."/><br>
	<div class="btn btn-default" onclick="findPassword()">修 改</div>
	<a class="btn btn-default" href="history.back();">返 回</a>

</form:form>

<script type="text/javascript">
function findPassword() {
	var params = {},
		usernameInput = $('[name=username]'), 
		passwordInput = $('[name=password]'), 
		newPasswordInput = $('[name=newPassword]'), 
		rePasswordInput = $('[name=rePassword]');
	
	params.username = $.trim(usernameInput.val());
	if (!params.username) {
		alert('请输入账号');
		usernameInput.select();
		return;
	}
	
	params.password = $.trim(passwordInput.val()); 
	if (!params.password) {
		alert('请输入原始密码');
		passwordInput.select();
		return;
	}
	
	params.newPassword = $.trim(newPasswordInput.val()); 
	if (!params.newPassword) {
		alert('请输入新密码');
		newPasswordInput.select();
		return;
	}
	
	params.rePassword = $.trim(rePasswordInput.val()); 
	if (params.newPassword != params.rePassword) {
		alert('重复密码错误');
		rePasswordInput.select();
		return;
	}
    
	params.${_csrf.parameterName} = '${_csrf.token}';
    $.ajax({
        url: '${modifyPasswordUrl}',
        type: "POST",
        dataType: "json",
        data: params,
        success:function(result) {

            if (result.message) {
                alert(result.message);
            }
            if (result.code == 0) {
                location.replace('/admin/index.do');
            }
        },
        error: function() {
            alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/base_tail.htm"%>