<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/admin/finance/modifyPayment.do" var="modifyPaymentUrl"/>
<form:form method="post" modelAttribute="payment" action="${modifyPaymentUrl}" enctype="multipart/form-data" name="f" id="f">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
	<h3>修改支付方式</h3><br>
	支付CODE:
	${payment.paymentCode} <form:input type="hidden" path="paymentCode"/><br>
	支付名称:
	<form:input type="text" path="paymentName" placeholder="请输入支付名称" maxlength="10"/><br>
	支付内容:
	<form:input type="text" path="paymentConfig" placeholder="请输入支付内容" maxlength="1000"/><br>
	状态:
	<form:radiobutton path="paymentStatus" value="1"/>启用
	<form:radiobutton path="paymentStatus" value="0"/>停用 <br>	
	排序:
	<form:input type="number" path="sort" placeholder="请输入排序"/><br>		
	
	<div class="btn btn-default" onclick="modifyGoodsStandard()">修改</div>

</form:form>

<script type="text/javascript">

function modifyGoodsStandard() {
	var params = {},
	paymentNameInput = $('[name=paymentName]');

	params.paymentName = $.trim(paymentNameInput.val());
	if (!params.paymentName) {
		alert('请输入支付名称');
		paymentNameInput.select();
		return;
	}
    
	var data = new FormData($('form')[0]);
    $.ajax({
        url: '${modifyPaymentUrl}?${_csrf.parameterName}=${_csrf.token}',
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
                location.replace('/admin/finance/paymentList.do');
            }
        },
        error: function() {
        	alert('<s:message code="program_error"/>');
        }
    });
}
</script>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>