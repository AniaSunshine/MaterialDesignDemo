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
 * Created by 012 on 2016/1/13.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder>  {
    public  Context mContext;
    public LayoutInflater mLayoutInflater;
    public List<String> mDatas;


    //在这里传入数据源
    public MyRecyclerViewAdapter(Context context) {
        this.mContext=context;
        mLayoutInflater=LayoutInflater.from(context);
        mDatas=new ArrayList<String>();
        for (int i=0;i<20;i++){
            mDatas.add(i,i+"");
        }
    }



    //得到viewholder 的实例
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.item_card,parent,false);
        MyRecyclerViewHolder myRecyclerViewHolder=new MyRecyclerViewHolder(view);
        return myRecyclerViewHolder;
    }

    //绑定viewholder，给viewhodler设置数据
    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
