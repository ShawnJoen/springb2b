<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/user/modifyAdminUser.do" var="modifyUserUrl"/>
<form:form method="post" modelAttribute="adminUser" action="${modifyUserUrl}" name="f" id="f">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<h3>修改管理员信息</h3><br>
	아이디:
	${adminUser.getUsername()}<form:input type="hidden" path="username" placeholder="아이디를 입력하세요."/><br>
	비밀번호:
	<input type="text" name="password" placeholder="비밀번호를 입력하세요."/><br>
	연락전화번호:
	<form:input type="text" path="contactMobile" placeholder="연락전화번호를 입력하세요."/><br>
	연락처이름:
	<form:input type="text" path="contactName" placeholder="연락처이을 입력하세요."/><br>
	
	<select name="groupId">
		<option value="0">选择管理组</option>
	    <c:forEach items="${adminGroup}" var="value">
		    <c:choose>
				<c:when test="${value.groupId eq adminUser.groupId}">
					<option value="${value.groupId}" selected="true">${value.groupName}</option>
				</c:when>
				<c:otherwise>
					<option value="${value.groupId}">${value.groupName}</option>
				</c:otherwise>
			</c:choose> 
	        
	    </c:forEach>
	</select>
	
	<div class="btn btn-default" onclick="createUser()">修改</div>

</form:form>

<script type="text/javascript">
function createUser() {
	var password, params = {},
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
	password = $.trim(passwordInput.val()); 
	if (password) {
		params.password = password;
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
        url: '${modifyUserUrl}',
        type: "POST",
        dataType: "json",
        data: params,
        success:function(result) {

            if (result.message) {
                alert(result.message);
            }
            if (result.code == 0) {
                location.replace('/admin/user/adminUserList.do');
            }
        },
        error: function() {
            alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>