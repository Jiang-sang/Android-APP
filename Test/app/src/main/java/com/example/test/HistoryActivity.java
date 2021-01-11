package com.example.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Dao.NoteDao;
import com.example.test.adapter.HistoryAdapter;
import com.example.test.bean.HistoryBean;

import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("历史成绩");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RecyclerView recyclerView = findViewById(R.id.rv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final HistoryAdapter adapter = new HistoryAdapter();
        final List<HistoryBean> historyBeans = new NoteDao(this).queryNotesAll();
        adapter.setQuestionBeans(historyBeans);
        adapter.setOnItemClick(new HistoryAdapter.OnItemClick() {
            @Override
            public void onLoginClick(final int position) {
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(HistoryActivity.this);
                normalDialog.setTitle("删除");
                normalDialog.setMessage("是否删除本条记录?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new NoteDao(HistoryActivity.this).delete(historyBeans.get(position));
                                final List<HistoryBean> historyBeans = new NoteDao(HistoryActivity.this).queryNotesAll();
                                adapter.setQuestionBeans(historyBeans);
                                adapter.notifyDataSetChanged();
                            }
                        });
                normalDialog.setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                normalDialog.show();
            }
        });
        recyclerView.setAdapter(adapter);


    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.home){
            Intent intent=new Intent(HistoryActivity.this,MainActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.autor){
            Intent intent=new Intent(HistoryActivity.this,AutorActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.directions){
            Intent intent=new Intent(HistoryActivity.this,DirectionsActivity.class);
            startActivity(intent);
        }
        this.finish();
        return super.onOptionsItemSelected(item);
    }

}