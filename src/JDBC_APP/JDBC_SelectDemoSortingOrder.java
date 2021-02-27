package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_SelectDemoSortingOrder {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void selectSortingOrder() {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			if (conn != null) {
				System.out.println("Connection is Established Successfully!!!");
			} else {
				System.out.println("Connection is not Established Successfully!!!");
			}
			System.out.println("After Connection Establishment perform the next operations......");
			Statement statement_validate = conn.createStatement();
			String sqlQuery = "select eno,ename,salary from Emp order by salary DESC";

			System.out.println("ENO\tENAME\tSALARY");
			System.out.println("------------------");

			boolean flag = false;

			ResultSet result = statement_validate.executeQuery(sqlQuery);
			while (result.next()) {
				flag = true;
				int eno = result.getInt("eno");
				String ename = result.getString("ename");
				String esal = result.getString("salary");

				System.out.println(eno + " -> " + ename + " -> " + esal);
			}

			if (flag == false) {
				System.out.println("No match records found in the table!!!");
			}

			System.out.println("Connection is closed!!!");
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		JDBC_SelectDemoSortingOrder checkOrder = new JDBC_SelectDemoSortingOrder();
		checkOrder.selectSortingOrder();
	}

}
