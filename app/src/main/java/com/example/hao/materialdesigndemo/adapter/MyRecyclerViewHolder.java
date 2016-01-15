package com.example.hao.materialdesigndemo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hao.materialdesigndemo.R;

/**
 * Created by 012 on 2016/1/14.
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public CardView mCardView;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.id_textview);
        mCardView= (CardView) itemView.findViewById(R.id.cardView);
    }
}
