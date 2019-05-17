package com.gongyou.recycleviewtest;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> dataList = new ArrayList<>();
    private StaggeredAdapter linearAdapter;
    private List<Item> mItems;
    private List<Integer> heights;
    private String url = "http://live.9158.com/Room/GetHotLive_v2?lon=0.0&province=&lat=0.0&page=1&type=1";

    private void getRandomHeight(List<Item> lists) {//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int) (300 + Math.random() * 400));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid);
        recyclerView = findViewById(R.id.recycle_view);
        mItems = new ArrayList<>();

        linearAdapter = new StaggeredAdapter();
        //最后一个参数表示是否倒序
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(linearAdapter);
        getData();
    }

    private void getData() {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // 添加自己的响应逻辑
                mItems.clear();
                JSONArray listArray = response.optJSONObject("data").optJSONArray("list");
                int len = listArray.length();
                for (int i = 0; i < len; i++) {
                    JSONObject json = listArray.optJSONObject(i);
                    Item item = Item.createItmfromJson(json);
                    mItems.add(item);
                }
                getRandomHeight(mItems);
                linearAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 错误处理
                Toast.makeText(StaggeredGridActivity.this , "联网错误", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    class  StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered_recycle,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Item item = mItems.get(position);

            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
            params.height = heights.get(position);//把随机的高度赋予itemView布局
            holder.itemView.setLayoutParams(params);//把params设置给itemView布局

            holder.mNameTxt.setText(item.getName());
            Glide.with(StaggeredGridActivity.this)
                    .load(item.getImageUrl())
                    // 占位图
                    .placeholder(R.mipmap.ic_tree)
                    // 加载错误图
                    .error(R.mipmap.ic_tree)
//                让Glide既缓存全尺寸又缓存其他尺寸
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView mNameTxt;
            ImageView mImage;
            public MyViewHolder(View itemView) {
                super(itemView);
                mNameTxt = itemView.findViewById(R.id.item_frag_recommend_name);
                mImage = itemView.findViewById(R.id.item_frag_recommend_image);
            }
        }
    }

    /**
     * 实现 每个Item之间的间隔的, 本项目中没有用到
     */
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
}
