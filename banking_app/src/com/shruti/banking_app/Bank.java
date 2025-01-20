package com.shruti.banking_app;
import java.util.Scanner;

public class Bank {

	private String accno;
	private String name;
	private String acc_type;
	private long balance;
	Scanner sc=new Scanner(System.in);
	
	public void openAccount() {
		System.out.println("Enter Account Number");
		accno=sc.next();
		System.out.println("Enter Name");
		name=sc.next();
		System.out.println("Enter Account Type");
		acc_type=sc.next();
		System.out.println("Enter Balance");
		balance=sc.nextLong();
	}
	
	public void showAccount() {
		System.out.println("Name of account holder: "+name);
		System.out.println("Account No: "+accno);
		System.out.println("Account Type: "+acc_type);
		System.out.println("Balance: "+balance);
		
	}
	
	public void withdraw() {
		long amt;
		System.out.println("Enter amount you want to withdraw");
		amt=sc.nextLong();
		if(balance>amt) {
			balance=balance-amt;
			System.out.println("Amount withdrawed Successfully...");
			System.out.println("Current balance is: "+balance);

		}
		else
			System.out.println("Your balance is less than "+amt+"\tTransaction Failed...");
	}
	
	public boolean searchAccount(String ac_no) {
		if(accno.equals(ac_no)) {
			showAccount();
			return(true);
		}
		return(false);
		}
	
	public void deposite() {
		long amt;
		System.out.println("Enter amount you want to deposite");
		amt=sc.nextLong();
		balance=balance+amt;
		System.out.println("Amount deposited Successfully..");
		System.out.println("Current balance is: "+balance);

	}
	
	}

