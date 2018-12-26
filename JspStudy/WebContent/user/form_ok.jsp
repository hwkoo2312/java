<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%--
등록처리를 작성하게요.
등록후 list.jps로 이동
 --%>
 <%
	 String name = request.getParameter("user_name");
	 String email = request.getParameter("user_email");
	 String message = request.getParameter("user_message");
	 String vehicle = request.getParameter("vehicle");
	 String like_item = request.getParameter("like_item");
	 String favcolor = request.getParameter("favcolor");
	 String gender = request.getParameter("gender");
	 String browser = request.getParameter("browser");
	 
	 String[] petAttr=request.getParameterValues("pet");
	 
	 Class.forName("com.mysql.jdbc.Driver");

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmtNext = null;
	PreparedStatement pstmtItem = null;
	ResultSet rs=null;
	ResultSet rsItem=null;
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
		pstmtNext=conn.prepareStatement("SELECT IFNULL(MAX(USER_NID),0)+1 AS NEXT_NID FROM USER");
		rs=pstmtNext.executeQuery();
		int next_nid=0;
		if(rs.next()){
			next_nid= rs.getInt("NEXT_NID");
		}
		// kind next nid
		/*pstmtNext=conn.prepareStatement("SELECT IFNULL(MAX(USER_LIKE_ITEM_NID),0)+1 AS NEXT_NID FROM USER_LIKE_ITEM");
		rsItem=pstmtNext.executeQuery();
		int user_like_item_next_nid=0;
		if(rsItem.next()){
			user_like_item_next_nid= rsItem.getInt("NEXT_NID");
		}
		*/
		
		String userSql="INSERT INTO   USER "
			       +"( USER_NID  , NAME  ,EMAIL , MESSAGE  , LIKE_ITEM_KIND  , LIKE_ITEM , FAVCOLOR , GENDER , BROWSER  , REGI_DT   )"
			    +   "     VALUES"
			     + "( ?,?,? ,? ,? ,?  ,?  ,? ,?  ,NOW() )";
		
		
		pstmt = conn.prepareStatement(userSql);// 실행할 쿼리 등록 (바인딩까지)
		pstmt.setInt(1, next_nid);
		pstmt.setString(2, name);
		pstmt.setString(3, email);
		pstmt.setString(4, message);
		pstmt.setString(5, vehicle);
		pstmt.setString(6, like_item);
		pstmt.setString(7, favcolor);
		pstmt.setString(8, gender);
		pstmt.setString(9, browser);
		
	 	pstmt.executeUpdate();// 쿼리 실행
	 if(petAttr != null){
		 String userLikeItemSql=" INSERT INTO USER_LIKE_ITEM ( USER_LIKE_ITEM_NID , LIKE_ITEM_NM , USER_NID , REGI_DT  )  "
				+"  VALUES( (SELECT IFNULL(MAX(USER_LIKE_ITEM_NID),0)+1 AS NEXT_NID FROM USER_LIKE_ITEM A ) ,  ?  , ? ,  NOW()  )  ";
		 pstmtItem = conn.prepareStatement(userLikeItemSql);// 실행할 쿼리 등록 (바인딩까지)
		// pstmtItem.setInt(1, user_like_item_next_nid);
		 for(int i=0; i<petAttr.length; i++){
			 pstmtItem.setString(1, petAttr[i]);
			 pstmtItem.setInt(2, next_nid);
			 pstmtItem.executeUpdate();// 쿼리 실행
		 }
	 }
	 
	 	//오류가 발생하지않았으면 commit
	 conn.commit();
	}catch(Exception e){
		conn.rollback();

	}finally {
		//사용한 리소드 해제 순서중요!!!
		if (pstmtItem != null) try { pstmtItem.close(); } catch(SQLException ex) {}
		if (pstmtNext != null) try { pstmtNext.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
 %>