package com.example.hao.materialdesigndemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao.materialdesigndemo.adapter.MyRecyclerViewAdapter;
import com.example.hao.materialdesigndemo.adapter.MyStaggeredViewAdapter;

/**
 * Created by 012 on 2016/1/13.
 */
public class MyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private View view;
    private SwipeRefreshLayout swiperefresh_layout;
    private RecyclerView recyclerview;

    private int flag;
    private static final int VERTICAL_LIST=0;
    private static final int HORIZONTAL_LIST=1;
    private static final int VERTICAL_GRID=2;
    private static final int HORIZONTAL_GRID=3;
    private static final int STAGGERED_GRID=4;

    private RecyclerView.LayoutManager layoutManager;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MyStaggeredViewAdapter myStaggeredViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swiperefresh_layout= (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh_layout);
        recyclerview= (RecyclerView) view.findViewById(R.id.recyclerview);

        flag =(int)getArguments().getInt("flag");
        Log.i("flag",flag+"");
        configView();

        swiperefresh_layout.setColorSchemeResources(R.color.main_blue_dark, R.color.main_blue_dark);
        swiperefresh_layout.setOnRefreshListener(this);

        swiperefresh_layout.post(new Thread(new Runnable() {
            @Override
            public void run() {
                swiperefresh_layout.setRefreshing(true);
            }
        }));
        onRefresh();

    }

    private void configView() {
        switch (flag){
            case VERTICAL_LIST:
                layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                break;
            case HORIZONTAL_LIST:
                layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                break;
            case VERTICAL_GRID:
                layoutManager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
                break;
            case HORIZONTAL_GRID:
                layoutManager=new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL,false);
                break;
            case STAGGERED_GRID:
                layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                break;
        }

        if (flag!=STAGGERED_GRID){
            myRecyclerViewAdapter=new MyRecyclerViewAdapter(getActivity());
            recyclerview.setAdapter(myRecyclerViewAdapter);
        }else{
            myStaggeredViewAdapter=new MyStaggeredViewAdapter(getActivity());
            recyclerview.setAdapter(myStaggeredViewAdapter);
        }

        recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public void onRefresh() {
        Log.i("onRefresh","走没走？？");
        //模拟数据刷新
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int temp= (int)(Math.random()*10);
                if (flag!=STAGGERED_GRID){
                    myRecyclerViewAdapter.mDatas.add(0,temp+"");
                    myRecyclerViewAdapter.notifyDataSetChanged();
                }else{
                    myStaggeredViewAdapter.mDatas.add(0,temp+"");
                    myStaggeredViewAdapter.mHeights.add(0,(int)((Math.random()*300)+200));
                    myStaggeredViewAdapter.notifyDataSetChanged();
                }

                swiperefresh_layout.setRefreshing(false);
            }
        },1000);
    }

    @Override
    public void onStart() {
        Log.i("MyFragment","---------------onStart----------------");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("MyFragment","---------------onResume----------------");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("MyFragment","---------------onPause----------------");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("MyFragment","---------------onStop----------------");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i("MyFragment","---------------obnDestroy----------------");
        super.onDestroy();
    }
}
