<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">participant Action Log</h1>
	<!-- モーダルを表示させるボタン -->
	<p class="btn btn-primary" data-toggle="modal"
		data-target="#exampleModal"
		style="background: #eee; color: #111; border: 1px #eee solid; font-weight: bold; user_select: none;">
		絞り込み</p>
</div>
<br>
<br>

<!-- 絞り込み検索のモーダル -->
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
					<div style="margin: 2em auto; text-align: center;">
						場所：<input type="text" name="searchPlace" placeholder="場所" value=""
							style="width: 15em;">
					</div>
					<div style="margin: 2em auto; text-align: center;">
						日付：<input type="date" name="searchDate" placeholder="日付" value=""
							style="width: 15em;">
					</div>
					<input type="hidden" name="view" value="participantLog"> <input
						type="hidden" name="search" value="search">
						<input type="hidden" name="mgtGroup" value="${mgtGroup}">
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">検索</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<c:choose>
	<c:when test="${participantLogList != null}">
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th>ユーザ</th>
						<th>外出時刻</th>
						<th>帰宅時刻</th>
						<th>場所</th>
						<th>理由</th>
						<th>備考</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="log" items="${participantLogList}">
						<tr>
							<td><c:out value="${log.user_id}" /></td>
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
	ないです
	</c:otherwise>
</c:choose>

<script>
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	})
</script>
