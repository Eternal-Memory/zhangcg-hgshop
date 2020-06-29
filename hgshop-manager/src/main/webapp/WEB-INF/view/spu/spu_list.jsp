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
<div>
	<div>
		<form action="" id="form1">
			<fieldset>
			<label for="key">关键字</label>
			<input type="text" id="key" name="key" value="${spuVO.key }">
			<label for="brand">品牌</label>
			<select id="brand" name="brandId">
				<option value="0">--请选择--</option>
				<c:forEach items="${brands }" var="brand">
					<option value="${brand.id }" ${spuVO.brand.id==brand.id?'selected':'' }> ${brand.name }</option>
				</c:forEach>
			</select>
			<!-- <c:if test="${brand.id eq '${spuVO.brandId}' }"></c:if> -->
			<label for="orderColumn">排序字段</label>
			<select id="orderColumn" name="orderColumn">
				<option value="">--请选择--</option>
				<option value="goods_name" ${spuVO.orderColumn=='goods_name'?"selected":''} >名称</option>
				<option value="caption" ${spuVO.orderColumn=='caption'?"selected":''}>标题</option>
				<option value="category_id" ${spuVO.orderColumn=='category_id'?"selected":''}>分类</option>
				<option value="brand_id" ${spuVO.orderColumn=='brand_id'?"selected":''}>品牌</option>
			</select>	
			
			<label for="orderType">排序方式</label>
			<input type="radio" name="orderType" value="ASC"  ${spuVO.orderType=='ASC'?"checked":''} >升序 
			<input type="radio" name="orderType" value="DESC"  ${spuVO.orderType=='DESC'?"checked":''} >降序 
			
			<button type="button" class="btn btn-primary btn-sm" onclick="query(1)">查询</button>
			
			</fieldset>
			
		</form>
		
	</div>
	<div><button type="button" class="btn btn-primary" onclick="toAdd()">添加</button></div>
	<div>
		<table class="table">
			<tr>
				<th><input type="checkbox" onclick="qx()"></th>
				<th>id</th>
				<th>名称</th>
				<th>在售</th>
				<th>品牌</th>
				<th>标题</th>
				<th>分类</th>
				<th>图片</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${info.list }" var="spu">
			<tr>
				<td><input type="checkbox" class="c1"></td>
				<td>${spu.id }</td>
				<td>${spu.goodsName }</td>
				<td>${spu.isMarketable==1?'在售':'下架' }</td>
				<td>${spu.brand.name }</td>
				<td>${spu.caption }</td>
				<td>${spu.category.name }</td>
				<td><img src="/pic/${spu.smallPic }" width="40px" height="40px"></td>
				<td>
					<button type="button" class="btn btn-danger btn-sm" onclick="delete1(${spu.id})">删除</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="toUpdate(${spu.id})">修改</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="toAddSku(${spu.id})">添加sku</button>
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
	//跳转到修改页面
	function toUpdate(id){
		$("#workContent").load('./spu/getById',{id:id});
	}
	
	//跳转到sku添加页面
	function toAddSku(spuId){
		$("#workContent").load('./sku/toAdd',{spuId:spuId});
	}
	
	//跳转到添加页面
	function toAdd(){
		$("#workContent").load('./spu/toadd');
	}
	
	//分页查询
	function goPage(pageNum){
		var query = $("#form1").serialize();
		$("#workContent").load('./spu/selects?pageNum='+pageNum,query);
	}

	//查询
	function query(page){
		var query = $("#form1").serialize();
		$("#workContent").load('./spu/selects',query);
	}
	
	function delete1(id){
		if(confirm("确定要删除吗")){
			$.post("/spu/delete",{ids:id},function(msg){
				if(Number(msg)>0){
					alert("删除成功");
					query("${info.pageNum}");
				}else{
					alert("删除失败");
				}
			})
		}
		
	}
</script>
</html>