package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC_PreparedStatementDelete {

	static {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			if (con != null) {
				System.out.println("Connection is Established!!!");
			} else {
				System.out.println("Connection is not Established successfully!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void cleanupActivity(PreparedStatement pst, Connection conn) {

		try {
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteOperation() {

		Connection con = null;
		String sqlQuery = "delete from Emp where ename=?";
		PreparedStatement pst = null;
		try {
			con = JDBC_PreparedStatementDelete.getConnection();
			pst = con.prepareStatement(sqlQuery);
			pst.setString(1, "Hero");
			int updateCount1 = pst.executeUpdate();
			if (updateCount1 == 1 || updateCount1 > 0) {
				System.out.println("Record 1 Deleted:- " + updateCount1);
			} else {
				System.out.println("Record 1 Not Deleted!!!");
			}
			System.out.println("Reusing PreparedStatement to delete one more record...");

			pst.setString(1, "Tesla");
			int updateCount2 = pst.executeUpdate();

			if (updateCount2 == 1 || updateCount2 > 0) {
				System.out.println("Record 2 Deleted:- " + updateCount2);
			} else {
				System.out.println("Record 2 Not Deleted!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			JDBC_PreparedStatementDelete.deleteOperation();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection is Closed Successfully!!!");
			JDBC_PreparedStatementDelete.cleanupActivity(pst, con);
		}
	}

}
