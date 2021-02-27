package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_MinDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void minResult() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Connection is Successfull perform the remaining operations.....");

		Statement statement_check = conn.createStatement();

		String sqlQuery = "select * from Emp where salary in(select min(salary) from Emp)";

		System.out.println("Least Salary Employee Information");
		System.out.println("---------------------------------");
		System.out.println("ENO   ENAME    ESAL");
		System.out.println("---------------------------------");

		boolean flag = false;
		ResultSet result_check = statement_check.executeQuery(sqlQuery);

		while (result_check.next()) {
			flag = true;
			int eno = result_check.getInt(1);
			String ename = result_check.getString(2);
			int esal = result_check.getInt(3);

			System.out.println(eno + " -> " + ename + " -> " + esal);
		}

		if (flag == false) {
			System.out.println("No matched records found!!!");
		}

		System.out.println("Connection is Closed!!!");
		conn.close();

	}

	public static void main(String[] args) throws Exception {
		JDBC_MinDemo.minResult();
	}

}
