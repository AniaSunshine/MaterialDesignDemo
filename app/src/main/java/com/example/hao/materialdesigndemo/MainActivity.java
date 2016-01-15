package com.example.hao.materialdesigndemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hao.materialdesigndemo.adapter.MyViewPagerAdapter;
import com.example.hao.materialdesigndemo.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer_layout;
    private NavigationView navigation;
    private CoordinatorLayout coordinator_layout;
    private AppBarLayout appbar_layout;
    private Toolbar toolbar;
    private TabLayout tab_layout;
    private ViewPager viewpager;
    private FloatingActionButton floating_btn;

    private String[] mTitles;
    private List<Fragment> mFragments;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        configViews();
    }

    private void initView() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigation = (NavigationView) findViewById(R.id.navigation);
        coordinator_layout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        appbar_layout = (AppBarLayout) findViewById(R.id.appbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        floating_btn = (FloatingActionButton) findViewById(R.id.floating_btn);
    }

    private void initData() {
        mTitles = getResources().getStringArray(R.array.tab_titles);

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            Bundle mBundle = new Bundle();
            mBundle.putInt("flag", i);
            MyFragment myFragment = new MyFragment();//TODO 想这洋会不会产生内存抖动
            myFragment.setArguments(mBundle);
            mFragments.add(i, myFragment);
        }

    }


    private void configViews() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawer_layout.setDrawerListener(actionBarDrawerToggle);

        navigation.inflateHeaderView(R.layout.header_nav);
        navigation.inflateMenu(R.menu.menu_nav);

        onNavigationViewMenuItemSelected(navigation);

        myViewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),mTitles,mFragments);
        viewpager.setAdapter(myViewPagerAdapter);
        viewpager.setOffscreenPageLimit(5);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                toolbar.setTitle(mTitles[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.setupWithViewPager(viewpager);
        tab_layout.setTabsFromPagerAdapter(myViewPagerAdapter);

        floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarUtils.show(v,"点击了",0);
            }
        });

    }

    private void onNavigationViewMenuItemSelected(NavigationView navigation) {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String msgString = "";

                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_home:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.nav_menu_categories:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.nav_menu_feedback:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.nav_menu_setting:
                        msgString = (String) menuItem.getTitle();
                        break;
                }

                menuItem.setCheckable(true);
                drawer_layout.closeDrawers();
                SnackbarUtils.show(viewpager,msgString,0);

                return true;

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
