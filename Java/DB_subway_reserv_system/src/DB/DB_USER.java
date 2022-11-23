package DB;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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
		// 테이블 체크
		createTable_User();

		Statement stmt;
		ResultSet rs;
		String sql = "select count(*) from USER where USER_NAME = \'" + userName + "\'";
		boolean check = true; // true: 중복, false: 안중복

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			int overlapNum = rs.getInt(1);
			if (overlapNum >= 1)
				check = true;
			else
				check = false;

			con.close();
		} catch (Exception e) {
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
				+ userName + "\', \'" + phoneNum + "\', \'SILVER\', \'" + cardNum + "\', \'" + password
				+ "\', \'FALSE\')";
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

	// 아이디로 유저 정보 반환
	public static HashMap<String, String> getUserByUserName(String userName) {
		// 테이블 체크
		createTable_User();

		Statement stmt;
		ResultSet rs = null;
		String sql = "select * from USER where USER_NAME = \'" + userName + "\'";
		HashMap<String, String> userData = new HashMap<>();

		Connection con = DB_Connect.connectSQL();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			userData.put("user_num", rs.getString(1));
			userData.put("user_name", rs.getString(2));
			userData.put("phone_number", rs.getString(3));
			userData.put("user_class", rs.getString(4));
			userData.put("card_number", rs.getString(5));
			userData.put("password", rs.getString(6));
			userData.put("accept", rs.getString(7));

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userData;
	}

	// 전체 유저 정보 반환
	public static ArrayList<HashMap<String, String>> getUserList() {
		// 테이블 체크
		createTable_User();

		Statement stmt;
		ResultSet rs = null;
<<<<<<< HEAD
		String sql = "select * from USER where ACCEPT = \'TRUE\'";
=======
		String sql = "select * from USER";
>>>>>>> 1e89b12eaf1ce7a850b03c5aa200d1d7ab473bf1
		ArrayList<HashMap<String, String>> userList = new ArrayList<>();

		Connection con = DB_Connect.connectSQL();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userList.add(new HashMap<>());
				userList.get(userList.size() - 1).put("user_num", rs.getString(1));
				userList.get(userList.size() - 1).put("user_name", rs.getString(2));
				userList.get(userList.size() - 1).put("phone_number", rs.getString(3));
				userList.get(userList.size() - 1).put("user_class", rs.getString(4));
				userList.get(userList.size() - 1).put("card_number", rs.getString(5));
				userList.get(userList.size() - 1).put("password", rs.getString(6));
				userList.get(userList.size() - 1).put("accept", rs.getString(7));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	// 가입대기 유저 정보 반환
	public static ArrayList<HashMap<String, String>> getSignUpAcceptUserList() {
		// 테이블 체크
		createTable_User();

		Statement stmt;
		ResultSet rs = null;
		String sql = "select * from USER WHERE ACCEPT = \'FALSE\'";
		ArrayList<HashMap<String, String>> userList = new ArrayList<>();

		Connection con = DB_Connect.connectSQL();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userList.add(new HashMap<>());
				userList.get(userList.size() - 1).put("user_num", rs.getString(1));
				userList.get(userList.size() - 1).put("user_name", rs.getString(2));
				userList.get(userList.size() - 1).put("phone_number", rs.getString(3));
				userList.get(userList.size() - 1).put("user_class", rs.getString(4));
				userList.get(userList.size() - 1).put("card_number", rs.getString(5));
				userList.get(userList.size() - 1).put("password", rs.getString(6));
				userList.get(userList.size() - 1).put("accept", rs.getString(7));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

<<<<<<< HEAD
	// 특정 필드 반환
	public static String getSpecificField(String userNum, String field) {
		// 테이블 체크
		createTable_User();

		Statement stmt;
		ResultSet rs = null;
		String sql = "select " + field + " from USER where USER_NUM = " + userNum;
		String returnS = null;

		Connection con = DB_Connect.connectSQL();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			returnS = rs.getString(1);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnS;
	}

=======
>>>>>>> 1e89b12eaf1ce7a850b03c5aa200d1d7ab473bf1
	// 유저 제거
	public static int deleteUser(String userNum) {
		// 테이블 체크
		createTable_User();

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String sql = "delete from USER where USER_NUM = " + userNum;
		PreparedStatement pstmt;
		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			System.out.println("USER DELETE");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

<<<<<<< HEAD
	// 유저 필드 업데이트
	public static int updateUser(String userNum, String field, String value) {
		// 테이블 체크
		createTable_User();

		Connection con = DB_Connect.connectSQL(); // 연결 객체 생성
		String sql = "update USER set " + field + " = \'" + value + "\' where USER_NUM = " + userNum;
		PreparedStatement pstmt;
		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			System.out.println("USER UPDATE");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

=======
>>>>>>> 1e89b12eaf1ce7a850b03c5aa200d1d7ab473bf1
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getUserList());

	}

}
