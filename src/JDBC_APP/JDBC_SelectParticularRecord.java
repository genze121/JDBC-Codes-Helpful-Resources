package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_SelectParticularRecord {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void selectParticularColumn() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Successfull Connection, now perform the remaining operations......");

		Statement statementValidate = conn.createStatement();

		System.out.println("ENAME\t  ESAL");
		System.out.println("-------------------");

		boolean flag = false;

		String sqlQuery = "select ename,salary from Emp";

		ResultSet result = statementValidate.executeQuery(sqlQuery);
		while (result.next()) {
			flag = true;
			System.out.println(result.getString(1) + " -> " + result.getInt(2));
		}

		if (flag == false) {
			System.out.println("No matched records found!!!");
		}

		System.out.println("Connection is closed!!!");
		conn.close();

	}

	public static void main(String[] args) throws Exception {
		JDBC_SelectParticularRecord.selectParticularColumn();
	}

}
