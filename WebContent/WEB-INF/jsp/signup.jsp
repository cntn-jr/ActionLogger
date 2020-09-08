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
.btn {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
}

.btn:hover {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
}
</style>
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/ActionLogger">Action
			Logger</a>
	</nav>

	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser" action="/ActionLogger/Signup"
				method="post">
				<h4 style="text-align: center; margin-top: 2em;">新規ユーザー</h4>
				<div class="mb-3">
					<label for="userid">ユーザーID</label> <input type="text"
						class="form-control" id="userid" name="user_id"
						placeholder="ユーザーID" value="" required>
					<div class="invalid-feedback">必須</div>
					<c:if test="${rewright != null}">
						<p style="color: red; font-size: 0.8em;">&lowast;<c:out value="${rewright}"></c:out></p>
					</c:if>
				</div>
				<div class="mb-3">
					<label for="password">パスワード</label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="パスワード" 　required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="name">氏名</label> <input type="text"
						class="form-control" id="name" name="name" placeholder="氏名"
						value="${rewright.name}" 　required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="address">住所</label> <input type="text"
						class="form-control" id="address" name="address"
						value="${rewright.address}" placeholder="住所" required>
				</div>
				<div class="mb-3">
					<label for="tel">電話番号</label> <input type="text"
						class="form-control" id="tel" name="tel_number"
						value="${rewright.tel_number}" required
						placeholder="xxxx-xxxx-xxxx">
				</div>
				<%-- フォームの正当性確認データ --%>
				<input type="hidden" name="vKey" value="${validationKey.value}">
				<div class="mb-3">
					<label for="email">メールアドレス</label> <input type="text"
						class="form-control" id="email" name="mail" placeholder="メールアドレス"
						value="${rewright.mail}" required>
				</div>
				<input type="submit" class="btn btn-secondary btn-block btn-lg"
					id="enterRoom" value="登録"></input>
			</form>
		</div>
		<div class="col"></div>

	</div>
</body>
</html>