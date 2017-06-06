<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/login_head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/user/modifyAdminGroup.do" var="modifyAdminGroupUrl"/>
<form:form method="post" modelAttribute="adminGroup" action="${modifyAdminGroupUrl}" name="f" id="f">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<h3>修改管理组信息</h3><br>
	编号:
	${adminGroup.getGroupId()}<form:input type="hidden" path="groupId"/><br>
	管理组名称:
	<form:input type="text" path="groupName" placeholder="请输入管理组名称"/><br>
	<c:choose>
		<c:when test="${adminGroup.getStatus() == 0}">
			停用
		</c:when>
		<c:otherwise>
			正常
		</c:otherwise>
	</c:choose> 
	<br>
	<div class="btn btn-default" onclick="modifyAdminGroup()">修改</div>

</form:form>

<script type="text/javascript">
function modifyAdminGroup() {
	var params = {},
		groupNameInput = $('[name=groupName]');
	params.groupName = $.trim(groupNameInput.val());
	if (!params.groupName) {
		alert('管理组名称不能为空');
		groupNameInput.select();
		return;
	}
	params.groupId = $('[name=groupId]').val();
	params.${_csrf.parameterName} = '${_csrf.token}';
    $.ajax({
        url: '${modifyAdminGroupUrl}',
        type: "POST",
        dataType: "json",
        data: params,
        success:function(result) {

            if (result.message) {
                alert(result.message);
            }
            if (result.code == 0) {
                location.replace('/admin/user/adminGroupList.do');
            }
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/base_tail.htm"%>