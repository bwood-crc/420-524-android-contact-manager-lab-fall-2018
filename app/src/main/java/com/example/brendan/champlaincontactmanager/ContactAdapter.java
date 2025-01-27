package com.example.brendan.champlaincontactmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brendan.champlaincontactmanager.Models.Contact;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView firstNameTextView;
        public TextView lastNameTextView;
        public TextView phoneNumberTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            firstNameTextView = itemView.findViewById(R.id.txtFirstName);
            lastNameTextView = itemView.findViewById(R.id.txtLastName);
            phoneNumberTextView = itemView.findViewById(R.id.txtPhoneNumb);
        }
    }

    // Store a member variable for the contacts
    private List<Contact> mContacts;

    // Pass in the contact array into the constructor
    public ContactAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.contact_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder viewHolder, int position)
    {
        // Get the data model based on position
        Contact contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textViewFN = viewHolder.firstNameTextView;
        textViewFN.setText(contact.getFirstName());

        viewHolder.lastNameTextView.setText(contact.getLastName());

        TextView textViewPH = viewHolder.phoneNumberTextView;

        //textViewPH.setText(contact.getPhoneNumbers().get(0).getPhoneNumber().toString());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount()
    {
        return mContacts.size();
    }
}