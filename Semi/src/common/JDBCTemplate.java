package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Service, Dao 클래스의 공통부문을 static메소드로 제공
 * 예외처리를 공통 부분에서 작성 하므로, 사용(호출)하는 쪽의 코드를 간결히 할 수 있다.
 */
public class JDBCTemplate {
	private static String driverClass = "oracle.jdbc.OracleDriver";
	
	static{
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			/**
			 * JNDI구조
			 * java:/comp/env/ + jdbc/myoracle
			 */
			DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		//7. 자원반납(conn)
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rset) {
		try {
			if(rset!=null)
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection conn) {
		try {
			if(conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
