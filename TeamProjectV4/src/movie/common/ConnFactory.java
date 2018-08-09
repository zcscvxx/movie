package movie.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// 1,2단계를 거쳐 커넥션객체를 만들어 주는 공장 
public class ConnFactory {
	
	public static Connection getConnection(String dbConfig) {
		ResourceBundle bundle = ResourceBundle.getBundle(dbConfig);
		// 1단계 
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("1단계:드라이버로딩실패~~");
			return null;
		}
		// 2단계 
		String url = bundle.getString("url");
		String user= bundle.getString("user");
		String password = bundle.getString("password");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("2단계:url,user,password점검요망");
			return null;
		}
		return conn;
	}
	// 4단계 
	public static void close(Connection conn) throws SQLException {
		if(conn!=null & !conn.isClosed()) {
			conn.close();
		}
	} 

}
