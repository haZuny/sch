package ChatClient;

import java.sql.*;

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

	// User ���� �߰�
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
//	
//	// port ��ȣ ��ȯ
//	public static int getPort(String ID) {
//		
//	}
}
