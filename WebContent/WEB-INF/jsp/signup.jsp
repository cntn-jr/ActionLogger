<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger サインアップ</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<style>
#container {
	width: 700px;
	margin: 80px auto;
}

#subContainer {
	background: #f8f9fa;
	border: #dfe6e9 solid 1px;
	color: #4b4b4b;
	border-radius: 10px;
	padding: 20px;
	margin-bottom: 30px;
	padding: 50px;
	font-weight: bold;
	width: 700px;
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

#linkLogin {
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
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3"
			href="/ActionLogger">Action Logger</a>
	</nav>

	<div id="container">
		<form action="/ActionLogger/Signup" class="form-addgroup"
			method="post">
			<div id="subContainer">
				<h3 id="headline">新規登録</h3>

				<label>ユーザーID</label> <input type="text" class="form-control"
					id="userid" name="user_id" placeholder="ユーザーID"
					value="${tempUser.user_id}" required>
				<div class="invalid-feedback">必須</div>
				<c:if test="${rewright.equals(\"id\")}">
					<p style="color: red; font-size: 0.8em;">&lowast;このIDは既に使用されています</p>
				</c:if>
				<label>パスワード</label> <input type="password" class="form-control"
					id="password" name="password" placeholder="パスワード(3文字～30文字)"
					required>
				<div class="invalid-feedback">必須</div>
				<c:if test="${rewright.equals(\"pass\")}">
					<p style="color: red; font-size: 0.8em;">&lowast;パスワードは3文字～30文字で入力して下さい</p>
				</c:if>
				<label>氏名</label> <input type="text" class="form-control" id="name"
					name="name" placeholder="氏名" value="${tempUser.name}" required>
				<div class="invalid-feedback">必須</div>
				<label>住所</label> <input type="text" class="form-control"
					id="address" name="address" value="${tempUser.address}"
					placeholder="住所" required> <label>電話番号</label> <input
					type="text" class="form-control" id="tel" name="tel_number"
					value="${tempUser.tel_number}" required
					placeholder="xxxx-xxxx-xxxx"> <label for="email">メールアドレス</label>
				<input type="text" class="form-control" id="email" name="mail"
					placeholder="メールアドレス" value="${tempUser.mail}" required>
			</div>
			<div class='btnBox'>
				<input type="submit" class="btn" value="確認"></input> <a
					id="linkLogin" href="/ActionLogger/Login">
					<div id="linkBox">戻る</div>
				</a>
			</div>
		</form>
	</div>

</body>
</html>