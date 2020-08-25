<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger パスワード変更</title>

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
.btn{
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
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>
	
	<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2" style="margin-left:5em;">Alter Password</h1>
</div>

<div class="row">
		<div class="col"></div>
		<div class="col-８">
			<form class="form-signin" action="/ActionLogger/AlterPassword" method="post">
				<div class="form-group">
					パスワード<br> 
					<input
						type="password" class="form-control" name="password"
						placeholder="パスワード" required>
				</div>
				<div class="form-group">
					パスワード再入力<br> 
					<input
						type="password" class="form-control" name="re_password"
						placeholder="パスワード再入力" required>
				</div>
				<input type="submit" class="btn btn-secondary btn-block btn-lg" id="login" value="確認"></input>
			</form>
		</div>
		<div class="col"></div>
	</div>
	</body>
	</html>