<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/loginHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/user/createAdminGroup.do" var="createAdminGroupUrl"/>
<form method="post" action="${createAdminGroupUrl}" name="f" id="f">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<h3>创建管理组</h3><br>
	管理组名称:
	<input type="text" name="groupName" placeholder="请输入管理组名称"/><br>
	
	<div class="btn btn-default" onclick="createAdminGroup()">创建</div>

</form>

<script type="text/javascript">
function createAdminGroup() {
	var params = {},
		groupNameInput = $('[name=groupName]');
	params.groupName = $.trim(groupNameInput.val());
	if (!params.groupName) {
		alert('管理组名称不能为空');
		groupNameInput.select();
		return;
	}
	params.${_csrf.parameterName} = '${_csrf.token}';
    $.ajax({
        url: '${createAdminGroupUrl}',
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
        },
        error: function() {
            alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>