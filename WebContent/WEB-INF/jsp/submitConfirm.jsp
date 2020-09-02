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
	width: 520px;
	margin: 50px auto 0;
}

table {
	margin: 0 auto;
	width: 500px;
	font-size: 1.2em;
}

th {
	width: 150px;
	text-align: center;
	height: 40px;
}

td {
	width: 350px;
	text-align: center;
	height: 40px;
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
	margin: 10px 0;
	font-size: 0.8em;
}

input[type="submit"]:hover{
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

	<div id="container">
		<form class="form-adduser" action="/ActionLogger/SubmitActionConfirm"
			method="post">
			<table>
				<tr>
					<th>外出時刻</th>
					<td><c:out value="${logAct.out_datetime}" /></td>
				</tr>
				<tr>
					<th>帰宅時刻</th>
					<td><c:out value="${logAct.in_datetime}" /></td>
				</tr>
				<tr>
					<th>場所</th>
					<td><c:out value="${logAct.place}" /></td>
				</tr>
				<tr>
					<th>理由</th>
					<td><c:out value="${logAct.reason}" /></td>
				</tr>
				<tr>
					<th>備考</th>
					<td><c:out value="${logAct.remarks}" /></td>
				</tr>
				<tr>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th><input type="submit" id="btn" value="登録"></input></th>
					<td></td>
				</tr>

			</table>
			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<input type="hidden" name="status" value="confirmed"></input>
		</form>

	</div>
</body>
</html>
