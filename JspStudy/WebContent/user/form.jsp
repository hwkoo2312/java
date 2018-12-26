<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록해보아요</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</head>
<script>
	$(document).ready(function() {
		$(".like_item_kind").on("click", function() {
			// 클릭한게 자동차이면 
			$(".like_item_kind_bike").find("select").prop("disabled",false).end().show();
			$(".like_item_kind_car").find("select").prop("disabled",false).end().show();
			
			if($(this).val() =="Car"){
				$(".like_item_kind_bike").hide();
				$(".like_item_kind_bike").find("select").prop("disabled",true);
			}else{
				$(".like_item_kind_car").hide();
				$(".like_item_kind_car").find("select").prop("disabled",true);
			}
		});
	});
	
	function add(){
		$("#userFm").submit();
	}
</script>
<body>
	<div class="container">
		<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbarr bg-dark text-white p-3 mb-2 " style="font-weight: bolder;">회원등록</header>
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">Card title</h5>
				<form action="/user/form_ok.jsp" id="userFm" method="post" >
					<div class="form-group">
						<label for="name">Name:</label> <input type="text" id="name" name="user_name" class="form-control">
					</div>
					<div class="form-group">
						<label for="mail">E-mail:</label> <input type="email" id="mail" name="user_email" class="form-control">
					</div>
					<div class="form-group">
						<label for="msg" id="message">Message:</label>
						<textarea id="msg" name="user_message" class="form-control"></textarea>
					</div>
					<div  class="form-check">
						<label for="msg" class="col-form-label">좋아하는 것:</label><br> 
						<div class=" form-check-inline  ">
							  <input class=" like_item_kind" checked="checked" type="radio" id="inlineCheckbox1" name="vehicle"  value="Bike">
							  <label class="form-check-label" for="inlineCheckbox1"> I have a bike</label>
						</div>
						<div class=" form-check-inline ">
							  <input class=" like_item_kind" type="radio" id="inlineCheckbox2" name="vehicle" value="Car">
							  <label class="form-check-label" for="inlineCheckbox2">I have a car</label>
						</div>
					</div>
					<div class="form-group like_item_kind_bike">
						<label for="mail">좋아하는 자전거:</label> 
						<select name="like_item"  class="form-control">
							<option value="garyfisher">고스트</option>
							<option value="fiat">구와하라</option>
							<option value="dahon">다혼</option>
							<option value="masi">마지</option>
						</select>
					</div>
					<div class="form-group like_item_kind_car " style="display: none;">
						<label for="mail">좋아하는 차:</label> 
						<select name="like_item" class="form-control" disabled="disabled">
							<option value="volvo">Volvo</option>
							<option value="saab">Saab</option>
							<option value="fiat">Fiat</option>
							<option value="audi">Audi</option>
						</select>
					</div>

					<div class="form-group">
						<label for="mail">좋아하는 색:</label> 
						<input type="color" name="favcolor" >
					</div>
					<div class="form-group">
						<label for="mail">성별:</label><br> 
						<input type="radio" name="gender" value="male" checked> Male<br> 
						<input type="radio" name="gender" value="female"> Female<br>
						<input type="radio" name="gender" value="other"> Other
					</div>
					<div class="form-group">
						<label for="mail">현재 브라우저:</label> 
						<input list="browsers" name="browser">
						<datalist id="browsers">
							<option value="Internet Explorer">
							<option value="Firefox">
							<option value="Chrome">
							<option value="Opera">
							<option value="Safari">
						</datalist>
					</div>
					<div class="form-group">
						<label for="mail"> 좋아하는 동물:</label> 
						<input type="checkbox" name="pet" value="dog" class="form-control">강아지 
						<input type="checkbox" name="pet" value="cat" class="form-control">고양이  
						<input type="checkbox" name="pet" value="pig" class="form-control">돼지
					</div>
				</form>
				<div class="card-footer text-muted">
					<div class="btn-group-toggle" data-toggle="buttons">
						<button type="button" onclick="add();return false;" class="btn btn-primary">등록</button>
						<button type="button" class="btn btn-secondary">목록</button>
					</div>
				</div>
			</div>
		</div> 
	</div>
</body>
</html>