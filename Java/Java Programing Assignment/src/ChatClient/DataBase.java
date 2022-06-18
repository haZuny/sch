package ChatClient;

import java.sql.*;

public class DataBase {

	// db 연결
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/chatProgram?characterEncoding=UTF-8&serverTimezone=UTC";
		String id = "root";
		String password = "hj12099";
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("db 연결 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("db 드라이버 찾을수 없음");
		} catch (SQLException e) {
			System.out.println("db 연결 실패");
		}
		return con;
	}

	// User 정보 추가
	public static int addUser(String ID, String PW) throws SQLException {
		Connection con = makeConnection();

		try {
			Statement stmt = con.createStatement();

			// 일치하는 아이디 여부 검색, 실패하면 -1 반환
			String sqlS = "SELECT * FROM USERS WHERE ID = \"" + ID + "\"";
			System.out.println(sqlS);
			ResultSet rs = stmt.executeQuery(sqlS);
			while (rs.next()) {
				con.close();
				return -1;
			}

			// 정보 추가
			sqlS = "INSERT INTO USERS (ID, PW) VALUES " + "('" + ID + "','" + PW + "')";
			System.out.println(sqlS);
			int i = stmt.executeUpdate(sqlS);

			// 실패
			if (i != 1) {
				con.close();
				System.out.println("레코드 추가 실패");
				return -1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		con.close();
		return 0;
	}

	// ID, PW 일치 여부 확인
	public static int signIn(String ID, String PW) throws SQLException {
		Connection con = makeConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlS = "SELECT * FROM USERS WHERE ID = \"" + ID + "\" AND PW = \"" + PW + "\"";
			System.out.println(sqlS);
			ResultSet rs = stmt.executeQuery(sqlS);

			// ID, PW 존재하면 port 반환
			while (rs.next()) {
				int port = rs.getInt("PORT");
				con.close();
				return port;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		con.close();
		// 발견 못하면 -1 반환
		return -1;
	}
//	
//	// port 번호 반환
//	public static int getPort(String ID) {
//		
//	}
}
