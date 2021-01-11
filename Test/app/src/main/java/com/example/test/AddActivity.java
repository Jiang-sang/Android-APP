package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_name,edit_A,edit_B,edit_C,edit_D;
    Button btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edit_name=findViewById(R.id.edit_name);
        edit_A=findViewById(R.id.edit_A);
        edit_B=findViewById(R.id.edit_B);
        edit_C=findViewById(R.id.edit_C);
        edit_D=findViewById(R.id.edit_D);
        btn_insert=findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        String name=edit_name.getText().toString();
        String A=edit_A.getText().toString();
        String B=edit_B.getText().toString();
        String C=edit_C.getText().toString();
        String D=edit_D.getText().toString();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("A",A);
        contentValues.put("B",B);
        contentValues.put("C",C);
        contentValues.put("D",D);
        /*Intent intent=new Intent(AddActivity.this,ShowActivity.class);
        startActivity(intent);*/
    }
}