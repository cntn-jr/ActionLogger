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

<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser" action="/ActionLogger/SubmitActionConfirm"
				method="post">

				<div class="mb-3">
					外出時刻　: <c:out value="${logAct.getOut_datetime()}"/>
				</div>
				<div class="mb-3">
					帰宅時刻 : <c:out value="${logAct.getIn_datetime()}"/>
				</div>
				<div class="mb-3">
					場所: <c:out value="${logAct.getPlace()}"/>
				</div>
				<div class="mb-3">
					理由 : <c:out value="${logAct.getReason()}"/>
				</div>
				<div class="mb-3">
					備考 : <c:out value="${logAct.getRemarks()}"/>
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
