<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
	<!-- 查询条件 -->
	<div>
		<form action="" id="form1">
		<fieldset>
			<label for="key">关键字:</label><input id="key" name="key" value="${skuVO.key}"> 
			&nbsp;&nbsp;
			<label for="sortColumn">价格范围:</label>
			<input name="minPrice" type="number" value="${skuVO.minPrice}">	
			<label for="sortColumn">到</label>
			<input name="maxPrice" type="number" value="${skuVO.maxPrice}">	
			<button type="button" class="btn btn-primary btn-sm" onclick="query(1)">查询</button>
		</fieldset>
		</form>
	</div>
	<!-- 查询结果 -->
	 <div>
	 	<table class="table">
	 		<tr>
	 			<th> <input type="checkbox" onclick="qx()"></th>
	 			<th>id</th>
	 			<th>标题</th>
	 			<th>卖点</th>
	 			<th>spu名</th>
	 			<th>品牌</th>
	 			<th>分类</th>
	 			<th>价格</th>
	 			<th>图片</th>
	 			<th>操作</th>
	 		</tr>
	 		<c:forEach items="${info.list}" var="sku">
	 			<tr>
	 				<td><input type="checkbox" class="c1"></td>
	 				<td>${sku.id}</td>
	 				<td>${sku.title}</td>
	 				<td>${sku.sellPoint}</td>
	 				<td>${sku.spu.goodsName}</td>
	 				<td>${sku.spu.brand.name}</td>
	 				<td>${sku.spu.category.name}</td>
	 				<td>${sku.price}</td>
	 				<td><img src="/pic/${sku.image}" width="40" height="40"></td>
	 				<td>
	 					<button type="button" class="btn btn-danger btn-sm" onclick="del(${spu.id})">删除</button>
						<button type="button" class="btn btn-primary btn-sm" onclick="toUpdate(${spu.id})">修改</button>
	 				</td>
	 			</tr>
	 		</c:forEach>
	 	</table>
	 </div>
	 <div>
	 	<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
	 </div>
</div>  	
</body>
<script type="text/javascript">
	//分页查询
	function goPage(pageNum){
		var query = $("#form1").serialize();
		$("#workContent").load('./sku/selects?pageNum='+pageNum,query);
	}

	//查询
	function query(page){
		var query = $("#form1").serialize();
		$("#workContent").load('./sku/selects',query);
	}


</script>
</html>