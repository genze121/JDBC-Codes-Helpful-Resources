package JDBC_APP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class JDBC_StoredProcedure {

	static {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		String JDBC_URL = "jdbc:mysql://localhost:3306/Proc";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			if (con != null) {
				System.out.println("Connection is Established Successfully!!!");
			} else {
				System.out.println("Connection is not Established Successfully!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void cleanupActivity(Connection con) {

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void storedProcedure() {

		Connection con = null;
		CallableStatement cst = null;

		try {
			con = JDBC_StoredProcedure.getConnection();
			cst = con.prepareCall("{call addProc(?,?,?)}");
			cst.setInt(1, 100);
			cst.setInt(2, 400);

			cst.registerOutParameter(3, Types.INTEGER);
			cst.execute();

			int result = cst.getInt(3);

			System.out.println("Result is:- " + result);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Connection con = null;
		try {
			JDBC_StoredProcedure.storedProcedure();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection is CLosed Successfully!!!");
			JDBC_StoredProcedure.cleanupActivity(con);
		}
	}

}
