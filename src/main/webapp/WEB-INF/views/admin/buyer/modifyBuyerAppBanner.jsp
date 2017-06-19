<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/admin/buyer/modifyBuyerAppBanner.do" var="modifyBuyerAppBannerUrl"/>
<form:form method="post" modelAttribute="buyerAppBanner" enctype="multipart/form-data" name="f" id="f">
	<form:input type="hidden" path="adId"/>
	<h3>修改广告</h3><br> 
	广告名称:
	<form:input type="text" path="adName" placeholder="请输入广告名称"/><br>
	
	<!-- 广告图 -->
	<form:input type="hidden" path="adImage"/>
	<div class="row-block-image clear">
		<table class="row-table-image">
		<tr>
			<td class="row-title-image"><span>广告图:</span></td>
			<td class="row-content-image">
				<div>
			        <div id="adImages"></div> <ul class="addImage"><li><a class="image-add">上传图片</a></li></ul>
			    </div>
		    </td>
		</tr>
		</table>
	</div>

	排序编号:
	<form:input type="number" path="sort" placeholder="请输入排序编号"/><br>
	
	状态:
	<form:radiobutton path="status" value="1"/>启用
	<form:radiobutton path="status" value="0"/>停用
	
	<div class="btn btn-default" onclick="modifyBuyerAppBanner()">修改</div>
</form:form>

<script type="text/javascript">

$(function() {
	var imageUploadController =  new ImageMultiUpload({_groupId: 'adImages', _max: 1})
	imageUploadController.creates('${buyerAppBanner.adImage}');
	$('.addImage').click(function()
	{
		imageUploadController.create('');
	});
});

function modifyBuyerAppBanner() {
	var params = {},
		adNameInput = $('[name=adName]'), 
		sortInput = $('[name=sort]');
	params.adName = $.trim(adNameInput.val());
	if (!params.adName) {
		alert('请输入广告名称');
		adNameInput.select();
		return;
	}
	params.sort = $.trim(sortInput.val());
	if (params.sort < 0) {
		alert('排序编号必须输入0~255范围数值');
		sortInput.select();
		return;
	}
	var data = new FormData($('form')[0]);
    $.ajax({
        url: '${modifyBuyerAppBannerUrl}?${_csrf.parameterName}=${_csrf.token}',
        type: 'POST',
        dataType: "json",
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        success: function(result) {
        	if (result.message) {
                alert(result.message);
            }
            if (result.code == 0) {
                location.replace('/admin/buyer/buyerAppBannerList.do');
            }
        },
        error: function() {
        	alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>