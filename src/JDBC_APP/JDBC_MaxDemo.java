package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_MaxDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void maxTest() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After the successful connection perform the remaining operations......");

		Statement statement_check = conn.createStatement();

		String sqlQuery = "select * from Emp where salary in(select max(salary) from Emp)";

		ResultSet result = statement_check.executeQuery(sqlQuery);

		System.out.println("Highest Salary Employee Information");
		System.out.println("|-----------------------------------|");
		System.out.println("ENO   ENAME   ESAL");
		System.out.println("|-----------------------------------|");

		boolean flag = false;

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

		System.out.println("Connection is Closed!!!!");
		conn.close();
	}

	public static void main(String[] args) throws Exception {
		JDBC_MaxDemo.maxTest();
	}

}
