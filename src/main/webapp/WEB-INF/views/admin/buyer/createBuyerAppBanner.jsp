<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>


<c:url value="/admin/buyer/createBuyerAppBanner.do" var="createBuyerAppBannerUrl"/>
<form method="post" enctype="multipart/form-data" name="f" id="f">
	<h3>创建广告</h3><br> 
	广告名称:
	<input type="text" name="adName" placeholder="请输入广告名称"/><br>
	
	<!-- 广告图 -->
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

	<div>排序编号: <input type="number" name="sort" placeholder="请输入排序编号" value="0"/></div>
	
	<div class="btn btn-default" onclick="createBuyerAppBanner()">创建</div>
</form>

<script type="text/javascript">

$(function() {
	var imageUploadController =  new ImageMultiUpload({_groupId: 'adImages', _max: 1})
	imageUploadController.creates('');
	$('.addImage').click(function()
	{
		imageUploadController.create('');
	});
});

function createBuyerAppBanner() {
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
        url: '${createBuyerAppBannerUrl}?${_csrf.parameterName}=${_csrf.token}',
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