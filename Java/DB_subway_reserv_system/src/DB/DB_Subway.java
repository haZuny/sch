package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import DB.DB_Connect;

public class DB_Subway {

	// 기차역 테이블 생성
	static int createTable_Subway() {
		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String check = "SELECT COUNT(*) FROM sqlite_master WHERE Name = 'SUBWAY'";
		String sql = "CREATE TABLE SUBWAY (SUBWAY_NUM INTEGER PRIMARY KEY, SUBWAY_NAME VARCHAR(255));";
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
				System.out.println("SUBWAY 테이블 생성");

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// 기차역 추가
	public static int insertTrain(int subwayNum, String subwayName) {
		// 테이블 체크
		createTable_Subway();

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String sql = "INSERT INTO SUBWAY  VALUES(" + subwayNum + ", \'" + subwayName + "\')";
		PreparedStatement pstmt;
		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			System.out.println("SUBWAY INSERT");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 전체 목록 조회
	public static ArrayList<HashMap<String, String>> getSubwayList() {
		ArrayList<HashMap<String, String>> subwayList = new ArrayList<>();

		// 테이블 체크
		createTable_Subway();

		Statement stmt;
		ResultSet rs = null;
		String sql = "select * from SUBWAY";
		String returnS = null;

		Connection con = DB_Connect.connectSQL();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				subwayList.add(new HashMap<>());
				subwayList.get(subwayList.size() - 1).put("subway_num", rs.getString(1));
				subwayList.get(subwayList.size() - 1).put("subway_name", rs.getString(2));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subwayList;
	}

//	public static void main(String[] args) {
//			// TODO Auto-generated method stub
//
//		insertTrain(1, "신창역");
//		insertTrain(2, "온양온천역");
//		insertTrain(3, "배방역");
//		insertTrain(4, "탕정역");
//		insertTrain(5, "아산역");
//		}

}
