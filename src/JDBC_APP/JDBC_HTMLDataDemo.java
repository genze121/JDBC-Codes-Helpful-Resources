package JDBC_APP;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_HTMLDataDemo {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void htmlData() throws Exception {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection is Established Successfully!!!");
		} else {
			System.out.println("Connection is not Established Successfully!!!");
		}

		System.out.println("After the successfull connection perform the remaining operations......");

		Statement statement_check = conn.createStatement();

		String sqlQuery = "select * from Emp";

		ResultSet result = statement_check.executeQuery(sqlQuery);

		String data = "";
		data = data + "<html><body bgcolor='green'><center><table border='3'>";
		data = data + "<tr><td>ENO</td><td>ENAME</td><td>ESAL</td></tr>";

		while (result.next()) {
			data = data + "<tr><td>" + result.getInt(1) + "</td><td>" + result.getString(2) + "</td><td>"
					+ result.getInt(3) + "</td></tr>";
		}

		data = data + "</table></center></body></html>";

		FileOutputStream fos = new FileOutputStream("WebContent/emp.html");
		byte[] b = data.getBytes();
		fos.write(b);
		fos.flush();
		fos.close();
		System.out.println("Open emp.html file to get Employees Data!!!");
		System.out.println("Connection is closed!!!");
		conn.close();
	}

	public static void main(String[] args) throws Exception {
		JDBC_HTMLDataDemo check = new JDBC_HTMLDataDemo();
		check.htmlData();
	}

}
