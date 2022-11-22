package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connect {
	static String dbFileUrl = "jdbc:sqlite:subway.db"; // db 파일 이름

	// DB 연결
	static Connection connectSQL() {
		Connection con = null; // 연결 객체

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(dbFileUrl); // 파일과 연결
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
