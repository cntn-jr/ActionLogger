<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="model.User" %>
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
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>

	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser" action="/ActionLogger/AlterUserInfo"
				method="post">
				<h4 h3 mb-3 font-weight-normal>プロフィール変更</h4>
				<div class="mb-3">
					<label for="name">氏名</label> <input type="text"
						class="form-control" id="name" name="name" placeholder="氏名" value="${user.name}"
						　required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="address">住所</label> <input type="text" value="${user.address}"
						class="form-control" id="address" name="address" placeholder="住所">
				</div>
				<div class="mb-3">
					<label for="tel">電話番号</label> <input type="text" value="${user.tel_number}"
						class="form-control" id="tel" name="tel_number"
						placeholder="xxxx-xxxx-xxxx">
				</div>
				<div class="mb-3">
					<label for="email">メールアドレス</label> <input type="text" value="${user.mail}"
						class="form-control" id="email" name="mail" placeholder="メールアドレス">
				</div>
				<input type="submit" class="btn btn-secondary btn-block btn-lg"
					id="enterRoom" value="変更"></input>
			</form>
		</div>
		<div class="col"></div>

	</div>
</body>
</html>