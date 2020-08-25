<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Entry Group</h1>
</div>

<div class="row">
	<div class="col-14"></div>
	<div class="col-2"></div>
	<div class="col-8">
		<form class="form-addgroup" action="/ActionLogger/EntryGroup"
			method="post">
			<div class="mb-3">
				<label for="name">グループID</label> <input type="text"
					class="form-control" id="name" name="group_id"
					placeholder="参加するグループIDを入力してください" required>
				<div class="invalid-feedback">必須</div>
			</div>
			<div class="col-14"></div>


			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<input type="submit" class="btn btn-secondary btn-block btn-lg"
				id="enterRoom" value="参加"></input>
	</div>
	</form>
</div>
