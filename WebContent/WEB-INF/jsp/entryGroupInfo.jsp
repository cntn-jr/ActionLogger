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
	background: #444;
	color: #ddd;
}

table {
	margin: auto;
	width: 400px;
	height: 220px;
}

td {
	text-align: center;
}

th {
	padding-left: 20px;
}

td, th {
	padding: 1em;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Group Information</h1>
</div>

<div id="container">
	<table>
		<tr>
			<th>グループ名</th>
			<td><c:out value="${selectGroup.group_name}" /></td>
		</tr>
		<tr>
			<th>グループID</th>
			<td><c:out value="${selectGroup.group_id}" /></td>
		</tr>
		<tr>
			<th>管理者ユーザID</th>
			<td><c:out value="${selectGroup.admin_id}" /></td>
		</tr>
	</table>
</div>