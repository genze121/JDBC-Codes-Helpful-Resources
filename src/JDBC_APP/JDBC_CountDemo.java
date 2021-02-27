package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_CountDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void countRecord() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After the successfull connection perform the remaining operations......");

		Statement statement_check = conn.createStatement();

		String sqlQuery = "select count(*) from Emp";
		ResultSet result = statement_check.executeQuery(sqlQuery);

		if (result.next()) {
			System.out.println("Total no of Records/Rows:- " + result.getInt(1));
		}

		System.out.println("Closed the Connection!!!");
		conn.close();
	}

	public static void main(String[] args) throws Exception {
		JDBC_CountDemo.countRecord();
	}

}
