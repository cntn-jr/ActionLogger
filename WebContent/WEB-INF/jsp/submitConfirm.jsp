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
	width: 500px;
	margin: 50px auto;
	padding: 20px;
	border-radius: 10px;
	background: #dcdde1;
	color: #353b48;
}

.box {
	width: 500px;
	font-size: 1.2em;
	margin: 0 auto;
}

.key {
	text-align: left;
	font-weight: bold;
}

.values {
	text-align: center;
}

#btnCnt {
	text-align: right;
}

input[type="submit"] {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
	border-radius: 10px;
	height: 40px;
	width: 100px;
	font-weight: bold;
	font-size: 0.8em;
}

input[type="submit"]:hover {
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
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">Submit Action Log Confirm</h1>
	</div>

	<form class="form-adduser" action="/ActionLogger/SubmitActionConfirm"
		method="post">
		<div id="container">
			<div class="key box">外出時刻</div>
			<div class="values box">
				<c:out value="${logAct.out_datetime}" />
			</div>
			<div class="key box">帰宅時刻</div>
			<div class="values box">
				<c:out value="${logAct.in_datetime}" />
			</div>
			<div class="key box">場所</div>
			<div class="values box">
				<c:out value="${logAct.place}" />
			</div>
			<div class="key box">理由</div>
			<div class="values box">
				<c:out value="${logAct.reason}" />
			</div>
			<div class="key box">備考</div>
			<div class="values box">
				<c:out value="${logAct.remarks}" />
			</div>

			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<input type="hidden" name="status" value="confirmed"></input>
		</div>
		<div class="box">
			<input type="submit" id="btn" value="登録"></input>
		</div>
	</form>

</body>
</html>
