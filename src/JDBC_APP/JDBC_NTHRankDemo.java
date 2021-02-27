package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_NTHRankDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void nthRank() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Connection is successfull perform the remaining operations.....");

		Statement statement_check = conn.createStatement();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Rank Number:- ");
		int number = scanner.nextInt();

		String sqlQuery = "select * from Emp(select eno,ename,salary,rank() over(order by salary desc) as 'ranking' from Emp) where ranking=" +number;

		ResultSet result = statement_check.executeQuery(sqlQuery);

		while (result.next()) {
			System.out.println(result.getInt(1) + " -> " + result.getString(2) + " -> " + result.getInt(3));
		}

		System.out.println("Connection is closed!!!");
		conn.close();

	}

	public static void main(String[] args) throws Exception {
		JDBC_NTHRankDemo rank = new JDBC_NTHRankDemo();
		rank.nthRank();
	}

}
