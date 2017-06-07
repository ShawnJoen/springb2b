<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/base_head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<c:url value="/admin/user/adminGroupList.do" var="adminGroupListUrl"/>
	<form method="get" action="${adminGroupListUrl}" name="form">
		<input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${pageInfo.pageSize}"/>
	</form>
</div>

<h3>管理组列表</h3><br>

<table >
<tr>
	<td width="100">编号</td>
	<td width="100">管理组名称</td>
	<td width="120">状态</td>
</tr>
<tbody id="content">
<c:forEach items="${pageInfo.list}" var="adminGroup">
<tr>
	<td>${adminGroup.groupId}</td>
	<td>${adminGroup.groupName}</td>
	<td>
		<c:choose>
			<c:when test="${adminGroup.status == 0}">
				停用
			</c:when>
			<c:otherwise>
				正常
			</c:otherwise>
		</c:choose> 
	</td>
	<td><a href="/admin/user/modifyAdminGroup.do?groupId=${adminGroup.groupId}">编辑</a>/<a onclick="deleteAdminGroup('${adminGroup.groupId}')">删除</a></td>
</tr>
</c:forEach>
</tbody>
</table>


<div id="page-selection"></div>
<script type="text/javascript">
$('#page-selection').bootpag({
	page: "${pageInfo.pageNum}",
	total: "${pageInfo.pages}",
	maxVisible: 6
}).on("page", function(event, pageNum) { 
	$('[name=pageNum]').val(pageNum);
	$('[name=form]').submit();
});
</script>
	
<script type="text/javascript">
function deleteAdminGroup(groupId) {
	
	if (confirm('确定要删除此管理组吗？')) {
		
		var params = {};
		params.groupId = groupId;
		params.${_csrf.parameterName} = '${_csrf.token}';
	    $.ajax({
	        url: '/admin/user/deleteAdminGroup.do',
	        type: "POST",
	        dataType: "json",
	        data: params,
	        success:function(result) {

	            if (result.message) {
	                alert(result.message);
	            }
	            if (result.code == 0) {
	                location.reload();
	            }
	        },
	        error: function() {
	            alert('<s:message code="program_error"/>');
	        }
	    });
	}
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/base_tail.htm"%>