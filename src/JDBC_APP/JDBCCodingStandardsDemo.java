package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class JdbcUtil {

	static {
		try {
			String driverClassName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		String JDBC_URL = "jdbc:mysql://localhost:3306/createTable";
		String USERNAME = "root";
		String PASSWORD = "root";

		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			if (con != null) {
				System.out.println("Connection is Established Successfully!!!");
			} else {
				System.out.println("Connection is not Established Successfully!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;

	}

	public static void cleanupActivity(ResultSet result, Statement statement, Connection conn) {

		try {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

public class JDBCCodingStandardsDemo {

	public static void main(String[] args) {

		Connection con = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			con = JdbcUtil.getConnection();
			statement = con.createStatement();
			String sqlQuery = "select * from Emp";
			System.out.println("-----------------------");
			System.out.println("ENO  ENAME    ESAL");
			System.out.println("-----------------------");
			result = statement.executeQuery(sqlQuery);

			while (result.next()) {
				System.out.println(result.getInt(1) + " -> " + result.getString(2) + " -> " + result.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			System.out.println("Connection is Closed Successfully!!!");
			JdbcUtil.cleanupActivity(result, statement, con);

		}
	}

}
