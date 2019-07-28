package com.example.brendan.champlaincontactmanager;

import com.example.brendan.champlaincontactmanager.DB.DBManager;
import com.example.brendan.champlaincontactmanager.Models.Contact;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class CRUDUnitTests
{
    @Test
    public void addAContact()
    {
        DBManager.clearContacts();

        Contact c = new Contact("John", "Joe", null);
        DBManager.addContact(c);

        ArrayList<Contact> result = DBManager.getContact_list();

        assertNotNull(result);
        assertTrue(result.size() == 1);
    }

    @Test
    public void AddContactSequence()
    {
        DBManager.clearContacts();

        Contact c1 = new Contact("John", "Joe", null);
        Contact c2 = new Contact("Mary", "Canary", null);

        DBManager.addContact(c1);
        DBManager.addContact(c2);

        ArrayList<Contact> contacts = DBManager.getContact_list();

        Contact secondContact = contacts.get(1);

        assertEquals(2, secondContact.getId());
    }


}
