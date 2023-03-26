<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 404 Error </h1>
<h3> 해당 페이지는 찾을 수 없습니다.</h3>

	<!--  
		jsp 출력보다 JSTL 출력 지양하기
		<%-- <% out.println("hi"); %><br>
		<%= 3 %> --%>
		
		<%-- 값 설정 후  
			String test = "hihi";
			pageContext.setAttribute("call", test);
		--%>
		
		<c:set var="know" value="${2222}" scope="page"></c:set>
	
		<c:out value="test"></c:out><br>
		<c:out value="3333"></c:out><br>
		<c:out value="${call }"></c:out><br>
		<c:out value="${know}"></c:out>
		
	-->

</body>
</html>