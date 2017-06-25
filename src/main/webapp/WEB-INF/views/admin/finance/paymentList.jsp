<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<c:url value="/admin/finance/paymentList.do" var="paymentListUrl"/>
	<form:form method="get" modelAttribute="goodsStandard" action="${paymentListUrl}" name="form">
		<input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${pageInfo.pageSize}"/>
	</form:form>
</div>

<h3>支付方式</h3><br>

<table >
<tr>
	<td width="100">序号</td>
	<td width="100">支付CODE</td>
	<td width="100">支付名称</td>
	<td width="100">状态</td>
	<td width="100">操作</td>
</tr>
<tbody id="content">
<c:forEach items="${pageInfo.list}" var="payment" varStatus="status">
<tr>
	<td>${sequence - status.count}</td>
	<td>${payment.paymentCode}</td>
	<td>${payment.paymentName}</td>
	<td>
		<c:choose>
			<c:when test="${payment.paymentStatus == 0}">
				停用
			</c:when>
			<c:when test="${payment.paymentStatus == 1}">
				启用
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose> 
	</td>
	<td><a href="/admin/finance/modifyPayment.do?paymentCode=${payment.paymentCode}">编辑</a></td>
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