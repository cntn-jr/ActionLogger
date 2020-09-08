<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger パスワード確認</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<style>
.headline {
	margin-left: 3em;
}

.container {
	width: 500px;
	margin-top: 80px;
}

.subContainer {
	background: #f8f9fa;
	border: #dfe6e9 solid 1px;
	color: #4b4b4b;
	border-radius: 10px;
	padding: 20px;
	margin-bottom: 30px;
	padding: 50px;
	font-weight: bold;
}

.btnBox {
	display: flex;
	marign: 10px 0;
}

.btn {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
	border-radius: 10px;
	height: 40px;
	width: 100px;
	font-weight: bold;
	margin-right: 10px;
}

.btn:hover {
	color: #fff;
	background-color: #546de5;
	border-color: #546de5;
}

#linkBox {
	text-align: center;
	height: 40px;
	width: 100px;
	line-height: 40px;
	background-color: #f8f9fa;
	border: solid 1px #c8d6e5;
	border-radius: 10px;
}

#linkBox:hover {
	background-color: #c8d6e5;
	border: solid 1px #c8d6e5;
}

#linkHome {
	text-decoration: none;
	color: #576574;
	height: 2em;
	line-height: 2em;
	font-weight: bold;
	height: 2em;
}
</style>
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3"
			href="/ActionLogger/">Action Logger</a>
	</nav>

	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h2 class="headline">パスワード確認</h2>
	</div>

	<div class="container">
		<form action="/ActionLogger/UserCheck" method="post">
			<div class="subContainer">
				<label for="name">現在のパスワードを入力</label> <input type="password"
					class="form-control" name="password" placeholder="パスワード" required>
				<div class="invalid-feedback">必須</div>
				<c:if test="${rewright != null}">
					<p style="color: red; font-size: 0.8em;">&lowast;<c:out value="${rewright}"></c:out></p>
				</c:if>
			</div>
			<input type="hidden" value="${alter}">


			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<div class='btnBox'>
				<input type="submit" class="btn" value="確認"></input> <a
					id="linkHome" href="/ActionLogger/">
					<div id="linkBox">戻る</div>
				</a>
			</div>
		</form>
	</div>
</body>
</html>
