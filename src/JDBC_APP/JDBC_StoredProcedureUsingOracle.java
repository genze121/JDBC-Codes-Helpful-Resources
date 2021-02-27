package JDBC_APP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/**
 * 
 * @author Tirtha Sharma
 *
 */

public class JDBC_StoredProcedureUsingOracle {

	static {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String USERNAME = "c##admin";
		String PASSWORD = "admin";

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

	public static void cleanupActivity(ResultSet result, CallableStatement cst, Connection con) {

		try {
			if (result != null && !result.isClosed()) {
				result.close();
			}
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
		Scanner scanner = new Scanner(System.in);
		try {
			con = JDBC_StoredProcedureUsingOracle.getConnection();
			cst = con.prepareCall("{call check11(?,?)}");
			System.out.println("Enter the initial Charater:- ");
			String initialCharacter = scanner.next() + "%";
			cst.setString(1, initialCharacter);
			cst.registerOutParameter(2, OracleTypes.VARCHAR);
			cst.execute();
			boolean flag = false;
			System.out.println("-------------------------");
			System.out.println("ENO    ENAME     ESAL");
			System.out.println("-------------------------");

			ResultSet result = (ResultSet) cst.getObject(2);
			while (result.next()) {
				flag = true;
				System.out.println(result.getInt(1) + " -> " + result.getString(2) + " -> " + result.getInt(3));
			}
			if (flag == false) {
				System.out.println("No match records found!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		ResultSet result = null;
		CallableStatement cst = null;
		Connection con = null;

		try {
			JDBC_StoredProcedureUsingOracle.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection is Closed!!!");
			JDBC_StoredProcedureUsingOracle.cleanupActivity(result, cst, con);
		}
	}

}
