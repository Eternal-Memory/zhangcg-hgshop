<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品浏览</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="/resource/bootstrap4/css/bootstrap.css" rel="stylesheet" >
<link href="/resource/bootstrap-treeview/css/bootstrap-treeview.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>
<style type="text/css">
	.p1 {
		display: -webkit-box;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 1;
		overflow: hidden;
	}
	
	.p3 {
		display: -webkit-box;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 1;
		overflow: hidden;
	}
</style>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Dropdown </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
				<li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Disabled</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="./query">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" name="key" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
			</form>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<div id="tree"></div>
			</div>
			<div class="col-md-9">
				<div class="row">
				<c:forEach items="${info.list }" var="spu">
					<div class="col-md-4">
						<div class="card" style="width: 18rem;">
							<a href="./spu?spuId=${spu.id }" target="_blank">
								<img src="/pic/${spu.smallPic }" width="180" height="240" class="card-img-top" alt="${spu.goodsName}">
							</a>
							<div class="card-body">
								<h5 class="card-title p1">${spu.goodsName }</h5>
								<p class="card-text p3">${spu.caption==null?'&nbsp;':spu.caption }</p>
								<p class="card-text p1">${spu.category==null?'&nbsp;':spu.category.name }</p>
								<p class="card-text p1">${spu.brand==null?'&nbsp;':spu.brand.name }</p>
							</div>
						</div>
					</div>
				</c:forEach>
				</div>
			
			<div class="row" style="padding-top: 10px;padding-bottom: 10px">
				<nav aria-label="..." class="justify-content-center">
				  <ul class="pagination justify-content-center">
				  	<c:choose>
					  	<c:when test="${info.pageNum==1}">
						    <li class="page-item disabled">
						      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">首页</a>
						    </li>
					    </c:when>
					    <c:otherwise>
					    	<li class="page-item ">
						      <a class="page-link"  href="./index?categoryId=${spuVO.categoryId}" tabindex="-1">首页</a>
						    </li>
					    </c:otherwise>
				    </c:choose>
				    <c:forEach begin="${info.pageNum-2<1?1:info.pageNum-2}" end="${info.pageNum+2>info.pages?info.pages:info.pageNum+2}" var="page">
				    	<c:if test="${info.pageNum==page}">
				    	  <li class="page-item active" aria-current="page">
				    		<a class="page-link" href="javascipt:void(0)">${page}<span class="sr-only">(current)</span></a>
				    	  </li>
				    	</c:if>
				    	<c:if test="${info.pageNum!=page}">
				    		<li class="page-item"><a class="page-link" href="./index?categoryId=${spuVO.categoryId}&pageNum=${page}">${page}</a></li>
				    	</c:if>
				    </c:forEach>
				    <li class="page-item">
				      <a class="page-link" href="#">尾页</a>
				    </li>
				  </ul>
				</nav>
			</div>
			</div>
		</div>

	</div>

</body>
<script type="text/javascript">
function initTree(){
	
	$.post('./catData',{},function(data){
		$('#tree').treeview({
			  data: data,         // data is not optional
			  levels: 2,
			  onNodeSelected: function(event, data) {
				//末级节点				
				 if(data.nodes.length<1){
					location.href="./index?categoryId="+data.id
				}
			  }
			});
	})

}	

	//初始化树状图
	initTree();
</script>
</html>