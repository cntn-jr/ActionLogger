<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
#submitContainer {
	padding: 0;
	width: 700px;
	margin: 0 auto;
	font-weight: 600;
}

#container {
	background: #f8f9fa;
	border: #dfe6e9 solid 1px;
	color: #4b4b4b;
	padding: 20px;
	border-radius: 10px;
	width: 700px;
	margin: auto;
}

.timeBox {
	display: flex;
}

.timeArea, .textBox {
	padding: 15px;
}

input[type="date"], input[type="time"] {
	outline: none;
}

input[type='text'], textarea {
	outline: none;
}

input[type='text'] {
	height: 40px;
}

textarea {
	resize: none;
	height: 100px;
}

.btn {
	color: #fff;
	background-color: #778beb;
	border-color: #546de5;
	border-radius: 10px;
	height: 40px;
	width: 200px;
	font-weight: bold;
	margin: 30px 0;
}

.btn:hover {
	color: #fff;
	background-color: #546de5;
	border-color: #546de5;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">活動記録登録</h1>
</div>
<div id="submitContainer">
	<form action="/ActionLogger/Submit" method="post">
		<div id="container">
			<label>外出日時</label>
			<div class="timeBox">
				<div class="timeArea">
					<input type="date" class="form-control" name="out_date" value="" required />
				</div>
				<div class="timeArea">
					<input type="time" class="form-control" name="out_time" value="" required />
				</div>
			</div>
			<label>帰宅日時</label>
			<div class="timeBox">
				<div class="timeArea">
					<input type="date" class="form-control" name="in_date" value="" required />
				</div>
				<div class="timeArea">
					<input type="time" class="form-control" name="in_time" value="" required />
				</div>
			</div>
			<label>場所</label>
			<div class="textBox">
				<input type="text" class="form-control" name="place" value="" required />
			</div>
			<label for="">理由</label>
			<div class="textBox">
				<textarea required class="form-control" name="reason" value=""></textarea>
			</div>
			<label for="">備考</label>
			<div class="textBox">
				<textarea class="form-control" name="remarks" value=""></textarea>
			</div>
		</div>
		<button type="submit" class="btn">確認</button>
	</form>
</div>
