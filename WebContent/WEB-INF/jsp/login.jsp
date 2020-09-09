<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger ログイン</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<style>
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
	width: 500px;
}

label, .form-control {
	margin: 8px;
}

#headline {
	text-align: center;
	font-weight: bold;
	margin: 5px 0 30px;
}

.btnBox {
	display: flex;
	justify-content: space-around;
	margin: 10px 0;
	width: 500px;
}

.btn {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
	border-radius: 10px;
	height: 40px;
	width: 200px;
	font-weight: bold;
	margin-right: 10px;
	marign: 10px 0;
}

.btn:hover {
	color: #fff;
	background-color: #546de5;
	border-color: #546de5;
}

#linkBox {
	text-align: center;
	height: 40px;
	width: 200px;
	line-height: 40px;
	background-color: #2ecc71;
	border: solid 1px #27ae60;
	border-radius: 10px;
}

#linkBox:hover {
	background-color: #27ae60;
	border: solid 1px #27ae60;
}

#linkSignup {
	text-decoration: none;
	color: #f5f6fa;
	height: 2em;
	line-height: 2em;
	font-weight: bold;
}
</style>
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>

	<div class="container">
		<form class="form-addgroup" action="/ActionLogger/Login" method="post">
			<div class="subContainer">
				<h3 id="headline">ActionLoggerログイン</h3>
				<label for="name">ユーザID</label> <input type="text"
					class="form-control" name="user_id" placeholder="ユーザID" required>
				<div class="invalid-feedback">必須</div>
				<label for="name">パスワード</label> <input type="password"
					class="form-control" name="password" placeholder="パスワード" required>
				<div class="invalid-feedback">必須</div>
				<c:if test="${rewright != null}">
					<p style="color: red; font-size: 0.8em;">
						&lowast;
						<c:out value="${rewright}"></c:out>
					</p>
				</c:if>
			</div>


			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<div class='btnBox'>
				<input type="submit" class="btn" value="ログイン"></input> <a
					id="linkSignup" href="/ActionLogger/Signup">
					<div id="linkBox">サインアップ</div>
				</a>
			</div>
		</form>
	</div>

</body>
</html>
