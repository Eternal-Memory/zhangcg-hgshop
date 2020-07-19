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
<div>
	<div>
		<button type="button" class="btn btn-primary btn-sm" onclick="add()">添加</button>
		<button type="button" class="btn btn-primary btn-sm" onclick="removeAll()">批量删除</button>
	</div>
	<div>
	<table class="table">
		<tr>
			<td>id<input type="checkbox" onclick="qx()"></td>
			<td>名称</td>
			<td>属性</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${info.list }" var="list" varStatus="count">
		<tr>
			<td>${list.id }<input type="checkbox" class="c1" value="${list.id }"></td>
			<td>${list.specName }</td>
			<td><c:forEach items="${list.options }" var="options" varStatus="index">  <c:if test="${index.index!=0}">,</c:if>${options.optionName }</c:forEach>  </td>
			<td>
				<button type="button" class="btn btn-primary btn-sm" onclick="toUpdate(${list.id})">修改</button>
				<button type="button" class="btn btn-danger btn-sm" onclick="del(${list.id})">删除</button>
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
	function goPage(pageNum){
		$("#workContent").load('./spec/selects?pageNum='+pageNum);
	}

	function qx(){
		$(".c1").map(function(){
			this.checked=!this.checked
		})
	}
	
	function add(){
		$("#workContent").load('./spec/add');
	}
	
	function toUpdate(id){
		$("#workContent").load('./spec/toUpdate?id='+id);
	}
	
	function removeAll(){
		var ids=$(".c1:checked").map(function(){
			return $(this).val();
		}).get().join();
		alert(ids);
		if(ids==''){
			alert("请至少选择一条数据");
			return;
		}
		var re=confirm("确认要删除改数据吗？")
		if(!re){
			return;
		}
		$.post("./spec/delete",{ids:ids},function(msg){
			if(Number(msg)>0){
				alert("删除成功");
				$("#workContent").load('./spec/selects');
			}else{
				alert("删除失败");
			}
		})
	}
	
	function del(id){
		var re=confirm("确认要删除改数据吗？")
		if(!re){
			return;
		}
		$.post("./spec/delete",{ids:id},function(msg){
			if(Number(msg)>0){
				alert("删除成功");
				$("#workContent").load('./spec/selects');
			}else{
				alert("删除失败");
			}
		})
	}
</script>
</html>