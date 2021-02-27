package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_SelectInitialCharacter {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void selectInitialCharater() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!!");
		}

		System.out.println("After Successfull Connection perform the remaining operations.....");

		Statement statement_validate = conn.createStatement();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the initial character:- ");
		String nameCharacter = scanner.next() + "%";

		boolean flag = false;
		System.out.println("ENO\tENAME\tESAL");
		System.out.println("-------------------");
		String sqlQuery = String.format("select * from Emp where ename like '%s'", nameCharacter);

		ResultSet result = statement_validate.executeQuery(sqlQuery);

		while (result.next()) {
			flag = true;
			int eno = result.getInt(1);
			String ename = result.getString(2);
			int esal = result.getInt(3);
			System.out.println(eno + " -> " + ename + " -> " + esal);
		}
		if (flag == false) {
			System.out.println("No matched records found!!!");
		}
		System.out.println("Connection is closed!!!");
		conn.close();

	}

	public static void main(String[] args) throws Exception {
		JDBC_SelectInitialCharacter.selectInitialCharater();
	}

}
