package com.example.test.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.bean.HistoryBean;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.QuestionHolder> {
    private List<HistoryBean> historyBeans;
    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setQuestionBeans(List<HistoryBean> historyBeans) {
        this.historyBeans = historyBeans;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.hisotry_item, parent, false);
        return new QuestionHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, final int position) {
        holder.tvNum.setText("(" + (position + 1) + ")");
        holder.tvScore.setText(historyBeans.get(position).getScore());
        holder.tvTime.setText(historyBeans.get(position).getCreateTime());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClick.onLoginClick(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return historyBeans == null ? 0 : historyBeans.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        TextView tvNum, tvScore, tvTime;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_num);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvTime = itemView.findViewById(R.id.tv_time);

        }
    }

    public interface OnItemClick {
        void onLoginClick(int position);
    }
}
