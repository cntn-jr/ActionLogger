<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger</title>

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
	width: 600px;
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

.form-control, label {
	margin: 8px;
}

.btnBox {
	display: flex;
	margin: 10px 0;
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

	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h2 class="headline">プロフィール変更</h2>
	</div>

	<div class="container">
		<form action="/ActionLogger/AlterUserInfo" method="post">
			<div class="subContainer">
				<div class="mb-3">
					<label for="name">氏名</label> <input type="text"
						class="form-control" id="name" name="name" placeholder="氏名"
						value="${user.name}" 　required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="address">住所</label> <input type="text"
						value="${user.address}" class="form-control" id="address"
						name="address" placeholder="住所">
				</div>
				<div class="mb-3">
					<label for="tel">電話番号</label> <input type="text"
						value="${user.tel_number}" class="form-control" id="tel"
						name="tel_number" placeholder="xxxx-xxxx-xxxx">
				</div>
				<div class="mb-3">
					<label for="email">メールアドレス</label> <input type="text"
						value="${user.mail}" class="form-control" id="email" name="mail"
						placeholder="メールアドレス">
				</div>
			</div>


			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<div class='btnBox'>
				<input type="submit" class="btn" value="変更"></input> <a
					id="linkHome" href="/ActionLogger/">
					<div id="linkBox">戻る</div>
				</a>
			</div>
		</form>
	</div>


</body>
</html>