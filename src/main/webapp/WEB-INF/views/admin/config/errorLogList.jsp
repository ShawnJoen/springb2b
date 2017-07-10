<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<c:url value="/admin/config/errorLogList.do" var="errorLogListUrl"/>
	<form:form method="get" modelAttribute="errorLog" action="${errorLogListUrl}" name="form">
		<input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${pageInfo.pageSize}"/>
		
		<form:input type="text" path="startCreateDate" style="width:160px" placeholder="选择创建开始日期" maxlength="10" class="form_datetime" readonly="true"/>
		<form:input type="text" path="endCreateDate" style="width:160px" placeholder="选择创建结束日期" maxlength="10" class="form_datetime" readonly="true"/>
		
		<input type="submit" id="searchButton" value="搜索"/>
	</form:form>
</div>

<input type="button" value="插入错误日志" onclick="location.href='/admin/config/createErrorLog.do';" class="btn btn-default"/><br>
<h3>错误日志列表</h3><br>

<table >
<tr>
	<td width="100">序号</td>
	<td width="100">用户编号</td>
	<td width="100">用户类型</td>
	<td width="100">设备类型</td>
	<td width="100">设备ID</td>
	<td width="100">品牌</td>
	<td width="100">系统版本</td>
	<td width="100">APP版本</td>
	<td width="100">操作</td>
</tr>
<tbody id="content">
<c:forEach items="${pageInfo.list}" var="errorLog" varStatus="status">
<tr>
	<td>${sequence - status.count}</td>
	<td>${errorLog.userId}</td>
	<td>${errorLog.userType}</td>
	<td>${errorLog.deviceType}</td>
	<td>${errorLog.deviceId}</td>
	<td>${errorLog.brandName}</td>
	<td>${errorLog.osVersion}</td>
	<td>${errorLog.appVersion}</td>
	<td><a href="/admin/config/viewErrorLog.do?id=${errorLog.id}">查看</a></td>
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

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>