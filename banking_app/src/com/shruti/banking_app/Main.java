package com.shruti.banking_app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class Main {
	private static Driver driver;
	private static Connection conn;
	private static Statement statement;
	private static String query;
	private static String query2;

	public static void main(String[] args){
		
		Scanner sc=new Scanner(System.in);
		System.out.println("*** Welcome to out Banking Application ***");
		System.out.println("How many number of customers do you want to input?");
		int n=sc.nextInt();
		Bank b[]=new Bank[n];
		for(int i=0;i<b.length;i++) {
			b[i]=new Bank();
			b[i].openAccount();
		}
		System.out.println("Account is created");
		
	    int choice;
		do {
			System.out.println("Enter 1 to Show Account");
			System.out.println("Enter 2 to search by Account no.");
			System.out.println("Enter 3 for deposite amount");
			System.out.println("Enter 4 for withdraw amount");
			System.out.println("Enter 5 for exit");



        
		choice=sc.nextInt();
		switch(choice) {
		case 1:
			for(int i=0;i<b.length;i++) {
				b[i].showAccount();
		}
			break;
		case 2:
			System.out.println("Enter Account No you want to search");
			String ac_no=sc.next();	
			boolean found=false;
			for(int i=0;i<b.length;i++) {
				found=b[i].searchAccount(ac_no);
				if(found) {
					break;
				}
			}
			if(!found) {
				System.out.println(ac_no+" this account not exist");
			}
			break;
			
		case 3:
			System.out.println("Enter Account Number");
			ac_no=sc.next();
			found=false;
			for(int i=0;i<b.length;i++) {
				found=b[i].searchAccount(ac_no);
				if(found) {
				  b[i].deposite();
				  break;
				}
			}
			if(!found) {
				System.out.println(ac_no+" this account not exist");
			}
			break;
			
		case 4:
			System.out.println("Enter Account Number");
			ac_no=sc.next();
			found=false;
			for(int i=0;i<b.length;i++) {
				found=b[i].searchAccount(ac_no);
				if(found) {
					b[i].withdraw();
					break;
				}
			}
			if(!found) {
				System.out.println(ac_no+" this account not exist");
			}
			break;
		case 5:
			System.out.println("Thank You.../n Visite again...");
			break;
			
		}
		}
		while(choice!=5);
			
       
		
	}
         
}


