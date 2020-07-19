<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<h4>品牌列表</h4>
	<table class="table">
		<!-- <tr>
			<th></th>
			<th></th>
		</tr> -->
		<c:forEach items="${list }" var="list">
		<tr>
			<td>${list.id }</td>
			<td>${list.name }</td>
			
		</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>