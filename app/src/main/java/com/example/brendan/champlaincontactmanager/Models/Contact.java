package com.example.brendan.champlaincontactmanager.Models;

import java.util.ArrayList;

public class Contact
{
    // Database Columns
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FNAME = "firstname";
    public static final String COLUMN_LNAME = "lastname";
    public static final String COLUMN_PHONENUMBER = "fk_phone";

    // DB create
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FNAME + " TEXT,"
            + COLUMN_LNAME + " TEXT,"
            + COLUMN_PHONENUMBER + " INTEGER";

    private Integer id;
    private String firstName;
    private String lastName;
    private ArrayList<PhoneNumber> phoneNumbers;

    public Contact() {}

    public Contact(String firstName, String lastName, ArrayList<PhoneNumber> phoneNumbers) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public boolean search(String searchString)
    {
        // Search contact
        if (this.getFirstName().contains(searchString) ||
                this.getLastName().contains(searchString))
        {
            return true;
        }

        // Search child phone numbers
        for (PhoneNumber pn : this.phoneNumbers)
        {
            if (pn.search(searchString))
            {
                return true;
            }
        }

        return false;
    }
}
