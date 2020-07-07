<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<form action="/ActionLogger/Submit" method="post">
		日付：<input type="date" name="date"><br/>
		外出時刻：<input type="time" name="out_time"><br/>
		帰宅時刻：<input type="time" name="in_time"><br/>
		場所：<input type="text" name="place"><br/>
		理由：<textarea rows="" cols="" name="reason"></textarea><br/>
		備考：<textarea rows="" cols="" name="body"></textarea><br/>
	</form>
