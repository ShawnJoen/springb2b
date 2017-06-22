<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/goods/modifyGoodsStandard.do" var="modifyGoodsStandardUrl"/>
<form:form method="post" modelAttribute="goodsStandard" action="${modifyGoodsStandardUrl}" enctype="multipart/form-data" name="f" id="f">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<!-- 商品分类暂时0 -->
	<input type="hidden"  name="cateId"   value="0"/>
	<form:input type="hidden" path="goodsStandardId"/>
	
	<h3>创建商品库商品</h3><br>
	商品名称:
	<form:input type="text" path="goodsName" placeholder="请输入商品名称" maxlength="50"/><br>
	商品规格:
	<form:input type="text" path="goodsNorms" placeholder="请输入商品规格" maxlength="20"/><br>
	箱入数:
	<form:input type="number" path="boxNum" placeholder="请输入箱入数"/><br>
	条形码:
	<form:input type="text" path="goodsBarcode" placeholder="请输入条形码" maxlength="30"/><br>
	<!-- 商品图 -->
	<form:input type="hidden" path="goodsImages"/>
	<div class="row-block-image clear">
		<table class="row-table-image">
		<tr>
			<td class="row-title-image"><span>商品图:</span></td>
			<td class="row-content-image">
				<div>
			        <div id="_goodsImages"></div> <ul class="addImage"><li><a class="image-add">上传图片</a></li></ul>
			    </div>
		    </td>
		</tr>
		</table>
	</div>
	<!-- 商品详情-->
	商品详情：
	<form:textarea path="goodsBrief"/> <br>
	<script>
		KindEditor.create('textarea[name="goodsBrief"]', {
	        uploadJson : "" + '/kindeditor/fileUpload?${_csrf.parameterName}=${_csrf.token}',  
	        fileManagerJson : "" + '/kindeditor/fileManager?${_csrf.parameterName}=${_csrf.token}',  
	        allowFileManager : true,
	              	afterBlur: function() {this.sync()},
	        items : ['source', '|', 'undo', 'redo', '|', 'preview', 'cut', 'copy', 'paste',
	                 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	                 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	                 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
	                 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	                 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image','multiimage',
	                 'flash', 'insertfile', 'table', 'hr', 'emoticons', 'pagebreak', 'link', 'unlink']
	   	});
	</script>
	
	状态:
	<form:radiobutton path="goodsStatus" value="1"/>已上架
	<form:radiobutton path="goodsStatus" value="0"/>未上架
				
	<div class="btn btn-default" onclick="modifyGoodsStandard()">修改</div>

</form:form>

<script type="text/javascript">
$(function() {
	var imageUploadController =  new ImageMultiUpload({_groupId: '_goodsImages', _max: 3})
	imageUploadController.creates('${goodsStandard.goodsImages}');
	$('.addImage').click(function()
	{
		imageUploadController.create('');
	});
});

function modifyGoodsStandard() {
	var params = {},
	cateIdInput = $('[name=cateId]'), 
	goodsNameInput = $('[name=goodsName]'), 
	goodsNormsInput = $('[name=goodsNorms]'), 
	boxNumInput = $('[name=boxNum]');
	
	params.cateId = $.trim(cateIdInput.val());
	/*if (!params.cateId) {
		alert('请选择商品分类');
		cateIdInput.select();
		return;
	}*/
	params.goodsName = $.trim(goodsNameInput.val());
	if (!params.goodsName) {
		alert('请输入商品名称');
		goodsNameInput.select();
		return;
	}
	params.goodsNorms = $.trim(goodsNormsInput.val());
	if (!params.goodsNorms) {
		alert('请输入商品规格');
		goodsNormsInput.select();
		return;
	}
	params.boxNum = Number($.trim(boxNumInput.val()));
	if (params.boxNum < 1) {
		alert('请输入箱入数');
		boxNumInput.select();
		return;
	}
    
	var data = new FormData($('form')[0]);
    $.ajax({
        url: '${modifyGoodsStandardUrl}?${_csrf.parameterName}=${_csrf.token}',
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
                location.reload();
            }
        },
        error: function() {
        	alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>