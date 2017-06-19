<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<c:url value="/admin/buyer/buyerAppBannerList.do" var="buyerAppBannerListUrl"/>
	<form method="get" action="${buyerAppBannerListUrl}" name="form">
		<input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${pageInfo.pageSize}"/>
	</form>
</div>

<input type="button" value="创建广告" onclick="location.href='/admin/buyer/createBuyerAppBanner.do';" class="btn btn-default"/><br>
<h3>广告列表</h3><br>

<table >
<tr>
	<td width="100">编号</td>
	<td width="100">广告名称</td>
	<td width="100">广告图</td>
	<td width="120">状态</td>
	<td width="120">创建时间</td>
	<td width="120">操作</td>
</tr>
<tbody id="content">
<c:forEach items="${pageInfo.list}" var="buyerAppBanner">
<tr>
	<td>${buyerAppBanner.adId}</td>
	<td>${buyerAppBanner.adName}</td>
	<td><img src="${buyerAppBanner.adImage}" width="50" height="50"/></td>
	<td>
		<c:choose>
			<c:when test="${buyerAppBanner.status == 0}">
				停用
			</c:when>
			<c:otherwise>
				正常
			</c:otherwise>
		</c:choose> 
	</td>
	<td>${buyerAppBanner.createTime}</td>
	<td><a href="/admin/buyer/modifyBuyerAppBanner.do?adId=${buyerAppBanner.adId}">编辑</a>/<a onclick="deleteBuyerAppBanner('${buyerAppBanner.adId}')">删除</a></td>
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
function deleteBuyerAppBanner(adId) {
	
	if (confirm('确定要删除此广告吗？')) {
		
		var params = {};
		params.adId = adId;
		params.${_csrf.parameterName} = '${_csrf.token}';
	    $.ajax({
	        url: '/admin/buyer/deleteBuyerAppBanner.do',
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

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>