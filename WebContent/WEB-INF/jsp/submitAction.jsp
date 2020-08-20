<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
#submitContainer {
	padding: 0;
	width: 500px;
	margin: 0 auto;
	font-weight: 600;
}

.timeBox {
	display: flex;
}

.timeArea, .textBox {
	padding: 15px;
}

input[type="date"], input[type="time"] {
	width: 200px;
	outline: none;
}

input[type='text'], textarea {
	width: 430px;
	outline: none;
}

input[type='text'] {
	height: 40px;
}

textarea {
	resize: none;
	height: 100px;
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
				<input type="date" class="" name="out_date" value="" required />
			</div>
			<div class="timeArea">
				<input type="time" class="" name="out_time" value="" required />
			</div>
		</div>
		<label>帰宅日時</label>
		<div class="timeBox">
			<div class="timeArea">
				<input type="date" class="" name="in_date" value="" required />
			</div>
			<div class="timeArea">
				<input type="time" class="" name="in_time" value="" required />
			</div>
		</div>
		<label>場所</label>
		<div class="textBox">
			<input type="text" class="" name="place" value="" required />
		</div>
		<label for="">理由</label>
		<div class="textBox">
			<textarea required name="reason" value=""></textarea>
		</div>
		<label for="">備考</label>
		<div class="textBox">
			<textarea class="" name="remarks" value=""></textarea>
		</div>
		<button type="submit" class="btn btn-primary">確認</button>
	</form>
</div>
