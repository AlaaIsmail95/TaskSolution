package com.example.alaaismail.tasksolution.ModelView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alaaismail.tasksolution.Model.*;
import com.example.alaaismail.tasksolution.R;

import java.util.List;

public class phonContactAdapter extends BaseAdapter {

    Context mContext;
    List<contactsItems> mList_Contacts;

    public phonContactAdapter(Context mContext, List<contactsItems> mList_Contacts) {
        this.mContext = mContext;
        this.mList_Contacts = mList_Contacts;
    }

    @Override
    public int getCount() {
        return mList_Contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return mList_Contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mview = View.inflate(mContext, R.layout.contacts_items,null);

        TextView tv_name = mview.findViewById(R.id.contact_name);
        TextView tv_number = mview.findViewById(R.id.contact_number);
        tv_name.setText(mList_Contacts.get(i).getName());
        tv_number.setText(mList_Contacts.get(i).getNumber());


        return mview;
    }
}
