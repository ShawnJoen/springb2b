<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<c:url value="/admin/user/operationRecordList.do" var="operationRecordListUrl"/>
	<form:form method="get" modelAttribute="operationRecord" action="${operationRecordListUrl}" name="form">
		<input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${pageInfo.pageSize}"/>
		<form:input type="text" path="keywords" style="width:160px" placeholder="请输入要搜索的账号"/>
		<input type="submit" id="searchButton" value="검색"/>
	</form:form>
</div>

<h3>操作记录列表</h3><br>

<table >
<tr>
	<td width="100">编号</td>
	<td width="100">管理账号</td>
	<td width="200">操作内容</td>
	<td width="140">操作时间</td>
</tr>
<tbody id="content">
<c:forEach items="${pageInfo.list}" var="operationRecord">
<tr>
	<td>${operationRecord.id}</td>
	<td>${operationRecord.username}</td>
	<td>${operationRecord.content}</td>
	<td>${operationRecord.createTime}</td>
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