package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_SQLInjectionAttackStatement {

	static {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
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

	public static void cleanupActivity(ResultSet result, Statement statement, Connection con) {

		try {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void sqlInjectionAttack() {

		Connection con = null;
		Statement statement = null;
		Scanner sc = new Scanner(System.in);
		try {
			con = JDBC_SQLInjectionAttackStatement.getConnection();
			statement = con.createStatement();
			System.out.println("Enter the Username:- ");
			String uname1 = sc.next();
			System.out.println("Enter the Password:- ");
			String upass1 = sc.next();

			String sqlQuery = "select count(*) from users where uname='"+uname1+"' and upass='"+upass1+"'";
			int validate = 0;
			ResultSet result = statement.executeQuery(sqlQuery);

			if (result.next()) {
				validate = result.getInt(1);
			}

			if (validate == 0) {
				System.out.println("Invalid Credentials!!!");
			} else {
				System.out.println("Valid Credentials!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ResultSet result = null;
		Connection con = null;
		Statement statement = null;
		try {
			JDBC_SQLInjectionAttackStatement.sqlInjectionAttack();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection is Closed Successfully!!!");
			JDBC_SQLInjectionAttackStatement.cleanupActivity(result, statement, con);
		}
	}

}
