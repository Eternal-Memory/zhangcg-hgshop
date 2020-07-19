<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品详情</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
<style type="text/css">
/* 没有选中 */
.skuNoSel {
	border: solid 1px white;
}

.skuSel {
	border: solid 1px red;
}
</style>

</head>
<body>
	<div class="container">
		<div class="row">
			<h3>商品详情</h3>
		</div>
		<div class="row">
			<div class="md-col-4">
				<div class="card" style="height: 240">
					<img src="/pic/${spu.smallPic}" width="120" height="240"
						class="card-img-top" alt="${spu.goodsName}">
					<div class="card-body">
						<h5 class="card-title p1">${spu.goodsName}</h5>
						<p class="card-text p3">
							<small class="text-muted">${spu.caption==null?'&nbsp;':spu.caption}</small>
						</p>
						<p class="card-text">
							<small class="text-muted"> <c:if
									test="${spu.category!=null}">
				      	分类：${spu.category.name}<br>
								</c:if> <c:if test="${spu.brand!=null}">
				      	品牌：${spu.brand.name} 
				      	</c:if>
							</small>
						</p>
					</div>
				</div>
			</div>

			<div class="md-col-3" style="padding-left: 50px">
				<c:forEach items="${skus}" var="sku">
					<div class="skuNoSel" onmouseenter="showSku('/pic/${sku.image}')"
						onclick="sel($(this),${sku.id})">
						<p>${sku.title}</p>
						<c:forEach items="${sku.options}" var="opt">
							${opt.specName}:${opt.optionName} <br>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<div class="md-col-3" style="padding-left: 50px">
				<img alt="" id="skuImg" src="" width="200px" height="200px">
			</div>
		</div>
		<div>
			<fieldset>
				<label for="buyNum">购买数量</label> <input id="buyNum" name="buyNum"
					type="number">
			</fieldset>
			<fieldset>
				<label for="address">配送地址</label>
				<textarea id="address" name="address">
				</textarea>
			</fieldset>
			<button onclick="addCart()">加入购物车</button>
		</div>
	</div>
</body>
<script type="text/javascript">
		function showSku(imgUrl){
			$("#skuImg").prop('src',imgUrl)
		}
		
		var gSkuid=0;
		
		//选中一个sku
		function sel(divObj,skuId){
			// 移除被选中的状态
			//alert('aa')
			$(".skuSel").each(function(){
				$(this).removeClass("skuSel")
				$(this).addClass("skuNoSel")
			})
			
			divObj.removeClass("skuNoSel")
			divObj.addClass("skuSel")
			
			gSkuid=skuId;
		}
		
		function addCart(){
			if(gSkuid==0){
				alert('请选择一个商品的规格')
				return;
			}
			
			if( !(parseInt($("#buyNum").val())>0) ){
				alert("请输入合法的购买数量")
				return
			}
			
			var buyNum=parseInt($("#buyNum").val());
			$.post("./insertCart",{skuid:gSkuid,pnum:buyNum},function(msg){
				if(msg.errorCode===0){
					alert("添加购物车成功");
				}else{
					alert(msg.errorInfo);
				}
			})
			//alert('将要加入购物车')
		}
		
		
	</script>
</html>