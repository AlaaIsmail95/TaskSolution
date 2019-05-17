package com.example.alaaismail.tasksolution.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.alaaismail.tasksolution.Model.DBconnection;
import com.example.alaaismail.tasksolution.ModelView.SimpleRVAdapter;
import com.example.alaaismail.tasksolution.R;

import java.util.ArrayList;

public class databaseActivity extends AppCompatActivity {

    EditText et_name;
    Button save;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        et_name = findViewById(R.id.et_name);
        save = findViewById(R.id.bu_save);
        rv = findViewById(R.id.recyckerview);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBconnection db = new DBconnection(getApplicationContext());
                db.insertInfo(et_name.getText().toString());
                ArrayList<String> arrayList = db.getAllrecord();

                String[] display_data = new String[arrayList.size()];
                for (int i=0; i<arrayList.size();i++){
                    display_data[i] = arrayList.get(i);
                }


                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                rv.setAdapter(new SimpleRVAdapter(display_data));



            }
        });
    }

}
