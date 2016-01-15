package com.example.hao.materialdesigndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao.materialdesigndemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 012 on 2016/1/14.
 */
public class MyStaggeredViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
    private Context mContext;
    private LayoutInflater layoutInflater;
    public List<String> mDatas;
    public List<Integer> mHeights;

    public MyStaggeredViewAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
        mDatas = new ArrayList<>();
        mHeights = new ArrayList<>();

        //1.模拟创建数据源
        for (int i = 0; i < 20; i++) {
            mDatas.add(i + "");
        }
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (Math.random() * 300) + 200);
        }
    }

    //2.初始化viewholder
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_card,parent,false);
        MyRecyclerViewHolder viewHolder=new MyRecyclerViewHolder(view);
        return viewHolder;
    }

    //3.绑定viewholder
    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams=holder.mCardView.getLayoutParams();
        layoutParams.height=mHeights.get(position);
        holder.mCardView.setLayoutParams(layoutParams);
        holder.mTextView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
