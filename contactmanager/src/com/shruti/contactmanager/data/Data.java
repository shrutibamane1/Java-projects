package com.shruti.contactmanager.data;
import com.shruti.contactmanager.entity.*;

public interface Data {

    void addContact(Contact contact);
	
	void deleteContact(int id);
	
	Contact findContactById(int id);
	
	Contact[] findAllContacts();
	
	
}
