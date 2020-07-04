<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品浏览</title>
<link href="${pageContext.request.contextPath}/resource/bootstrap4/css/bootstrap.css" rel="stylesheet" >

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
<link href="${pageContext.request.contextPath}/resource/bootstrap-treeview/css/bootstrap-treeview.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>
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
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="./query">
      <input class="form-control mr-sm-2" type="search" placeholder="关键字"  name="key"  aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
    </form>
  </div>
</nav>
<div class="container-fluid">
  <div class="row">
		
		<div class="col-md-9">
			<div class="row">
				本次搜索花费了${consumerTime}毫秒
			</div>
			<div class="row">
				<c:forEach items="${page.content}" var="spu">
				<div class="col-md-3">
				<div class="card" style="height: 240">
				   	<a href="./spu?spuId=${spu.id}" target="_blank">
				    	<img src="/pic/${spu.smallPic}" width="180" height="120" class="card-img-top" alt="${spu.goodsName}">
				 	 </a>
				    <div class="card-body">
				      <h5 class="card-title p1" >${spu.goodsName}</h5>
				      <p class="card-text p3"><small class="text-muted">${spu.caption==null?'&nbsp;':spu.caption}</small></p>
				      <p class="card-text"><small class="text-muted">
				      <c:if test="${spu.categoryName!=null}">
				      	分类:${spu.categoryName}<br>
				      </c:if> 
				       <c:if test="${spu.brandName!=null}">
				      	品牌：${spu.brandName} 
				      	</c:if>
				      </small></p>
				    </div>
				  </div>
					
				</div>
				</c:forEach>
			</div>
			<div class="row" style="padding-top: 10px;padding-bottom: 10px">
				<nav aria-label="..." class="justify-content-center">
				  <ul class="pagination justify-content-center">
				  	<c:choose>
					  	<c:when test="${pageInfo.pageNum==1}">
						    <li class="page-item disabled">
						      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">首页</a>
						    </li>
					    </c:when>
					    <c:otherwise>
					    	<li class="page-item ">
						      <a class="page-link"  href="./index?categoryId=${spuVo.categoryId}&" tabindex="-1">首页</a>
						    </li>
					    </c:otherwise>
				    </c:choose>
				    
				    
				    <c:forEach begin="${pageInfo.pageNum-2<1?1:pageInfo.pageNum-2}" end="${pageInfo.pageNum+2>pageInfo.pages?pageInfo.pages:pageInfo.pageNum+2}" var="page">
				    	<c:if test="${pageInfo.pageNum==page}">
				    	  <li class="page-item active" aria-current="page">
				    		<a class="page-link" href="javascipt:void(0)">${page}<span class="sr-only">(current)</span></a>
				    	  </li>
				    	</c:if>
				    	<c:if test="${pageInfo.pageNum!=page}">
				    		<li class="page-item"><a class="page-link" href="./index?categoryId=${spuVo.categoryId}&pageNum=${page}">${page}</a></li>
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

<nav class="navbar navbar-expand-lg fixed-bottom navbar-light bg-light">

</nav>

</body>

</html>