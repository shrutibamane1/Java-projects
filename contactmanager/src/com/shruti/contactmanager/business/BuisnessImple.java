package com.shruti.contactmanager.business;

import java.util.Scanner;
import com.shruti.contactmanager.data.Data;
import com.shruti.contactmanager.entity.Contact;
import com.shruti.contactmanager.data.DataImple;

public class BuisnessImple implements Buisness {
	private Scanner sc=new Scanner(System.in);
	
	private int id;
	
	private Data data =new DataImple();

	@Override
	public void addContact() {
		System.out.println("Enter First name");
		String firstName=sc.next();
		System.out.println("Enter last name");
		String lastName=sc.next();
		System.out.println("Enter Mobile number");
		long mobile=sc.nextLong();
		System.out.println("Enter work number");
		long work=sc.nextLong();
		System.out.println("Enter email");
		String email=sc.next();
		
		Contact contact=new Contact(id(), firstName,lastName,mobile,work,email);
		
		data.addContact(contact);
		
		Contact[] contacts =data.findAllContacts();
		
		for(int i=0;i<contacts.length;i++) {
			System.out.println(contacts[i]);
		}		
	}

	@Override
	public void deleteContact() {
		System.out.println("Enter contact id");
		int id=sc.nextInt();
		data.deleteContact(id);
		Contact[] contacts =data.findAllContacts();
		if(contacts.length >0) {
		for(int i=0;i<contacts.length;i++) {
			System.out.println(contacts[i]);
		}		
	}
		else {
			System.out.println("contact not found");
		}
	}

	@Override
	public void updateContact() {
		System.out.println("Enter contact id");
	    int id =sc.nextInt();
		Contact contact =data.findContactById(id);
	    if(contact !=null) {
		System.out.println("Enter First name");
		String firstName=sc.next();
		System.out.println("Enter last name");
		String lastName=sc.next();
		System.out.println("Enter Mobile number");
		long mobile=sc.nextLong();
		System.out.println("Enter work number");
		long work=sc.nextLong();
		System.out.println("Enter email");
		String email=sc.next();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setMobile(mobile);
		contact.setWork(work);
		contact.setEmail(email);
		System.out.println("Contact updated");
		System.out.println(contact);
	}
	    else
			System.out.println("contact not found");
		
	}

	@Override
	public void findContactByFirstName() {
		System.out.println("Enter first name");
		String firstName=sc.next();
		Contact[] contacts =data.findAllContacts();
		if(contacts.length >0) {
		boolean flag=false;
		for(int i=0;i<contacts.length;i++) {
			if(contacts[i].getFirstName().equals(firstName)) {
				flag= true;
				System.out.println(contacts[i]);
			}
		}
		if(!flag)
			System.out.println("Contact not found");
		}	else
			System.out.println("contacts not found");
	}
		

	@Override
	public void findContactByLastName() {
		System.out.println("Enter last name");
		String lastName=sc.next();
		Contact[] contacts =data.findAllContacts();
		if(contacts.length >0) {
		boolean flag=false;
		for(int i=0;i<contacts.length;i++) {
			if(contacts[i].getLastName().equals(lastName)) {
				flag= true;
				System.out.println(contacts[i]);
			}
		}
		if(!flag)
			System.out.println("Contact not found");
		}
	}
		
	private int id() {
		return ++id;		
	}

	@Override
	public void findAllContacts() {
		Contact[] contacts=data.findAllContacts();
		if(contacts.length>0) {
			for(int i=0;i<contacts.length;i++) {
				System.out.println(contacts[i]);
			}
		}
		else
			System.out.println("contact not found");
	}

	
	@Override
	public void search() {
      System.out.println("Enter Pattern");
      String pattern=sc.next();
      Contact[] contacts=data.findAllContacts();
      if(contacts.length>0) {
    	  boolean flag =false;
    	  for(int i=0;i<contacts.length;i++) {
    		  Contact contact=contacts[i];
    		  if(contact.getFirstName().contains(pattern) || contact.getLastName().contains(pattern) ||contact.getEmail().contains(pattern) ||
    			String.valueOf(contact.getMobile()).contains(pattern )||String.valueOf(contact.getWork()).contains(pattern)){
    			flag=true;
    			System.out.println(contact);
    		  }
    	  }
    	  if(!flag) {
    		  System.out.println("No contact found");
    	  }
    	  else
    		  System.out.println("contact not found");
      }
	}
}
