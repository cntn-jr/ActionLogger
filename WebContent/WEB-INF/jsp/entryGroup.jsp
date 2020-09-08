<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.container {
	width: 500px;
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

.btn {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
	border-radius: 10px;
	height: 40px;
	width: 100px;
	font-weight: bold;
	margin: 10px 0;
}

.btn:hover {
	color: #fff;
	background-color: #546de5;
	border-color: #546de5;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">グループに参加</h1>
</div>

<div class="container">
	<form class="form-addgroup" action="/ActionLogger/EntryGroup"
		method="post">
		<div class="subContainer">
			<label for="name">グループID</label> <input type="text"
				class="form-control" id="name" name="group_id"
				placeholder="参加するグループIDを入力してください" required>
			<div class="invalid-feedback">必須</div>
		</div>


		<%-- フォームの正当性確認データ --%>
		<input type="hidden" name="vKey" value="${validationKey.value}">
		<input type="submit" class="btn" value="確認"></input>
	</form>
</div>


