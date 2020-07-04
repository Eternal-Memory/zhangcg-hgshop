<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
 <tr class="orderDetailTr">
	<td>详情<td>
		<td colspan="3">
			<table>
				<tr>
					<th>商品名称</th>
					<th>总价</th>
					<th>数量</th>
				</tr>
				<c:forEach items="${orderDetails}" var="d">
					<tr>
						<td>商品名称</td>
						<td>${d.total}</td>
						<td>${d.pnum}</td>
					</tr>
				</c:forEach>
			</table>		
		</td>
  </tr>