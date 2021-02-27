package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC_DropTable {

	static {
		try {

			Class.forName("com.mysql.cj.driver.Driver");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void dropTable() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/CreateTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

		if (con != null) {
			System.out.println("Connection is Established Successfully!!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("Perform the Dropping table Operation.......");

		Statement statementTest = con.createStatement();

		String sqlQuery = "drop table Emp";

		statementTest.executeUpdate(sqlQuery);

		System.out.println("Table is Dropped Successfully!!!");

		System.out.println("Closing the COnnection!!!");

		con.close();
	}

	public static void main(String[] args) {

		try {
			JDBC_DropTable.dropTable();
		} catch (Exception sqlex) {
			System.out.println(sqlex.getMessage());
		}
	}

}
