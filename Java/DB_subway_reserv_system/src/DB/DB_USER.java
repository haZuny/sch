package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DB_Connect;

public class DB_USER {

	// 회원 테이블 생성
	static int createTable_User() {
		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String check = "SELECT COUNT(*) FROM sqlite_master WHERE Name = 'USER'";
		String sql = "CREATE TABLE USER (USER_NUM INTEGER PRIMARY KEY AUTOINCREMENT, USER_NAME VARCHAR(255), PHONE_NUMBER VARCHAR(255), USER_CLASS VARCHAR(255), CARD_NUMBER VARCHAR(255), PASS_WORD VARCHAR(255), ACCEPT VARCHAR(255));";
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
				System.out.println("USER 테이블 생성");

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// 회원 아이디 중복 체크
	public static boolean checkUserNameOverlap(String userName) {
		Statement stmt;
		ResultSet rs;
		String sql = "select count(*) from USER where USER_NAME = \'" + userName + "\'";
		boolean check = true;

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			int overlapNum = rs.getInt(1);
			if(overlapNum >= 1)
				check = true;
			else
				check = false;
			
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return check;
	}

	// 회원 추가
	public static int insertUser(String userName, String password, String phoneNum, String cardNum) {
		// 테이블 체크
		createTable_User();

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String sql = "INSERT INTO USER (USER_NAME, PHONE_NUMBER, USER_CLASS, CARD_NUMBER, PASS_WORD, ACCEPT) VALUES(\'"
				+ userName + "\', \'" + phoneNum + "\', \'SILVER\', \'" + cardNum + "\', \'" + password + "\', \'FALSE\')";
		PreparedStatement pstmt;
		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			System.out.println("USER INSERT");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	// 가입 대기 회원 조회
//	public static ResultSet searchAcceptUser() {
//		Statement stmt; // 리턴 있는 쿼리
//		ResultSet rs; // 리턴값
//		String sql = 
//		
//		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
//		
//		stmt = con.createStatement();
//		
//		stmt = sql
//	}

//	public static void main(String[] args) {
//			// TODO Auto-generated method stub
//
//			insertUser("hajun", "aaaa", "010-1111-2222", "0000-0000-0000-0000");
//		}

}
