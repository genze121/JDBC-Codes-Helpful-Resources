package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC_CreateTable {

	static {
		try {
			Class.forName("com.jdbc.cj.mysql.Driver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/CreateTable";
		String USER_NAME = "root";
		String PASS_WORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After Successfull Connection perform the operation for creating a table.....");
		Statement state_ment = conn.createStatement();

		String CREATE_TABLE_QUERY = "create table Emp(\r\n" + " eno int primary key not null,\r\n"
				+ " ename varchar(30),\r\n" + " salary int\r\n" + ");";

		state_ment.executeUpdate(CREATE_TABLE_QUERY);

		System.out.println("Table Created Successfully!!!");

		System.out.println("CLosing the Connection!!!");

		conn.close();
	}

}
