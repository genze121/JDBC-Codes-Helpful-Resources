package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC_DeleteSingleRow {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteSingleRecord() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/CreateTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Connection got initialized perform the delete operation......");

		Statement statement_Check = conn.createStatement();

		String deleteQuery = "delete from Emp where ename='Sultan'";

		int deletedRecord = statement_Check.executeUpdate(deleteQuery);

		if (deletedRecord > 0) {
			System.out.println("The record is deleted Successfully!!!");
		} else {
			System.out.println("The record is not deleted Successfully!!!");
		}

		System.out.println("The no of records deleted is :- " + deletedRecord);

		System.out.println("Connection is closed!!!");
		conn.close();
	}

	public static void main(String[] args) {

		try {
			JDBC_DeleteSingleRow.deleteSingleRecord();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
