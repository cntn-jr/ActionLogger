<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#modalButton {
	background: #eee;
	color: #111;
	border: 1px #eee solid;
	font-weight: bold;
	user-select: none;
	outline: 0;
	transition: none;
}

#notAction {
	text-align: center;
	font-size: 1.2em;
	margin-top: 100px;
}

.searchArea {
	margin: 2em auto;
	text-align: center;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Action Log</h1>
	<!-- Button trigger modal -->
	<p id="modalButton" class="btn btn-primary" data-toggle="modal"
		data-target="#exampleModal">絞り込み</p>
</div>
<br />
<br />

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!--中身-->
				<form action="/ActionLogger/" method="get">
					<div class="searchArea">
						場所：<input type="text" name="searchPlace" placeholder="場所" value=""
							style="width: 15em;" />
					</div>
					<div class="searchArea">
						日付：<input type="date" name="searchDate" placeholder="日付" value=""
							style="width: 15em;" />
					</div>
					<input type="hidden" name="view" value="activities" /> <input
						type="hidden" name="search" value="search" />
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">検索</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<c:choose>
	<c:when test="${allLogList[0] != null}">
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
					<c:forEach var="log" items="${allLogList}">
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
	</c:when>
	<c:otherwise>
		<p id="notAction">登録されていません</p>
	</c:otherwise>
</c:choose>

<script>
	$("#myModal").on("shown.bs.modal", function() {
		$("#myInput").trigger("focus");
	});
</script>
