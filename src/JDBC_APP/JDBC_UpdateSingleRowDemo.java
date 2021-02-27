package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC_UpdateSingleRowDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void updateSingleRecord() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/CreateTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Successfull Connection perform the update operation.....");

		Statement statement_test = conn.createStatement();

		String updateQuery = "update Emp set ename='Sultan' where eno=10;";

		int updatedRecord = statement_test.executeUpdate(updateQuery);

		if (updatedRecord > 0) {
			System.out.println("Record updated Successfully!!!");
		} else {
			System.out.println("Record is not updated Successfully!!!");
		}

		System.out.println("The no of record updated is :- " + updatedRecord);

		System.out.println("Connection is closed!!!");
		conn.close();

	}

	public static void main(String[] args) {

		try {
			JDBC_UpdateSingleRowDemo.updateSingleRecord();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
