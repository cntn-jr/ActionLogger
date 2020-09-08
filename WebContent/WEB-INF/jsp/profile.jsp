<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#div-table {
	margin: 3em auto;
	font-size: 1.2em;
	font-weight: 500;
	/* border: 2px #eeeeee solid; */
	border-radius: 10px;
	width: 25em;
	/* box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); */
	background: #f8f9fa;
	color: #555555;
}

table {
	margin: auto;
}

td {
	padding: 15px;
}

#linkBox {
	text-align: center;
	width: 150px;
	margin: 20px auto;
	height: 40px;
	line-height: 40px;
	background: #78e08f;
	border: solid 1px #1dd1a1;
	border-radius: 4px;
	user-select: none;
}

#linkBox:hover {
	background: #1dd1a1;
}

#linkEdit {
	text-decoration: none;
	color: #f5f6fa;
	height: 2em;
	line-height: 2em;
	font-weight: bold;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Profile</h1>
</div>
<div id="div-table">
	<table>
		<tr>
			<td>ユーザID</td>
			<td><c:out value="${user.user_id }" /></td>
		</tr>
		<tr>
			<td>ユーザ名</td>
			<td><c:out value="${user.name }" /></td>
		</tr>
		<tr>
			<td>住所</td>
			<td><c:out value="${user.address }" /></td>
		</tr>
		<tr>
			<td>電話番号</td>
			<td><c:out value="${user.tel_number }" /></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><c:out value="${user.mail }" /></td>
		</tr>
	</table>
</div>

<a id="linkEdit" href="/ActionLogger/UserCheck?alter=userInfo">
	<div id="linkBox">プロフィール変更</div>
</a>
