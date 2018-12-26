<%@page import="com.jsp.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="com.jsp.user.service.UserService" %>

<jsp:useBean id="user" class="com.jsp.user.User" />
<jsp:setProperty name="user" property="*" />
<%
out.println(user.getBrowser());
int result = ();
%>
<%=result %>