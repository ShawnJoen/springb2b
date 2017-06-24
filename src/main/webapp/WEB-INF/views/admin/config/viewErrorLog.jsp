<%@ page language="java" contentType="text/html; charset=utf8" 
	pageEncoding="utf8"%><%@include file="/WEB-INF/views/admin/_layout/baseHead.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
	<h3>错误日志详情</h3><br>

	用户编号:	${errorLog.userId}<br>
	版本:	${errorLog.appVersion}<br>
	错误信息:	${errorLog.error}<br>
	Device类型:	${errorLog.device}<br>
	项目名称:	${errorLog.projectName}<br>

<%@include file="/WEB-INF/views/admin/_layout/baseTail.htm"%>