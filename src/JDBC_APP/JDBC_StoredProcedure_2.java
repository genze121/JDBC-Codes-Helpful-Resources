package JDBC_APP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class JDBC_StoredProcedure_2 {

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
			con = JDBC_StoredProcedure_2.getConnection();
			cst = con.prepareCall("{call checkEmp(?,?)}");
			cst.setInt(1, 1001);
			cst.registerOutParameter(2, Types.INTEGER);
			boolean b = cst.execute();
			if (b == true) {
				System.out.println("Procedure didn't work properly!!!");
			} else {
				int salary = cst.getInt(2);
				System.out.println("Salary is:- " + salary);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Connection con = null;
		try {
			JDBC_StoredProcedure_2.storedProcedure();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection is Closed Successfully!!!");
			JDBC_StoredProcedure_2.cleanupActivity(con);
		}
	}

}
