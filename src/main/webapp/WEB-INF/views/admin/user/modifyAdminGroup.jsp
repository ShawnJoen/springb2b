<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/user/modifyAdminGroup.do" var="modifyAdminGroupUrl"/>
<form:form method="post" modelAttribute="adminGroup" action="${modifyAdminGroupUrl}" name="f" id="f">
<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
<h3>修改管理组信息</h3><br>
编号:
${adminGroup.groupId}<form:input type="hidden" path="groupId"/><br>
管理组名称:
<form:input type="text" path="groupName" placeholder="请输入管理组名称"/><br>
<c:choose>
	<c:when test="${adminGroup.status == 0}">
		停用
	</c:when>
	<c:otherwise>
		正常
	</c:otherwise>
</c:choose> 
<br>


<h3>使用权限列表</h3><br>


<style>
fieldset {border:solid 1px #999;border-radius:4px;width:80%;font-size:14px;}
fieldset legend {padding: 0 5px;width:auto;border:none;margin:0;font-size:14px;}
fieldset div.actions {width:96%;margin:5px 5px;}
fieldset div label{display:inline-block;margin-right:15px;}
</style>



<c:forEach items="${adminGroup.adminRoles}" var="adminRoleDeep1">
<fieldset>
<legend>
	<label><input type="checkbox" name="roleCode[]" value="${adminRoleDeep1.roleCode}" alt="${adminRoleDeep1.pageTree}" onclick="checkRoleCode(this)"
			<c:if test="${adminGroup.adminRoleAccess.contains(adminRoleDeep1.roleCode)}">
				checked
			</c:if>
			/><span>${adminRoleDeep1.pageName}</span></label>
</legend>
	<c:forEach items="${adminRoleDeep1.adminRoles}" var="adminRoleDeep2">
	<div class="actions">
		<fieldset>
		<legend>
			<label><input type="checkbox" name="roleCode[]" value="${adminRoleDeep2.roleCode}" class="${adminRoleDeep1.pageTree}"  onclick="checkRoleCode(this)" 
			alt="${adminRoleDeep2.pageTree}" <c:if test="${adminGroup.adminRoleAccess.contains(adminRoleDeep2.roleCode)}">
					checked
				</c:if>
				/><span>${adminRoleDeep2.pageName}</span></label>
		</legend>
			<div class="actions">
				<c:forEach items="${adminRoleDeep2.adminRoles}" var="adminRoleDeep3">
				<label><input type="checkbox" name="roleCode[]" value="${adminRoleDeep3.roleCode}" class="${adminRoleDeep1.pageTree} ${adminRoleDeep2.pageTree}"
				<c:if test="${adminGroup.adminRoleAccess.contains(adminRoleDeep3.roleCode)}">
					checked
				</c:if>
				/>
				<span>${adminRoleDeep3.pageName}</span></label>
				</c:forEach>
			</div>
		</fieldset>
	</div>
	</c:forEach>
</fieldset>
</c:forEach>



<br>
<div class="btn btn-default" onclick="modifyAdminGroup()">修改</div>
</form:form>

<script type="text/javascript">

function checkRoleCode(_this) {
	var roleCode = $(_this).prop('alt'); $('.' + roleCode).prop('checked', _this.checked);
}

function modifyAdminGroup() {
	var params = {},
		groupNameInput = $('[name=groupName]');
	params.groupName = $.trim(groupNameInput.val());
	if (!params.groupName) {
		alert('管理组名称不能为空');
		groupNameInput.select();
		return;
	}
    $('[name=f]').ajaxSubmit({
        url: '${modifyAdminGroupUrl}',
        type: "POST",
        dataType: "json",
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