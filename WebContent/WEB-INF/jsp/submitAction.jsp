<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
	<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Submit</h1>
</div>
	<form action="/ActionLogger/Submit" method="post">
	<div class="form-group">
      <label>日付</label>
      <input type="date" class="form-control" name="date">
    </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label>外出時刻</label>
      <input type="time" class="form-control" name="out_time">
    </div>
    <div class="form-group col-md-6">
      <label>帰宅時刻</label>
      <input type="time" class="form-control" name="out_time">
    </div>
  </div>
  <div class="form-group">
    <label>場所</label>
    <input type="text" class="form-control"　name="reason">
  </div>
  <div class="mb-3">
    <label for="validationTextarea"　name="reason">理由</label>
    <textarea class="form-control" required></textarea>
  </div>
  <div class="mb-3">
    <label for="validationTextarea">備考</label>
    <textarea class="form-control"　name="body"></textarea>
  </div>
  <button type="submit" class="btn btn-primary">送信</button>
</form>
