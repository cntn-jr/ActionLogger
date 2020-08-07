<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Submit Action</h1>
</div>
<form action="/ActionLogger/Submit" method="post" style="padding: 2em 0 0 10em;">
	<div class="form-group row">
		<label>外出日時</label>
		<div class="col-md-3">
			<input type="date" class="form-control" name="out_date">
		</div>
		<div class="col-md-3">
			<input type="time" class="form-control" name="out_time">
		</div>
	</div>
	<div class="form-group row">
		<label>帰宅日時</label>
		<div class="col-md-3">
			<input type="date" class="form-control" name="in_date">
		</div>
		<div class="col-md-3">
			<input type="time" class="form-control" name="in_time">
		</div>
	</div>
	<div class="form-group">
		<label>場所</label> <input type="text" class="form-control col-7"
			name="place">
	</div>
	<div class="mb-3">
		<label for="validationTextarea">理由</label>
		<textarea class="form-control col-7" required name="reason"></textarea>
	</div>
	<div class="mb-3">
		<label for="validationTextarea">備考</label>
		<textarea class="form-control col-7" name="remarks"></textarea>
	</div>
	<button type="submit" class="btn btn-primary">送信</button>
</form>
