<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="s"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  1111
</h1>

<P>  The time on the server is ${serverTime}.11111 </P>







<s:authorize access="isAuthenticated()">
    로그아웃:<a href="${pageContext.request.contextPath}/admin/user/logout.do">logout</a>
</s:authorize>


</body>
</html>
