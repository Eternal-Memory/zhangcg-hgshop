<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户 </title>
<meta name="theme-color" content="#563d7c">
<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resource/css/floating-labels.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
</head>
<body>
	<form class="form-signin" action="./login" method="post">
		<div class="text-center mb-4">
			<img class="mb-4" src="/resource/img/logo.jpg" alt="" width="108"
				height="72">
			<h1 class="h3 mb-3 font-weight-normal">欢迎登录豪哥商城</h1>
		</div>

		<div class="form-label-group">
			<input type="text" id="inputEmail" class="form-control"
				name="username" placeholder="用户名" required="" autofocus="">
			<label for="inputEmail">用户名</label>
		</div>

		<div class="form-label-group">
			<input type="password" id="inputPassword" class="form-control"
				name="password" placeholder="密码" required=""> <label
				for="inputPassword">密码</label>
		</div>


		<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		<p class="mt-5 mb-3 text-muted text-center">© 2017-2020</p>
	</form>

</body>
</html>