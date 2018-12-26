<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 입력 폼</title>
</head>
<body>
	<form action="/user/boardProc.jsp" method="post">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" maxlength="12"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" maxlength="15"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" maxlength="20"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="25" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록">
					<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>

