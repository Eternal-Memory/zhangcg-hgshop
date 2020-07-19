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
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
	<!-- 查询结果 -->
	 <div>
	 	<table class="table">
	 		<tr>
	 			<th><input id="ids" type="checkbox" onclick="qx()"> </th>
	 			<th>id</th>
	 			<th>配送地址</th>
	 			<th>总价格</th>
	 			<th>下单时间</th>
	 		</tr>
	 		<c:forEach items="${info.list}" var="order">
	 			<tr onclick="showDetail($(this),${order.oid})">
	 				<td><input id="ids" type="checkbox" value="${order.oid}" ></td>
	 				<td>${order.oid}</td>
	 				<td>${order.address}</td>
	 				<td>${order.sumtotal}</td>
	 				<td>
	 				<fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd"/></td>
	 			</tr>
	 		</c:forEach>
	 	</table>
	 </div>
</div>  

</body>
<script type="text/javascript">
//点击行查看详情
function showDetail(trobj,oid){
	$(".orderDetailTr").each(function(){
		$(this).remove()
	})
	
	 $.post('./orderDetails',{oid:oid},function(htmlData){
		trobj.after(htmlData)	
	}) 
}

</script>
</html>