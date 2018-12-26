<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%--
삭제
 --%>
 <%
 	request.setCharacterEncoding("utf-8");
 	String user_nid = request.getParameter("user_nid");
	 
 	Class.forName("com.mysql.jdbc.Driver");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	try {
		// 데이터베이스 접속 정보
		// 데이터베이스 서버 ip, port, 데이터베이스,계정
		String jdbcDriver = "jdbc:mysql://192.168.99.100:3306/JSP_STUDY?" 
										+"useUnicode=true&autoReconnect=true&useTimezone=true&serverTimezone=UTC&connectTimeout=3000&useSSL=false&noAccessToProcedureBodies=true&characterEncoding=utf8";
		String dbUser = "hwkoo";
		String dbPass = "3927";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass); // connection 객체 얻어온다
		conn.setAutoCommit(false);
		// user next nid
		
		String userSql="DELETE FROM USER WHERE USER_NID =? ";
		
		pstmt = conn.prepareStatement(userSql);// 실행할 쿼리 등록 (바인딩까지)
		pstmt.setString(1, user_nid);
		
	 	pstmt.executeUpdate();// 쿼리 실행
	 	
		 conn.commit();
	}catch(Exception e){
			//conn.rollback();
	}finally {
			//사용한 리소드 해제 순서중요!!!
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
	}
 %>