package com.gongyou.recycleviewtest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * @author hezijie
 * @create 2018/5/30
 * @Describe 关于recycleview的各种使用方法
 *
 * 重点学习：https://blog.csdn.net/skykingf/article/details/50827141
 *
 *             https://www.jianshu.com/p/4fc6164e4709
 *
 * notifyDataSetChanged()这个方法跟我们平时用到的ListView的Adapter的方法一样，这里就不多做描述了。

notifyItemChanged(int position)，当position位置的数据发生了改变时就会调用这个方法，就会回调对应position的onBindViewHolder()方法了，当然，因为ViewHolder是复用的，所以如果position在当前屏幕以外，也就不会回调了，因为没有意义，下次position滚动会当前屏幕以内的时候同样会调用onBindViewHolder()方法刷新数据了。其他的方法也是同样的道理。public final void notifyItemRangeChanged(int positionStart, int itemCount)，顾名思义，可以刷新从positionStart开始itemCount数量的item了（这里的刷新指回调onBindViewHolder()方法）。

public final void notifyItemInserted(int position)，这个方法是在第position位置被插入了一条数据的时候可以使用这个方法刷新，注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义。

public final void notifyItemMoved(int fromPosition, int toPosition)，这个方法是从fromPosition移动到toPosition为止的时候可以使用这个方法刷新

public final void notifyItemRangeInserted(int positionStart, int itemCount)，显然是批量添加。

public final void notifyItemRemoved(int position)，第position个被删除的时候刷新，同样会有动画。
将上述更改运行，点击添加和删除按钮效果图如下：

public final void notifyItemRangeRemoved(int positionStart, int itemCount)，批量删除。
 */


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookBaseAdapter bookBaseAdapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle_view);
        //false 表示正序排列，true表示倒叙显示
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
        bookBaseAdapter = new BookBaseAdapter(getData());
        recyclerView.setAdapter(bookBaseAdapter);
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookBaseAdapter.setItemClickListener(new BookBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this,getData().get(position) , Toast.LENGTH_SHORT).show();
            }
        });

        bookBaseAdapter.setLongClickListener(new BookBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "被长按了" + position, Toast.LENGTH_SHORT).show();
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

    public void goClick(View view) {
        startActivity(new Intent(this,GridLayoutActivity.class));
    }


    static class BookBaseAdapter extends RecyclerView.Adapter<BookBaseAdapter.ViewHolder>{
        private List<String> mData;
        private OnItemClickListener itemClickListener;
        private OnItemLongClickListener longClickListener;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public void setLongClickListener(OnItemLongClickListener longClickListener) {
            this.longClickListener = longClickListener;
        }

        public BookBaseAdapter(List<String> mData) {
            this.mData = mData;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
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
