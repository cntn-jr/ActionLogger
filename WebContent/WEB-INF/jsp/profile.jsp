<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Profile</h1>
</div>

<div class="container" style="margin: 5em 0;">
	<div class="row">
		<div class="col-3"
			style="margin-left: auto; padding:1em;">
			<h4>ユーザID</h4>
		</div>
		<div class="col-3"
			style="margin-right: auto; padding:1em;">
			<h5><c:out value="${user.user_id }" /></h5>
		</div>
	</div>
	<div class="row">
		<div class="col-3"
			style="margin-left: auto; padding:1em;">
			<h4>ユーザ名</h4>
		</div>
		<div class="col-3"
			style="margin-right: auto; padding:1em;">
			<h5><c:out value="${user.name }" /></h5>
		</div>
	</div>
	<div class="row">
		<div class="col-3"
			style="margin-left: auto; padding:1em;">
			<h4>住所</h4>
		</div>
		<div class="col-3"
			style="margin-right: auto; padding:1em;">
			<h5><c:out value="${user.address }" /></h5>
		</div>
	</div>
	<div class="row">
		<div class="col-3"
			style="margin-left: auto; padding:1em;">
			<h4>電話番号</h4>
		</div>
		<div class="col-3"
			style="margin-right: auto; padding:1em;">
			<h5><c:out value="${user.tel_number }" /></h5>
		</div>
	</div>
	<div class="row">
		<div class="col-3"
			style="margin-left: auto; padding:1em;">
			<h4>メールアドレス</h4>
		</div>
		<div class="col-3"
			style="margin-right: auto; padding:1em;">
			<h5><c:out value="${user.mail }" /></h5>
		</div>
	</div>
</div>
