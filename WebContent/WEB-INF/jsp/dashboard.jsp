<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#tables {
	display: flex;
	margin: 3em auto;
	width: max-content;
}

#tables table {
	width: 20em;
	margin: 0 50px;
}

#tables table th {
	width: 7em;
}

#tables table td {
	width: 12em;
	line-height: 1.4em;
	font-weight: 500;
}

#tables table td, #tables table th {
	padding: 0.5em;
}

#linkBox {
	text-align: center;
	width: 8em;
	margin: 20px auto;
	height: 2em;
	line-height: 2em;
	background: #2ecc71;
	border: solid 1px #27ae60;
	border-radius: 4px;
	user-select: none;
}

#linkBox:hover {
	background: #27ae60;
	border: solid 1px #27ae60;
}

#linkAll {
	text-decoration: none;
	color: #f5f6fa;
	height: 2em;
	line-height: 2em;
	font-weight: bold;
}

#notAction {
	text-align: center;
	margin-top: 4em;
	font-weight: 500;
}
</style>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Dashboard</h1>
</div>
<h3 style="margin: 1em 0 0 2em;">グループ情報</h3>

<div id="tables">
	<table>
		<tr>
			<th>参加グループ</th>
			<td></td>
		</tr>
		<c:choose>
			<c:when test="${entryGroupList[0] != null}">
				<c:forEach var="group" items="${entryGroupList}">
					<tr>
						<th></th>
						<td><c:out value="${group.group_name}" /></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<th></th>
				<td>参加中のグループはありません</td>
			</c:otherwise>
		</c:choose>
	</table>

	<table>
		<tr>
			<th>管理グループ</th>
			<td></td>
		</tr>
		<c:choose>
			<c:when test="${groupList[0] != null}">
				<c:forEach var="group" items="${groupList}">
					<tr>
						<th></th>
						<td><c:out value="${group[1]}" /></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<th></th>
				<td>管理中のグループはありません</td>
			</c:otherwise>
		</c:choose>
	</table>
</div>
<h3 style="margin: 2em 0 1em 2em;">最近の行動記録</h3>
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
						<tr style="font-weight: 500;">
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
		<a id="linkAll" href="/ActionLogger/?view=activities">
			<div id="linkBox">全て表示</div>
		</a>
		<br>
	</c:when>
	<c:otherwise>
		<p id="notAction">最近の投稿はまだありません</p>
	</c:otherwise>
</c:choose>
