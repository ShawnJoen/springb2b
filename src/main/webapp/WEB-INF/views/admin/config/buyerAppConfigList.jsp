<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
<h3>买家APP配置</h3><br>

<form name="f" id="f">
<table >
<c:forEach items="${siteConfigs}" var="siteConfig">
<tr>
	<td>${siteConfig.configName}</td>
	<td>
		<input type="hidden" name="configCode[]" value="${siteConfig.configCode}" class="configCode"/> 
		<input type="hidden" name="editType[]" value="${siteConfig.editType}" class="editType"/> 
		<c:choose>
			<c:when test="${siteConfig.editType eq 'editor'}">
				<textarea name="configVal[]" class="configVal">${siteConfig.configVal}</textarea>
				<script>
				KindEditor.create('textarea[name="configVal[]"]', {
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
			</c:when>
			<c:when test="${siteConfig.editType eq 'textarea'}">
				<textarea name="configVal[]" class="configVal">${siteConfig.configVal}</textarea>
			</c:when>
			<c:otherwise>
				<input type="text" name="configVal[]" value="${siteConfig.configVal}" class="configVal"/>
			</c:otherwise>
		</c:choose> 
	</td>
</tr>
</c:forEach>
</table>
</form>

<div class="btn btn-default" onclick="modifySiteConfig()">修改</div>

<script type="text/javascript">
function modifySiteConfig() {

	var configCount = $(".configCode").length;
	for (var i = 0; i < configCount; i++) {

		var configVal = $.trim($(".configVal")[i].value);
		if (!$(".configCode")[i].value || configVal === '') {
			
			alert('配置内容不能为空');
			return;
		}
	}
	
	var data = new FormData($('form')[0]);
    $.ajax({
        url: '/admin/config/modifySiteConfig.do?${_csrf.parameterName}=${_csrf.token}',
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
                location.replace('/admin/config/buyerAppConfigList.do');
            }
        },
        error: function() {
        	alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>