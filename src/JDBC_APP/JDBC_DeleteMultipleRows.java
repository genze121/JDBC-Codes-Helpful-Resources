package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_DeleteMultipleRows {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteMultipleRows() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Successful Connection.. now perform the multiple deletion operation....");

		Statement statement_Validate = conn.createStatement();

		Scanner scanner = new Scanner(System.in);
		int cutOff = 0;
		System.out.println("Enter the cutoff Salary:- ");
		cutOff = scanner.nextInt();

		String deleteMultipleQuery = String.format("delete from Emp where salary>=%d", cutOff);

		int deleteRows = statement_Validate.executeUpdate(deleteMultipleQuery);

		if (deleteRows > 0) {
			System.out.println("Rows Deleted Successfully!!!");
		} else {
			System.out.println("Rows are not Deleted!!!");
		}

		System.out.println("The no of rows Deleted:- " + deleteRows);

		System.out.println("Connection is closed!!!");
		conn.close();
	}

	public static void main(String[] args) {

		try {
			JDBC_DeleteMultipleRows.deleteMultipleRows();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
