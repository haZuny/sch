package ChatClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

	// db ����
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/chatProgram?characterEncoding=UTF-8&serverTimezone=UTC";
		String id = "root";
		String password = "hj12099";
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("db ���� �Ϸ�");
		} catch (ClassNotFoundException e) {
			System.out.println("db ����̹� ã���� ����");
		} catch (SQLException e) {
			System.out.println("db ���� ����");
		}
		return con;
	}

	// User ���� �߰�(ȸ������)
	public static int addUser(String ID, String PW) throws SQLException {
		Connection con = makeConnection();

		try {
			Statement stmt = con.createStatement();

			// ��ġ�ϴ� ���̵� ���� �˻�, �����ϸ� -1 ��ȯ
			String sqlS = "SELECT * FROM USERS WHERE ID = \"" + ID + "\"";
			System.out.println(sqlS);
			ResultSet rs = stmt.executeQuery(sqlS);
			while (rs.next()) {
				con.close();
				return -1;
			}

			// ���� �߰�
			sqlS = "INSERT INTO USERS (ID, PW) VALUES " + "('" + ID + "','" + PW + "')";
			System.out.println(sqlS);
			int i = stmt.executeUpdate(sqlS);

			// ����
			if (i != 1) {
				con.close();
				System.out.println("���ڵ� �߰� ����");
				return -1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		con.close();
		return 0;
	}

	// ID, PW ��ġ ���� Ȯ��
	public static int signIn(String ID, String PW) throws SQLException {
		Connection con = makeConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlS = "SELECT * FROM USERS WHERE ID = \"" + ID + "\" AND PW = \"" + PW + "\"";
			System.out.println(sqlS);
			ResultSet rs = stmt.executeQuery(sqlS);

			// ID, PW �����ϸ� port ��ȯ
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
		// �߰� ���ϸ� -1 ��ȯ
		return -1;
	}
	
	//ID�� ��Ʈ �˻��Ͽ� ��ȯ
	static int searchID(String ID) throws SQLException {
		Connection con = makeConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlS = "SELECT ID, PORT FROM USERS WHERE ID = \"" + ID +  "\"";
			System.out.println(sqlS);
			ResultSet rs = stmt.executeQuery(sqlS);

			// ID �����ϸ� port ��ȯ
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
		// �߰� ���ϸ� -1 ��ȯ
		return -1;
	}
	
	
	// ��ü ģ�� ��� ��ȯ
	public static HashMap getFriendsID() throws SQLException{
		HashMap<String, Integer> friendData = new HashMap<>();
		
		Connection con = makeConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlS = "SELECT ID, PORT FROM USERS";
			System.out.println(sqlS);
			ResultSet rs = stmt.executeQuery(sqlS);

			// ID �����ϸ� port ��ȯ
			while (rs.next()) {
				friendData.put(rs.getString("ID"), rs.getInt("PORT"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		con.close();
		return friendData;
	}
	
}