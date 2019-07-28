package com.example.brendan.champlaincontactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.brendan.champlaincontactmanager.DB.DBManager;
import com.example.brendan.champlaincontactmanager.DB.DBSQLiteManager;
import com.example.brendan.champlaincontactmanager.Models.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;
    ContactAdapter adapter;
    DBSQLiteManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ADD BUTTON
        Button b = findViewById(R.id.btnAddContact);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddContact.class);

                startActivity(i);
            }
        });

        RecyclerView rvContacts = findViewById(R.id.recyclerView);

        rvContacts.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));

        //contacts = DBManager.getContact_list();

        manager = new DBSQLiteManager(this);

        contacts = manager.getContact_list();
        manager.getContacts();

        adapter = new ContactAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }
}
