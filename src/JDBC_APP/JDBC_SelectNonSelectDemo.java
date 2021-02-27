package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_SelectNonSelectDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void selectNonselect() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After the Successfull Connection perform the remaining operations.....");

		Statement statement_result = conn.createStatement();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Query:- ");
		String sqlQuery = scanner.nextLine();
		System.out.println("------------------------");
		System.out.println("ENO   ENAME   ESAL");
		System.out.println("------------------------");

		boolean b = statement_result.execute(sqlQuery);

		if (b == true) { // For Select Query
			ResultSet result = statement_result.getResultSet();
			while (result.next()) {
				int eno = result.getInt(1);
				String ename = result.getString(2);
				int esal = result.getInt(3);
				System.out.println(eno + " -> " + ename + " -> " + esal);
			}
		} else { // For Non-Select Query
			int rowCount = statement_result.getUpdateCount();
			System.out.println("The number of records effected is:- " + rowCount);  
		}

		System.out.println("Connection is closed!!!");
		conn.close();
	}

	public static void main(String[] args) throws Exception {
		JDBC_SelectNonSelectDemo test = new JDBC_SelectNonSelectDemo();
		test.selectNonselect();
	}
}
