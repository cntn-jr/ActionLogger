<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger ログアウト</title>

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

.container {
	width: 500px;
	margin: 80px auto;
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

#headline {
	text-align: center;
	font-weight: bold;
	margin: 5px 0 30px;
}

p {
	text-align: center;
}

#linkBox {
	margin: auto;
	text-align: center;
	height: 40px;
	width: 250px;
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

<script>
	window.history.pushState(null, null, null);
	window.addEventListener("popstate", function() {
		window.history.pushState(null, null, null);
		return;
	});
</script>

</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>

	<div class="container">
		<div class="subContainer">
			<h4 id="headline">AcctionLoggerログアウト</h4>
			<p>ログアウトしました</p>

		</div>
		<a id="linkLogin" href="/ActionLogger/Login">
			<div id="linkBox">ログインページへ</div>
		</a>
	</div>
</body>

</html>