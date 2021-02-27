package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_SelectRowsDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void selectRows() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After the connection initialize the upcoming operations......");

		Statement statement_validate = conn.createStatement();

		String selectQuery = "select eno,ename,salary from Emp where salary>1000";

		System.out.println("ENO\tENAME\tSALARY");
		System.out.println("--------------------");

		boolean flag = false;

		ResultSet resultset_values = statement_validate.executeQuery(selectQuery);

		while (resultset_values.next()) {
			flag = true;
			int eno = resultset_values.getInt("eno");
			String ename = resultset_values.getString("ename");
			String esal = resultset_values.getString("salary");

			System.out.println(eno + " -> " + ename + " -> " + esal);
		}

		if (flag == false) {
			System.out.println("No match Records Found!!!");
		}

		System.out.println("Connection is closed!!!");
		conn.close();
	}

	public static void main(String[] args) {

		try {
			JDBC_SelectRowsDemo.selectRows();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
