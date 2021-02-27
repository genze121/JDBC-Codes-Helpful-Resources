package JDBC_APP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class JDBC_StoredProcedure3 {

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

	public static void cleanupActivity(CallableStatement cst, Connection con) {

		try {
			if (cst != null && !cst.isClosed()) {
				cst.close();
			}
			if (con != null && !con.isClosed()) {
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
			con = JDBC_StoredProcedure3.getConnection();
			String sqlQuery = "{call getInfo(?,?,?)}";
			cst = con.prepareCall(sqlQuery);
			cst.setInt(1, 1001);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.registerOutParameter(3, Types.INTEGER);

			cst.execute();

			System.out.println("Employee Name:- " + cst.getString(2));
			System.out.println("Employee Salary:- " + cst.getString(3));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		CallableStatement cst = null;
		Connection con = null;
		try {
			JDBC_StoredProcedure3.storedProcedure();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			System.out.println("Connection is Closed!!!");
			JDBC_StoredProcedure3.cleanupActivity(cst, con);
		}
	}

}
