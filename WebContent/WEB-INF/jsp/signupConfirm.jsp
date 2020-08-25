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
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser" action="/ActionLogger/SignupConfirm"
				method="post">

				<h4>新規ユーザー</h4>
				<div class="mb-3">
					ユーザーID　: <c:out value="${newUser.user_id}"/>
				</div>
				<div class="mb-3">
					パスワード : *******
				</div>
				<div class="mb-3">
					氏名 : <c:out value="${newUser.name}"/>
				</div>
				<div class="mb-3">
					住所 : <c:out value="${newUser.address}"/>
				</div>
				<div class="mb-3">
					電話番号 : <c:out value="${newUser.tel_number}"/>
				</div>
				<div class="mb-3">
					メールアドレス　: <c:out value="${newUser.mail}"/>
				</div>
				<%-- フォームの正当性確認データ --%>
				<input type="hidden" name="vKey" value="${validationKey.value}">
				<input type="hidden" name="status" value="confirmed"></input>
				<input type="submit" class="btn btn-secondary btn-block btn-lg" id="enterRoom" value="OK"></input>
			</form>
		</div>
		<div class="col"></div>

	</div>
</body>
</html>
