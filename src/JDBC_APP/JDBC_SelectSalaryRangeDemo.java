package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_SelectSalaryRangeDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void selectSalaryRange() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Successfull Connection now perform the remaining operations.....");

		Statement statement_validate = conn.createStatement();
		Scanner scanner = new Scanner(System.in);
		int beginSal = 0, endSal = 0;
		System.out.println("Enter the begininning salary:- ");
		beginSal = scanner.nextInt();
		System.out.println("Enter the ending salary:- ");
		endSal = scanner.nextInt();

		System.out.println("ENO\tENAME\tESAL");
		System.out.println("--------------------------");

		String sqlQuery = String.format("select * from Emp where salary>=%d and salary<=%d", beginSal, endSal);

		boolean flag = false;

		ResultSet result_validate = statement_validate.executeQuery(sqlQuery);

		while (result_validate.next()) {
			flag = true;
			int eno = result_validate.getInt(1);
			String ename = result_validate.getString(2);
			int esal = result_validate.getInt(3);
			System.out.println(eno + " -> " + ename + " -> " + esal);
		}

		if (flag == false) {
			System.out.println("No matched records found!!!");
		}

		System.out.println("Connection is closed!!!");
		conn.close();
	}

	public static void main(String[] args) {
		try {
			JDBC_SelectSalaryRangeDemo.selectSalaryRange();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
