package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_MultipleRecord_Insertion {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static int insertMultipleRecord() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/CreateTable";
		String USERNAME = "root";
		String Password = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, Password);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Successfull Connection let's perform the operation for multiple insertion......");

		Statement statementTest = conn.createStatement();

		Scanner scan = new Scanner(System.in);

		int rowsAffected = 0;

		while (true) {
			int eno = 0;
			String ename = null;
			int esal = 0;
			System.out.println("Enter the Employee number :- ");
			eno = scan.nextInt();
			System.out.println("Enter the Employee name :- ");
			ename = scan.next();
			System.out.println("Enter the Employee Salary :- ");
			esal = scan.nextInt();

			String sqlMultipleInsertion = String.format("insert into Emp(eno,ename,salary) values(%d,'%s',%d)", eno,
					ename, esal);

			rowsAffected = statementTest.executeUpdate(sqlMultipleInsertion);

			if (rowsAffected > 0) {
				System.out.println("The data is inserted successfully!!!");
			} else {
				System.out.println("The data is not inserted successfully!!!");
			}

			System.out.println("Do you want to insert more records? If you want then type [Yes/No]");

			String option = scan.next();

			if (option.equalsIgnoreCase("NO")) {
				break;
			}

		}

		System.out.println("Connection Closed Successfully!!!");

		conn.close();

		return rowsAffected;

	}

	public static void main(String[] args) {

		try {
			JDBC_MultipleRecord_Insertion.insertMultipleRecord();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
