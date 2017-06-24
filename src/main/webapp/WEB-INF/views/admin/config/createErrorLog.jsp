<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/config/createErrorLog.do" var="createErrorLogUrl"/>
<form:form method="post" modelAttribute="errorLog" action="${createErrorLogUrl}" enctype="multipart/form-data" name="f" id="f">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

	<h3>插入错误日志</h3><br>
	用户编号:
	<form:input type="number" path="userId" placeholder="请输入用户编号" maxlength="10"/><br>
	版本:
	<form:input type="text" path="appVersion" placeholder="请输入本" maxlength="10"/><br>
	错误信息:
	<form:input type="text" path="error" placeholder="请输入箱入数" maxlength="1000"/><br>
	Device类型:
	<form:input type="text" path="device" placeholder="请输入Device类型" maxlength="10"/><br>
	项目名称:
	<form:input type="text" path="projectName" maxlength="10" value="StringB2B"/><br>
				
	<div class="btn btn-default" onclick="createErrorLog()">创建</div>

</form:form>

<script type="text/javascript">
function createErrorLog() {
	var data = new FormData($('form')[0]);
    $.ajax({
        url: '${createErrorLogUrl}?${_csrf.parameterName}=${_csrf.token}',
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
                location.replace('/admin/config/errorLogList.do');
            }
        },
        error: function() {
        	alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>