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
<link href="${pageContext.request.contextPath }/resource/bootstrap4/css/bootstrap.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath }/resource/bootstrap-treeview/css/bootstrap-treeview.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-4" id="tree"></div>
		<div class="col-md-5" >
		<!-- 用于添加 -->
			<div class="container">
				<form id="addForm" action="">
					<fieldset>
					<label for="parentNodeName">父类名称:</label>
					<input type="hidden" name="parentId" id="parentId">				
					<input id="parentNodeName" disabled="disabled" >
					<br>
					<label for="name">分类名称:</label>
					<input id="name" name="name" >
					<br>	
					<input type="button" onclick="addCat()" value="添加">			
				</fieldset>
				</form>
				
			</div>
			
			<!-- 用于修改和删除 -->
			<div class="container">
				<form id="updateForm" action="">
					<fieldset>
					<br>
					<input type="hidden" name="id" id="currentId">				
					<label for="name">分类名称:</label>
					<input id="currentName" name="name" >
					<br>	
					<input type="button" id="btnUpdate" onclick="updateCat()" value="修改">			
					<input type="button" id="btnDel" onclick="delCat()" value="删除">			
				</fieldset>
				</form>
				
			</div>
			
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	function initTree(){
		$.post("./category/selects",{},function(data){
			$("#tree").treeview({
				data: data,
				levels: 2,
				onNodeSelected: function(event, data) {
					  // 用于显示添加部分
					  $("#parentNodeName").val(data.text)
					  $("#parentId").val(data.id);
					  $("#name").val("")
					  
					  // 用于显示修改和删除的部分
					  $("#currentName").val(data.text)
					  $("#currentId").val(data.id);
					  $("#butnDel").prop("disabled",data.nodes.length>0)
				  }
			});
		})
	}
	
	initTree();
	
	function addCat(){
		$.post('./category/insert',$("#addForm").serialize(),function(msg){
			if(Number(msg)>0){
				alert('添加成功')
				//刷新数据
				initTree();
			}else{
				alert('添加失败')
			}
		})
	}
	
	// 删除
	function delCat(){
		
		if(!confirm('确认删除该分类么'))
			return;
		
		$.post('./category/delete',{id:  $("#currentId").val()},function(msg){
			if(Number(msg)>0){
				alert('删除成功')
				//刷新数据
				initTree();
			}else{
				alert('删除失败')
			}
		})
	}
	
	// 修改
	function updateCat(){
		
		$.post('./category/update',$("#updateForm").serialize(),function(msg){
			if(Number(msg)>0){
				alert('修改成功')
				//刷新数据
				initTree();
			}else{
				alert('修改失败')
			}
		})
	}
	
</script>
</html>