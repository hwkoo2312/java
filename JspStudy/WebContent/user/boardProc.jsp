<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="b" class="com.koo.usebean.Board" />
<jsp:setProperty name="b" property="*" />
<jsp:setProperty name="b" property="password" value="<%=b.getId()%>" /><!-- 비밀번호 숨기기위해서 아이디로 바꿔준다. -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
</head>
<body>
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<th>아이디</th>
			<td><jsp:getProperty name="b" property="id" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><jsp:getProperty name="b" property="password" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><jsp:getProperty name="b" property="title" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><jsp:getProperty name="b" property="content" /></td>
		</tr>
	</table>
</body>
</html>

