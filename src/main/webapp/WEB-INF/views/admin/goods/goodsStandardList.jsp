<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<c:url value="/admin/goods/goodsStandardList.do" var="goodsStandardListUrl"/>
	<form:form method="get" modelAttribute="goodsStandard" action="${goodsStandardListUrl}" name="form">
		<input type="hidden" name="pageNum" id="pageNum" value="${pageInfo.pageNum}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${pageInfo.pageSize}"/>
		<form:input type="text" path="goodsStandardId" style="width:160px" placeholder="请输入商品编号" maxlength="8"/>
		<form:input type="text" path="goodsName" style="width:160px" placeholder="请输入商品名称" maxlength="50"/>
		
		<form:input type="text" path="startCreateDate" style="width:160px" placeholder="选择创建开始日期" maxlength="10" class="form_datetime" readonly="true"/>
		<form:input type="text" path="endCreateDate" style="width:160px" placeholder="选择创建结束日期" maxlength="10" class="form_datetime" readonly="true"/>
		
		<input type="submit" id="searchButton" value="搜索"/>
	</form:form>
</div>

<input type="button" value="创建商品库商品" onclick="location.href='/admin/goods/createGoodsStandard.do';" class="btn btn-default"/><br>
<h3>管理员用户列表</h3><br>

<table >
<tr>
	<td width="100">序号</td>
	<td width="100">商品编号</td>
	<td width="100">商品图</td>
	<td width="120">商品名称</td>
	<td width="120">商品规格</td>
	<td width="100">商品分类</td>
	<td width="120">箱入数</td>
	<td width="100">状态</td>
	<td width="100">操作</td>
</tr>
<tbody id="content">
<c:forEach items="${pageInfo.list}" var="goodsStandard" varStatus="status">
<tr>
	<td>${sequence - status.count}</td>
	<td>${goodsStandard.goodsStandardId}</td>
	<td><img src="${goodsStandard.goodsImages.split("[\\,]+")[0]}" width="80" height="80"/></td>
	<td>${goodsStandard.goodsName}</td>
	<td>${goodsStandard.goodsNorms}</td>
	<td>${goodsStandard.cateId}</td>
	<td>${goodsStandard.boxNum}</td>
	<td>
		<c:choose>
			<c:when test="${goodsStandard.goodsStatus == 0}">
				未上架
			</c:when>
			<c:when test="${goodsStandard.goodsStatus == 1}">
				已上架
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose> 
		<c:if test="${goodsStandard.isDel > 0}">已删除</c:if>
	</td>
	<td><a href="/admin/goods/modifyGoodsStandard.do?goodsStandardId=${goodsStandard.goodsStandardId}">编辑</a>/<a onclick="deleteGoodsStandard('${goodsStandard.goodsStandardId}')">删除</a></td>
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

function deleteGoodsStandard(goodsStandardId) {
	
	if (confirm('确定要删除此商品吗？')) {
		
		var params = {};
		params.goodsStandardId = goodsStandardId;
	    $.ajax({
	        url: '/admin/goods/deleteGoodsStandard.do?${_csrf.parameterName}=${_csrf.token}',
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
	            alert('<m:message code="program_error"/>');
	        }
	    });
	}
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>