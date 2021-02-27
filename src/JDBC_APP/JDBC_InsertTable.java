package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC_InsertTable {

	static {

		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static int insertRecord() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/CreateTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (con != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("Connection is Established now.Perform the insertion operation.......");

		Statement statementTest = con.createStatement();

		// Insertion of single record into the table
		// String insertQuery = "insert into Emp(eno,ename,salary)
		// values(3,'Hero',4500)";

		// Insertion of multiple records into the table
		String insertMultipleRecord = "insert into Emp(eno,ename,salary) " + "values(4,'Honda',5000),"
				+ "(5,'Hayabusa',6000)," + "(6,'John',7000)," + "(7,'Abhi',8000)," + "(8,'Ali',9000)";

		int rowsAffected = statementTest.executeUpdate(insertMultipleRecord);

		if (rowsAffected > 0) {
			System.out.println("The data is inserted successfully in the Table!!!");
		} else {
			System.out.println("The data is not inserted in the Table.Check once again!!!");
		}

		System.out.println("The no of rows inserted in the table is:- " + rowsAffected);

		System.out.println("Closing the Connection!!!");
		con.close();

		return rowsAffected;
	}

	public static void main(String[] args) {

		try {
			JDBC_InsertTable.insertRecord();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
