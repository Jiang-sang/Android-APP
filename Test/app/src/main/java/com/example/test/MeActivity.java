package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton quit,wnb,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        setTitle("我的");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        quit = findViewById(R.id.quit);
        quit.setOnClickListener(this);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        wnb = findViewById(R.id.wnb);
        wnb.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.quit) {
            //android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        } else if (v.getId() == R.id.add) {
            Intent intent = new Intent(MeActivity.this, AddActivity.class);
            startActivity(intent);

        }else if (v.getId() == R.id.wnb) {
            Intent intent = new Intent(MeActivity.this, MemoActivity.class);
            startActivity(intent);
        }
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu,menu);
            return super.onCreateOptionsMenu(menu);
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId()==R.id.home){
                Intent intent=new Intent(MeActivity.this,MainActivity.class);
                startActivity(intent);
            }else if (item.getItemId()==R.id.autor){
                Intent intent=new Intent(MeActivity.this,AutorActivity.class);
                startActivity(intent);
            }else if (item.getItemId()==R.id.directions){
                Intent intent=new Intent(MeActivity.this,DirectionsActivity.class);
                startActivity(intent);
            }
            this.finish();
            return super.onOptionsItemSelected(item);
        }

    }
