package com.example.brendan.champlaincontactmanager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brendan.champlaincontactmanager.DB.DBManager;
import com.example.brendan.champlaincontactmanager.DB.DBSQLiteManager;
import com.example.brendan.champlaincontactmanager.Models.Contact;
import com.example.brendan.champlaincontactmanager.Models.PhoneNumber;
import com.example.brendan.champlaincontactmanager.Models.PhoneNumberType;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button bAdd = findViewById(R.id.btnAdd);
        Button bCancel = findViewById(R.id.btnCancel);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtFirst = findViewById(R.id.txtFirst);
                EditText txtLast = findViewById(R.id.txtLast);
                EditText txtPhone = findViewById(R.id.txtPhone);

                ArrayList<PhoneNumber> phones = new ArrayList<>();
                PhoneNumber p = new PhoneNumber(1, PhoneNumberType.MOBILE, txtPhone.getText().toString());
                phones.add(p);

                Contact c = new Contact(txtLast.getText().toString(), txtFirst.getText().toString(), phones);

                addContact(c);

                txtFirst.setText("");
                txtLast.setText("");
                txtPhone.setText("");
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Contact addContact(Contact c)
    {
        //Contact createdContact = DBManager.addContact(c);

        DBSQLiteManager dbslm = new DBSQLiteManager(this);
        Contact createdContact = dbslm.addContact(c);

        Toast t = Toast.makeText(getApplicationContext(),
                "Success! Contact created with id: " + createdContact.getId(), Toast.LENGTH_LONG);

        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();

        return createdContact;
    }
}
