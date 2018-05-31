package com.gongyou.recycleviewtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridLayoutActivity.BookBaseAdapter bookBaseAdapter;
    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        recyclerView = findViewById(R.id.recycle_view);
        //false 表示正序排列，true表示倒叙显示
        recyclerView.setLayoutManager(new GridLayoutManager(this,4, OrientationHelper.VERTICAL,true));
        bookBaseAdapter = new GridLayoutActivity.BookBaseAdapter(getData());
        recyclerView.setAdapter(bookBaseAdapter);
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookBaseAdapter.setItemClickListener(new MainActivity.BookBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(GridLayoutActivity.this,getData().get(position) , Toast.LENGTH_SHORT).show();
            }
        });

        bookBaseAdapter.setLongClickListener(new MainActivity.BookBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(GridLayoutActivity.this, "被长按了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> getData() {
        data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 100; i++) {
            data.add(i + temp);
        }

        return data;
    }

    public void onClick(View view) {
        data.remove(0);
        bookBaseAdapter.notifyItemRemoved(0);
    }


    static class BookBaseAdapter extends RecyclerView.Adapter<MainActivity.BookBaseAdapter.ViewHolder>{
        private List<String> mData;
        private MainActivity.BookBaseAdapter.OnItemClickListener itemClickListener;
        private MainActivity.BookBaseAdapter.OnItemLongClickListener longClickListener;

        public void setItemClickListener(MainActivity.BookBaseAdapter.OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public void setLongClickListener(MainActivity.BookBaseAdapter.OnItemLongClickListener longClickListener) {
            this.longClickListener = longClickListener;
        }

        public BookBaseAdapter(List<String> mData) {
            this.mData = mData;
        }

        @NonNull
        @Override
        public MainActivity.BookBaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
            MainActivity.BookBaseAdapter.ViewHolder holder = new MainActivity.BookBaseAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.BookBaseAdapter.ViewHolder holder, final int position) {
            holder.mTv.setText(mData.get(position));
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null)
                        itemClickListener.onItemClickListener(v,position);
                }
            });

            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null)
                        longClickListener.onItemLongClickListener(v,position);
                    //返回值为true则其他类型的点击将无效。为false 则其他点击也会同样有效。
                    //官方解释：true则回调会消耗掉长点击，其他则为false。
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView mTv;
            View view;
            public ViewHolder(View itemView) {
                super(itemView);
                this.view = itemView;
                mTv =  itemView.findViewById(R.id.item_tv);
            }
        }

        public interface OnItemClickListener{
            void onItemClickListener(View view,int position);
        }
        public interface OnItemLongClickListener{
            void onItemLongClickListener(View view,int position);
        }
    }
}
