<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%

	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	String jdbcDriver = "jdbc:mysql://192.168.99.100:3306/JSP_STUDY?" 
			+"useUnicode=true&autoReconnect=true&useTimezone=true&serverTimezone=UTC&connectTimeout=3000&useSSL=false&noAccessToProcedureBodies=true&characterEncoding=utf8";
String dbUser = "hwkoo";
String dbPass = "3927";	
	conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass); // connection 객체 얻어온다
	
	
	String sch_type=request.getParameter("sch_type");
	String sch_txt=request.getParameter("sch_txt");
	
	String userListSql="SELECT  USER_NID , NAME , EMAIL , REGI_DT FROM USER ";
	
	if("all".equals(sch_type) && !"".equals(sch_txt)){
		userListSql=userListSql+" WHERE ( NAME LIKE ? OR  EMAIL LIKE   ? ) ";//ALL
	}
	if("name".equals(sch_type)  && !"".equals(sch_txt)){
		userListSql=userListSql+" WHERE NAME LIKE  ? ";
	}
	if( "email".equals(sch_type)  && !"".equals(sch_txt)){
		userListSql=userListSql+" WHERE EMAIL LIKE  ? ";
	}
	pstmt =conn.prepareStatement(userListSql);
	
	if("all".equals(sch_type)  && !"".equals(sch_txt)){
		pstmt.setString(1, "%"+sch_txt+"%");
		pstmt.setString(2, "%"+sch_txt+"%");
	}else if( !"".equals(sch_txt) && sch_txt!=null){
		pstmt.setString(1, "%"+sch_txt+"%");
	}
	
	rs=pstmt.executeQuery();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원 리스트</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">  
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</head>
<script>
function search(){
	var sch_type = $("select[name=sch_type]").val();
	var sch_txt = $("input[name=sch_txt]").val();
	location.href="/user/list.jsp?sch_type="+sch_type+"&sch_txt="+sch_txt;
}
</script>
<body>      
	<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbarr bg-dark text-white p-3 mb-2 " style="font-weight: bolder;">회원등록</header>
	<div class="container">
		<div id="search-box">
		        <div class="card mb-2 bg-info text-white">
		        	<div class="card-body">
					    <div class="input-group">
			                <div class="input-group-btn">
				                   <select class="form-control" name="sch_type">
				                   		<option value="all">전체</option>
				                   		<option value="name">이름</option>
				                   		<option value="email">이메일</option>
				                   </select>
							</div>
			                <div class="input-group">
							    <input type="text" class="form-control"  name="sch_txt" placeholder="이름, 이메일" aria-describedby="btnGroupAddon">
								<span class="input-group-addon" id="btnGroupAddon" onclick="search();"><span class="oi" data-glyph="magnifying-glass"></span></span>
							</div>
			            </div>
		            </div>
		        </div>
		</div>
		<div class="card">
			<div class="card-header">회원 리스트</div>
			<div class="card-body">
				<table class="table table-striped table-bordered">
				  <thead class="thead-inverse">
				    <tr>
				      <th>NO</th>
				      <th>이름</th>
				      <th>이메일</th>
				      <th>가입일</th>
				    </tr>
				  </thead>
				  <tbody>
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
				  </tbody>
				</table>
			</div>
			<div class="card-footer">
			   <a href="/user/form.jsp" class="btn btn-primary">등록</a>
			</div>
		</div>
	</div>
</body>
</html>
<%
if(rs!=null)rs.close();
if(pstmt!=null)pstmt.close();
if(conn!=null)conn.close();
%>