package com.jsp.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jsp.jdbc.JdbcUtil;
import com.jsp.user.User;

public class UserDao {
	private static UserDao UserDao = new UserDao();
	
	private UserDao(){
		
	}
	
	public static UserDao getInstance(){
		return UserDao;
	}
	
	public int insert(Connection conn, User user){
		int result=0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtNext = null;
		ResultSet rs=null;
		try {
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
			pstmt.setString(2, user.getUser_name());
			pstmt.setString(3, user.getUser_email());
			pstmt.setString(4, user.getUser_message());
			pstmt.setString(5, user.getVehicle());
			pstmt.setString(6, user.getLike_item());
			pstmt.setString(7, user.getFavcolor());
			pstmt.setString(8, user.getGender());
			pstmt.setString(9, user.getBrowser());
			
		 	result = pstmt.executeUpdate();// 쿼리 실행
		 	
		 	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmtNext);
			JdbcUtil.close(pstmt);
			
		}
		return result;
	
	}
}
