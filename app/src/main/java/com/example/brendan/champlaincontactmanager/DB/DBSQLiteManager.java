package com.example.brendan.champlaincontactmanager.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.brendan.champlaincontactmanager.Models.Contact;

import java.util.ArrayList;

public class DBSQLiteManager extends SQLiteOpenHelper {

    private static ArrayList<Contact> contact_list = new ArrayList<>();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contact_db";

    public DBSQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Contact addContact(Contact c)
    {
        SQLiteDatabase
                db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contact.COLUMN_FNAME, c.getFirstName());
        values.put(Contact.COLUMN_LNAME, c.getLastName());
        values.put(Contact.COLUMN_PHONENUMBER, 0);

        long id = db.insert(Contact.TABLE_NAME, null, values);
        db.close();

        c.setId((int)id);
        contact_list.add(c);

        return c;
    }

    public void getContacts()
    {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Contact.TABLE_NAME + " ORDER BY " +
                Contact.COLUMN_LNAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact c = new Contact();
                c.setId(cursor.getInt(cursor.getColumnIndex(Contact.COLUMN_ID)));
                c.setFirstName(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_FNAME)));
                c.setLastName(cursor.getString(cursor.getColumnIndex(Contact.COLUMN_LNAME)));

                contact_list.add(c);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();
    }

    public ArrayList<Contact> getContact_list()
    {
        // return contact list
        return contact_list;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Contact.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing, like a boss.
    }
}
