package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MemoActivity2 extends AppCompatActivity {

    Button btn_save,btn_cancel;
    EditText editText;
    TextView txv_num;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo2);

        btn_cancel=findViewById(R.id.btn_cancel);
        btn_save=findViewById(R.id.btn_save);
        editText=findViewById(R.id.editText2);
        txv_num=findViewById(R.id.txv_num);

        Intent intent=getIntent();
        num=intent.getIntExtra("编号",0);
        String content=intent.getStringExtra("备忘");

        txv_num.setText(num+"");

        editText.setText(content);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_back=new Intent();

                intent_back.setClass(MemoActivity2.this,MainActivity.class);

                intent_back.putExtra("content",editText.getText().toString());
                intent_back.putExtra("num",num);
                setResult(RESULT_OK,intent_back);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
