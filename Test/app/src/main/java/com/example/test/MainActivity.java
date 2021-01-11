package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.test.Dao.NoteDao;
import com.example.test.adapter.QuestionAdapter;
import com.example.test.bean.HistoryBean;
import com.example.test.bean.QuestionBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private QuestionAdapter adapter;

    private Button btn_test;
    private Button btn_answer;
    private Button btn_me;
    private Button btn_history;

    private int currentPosition = 0;
    List<QuestionBean> list;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String json = getJson("data.json", this);

        List<QuestionBean> questionBeans = (List<QuestionBean>) new Gson().fromJson(json, new TypeToken<List<QuestionBean>>() {
        }.getType());
        list = getRandomList(10, questionBeans);
        recyclerView = findViewById(R.id.rlv_test);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionAdapter();
        adapter.setQuestionBeans(questionBeans);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 使用一个List来保存数组，每次随机取出一个移除一个。
     */
    public List<QuestionBean> getRandomList(int n, List<QuestionBean> questionBeans) {
        List<QuestionBean> list = new ArrayList<QuestionBean>();
        for (int i = 0; i < questionBeans.size(); i++) {
            list.add(questionBeans.get(i));
        }
        Random random = new Random();

        // 当取出的元素个数大于数组的长度时，返回null
        if (n > list.size()) {
            return null;
        }
        List<QuestionBean> result = new ArrayList<QuestionBean>();
        for (int i = 0; i < n; i++) {
            // 去一个随机数，随机范围是list的长度
            int index = random.nextInt(list.size());
            result.add(list.get(index));
            list.remove(index);
        }
        return result;
    }

    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void initView() {
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);
        btn_history = (Button) findViewById(R.id.btn_history);
        btn_history.setOnClickListener(this);
        btn_answer=(Button) findViewById(R.id.btn_answer);
        btn_answer.setOnClickListener(this);
        btn_me=(Button)findViewById(R.id.btn_me);
        btn_me.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_test:
                //状态清零
                currentPosition = -1;
                score = 0;
                showCustomizeDialog();
                break;

            case R.id.btn_history:
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                break;

            case R.id.btn_answer:
                startActivity(new Intent(MainActivity.this, AnswerActivity.class));
                break;

            case R.id.btn_me:
                startActivity(new Intent(MainActivity.this, MeActivity.class));
                break;

            default:
                break;
        }
    }

    AlertDialog show = null;

    private void showCustomizeDialog() {
        currentPosition++;

        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(MainActivity.this);
        final View dialogView = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.activity_choose, null);

        final TextView txvTime = dialogView.findViewById(R.id.txv_time);

        QuestionBean questionBean = list.get(currentPosition);
        TextView txvTitle = dialogView.findViewById(R.id.tv_title);
        txvTitle.setText((currentPosition + 1) + "." + questionBean.getTitle());

        final CountDownTimer countDownTimer = new CountDownTimer(8 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txvTime.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                if (currentPosition == list.size() - 1) {
                    showFinishDialog();
                } else {
                    if (show != null) {
                        show.dismiss();
                    }
                    showCustomizeDialog();
                }
            }
        };
        countDownTimer.start();
        RadioGroup radioGroup = dialogView.findViewById(R.id.RG);
        RadioButton radioButtonA = dialogView.findViewById(R.id.rbA);
        RadioButton radioButtonB = dialogView.findViewById(R.id.rbB);
        RadioButton radioButtonC = dialogView.findViewById(R.id.rbC);
        RadioButton radioButtonD = dialogView.findViewById(R.id.rbD);
        radioButtonA.setText(questionBean.getAnswer().get(0));
        radioButtonB.setText(questionBean.getAnswer().get(1));
        radioButtonC.setText(questionBean.getAnswer().get(2));
        radioButtonD.setText(questionBean.getAnswer().get(3));

        final int[] selectPosition = {0};
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbA:
                        selectPosition[0] = 1;
                        break;
                    case R.id.rbB:
                        selectPosition[0] = 2;
                        break;
                    case R.id.rbC:
                        selectPosition[0] = 3;
                        break;
                    case R.id.rbD:
                        selectPosition[0] = 4;
                        break;
                }
            }
        });
        customizeDialog.setView(dialogView);

        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //判断选择答案与正确答案是否相等
                        if (Integer.parseInt(list.get(currentPosition).getRightAnswer()) == selectPosition[0]) {
                            score += 10;//每答对一题加10分
                        }
                        dialog.dismiss();
                        countDownTimer.cancel();
                        if (currentPosition == list.size() - 1) {
                            showFinishDialog();
                        } else {
                            if (show != null) {
                                show.dismiss();
                            }
                            showCustomizeDialog();
                        }
                    }
                });
        customizeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                countDownTimer.cancel();
            }
        });
        customizeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                countDownTimer.cancel();
            }
        });
        show = customizeDialog.show();
    }

    private void showFinishDialog() {

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        normalDialog.setTitle("答题测试");
        normalDialog.setMessage("你的最终得分为：" + score);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                        HistoryBean historyBean = new HistoryBean();
                        historyBean.setScore(score + "");
                        historyBean.setCreateTime(simpleDateFormat.format(new Date()));
                        new NoteDao(MainActivity.this).insertNote(historyBean);
                    }
                });

        normalDialog.show();
    }


    /*MENU方法*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.autor){
            Intent intent=new Intent(MainActivity.this,AutorActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.directions){
            Intent intent=new Intent(MainActivity.this,DirectionsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}