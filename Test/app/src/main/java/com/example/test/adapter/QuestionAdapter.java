package com.example.test.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.bean.QuestionBean;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {
    private List<QuestionBean> questionBeans;

    public void setQuestionBeans(List<QuestionBean> questionBeans) {
        this.questionBeans = questionBeans;
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_test, parent, false);
        return new QuestionHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.QuestionHolder holder, int position) {
        holder.title.setText("(" + (position + 1) + ")" + questionBeans.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return questionBeans == null ? 0 : questionBeans.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        TextView title;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txv_que);
        }
    }
}
