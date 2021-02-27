package JDBC_APP;

import java.util.Scanner;

public class Dynamic_Input_Demo1 {

	public static void main(String[] args) {

		int eno = 0;
		String ename = null;
		int esal = 0;

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Employee number : ");
		eno = scan.nextInt();
		System.out.println("Enter the Employee name : ");
		ename = scan.next();
		System.out.println("Enter the Employee Salary : ");
		esal = scan.nextInt();

		String sqlQuery = "insert into Emp(eno,ename,esal) values(" + eno + ",'" + ename + "'," + esal + ")";

		System.out.println("Hello the provided informations are :- ");
		System.out.println(sqlQuery);
	}

}
