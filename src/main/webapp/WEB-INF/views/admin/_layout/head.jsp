<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%><!DOCTYPE html>
<!--[if IE 6]><html lang="zh-CN" class="ie6 ie9- ie8-"><![endif]-->
<!--[if IE 7]><html lang="zh-CN" class="ie7 ie9- ie8-"><![endif]-->
<!--[if IE 8]><html lang="zh-CN" class="ie8 ie9-"><![endif]-->
<!--[if IE 9]><html lang="zh-CN" class="ie9"><![endif]-->
<!--[if (gt IE 8)|!(IE)]><!-->
<html lang="zh-CN">
<!--<![endif]-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;"/>
	<title>Insert title here</title>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/resources/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/admin.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<style>
#top_menu_ul {}
#top_menu_ul li {
	float:left;
	width:100px;
	border:1px solid red;
}
</style>

<ul id="top_menu_ul">
<li>概况</li>
<li>商品</li>
<li>订单</li>
<li>环境设置</li>
</ul>

<%  
	String str = request.getParameter("ref1");  
	//int n = Integer.parseInt(str);  
%> 
<%=str %>