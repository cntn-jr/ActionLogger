<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#container {
	width: 400px;
	height: 250px;
	margin: 3em auto;
	padding-top: 1em;
	padding-bottom: 1em;
	border-radius: 10px;
	background: #303952;
	color: #ddd;
}

table {
	margin: auto;
	width: 400px;
	height: 220px;
}

td {
	text-align: center;
	font-size: 1.1em;
	letter-spacing: 5px;
}

th {
	padding-left: 20px;
}

td, th {
	padding: 1em;
	font-weight: bold;
}

.btn {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
	border-radius: 10px;
	height: 40px;
	width: 400px;
	font-weight: bold;
	margin: 20px auto;
	font-size: 0.8em;
}

.btn:hover {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Group Information</h1>
</div>

<div id="container">
	<table>
		<tr>
			<th>グループID</th>
			<td><c:out value="${selectGroup[0]}" /></td>
		</tr>
		<tr>
			<th>グループ名</th>
			<td><c:out value="${selectGroup[1]}" /></td>
		</tr>
		<tr>
			<th>管理者ユーザ名</th>
			<td><c:out value="${selectGroup[2]}" /></td>
		</tr>
	</table>

	<form action="/ActionLogger/EntryGroup?group_id=${selectGroup[0]}"
		method="post">
		<c:choose>
			<c:when test="${alreadyEntry}">
				<input type="submit" value="退会" class="btn">
			</c:when>
			<c:otherwise>
				<input type="submit" value="参加" class="" btn>
			</c:otherwise>
		</c:choose>
	</form>
</div>