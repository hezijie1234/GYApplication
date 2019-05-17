package com.gongyou.recycleviewtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LinearLayoutActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<String> dataList = new ArrayList<>();
    private LinearAdapter linearAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        for (int i = 0; i < 100; i++) {
            dataList.add(i + "item");
        }
        recyclerView = findViewById(R.id.rv);
        linearAdapter = new LinearAdapter();
        //最后一个参数表示是否倒序
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.HORIZONTAL,false));
        recyclerView.setAdapter(linearAdapter);
    }

    class  LinearAdapter extends RecyclerView.Adapter<LinearAdapter.MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(LinearLayoutActivity.this).inflate(R.layout.item_recycle,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tvText.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tvText;
            public MyViewHolder(View itemView) {
                super(itemView);
                tvText = itemView.findViewById(R.id.item_tv);
            }
        }
    }
}
