package com.shruti.hotel_management_stytem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelManagement {

	private static final String url = "jdbc:mysql://localhost:3306/hotel_management_system";
	private static final String username="root";
	private static final String password="root";
	
	public static void main(String[] args) throws ClassNotFoundException {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			Connection connection=DriverManager.getConnection(url, username, password);
		
		while(true) {
			System.out.println("HOTEL MANAGEMENT SYSTEM");
			Scanner sc=new Scanner(System.in);
			System.out.println("1. Reserve a room");
			System.out.println("2. View reservation");
			System.out.println("3. Get room number");
			System.out.println("4. Update reservation");
			System.out.println("5. Delete Reservation");
			System.out.println("0. Exit");
			System.out.println("Choose an option: ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				reserveRoom(connection, sc);
				break;
			
		    case 2:
			    viewReservation(connection);
				break;
		    
            case 3:
	            getRoomNumber(connection, sc);
				break;
            
            case 4:
	            updateReservation(connection, sc);
				break;
            
            case 5:
				deleteReservation(connection, sc);
				break;
			
			case 0:
				exit();
				sc.close();
				return;
			default:
				System.out.println("Invalid choice");

			}
			
		}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
			
	}
	
	  private static void reserveRoom(Connection connection, Scanner sc) throws SQLException {
		 
			  String sql="select room_number from room where booking_status='No'";
		        List<String> availableRooms = new ArrayList<>();
			  try(Statement statement= connection.createStatement();
				  ResultSet resultSet = statement.executeQuery(sql)){
		            
	                String roomNumber = resultSet.getString("room_number");
	                availableRooms.add(roomNumber);
	                
	                if (availableRooms.contains(roomNumber)) {
	                    System.out.println("No available rooms.");
	                   
	                
		            }else {
	                    System.out.println("Available Room Numbers:");
	                    for (String room : availableRooms) {
	                        System.out.println(room);
	                    }
	                }
				  }
			  
			  System.out.println("Enter room number: ");
			  int roomNumber=sc.nextInt();
			  	
		  String sql3="update room set booking_status='yes' where room_number=?";
		  PreparedStatement updateStmt = connection.prepareStatement(sql3);
          updateStmt.setInt(1, roomNumber);
          int rowsUpdated = updateStmt.executeUpdate();
          if (rowsUpdated > 0) {
              System.out.println("Room " + roomNumber + " has been successfully booked.");
              
          }
      
        else {
         System.out.println("Room " + roomNumber + " does not exist.");
         
        }
          
          
          System.out.println("Enter guest name: ");
		  String guestName=sc.next();
		  sc.nextLine();
		  System.out.println("Enter contact number: ");
		  String contactNumber=sc.next();
		  String sql2="Insert into hotel (guest_name, room_number, contact_number)" +"values('" + guestName + "', " + roomNumber + ", '"+ contactNumber + "')";
		  
			try(Statement statement= connection.createStatement()){
				int affectedRows= statement.executeUpdate(sql2);
				
				if(affectedRows>0) {
					System.out.println("Reservation Successful");
				}
				else {
					System.out.println("Reservation failed");
				}
				
	  }
			
          catch(SQLException e) {
			  e.printStackTrace();
		  }

	  }
	  
	  private static void viewReservation(Connection connection) throws SQLException {
		 
	 String sql="select reservation_id ,guest_name, room_number, contact_number, reservation_date from hotel";
	 try(Statement statement= connection.createStatement();
			ResultSet resultSet= statement.executeQuery(sql)){
		    
		 while(resultSet.next()) {
			 int reservationId = resultSet.getInt("reservation_id");
			 String guestName = resultSet.getString("guest_name");
			 int roomNumber = resultSet.getInt("room_number");
			 String contactNumber = resultSet.getString("contact_number");
			 String reservationDate = resultSet.getString("reservation_date").toString();
			 
			 System.out.println("Reservation Id is: "+reservationId);
			 System.out.println("Guest Name is: "+guestName);
			 System.out.println("Room Number is: "+roomNumber);
			 System.out.println("Contact Number is: "+contactNumber);
			 System.out.println("Reservation Date is: "+reservationDate);
			 System.out.println();

		 }
		 }
	 }
	  
	  private static void getRoomNumber(Connection connection, Scanner sc) {
		  
           try {
        	   System.out.println("Eneter guest name: ");
        	   String guestName= sc.next();
        	   
      String sql="select room_number from hotel where guest_name= '" + guestName + "'";
      
      try(Statement statement= connection.createStatement();
  			ResultSet resultSet= statement.executeQuery(sql)){
    	  
  		 if(resultSet.next()) {
   			int roomNumber = resultSet.getInt("room_number");
  			 System.out.println(guestName + " Room no is: "+ roomNumber);
  		 }
  		 else {
 			System.out.println("Reservation not found");
  		 }
      }
           }
           catch(SQLException e) {
 			  e.printStackTrace();
 		  }
	   
        	   
           }
	  
	  private static void updateReservation(Connection connection, Scanner sc) {
		  
		  try {
			  System.out.println("Enter reservation ID to update");
			  int reservationId=sc.nextInt();
			  sc.nextLine();
			  
			  if(!reservationExists(connection, reservationId)) {
				  System.out.println("Reservation no found for the given Id");
				  return;
			  }
			  
			  System.out.println("Enter new guest name: ");
			  String newGuestName=sc.nextLine();
			  System.out.println("Enter new room number: ");
			  int newRoomNumber=sc.nextInt();
			  System.out.println("Enter new contact number: ");
			  String newContactNumber=sc.next();
			  
		 String sql="update hotel set guest_name= '"+ newGuestName + "', "+ "room_number = " + newRoomNumber + ", "+ "contact_number =  '"+ newContactNumber + "' "+
		 "where reservation_id = " + reservationId;
		 
		 try(Statement statement= connection.createStatement()){
				int affectedRows= statement.executeUpdate(sql);
				
				if(affectedRows>0) {
					System.out.println("Reservation updated Successful");
				}
				else {
					System.out.println("Reservation update failed");
				}
				
			}
			  }
			  catch(SQLException e) {
				  e.printStackTrace();
			  }

			  
		  }
	  
	  private static void deleteReservation(Connection connection, Scanner sc) {
		  
		  try {
			  System.out.println("Enter room number to delete");
			  int roomNumber=sc.nextInt();
			  sc.nextLine();
			  
			  if(!reservationExists(connection, roomNumber)) {
				  System.out.println("Reservation no found for the given room number");
				  return;
			  }
		      String sql="delete from hotel where room_number= "+ roomNumber;
		      try(Statement statement= connection.createStatement()){
					int affectedRows= statement.executeUpdate(sql);
					
					if(affectedRows>0) {
						System.out.println("Reservation deleted Successful");
					}
					else {
						System.out.println("Reservation deleted failed");
					}
					
				}
		      String sql3="update room set booking_status='No' where room_number=?";
			  PreparedStatement updateStmt = connection.prepareStatement(sql3);
	          updateStmt.setInt(1, roomNumber);
	          int rowsUpdated = updateStmt.executeUpdate();
				  }
				  catch(SQLException e) {
					  e.printStackTrace();
				  }
	  }
	  private static boolean reservationExists(Connection connection, int reservationId) {
		  try {
		      String sql="select reservation_id from hotel where reservation_id= "+reservationId;
  
		  
		  try(Statement statement= connection.createStatement();
		  			ResultSet resultSet= statement.executeQuery(sql)){
			  return resultSet.next();
			  }
	  }
		  catch(SQLException e) {
			  e.printStackTrace();
			  return false;
		  }
	  }
	  
	  private static void exit() throws InterruptedException {
		  System.out.println("Exiting System");
		  int i = 5;
		  while(i!=0) {
			  System.out.print(".");
			  Thread.sleep(450);
			  i--;
		  }
		  System.out.println();
		  System.out.println("Thank You for using hotel management system");
	  }
}
