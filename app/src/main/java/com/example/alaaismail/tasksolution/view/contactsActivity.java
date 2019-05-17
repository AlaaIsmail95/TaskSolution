package com.example.alaaismail.tasksolution.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.alaaismail.tasksolution.Model.contactsItems;
import com.example.alaaismail.tasksolution.ModelView.*;
import com.example.alaaismail.tasksolution.R;

import java.util.ArrayList;

public class contactsActivity extends AppCompatActivity {

    ListView lv_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        lv_contacts = (ListView) findViewById(R.id.ls_contacts);
        if (ContextCompat.checkSelfPermission(contactsActivity.this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {


            contactsViewModel contacts = new contactsViewModel();
            contacts.loadContacts(this);

            ArrayList<contactsItems> List_Contact = new ArrayList<contactsItems>();
            List_Contact = contacts.getContacts();


            phonContactAdapter adapter = new phonContactAdapter(this, List_Contact);

            lv_contacts.setAdapter(adapter);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                new AlertDialog.Builder(this).setTitle("Permission needed")
                        .setMessage("We need to access your contacts to display it in the application")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(contactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                            }
                        })
                        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                contactsViewModel contacts = new contactsViewModel();
                contacts.loadContacts(this);

                ArrayList<contactsItems> List_Contact = new ArrayList<contactsItems>();
                List_Contact = contacts.getContacts();


                phonContactAdapter adapter = new phonContactAdapter(this, List_Contact);

                lv_contacts.setAdapter(adapter);
            }
        }
    }
}
