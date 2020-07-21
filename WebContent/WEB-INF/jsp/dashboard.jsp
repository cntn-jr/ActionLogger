<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Dashboard</h1>
</div>
<h3>ユーザー情報</h3>
<div class="table-responsive">
	<table class="table table-borderless table-sm">
		<tbody>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">ユーザー名</tk>
				<td><c:out value="${loginUser_id}" /></td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">氏名</th>
				<td><c:out value="${loginUserName}" /></td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">参加グループ</th>
				<td>grp01</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right"></th>
				<td>grp02</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">管理グループ</th>
				<td>KBC ITE19</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right"></th>
				<td>KBC 教職員</td>
			</tr>
		</tbody>
	</table>
</div>

<br>
<br>

<h3>最近の行動記録</h3>
<c:choose>
<c:when test="${easyLogList != null}">
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<th>外出時刻</th>
					<th>帰宅時刻</th>
					<th>場所</th>
					<th>理由</th>
					<th>備考</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="log" items="${easyLogList}">
				<tr>
				<td><c:out value="${log.out_datetime}"/></td>
					<td><c:out value="${log.in_datetime}"/></td>
					<td><c:out value="${log.place}"/></td>
					<td><c:out value="${log.reason}"/></td>
					<td><c:out value="${log.remarks}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<a href="/ActionLogger/?view=activities">全て表示</a>
	</div>
	</c:when>
	<c:otherwise>
	ないです
	</c:otherwise>
	</c:choose>
