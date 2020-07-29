<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">participant Action Log</h1>
</div>
<br><br>

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
					<td><c:out value="${log.user_id}"/></td>
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
	</c:when>
	<c:otherwise>
	ないです
	</c:otherwise>
	</c:choose>
