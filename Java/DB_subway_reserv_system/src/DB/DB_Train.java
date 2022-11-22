package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DB_Connect;

public class DB_Train {

	// 열차 테이블 생성
	static int createTable_Train() {
		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String check = "SELECT COUNT(*) FROM sqlite_master WHERE Name = 'TRAIN'";
		String sql = "CREATE TABLE TRAIN (TRAIN_NUM INTEGER PRIMARY KEY AUTOINCREMENT, TRAIN_CLASS VARCHAR(255));";
		int count = 0;

		PreparedStatement pstmt; // 리턴 없는 쿼리
		Statement stmt; // 리턴 있는 쿼리
		ResultSet rs; // 리턴값
		try {

			// 테이블 존재 유무 확인
			stmt = con.createStatement();
			rs = stmt.executeQuery(check);
			rs.next();
			int tableNum = rs.getInt(1);
			// 테이블 없으면 생성
			if (tableNum == 0) {
				pstmt = con.prepareStatement(sql); // 쿼리 전달
				count = pstmt.executeUpdate(); // 쿼리 실행
				System.out.println("TRAIN 테이블 생성");

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	
	// 열차 추가
	public static int insertTrain(String trainClass) {
		// 테이블 체크
		createTable_Train();

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String sql = "INSERT INTO TRAIN (TRAIN_CLASS) VALUES(\'"
				+ trainClass +  "\')";
		PreparedStatement pstmt;
		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			System.out.println("TRAIN INSERT");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void main(String[] args) {
			// TODO Auto-generated method stub

		insertTrain("새마을");
		}

}
