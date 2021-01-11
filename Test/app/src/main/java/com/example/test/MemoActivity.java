package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

public class MemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView LV;
    ArrayAdapter<String> aa;
    Intent it;
    String [] note={
            "1.","2.","3.","4."
            ,"5.","6.","7.","8.","9.","10."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        LV=findViewById(R.id.LV);
        aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,note);
        LV.setAdapter(aa);
        LV.setOnItemClickListener(this);
        LV.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        it=new Intent(this,MemoActivity2.class);
        it.putExtra("编号",i+1);
        it.putExtra("备忘",note[i]);
        startActivityForResult(it,i);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        note[i]=(i+1)+"";
        aa.notifyDataSetChanged();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent it2) {
        super.onActivityResult(requestCode, resultCode, it2);
        if (resultCode==RESULT_OK){
            note[requestCode]=it2.getStringExtra("备忘");
            aa.notifyDataSetChanged();
        }
    }
}
