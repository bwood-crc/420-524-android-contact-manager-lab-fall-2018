package com.example.brendan.champlaincontactmanager.DB;

import com.example.brendan.champlaincontactmanager.Models.Contact;

import java.util.ArrayList;

public class DBManager {

    private static ArrayList<Contact> contact_list = new ArrayList<>();

    public static void clearContacts()
    {
        contact_list = new ArrayList<>();
    }

    public static Contact addContact(Contact c)
    {
        // Find the highest ID
        int max = 0;

        for (Contact c1 : contact_list)
        {
            max = c1.getId() > max ? c1.getId() : max;
        }

        // Assign a new ID
        c.setId(max + 1);

        contact_list.add(c);

        return c;
    }

    public static ArrayList<Contact> getContact_list()
    {
        return contact_list;
    }

    public static Contact getContact(int id)
    {
        for (Contact c : contact_list)
        {
            if (c.getId() == id)
            {
                return c;
            }
        }

        return null;
    }

    public static void updateContact(Contact c)
    {
        for (int i = 0; i <= contact_list.size(); i++)
        {
            if (c.getId() == contact_list.get(i).getId())
            {
                contact_list.set(i, c);
            }
        }
    }

    public ArrayList<Contact> searchContacts(String searchTerm)
    {
        return null;
    }
}
