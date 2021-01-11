package com.example.test.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;
import com.example.test.bean.Message;

import java.util.List;

public class MyAdapter extends ArrayAdapter {
    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Message> objects) {
        super(context,resource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Message msg= (Message) getItem(position);
        View view=null;
        if(msg.getType()==Message.SER_TYPE){
            view= LayoutInflater.from(getContext()).inflate(R.layout.service_item,null);
            TextView txv_left =view.findViewById(R.id.txv_service);
            txv_left.setText(msg.getMsg());
            /*String str=msg.getMsg();
            txv_left.setText(str);*/
            TextView txv_date=view.findViewById(R.id.service_date);
            txv_date.setText(msg.getDate());
        }else {
            view= LayoutInflater.from(getContext()).inflate(R.layout.me_item,null);
            TextView txv_right =view.findViewById(R.id.txv_me);
            txv_right.setText(msg.getMsg());
            /*String str=msg.getMsg();
            txv_right.setText(str);*/
            TextView txv_date=view.findViewById(R.id.me_date);
            txv_date.setText(msg.getDate());
        }

        return view;
    }
}


