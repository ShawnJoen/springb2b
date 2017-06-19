<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/user/createAdminUser.do" var="createUserUrl"/>
<form:form method="post" modelAttribute="adminUser" action="${createUserUrl}" name="f" id="f">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<h3>创建管理员</h3><br>
	아이디:
	<form:input type="text" path="username" placeholder="아이디를 입력하세요."/><br>
	비밀번호:
	<form:input type="text" path="password" disableFromBinding="false" placeholder="비밀번호를 입력하세요."/><br>
	연락전화번호:
	<form:input type="text" path="contactMobile" placeholder="연락전화번호를 입력하세요."/><br>
	연락처이름:
	<form:input type="text" path="contactName" placeholder="연락처이을 입력하세요."/><br>
	
	<select name="groupId">
		<option value="0">选择管理组</option>
	    <c:forEach items="${adminGroup}" var="value">
	        <option value="${value.groupId}">${value.groupName}</option>
	    </c:forEach>
	</select>
	
	<div class="btn btn-default" onclick="createUser()">创建</div>

</form:form>

<script type="text/javascript">
function createUser() {
	var params = {},
		usernameInput = $('[name=username]'), 
		passwordInput = $('[name=password]'), 
		contactMobileInput = $('[name=contactMobile]'), 
		contactNameInput = $('[name=contactName]'), 
		groupIdInput = $('[name=groupId]');
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
	params.contactMobile = $.trim(contactMobileInput.val()); 
	if (!params.contactMobile) {
		alert('请输入联系电话');
		passwordInput.select();
		return;
	}
	params.contactName = $.trim(contactNameInput.val()); 
	if (!params.contactName) {
		alert('请输入联系人名称');
		passwordInput.select();
		return;
	}
	params.groupId = $.trim(groupIdInput.val()); 
	if (!params.groupId) {
		alert('请选择管理组');
		groupIdInput.select();
		return;
	}
	params.${_csrf.parameterName} = '${_csrf.token}';
    $.ajax({
        url: '${createUserUrl}',
        type: "POST",
        dataType: "json",
        data: params,
        success:function(result) {

            if (result.message) {
                alert(result.message);
            }
            if (result.code == 0) {
                location.replace('/admin/user/login.do');
            }
        },
        error: function() {
            alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>