package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_SelectRangeDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void selectRangeName() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Sucessfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Successfull connection perform the remaining operations.....");

		Statement statement_validate = conn.createStatement();

		Scanner scanner = new Scanner(System.in);
		String name = null;
		System.out.println("Enter the name:- ");
		name = scanner.next();

		System.out.println("ENO\tENAME\tESAL");
		System.out.println("---------------------");

		String format = "select eno,ename,salary from Emp where ename='%s'";

		String sqlQuery = String.format(format, name);

		ResultSet resultSet_check = statement_validate.executeQuery(sqlQuery);
		boolean flag = false;

		while (resultSet_check.next()) {
			flag = true;
			int eno = resultSet_check.getInt("eno");
			String ename = resultSet_check.getString("ename");
			String esal = resultSet_check.getString("salary");

			System.out.println(eno + " -> " + ename + " -> " + esal);
		}

		if (flag == false) {
			System.out.println("No match Records found!!!");
		}

		System.out.println("Connection is CLosed!!!!");
		conn.close();
	}

	public static void main(String[] args) {

		JDBC_SelectRangeDemo test = new JDBC_SelectRangeDemo();
		try {
			test.selectRangeName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
