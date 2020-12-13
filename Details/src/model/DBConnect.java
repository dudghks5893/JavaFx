package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USER = "scott";
	private final String PASSWORD = "1234";

	Connection conn = null;

	public Connection getConnection(){

		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. DB연결							DB위치 , 유저네임 , 패스워드
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args) {
		DBConnect connect = new DBConnect();

		Connection conn = connect.getConnection(); //DB 연결 객체
		Statement stmt = null; // SQL 실행 하기 위한 객체
		ResultSet rs = null; // SQL 실행 후 결과 리턴


		String sql = "SELECT * FROM review";


		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getNString("text"));
				System.out.println(rs.getNString("maskid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
