<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
#submitContainer {
	padding: 0;
	width: 700px;
	margin: 0 auto;
}

.timeBox {
	display: flex;
}

.timeArea{
	padding: 1em;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Submit Action</h1>
</div>
<div id="submitContainer">
	<form action="/ActionLogger/Submit" method="post">
		<label>外出日時</label>
		<div class="timeBox">
			<div class="timeArea">
				<input type="date" class="" name="out_date" required />
			</div>
			<div class="timeArea">
				<input type="time" class="" name="out_time" required />
			</div>
		</div>
		<div>
			<label>帰宅日時</label>
			<div>
				<input type="date" class="" name="in_date" required />
			</div>
			<div>
				<input type="time" class="" name="in_time" required />
			</div>
		</div>
		<div>
			<label>場所</label> <input type="text" class="" name="place" required />
		</div>
		<div>
			<label for="">理由</label>
			<textarea required name="reason"></textarea>
		</div>
		<div>
			<label for="">備考</label>
			<textarea class="" name="remarks"></textarea>
		</div>
		<button type="submit" class="btn btn-primary">送信</button>
	</form>
</div>
