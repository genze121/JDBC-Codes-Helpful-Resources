package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_UpdateMultipleRecordDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void multipleRecord() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/CreateTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After the successful connection perform the multiple updation operation.....");

		Statement statement_Test = conn.createStatement();
		Scanner scanner = new Scanner(System.in);
		int increment_sal = 0, salaryRange = 0;
		System.out.println("Enter the Increment Salary :- ");
		increment_sal = scanner.nextInt();
		System.out.println("Enter the salary range :- ");
		salaryRange = scanner.nextInt();

		String updateMultipleRecord = String.format("update Emp set salary=salary + %d where salary<%d", increment_sal,
				salaryRange);

		int updatedRows = statement_Test.executeUpdate(updateMultipleRecord);

		if (updatedRows > 0) {
			System.out.println("Data is updated Successfully!!!");
		} else {
			System.out.println("Data is not updated Successfully!!!");
		}

		System.out.println("Total no of records updated :- " + updatedRows);

		System.out.println("Connection is closed!!!");

		conn.close();
	}

	public static void main(String[] args) {
		try {
			JDBC_UpdateMultipleRecordDemo.multipleRecord();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
