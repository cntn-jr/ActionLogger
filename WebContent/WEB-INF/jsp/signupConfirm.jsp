<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger ユーザー登録確認</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<style>
.h2 {
	margin-left: 3em;
}

#container {
	width: 700px;
	margin: 50px auto;
	padding: 20px;
	border-radius: 10px;
	background: #f8f9fa;
	border: #dfe6e9 solid 1px;
	color: #4b4b4b;
}

.box {
	width: 500px;
	font-size: 1.2em;
	margin: 8px auto;
}

.key {
	text-align: left;
	font-weight: bold;
	margin: 20px 0;
}

.values {
	text-align: center;
	letter-spacing: 5px;
	margin: 20px auto;
}

.btnBox {
	display: flex;
	justify-content: space-around;
	margin: 10px auto;
	width: 700px;
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
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>

	<form class="form-adduser" action="/ActionLogger/SignupConfirm"
		method="post">
		<div id="container">
			<div class="key box">ユーザID</div>
			<div class="values box">
				<c:out value="${newUser.user_id}" />
			</div>
			<div class="key box">パスワード</div>
			<div class="values box">
				<c:out value="***************" />
			</div>
			<div class="key box">氏名</div>
			<div class="values box">
				<c:out value="${newUser.name}" />
			</div>
			<div class="key box">住所</div>
			<div class="values box">
				<c:out value="${newUser.address}" />
			</div>
			<div class="key box">電話番号</div>
			<div class="values box">
				<c:out value="${newUser.tel_number}" />
			</div>
			<div class="key box">メールアドレス</div>
			<div class="values box">
				<c:out value="${newUser.mail}" />
			</div>

			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<input type="hidden" name="status" value="confirmed"></input>
		</div>
		<div class='btnBox'>
			<input type="submit" class="btn" value="登録"></input> <a
				id="linkSignup" href="/ActionLogger/Signup">
				<div id="linkBox">戻る</div>
			</a>
		</div>
	</form>
</body>
</html>
