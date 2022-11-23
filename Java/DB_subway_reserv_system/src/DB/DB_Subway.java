package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DB_Connect;

public class DB_Subway {

	// 시간표 테이블 생성
	static int createTable_Schedule() {
		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String check = "SELECT COUNT(*) FROM sqlite_master WHERE Name = 'SCHEDULE'";
		String sql = "CREATE TABLE SCHEDULE (SUBWAY_NUM INTEGER, TRAIN_NUM INTEGER, DIRECTION VARCHAR(255), TIME VARCHAR(255), "
				+ "FOREIGN KEY(SUBWAY_NUM) REFERENCES SUBWAY(SUBWAY_NUM) ON UPDATE CASCADE, "
				+ "FOREIGN KEY(TRAIN_NUM) REFERENCES TRAIN(TRAIN_NUM) ON UPDATE CASCADE)";
//		String sql = "CREATE TABLE SCHEDULE (SUBWAY_NUM INTEGER, TRAIN_NUM INTEGER, DIRECTION VARCHAR(255), TIME VARCHAR(255), "
//				+ "CONSTRAINT SUBWAY_NUM_FK FOREIGN KEY(SUBWAY_NUM) REFERENCES SUBWAY(SUBWAY_NUM), "
//				+ "CONSTRAINT TRAIN_NUM_FK FOREIGN KEY(TRAIN_NUM) REFERENCES TRAIN(TRAIN_NUM))";
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
				pstmt = con.prepareStatement("PRAGMA foreign_keys = 1");
				count = pstmt.executeUpdate();
				System.out.println("SCHEDULE 테이블 생성");

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	
	// 시간표 추가
	public static int insertSchedule(int subwayNum, int trainNum, String direction, String time) {
		// 테이블 체크
		createTable_Schedule();

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String sql = "INSERT INTO SCHEDULE  VALUES("
				+ subwayNum + ", " + trainNum +  ", \'" + direction + "\', \'" + time + "\')";
		PreparedStatement pstmt;
		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			System.out.println("SCHEDULE INSERT");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

//	public static void main(String[] args) {
//			// TODO Auto-generated method stub
//		}

}
