<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="sidebar-sticky pt-3">

	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link active"
			href="/ActionLogger/?view=dashboard" style="color: #444;">
				Dashboard </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 活動記録 ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=submit" style="color: #444;"> <span
				data-feather="file-text"></span> <i class="far fa-file-alt"></i>活動記録登録
		</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=activities" style="color: #444;"> <i
				class="fas fa-book"></i>表示
		</a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 管理グループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=createGroup" style="color: #444;">
				新規グループ </a></li>
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=entryGroup" style="color: #444;">
				グループに参加 </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 管理中のグループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<c:forEach var="group" items="${groupList}">
			<li class="nav-item"><a class="nav-link"
				href="/ActionLogger?mgtGroup=${group[0]}&view=participantLog"
				style="color: #444;"> <c:out value="${group[1]}" />
			</a></li>
		</c:forEach>
	</ul>
	
	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 参加中のグループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<c:forEach var="group" items="${entryGroupList}">
			<li class="nav-item"><a class="nav-link"
				href="/ActionLogger?selectGroup=${group.group_id}&view=groupInfo"
				style="color: #444;"> <c:out value="${group.group_name}" />
			</a></li>
		</c:forEach>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== プロフィール ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger?view=profile" style="color:#444;"> プロフィール確認 </a></li>
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/UserCheck?alter=userInfo" style="color:#444;"> プロフィール変更 </a></li>
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/UserCheck?alter=password" style="color:#444;"> パスワード変更 </a></li>
	</ul>
</div>