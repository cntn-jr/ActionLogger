<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Dashboard</h1>
</div>
<h3 style="text-align: center;">グループ情報</h3>
<!--
<div class="table-responsive">
	<table class="table table-borderless table-sm">
		<tbody>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">参加グループ</th>
				<td></td>
			</tr>
			<c:forEach var="name" items="${entryNameList}">
				<tr class="d-flex">
					<th scope="row" class="col-2 text-right"></th>
					<td><c:out value="${name}" /></td>
				</tr>
			</c:forEach>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">管理グループ</th>
				<td></td>
			</tr>
			<c:forEach var="group" items="${groupList}">
				<tr class="d-flex">
					<th scope="row" class="col-2 text-right"></th>
					<td><c:out value="${group[1]}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
-->

<div class="container" style="margin: 2em 0;">
	<div class="row">
		<div class="col-2" style="margin-left: auto;  font-weight:bold;">
			参加グループ</div>
		<div class="col-3" style="margin-right: auto; padding: 0.5em 0;"></div>
	</div>
	<c:forEach var="name" items="${entryNameList}">
		<div class="row">
			<div class="col-2" style="margin-left: auto; padding: 0.5em 0;"></div>
			<div class="col-3" style="margin-right: auto; padding: 0.5em 0;"><c:out value="${name}" /></div>
		</div>
	</c:forEach>
	<div class="row">
		<div class="col-2" style="margin-left: auto;  font-weight:bold;">
			管理グループ</div>
		<div class="col-3" style="margin-right: auto; padding: 0.5em 0;"></div>
	</div>
	<c:forEach var="group" items="${groupList}">
		<div class="row">
			<div class="col-2" style="margin-left: auto; padding: 0.5em 0;"></div>
			<div class="col-3" style="margin-right: auto; padding: 0.5em 0;"><c:out value="${group[1]}" /></div>
		</div>
	</c:forEach>
</div>

<h3 style="text-align: center;">最近の行動記録</h3>
<c:choose>
	<c:when test="${easyLogList[0] != null}">
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
							<td><c:out value="${log.out_datetime}" /></td>
							<td><c:out value="${log.in_datetime}" /></td>
							<td><c:out value="${log.place}" /></td>
							<td><c:out value="${log.reason}" /></td>
							<td><c:out value="${log.remarks}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div style="text-align: center;">
			<a href="/ActionLogger/?view=activities">全て表示</a>
		</div>
		<br>
	</c:when>
	<c:otherwise>
	ないです
	</c:otherwise>
</c:choose>
