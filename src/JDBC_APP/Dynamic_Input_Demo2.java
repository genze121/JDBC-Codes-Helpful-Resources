package JDBC_APP;

import java.util.Scanner;

public class Dynamic_Input_Demo2 {

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

		String sqlQuery = String.format("insert into Emp(eno,ename,esal) values(%d,'%s',%d)", eno, ename, esal);

		System.out.println("Hello the Query with dynamic input is:- ");
		System.out.println(sqlQuery);
		
	}

}
