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
	
	<div>
		<!-- 生成订单的数据 -->
		<fieldset > <label for="address">配送地址：</label><br>
					<textarea id="address" rows="3" cols="50">
						
					</textarea>
		</fieldset>
		<fieldset>
			<button type="button" class="btn btn-primary " onclick="createOrder()">下单</button>
			<button type="button" class="btn btn-danger " onclick="removeAll()">批量删除</button>
		</fieldset>
	</div>
	<!-- 查询结果 -->
	 <div>
	 	<table class="table">
	 		<tr>
	 			<th> <input id="ids" type="checkbox" onclick="qx()"> </th>
	 			<th>id</th>
	 			<th>标题</th>
	 			<th>价格</th>
	 			<th>购买数量</th>
	 			<th>总价格</th>
	 			<th>图片</th>
	 			<th>操作</th>
	 		</tr>
	 		<c:forEach items="${list}" var="cart">
	 			<tr>
	 				<td><input type="checkbox" value="${cart.id}" name="id" class="c1"> </td>
	 				<td>${cart.id} </td>
	 				<td>${cart.sku.title}</td>
	 				<td>${cart.sku.price}</td>
	 				<td>${cart.pnum}</td>
	 				<td>${cart.pnum*cart.sku.price}</td>
	 				<td><img src="/pic/${cart.sku.cartThumbnail}" width="40" height="40"></td>
	 				<td>
	 					<button type="button" class="btn btn-danger btn-sm" onclick="del(${cart.id})">删除</button>
	 				</td>
	 			</tr>
	 		</c:forEach>
	 	</table>
	 </div>
</div>
<script>
	//删除
	function del(id){
		if(confirm('确认删除吗？')){
			$.post('/user/deleteCart',{ids:id},function(msg){
				if(Number(msg)>0){
					alert('删除成功')
					//刷新
					$("#workContent").load('/user/cartList');
				}else{
					alert('删除失败')
				}
			})
		}
	}
	
	function removeAll(){
		var ids=$(".c1:checked").map(function(){
			return $(this).val();
		}).get().join();
		if(confirm('确认全部删除吗？')){
			$.post('/user/deleteCart',{ids:ids},function(msg){
				if(Number(msg)>0){
					alert('删除成功')
					//刷新
					$("#workContent").load('/user/catList');
				}else{
					alert('删除失败')
				}
			})
		}
	}
	
	function createOrder(){
		var ids=$(".c1:checked").map(function(){
			return $(this).val();
		}).get().join();
		if(ids==null){
			alert("请选择一条数据");
			return ;
		}
		alert($("#address").val());
		// 生成订单
		$.post("/user/createOrder",{cartIds:ids,address:$("#address").val()},function(msg){
			if(msg.errorCode===0){
				alert('购物成功，请及时支付')
				//刷新
				$("#workContent").load('/user/catList');
			}else{
				alert(data.errorInfo)
			}
			
		})
	}

</script>	
</body>
</html>