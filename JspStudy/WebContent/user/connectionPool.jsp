<%@page import="com.jsp.jdbc.connection.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	//conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass); // connection 객체 얻어온다
	
	conn = ConnectionProvider.getConnection();
	
	String userListSql="SELECT  USER_NID , NAME , EMAIL , REGI_DT FROM USER ";
	pstmt =conn.prepareStatement(userListSql);
	rs=pstmt.executeQuery();

%>
 <% 
				  	  int idx=1;
					  while(rs.next()){
				  %>
				    <tr>
				      <th scope="row"><%=idx++ %></th>
				      <td><a href="/user/view.jsp?user_nid=<%=rs.getInt("USER_NID")%>"><%=rs.getString("NAME") %></a></td>
				      <td><%=rs.getString("EMAIL") %></td>
				      <td><%=rs.getString("REGI_DT") %></td>
				    </tr>
				    <%
					  }%>

<%
if(rs!=null)rs.close();
if(pstmt!=null)pstmt.close();
if(conn!=null)conn.close();
%>