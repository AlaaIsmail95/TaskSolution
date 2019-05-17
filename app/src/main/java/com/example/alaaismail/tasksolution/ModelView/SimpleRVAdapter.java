package com.example.alaaismail.tasksolution.ModelView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.SimpleViewHolder> {
private String[] dataSource;
public SimpleRVAdapter(String[] dataArgs){
        dataSource = dataArgs;
        }

@Override
public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = new TextView(parent.getContext());
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
        }

public static class SimpleViewHolder extends RecyclerView.ViewHolder{
    public TextView textView;
    public SimpleViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
}

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(dataSource[position]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }
}