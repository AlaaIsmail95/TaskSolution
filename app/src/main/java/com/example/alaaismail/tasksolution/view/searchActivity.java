package com.example.alaaismail.tasksolution.view;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.alaaismail.tasksolution.R;

import java.util.ArrayList;
import java.util.List;

public class searchActivity extends AppCompatActivity {

    String[] countries ={"Cairo","Asyut","Alexandria","Aswan","Luxor"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.listView);
        Adapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,countries);
        listView.setAdapter((ListAdapter) adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        SearchView sv =(SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s != null && !s.isEmpty()) {
                    List<String> result = new ArrayList<String>();
                    for (String item:countries){
                        if (item.contains(s)){
                            result.add(item);
                        }
                        Adapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,result);
                        listView.setAdapter((ListAdapter) adapter);

                    }
                }else {
                    Adapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,countries);
                    listView.setAdapter((ListAdapter) adapter);
                }
            return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
