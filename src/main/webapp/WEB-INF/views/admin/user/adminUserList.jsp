<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/base_head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<c:url value="/admin/user/adminUserList.do" var="adminUserListUrl"/>
	<form:form method="get" modelAttribute="adminUser" action="${adminUserListUrl}" name="form">
		<input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${pageInfo.pageSize}"/>
		<form:input type="text" path="keywords" style="width:160px" placeholder="请输入要搜索的账号"/>
		<input type="submit" id="searchButton" value="검색"/>
	</form:form>
</div>

<h3>管理员用户列表</h3><br>

<table >
<tr>
	<td width="100">账号</td>
	<td width="100">管理组</td>
	<td width="120">联系电话</td>
	<td width="120">联系名称</td>
	<td width="100">状态</td>
	<td width="100">操作</td>
</tr>
<tbody id="content">
<c:forEach items="${pageInfo.list}" var="adminUser">
<tr>
	<td>${adminUser.username}</td>
	<td>${adminUser.groupName}</td>
	<td>${adminUser.contactMobile}</td>
	<td>${adminUser.contactName}</td>
	<td>
		<c:choose>
			<c:when test="${adminUser.status == 0}">
				停用
			</c:when>
			<c:otherwise>
				正常
			</c:otherwise>
		</c:choose> 
		<c:if test="${adminUser.isDel > 0}">已删除</c:if>
	</td>
	<td><a href="/admin/user/modifyAdminUser.do?username=${adminUser.username}">编辑</a>/<a onclick="deleteAdminUser('${adminUser.username}')">删除</a></td>
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
function deleteAdminUser(username) {
	
	if (confirm('确定要删除此管理账号吗？')) {
		
		var params = {};
		params.username = username;
		params.${_csrf.parameterName} = '${_csrf.token}';
	    $.ajax({
	        url: '/admin/user/deleteAdminUser.do',
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