package com.example.alaaismail.tasksolution.ModelView;

import android.arch.lifecycle.ViewModel;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

import com.example.alaaismail.tasksolution.Model.*;

public class contactsViewModel extends ViewModel {

    ArrayList<contactsItems> contacts;


    public void loadContacts(Context context) {
        contacts = new ArrayList<contactsItems>();
        String phoneNumber = null;
        Cursor cursor_contact = null;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            cursor_contact = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor_contact.getCount() > 0) {
            while (cursor_contact.moveToNext()) {

                String contactName = cursor_contact.getString(cursor_contact.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasphoneNumber = Integer.parseInt(cursor_contact.getString(cursor_contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER)));
                if (hasphoneNumber > 0) {
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , null
                            , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                            , new String[]{cursor_contact.getString(cursor_contact.getColumnIndex(ContactsContract.Contacts._ID))}
                            , null
                    );
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                }

                contacts.add(new contactsItems(contactName, phoneNumber));
            }
        }
        cursor_contact.close();
    }

    public ArrayList<contactsItems> getContacts() {
        return contacts;
    }

}
