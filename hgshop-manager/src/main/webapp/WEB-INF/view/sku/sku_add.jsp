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
	<form action="" id="form1">	
		<div class="form-group row">
		    <label for="goodsName" class="col-sm-2 col-form-label">spu名称</label>
		    <div class="col-sm-4">
		    	<input type="hidden" name="spuId" value="${spu.id}">	
		      <input type="text" class="form-control" id="goodsName" disabled="disabled" name="goodsName" value="${spu.goodsName}" >
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="skutitle" class="col-sm-2 col-form-label">sku名称</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control"  name="title" >
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="skutitle" class="col-sm-2 col-form-label">规格</label>
		    <div class="col-sm-4">
		      <table class="table" id="specTable">
		      	<tr>
		      		<th>规格名称</th>
		      		<th>属性值</th>
		      		<th><button type="button" class="btn btn-primary btn-sm" onclick="addLine()">添加</button> </th>
		      		
		      	</tr>
		      	<tr>
		      		<td><select name="options[0].specId" onchange="specChange($(this))">
		      			<c:forEach items="${info.list}" var="spec">
		      		 		<option value="${spec.id}">${spec.specName}</option>
		      		 		</c:forEach>
		      		 	</select>
		      		 </td>
		      		<td>
		      			<select name="options[0].id"></select>
		      		</td>
		      		<td>
		      			<button type="button" class="btn btn-danger btn-sm" onclick="removeLine($(this))">移除</button>
		      		</td>
		      	</tr>
		      </table>
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="sellPoint" class="col-sm-2 col-form-label">卖点</label>
		    <div class="col-sm-4">
		      <input type="text" id="sellPoint" class="form-control"  name="sellPoint" >
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="price" class="col-sm-2 col-form-label">价格</label>
		    <div class="col-sm-3">
		      <input type="number" id="price" class="form-control"  name="price" >
		    </div>
		    <label for="price" class="col-sm-2 col-form-label">库存</label>
		    <div class="col-sm-3">
		      <input type="number" id="stockCount" class="form-control"  name="stockCount" >
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="costPrice" class="col-sm-2 col-form-label">成本</label>
		    <div class="col-sm-3">
		      <input type="number" id="costPrice" class="form-control"  name="costPrice" >
		    </div>
		    <label for="marketPrice" class="col-sm-2 col-form-label">市价</label>
		    <div class="col-sm-3">
		      <input type="number" id="marketPrice" class="form-control"  name="marketPrice" >
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="barcode" class="col-sm-2 col-form-label">条形码</label>
		    <div class="col-sm-4">
		      <input type="text" id="barcode" class="form-control"  name="barcode" >
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="imageFile" class="col-sm-2 col-form-label">图片</label>
		    <div class="col-sm-4">
		      <input type="file" id="imageFile" class="form-control"  name="imageFile" >
		    </div>
		 </div>
		 <div class="form-group row">
		    <label for="cartThumbnailFile" class="col-sm-2 col-form-label">购物车图</label>
		    <div class="col-sm-4">
		      <input type="file" id="cartThumbnailFile" class="form-control"  name="cartThumbnailFile" >
		    </div>
		 </div>	
		  <div >
		  	<button type="button" class="btn btn-primary " onclick="commitData()">提交数据</button>
		  </div>	 
		 
	</form>
		 
</div>    	
</body>
<script type="text/javascript">
//两个下拉框联动的效果

function specChange(selObj){
	// 得到规格id
	var specId=selObj.val();
	$.post('./sku/getSpecOptions',{specId:specId},function(data){
		var selOption= selObj.parent().next().children(0);
		selOption.empty();
		for(var i=0;i<data.length;i++ ){
			selOption.append('<option value="'+data[i].id+'">'+data[i].optionName+'</option>')
		}
	})
	
}
//移除一行
function removeLine(btnObj){
	btnObj.parent().parent().remove();
}

var index=1;
function addLine(){
	
	$('#specTable').append(`
			<tr>
      		<td><select name="options[`+index+`].specId" onchange="specChange($(this))">
      			<c:forEach items="${info.list}" var="spec">
      		 		<option value="${spec.id}">${spec.specName}</option>
      		 		</c:forEach>
      		 	</select>
      		 </td>
      		<td>
      			<select name="options[`+index+`].id"></select>
      		</td>
      		<td>
      			<button type="button" class="btn btn-danger btn-sm" onclick="removeLine($(this))">移除</button>
      		</td>
      	</tr>		
	`)
	index++;
	
}

//提交数据
function commitData(){
	
	var formData = new FormData($("#form1")[0])
	$.ajax({
		url:'./sku/insert',
		type:'post',
		data:formData,
		processData:false,
		contentType:false,
		success:function(msg){
			if(Number(msg)>0){
				alert('添加成功')
				$("#workContent").load('./sku/selects');
			}else{
				alert('添加失败')
			}
		}
	})
}

</script>
</html>