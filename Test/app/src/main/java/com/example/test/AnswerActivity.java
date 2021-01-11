package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.test.bean.Message;
import com.example.test.adapter.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswerActivity extends AppCompatActivity {

    Button btn_send;
    ListView list_answer;
    EditText editText;
    SimpleDateFormat simpleDateFormat;
    List<Message> messagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        setTitle("在线答疑");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_answer = findViewById(R.id.list_answer);
        editText = findViewById(R.id.editText);
        simpleDateFormat = new SimpleDateFormat("MM月dd日——HH:mm:ss");
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)  {
                String str = editText.getText().toString();
                Date date = new Date(System.currentTimeMillis());
                String time = simpleDateFormat.format(date);
                Message msg = new Message(Message.ME_TYPE, str, time);
                messagesList.add(msg);
                MyAdapter myAdapter = new MyAdapter(AnswerActivity.this, R.layout.me_item, messagesList);
                list_answer.setAdapter(myAdapter);
                editText.setText("");

                String str1 = "请打开浏览器输入网址查询：https://www.baidu.com/?tn=40020637_14_oem_dg";
                Date date1 = new Date(System.currentTimeMillis());
                String time1 = simpleDateFormat.format(date1);
                Message msg1 = new Message(Message.SER_TYPE, str1, time1);
                messagesList.add(msg1);
                MyAdapter myAdapter1 = new MyAdapter(AnswerActivity.this, R.layout.service_item, messagesList);
                list_answer.setAdapter(myAdapter1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.home){
            Intent intent=new Intent(AnswerActivity.this,MainActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.autor){
            Intent intent=new Intent(AnswerActivity.this,AutorActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.directions){
            Intent intent=new Intent(AnswerActivity.this,DirectionsActivity.class);
            startActivity(intent);
        }
        this.finish();
        return super.onOptionsItemSelected(item);
    }

}


