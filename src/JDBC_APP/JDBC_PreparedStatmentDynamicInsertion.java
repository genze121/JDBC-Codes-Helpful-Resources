package JDBC_APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_PreparedStatmentDynamicInsertion {

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

	public static void cleanupActivity(PreparedStatement prepared_statement, Connection con) {

		try {
			if (prepared_statement != null) {
				prepared_statement.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void dynamicInsertion() {

		Connection con = null;
		String sqlQuery = "insert into Emp(eno,ename,salary) values(?,?,?)";
		PreparedStatement pst = null;
		Scanner scanner = new Scanner(System.in);
		try {
			con = JDBC_PreparedStatmentDynamicInsertion.getConnection();
			pst = con.prepareStatement(sqlQuery);

			while (true) {
				System.out.println("Enter the Employee number:- ");
				int eno = scanner.nextInt();
				System.out.println("Enter the Employee name:- ");
				String ename = scanner.next();
				System.out.println("Enter the Employee Salary:- ");
				int esal = scanner.nextInt();

				pst.setInt(1, eno);
				pst.setString(2, ename);
				pst.setInt(3, esal);

				int rowInserted = pst.executeUpdate();
				if (rowInserted == 1 || rowInserted > 0) {
					System.out.println("Record is inserted Successfully!!!!");
				} else {
					System.out.println("Record insertion has some problem.Please rectify it!!!!");
				}

				System.out.println("Do you want to insert more records? If then type [Yes|No]");
				String option = scanner.next();

				if (option.equalsIgnoreCase("NO")) {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PreparedStatement pst = null;
		Connection con = null;
		try {
			JDBC_PreparedStatmentDynamicInsertion.dynamicInsertion();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection is Closed Successfully!!!");
			JDBC_PreparedStatmentDynamicInsertion.cleanupActivity(pst, con);
		}

	}

}
