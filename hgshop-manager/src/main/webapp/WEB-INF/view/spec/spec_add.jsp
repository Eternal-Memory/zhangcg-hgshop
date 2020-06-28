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
<div class="container">
	<form id="form1">
		<div class="form-group row">
		    <label for="specName" class="col-sm-2 col-form-label">规格名称</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="specName" name="specName">
		    </div>
		 </div>
		 <div class="form-group row">
			 <div class="col-sm-1">
			 </div>
		 	<div class="col-sm-6">
		 	<table id="table">
		 		<tr>
		 			<td>属性名称</td>
		 			<td>显示顺序</td>
		 			<td><button type="button" onclick="addLine()">添加</button></td>
		 		</tr>
		 		<tr>
		 			<td><input name="options[0].optionName"></td>
		 			<td><input type="number" name="options[0].orders"></td>
		 			<td><button type="button" onclick="removeLine($(this))">删除</button></td>
		 		</tr>
		 	</table>
		 	</div>
		 </div>
		 <div class="form-group row">
		 	<button type="button" onclick="commitData()">提交</button>
		 </div>
	</form>
</div>
</body>
<script type="text/javascript">
var i=1;
function addLine(){
	$("#table").append(`<tr>
 			<td><input name="options[`+i+`].optionName"></td>
 			<td><input type="number" name="options[`+i+`].orders"></td>
 			<td><button onclick="removeLine($(this))">删除</button></td>
 		</tr>`)
		i++;	
}

function removeLine(btnObj){
	btnObj.parent().parent().remove();
}

function commitData(){
	// 得到需要提交的数据
	var formData = new FormData($('#form1')[0]);
	$.ajax({url:'/spec/insert',
		  type:'post',
		  processData:false,
		  contentType:false,
		  data:formData,
		  success:function(msg){
			  if(Number(msg)>0){
			 	 alert("添加成功");
				 $("#workContent").load('/spec/selects');			
			  }else{
				  alert("添加失败");
			  }
		  }
      })
}


</script>
</html>