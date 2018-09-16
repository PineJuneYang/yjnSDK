package com.jn.sdk;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;


import com.jn.sdk.R;
import com.jn.sdk.activity.BaseActivity;
import com.jn.sdk.fragment.HomeFragment;
import com.jn.sdk.fragment.MineFragment;
import com.jn.sdk.fragment.RouteFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_index)
    RadioButton tabIndex;
    @BindView(R.id.tab_route)
    RadioButton tabRoute;
    @BindView(R.id.tab_setting)
    RadioButton tabMine;
    @BindView(R.id.tab_host)
    RadioGroup tabHost;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private Unbinder bind;


    private static final int FRAGMENT_FLAG_HOME = 0X01;
    private static final int FRAGMENT_FLAG_ROUTE = 0X02;
    private static final int FRAGMENT_FLAG_MINE = 0X03;


    private HomeFragment homeFragment;
    private RouteFragment routeFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidetoolBar();
        bind = ButterKnife.bind(this);
        initViews();
        initData();
        loadData();

        initListener();

    }

    private void initViews() {


    }

    private void initData() {


    }

    private void loadData() {



    }

    @Override
    protected void initTitleData() {
        hasBackview = true;
        isToolbarBack = true;
        hasLogo = true;
        hasAppTitle = true;
    }

    private void initListener() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tabIndex.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_HOME));
        tabRoute.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_ROUTE));
        tabMine.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_MINE));


        tabIndex.setChecked(true);

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void addToolbarStyle() {
        super.addToolbarStyle();

    }

    @Override
    public int getAppTitle() {
        return R.string.app_name;
    }


    /**
     * 这个方法和        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
     *
     * @param item
     * @return
     * @Override public void onClick(View v) {
     * finish();  是一样的效果
     * }
     * });
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=bind){
            bind.unbind();
        }
    }

    /**
     * 定义RadioButton的选中改变监听
     */
    class OnNaviCheckChangeListener implements CompoundButton.OnCheckedChangeListener {

        private int posttion;

        public OnNaviCheckChangeListener(int posttion) {
            this.posttion = posttion;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                changeTab(posttion);
            }
        }
    }
    /**
     * 切换tab
     *
     * @param posttion
     */
    private void changeTab(int posttion) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (posttion) {
            case FRAGMENT_FLAG_HOME:
                changeTabToFragmentHome(transaction);
                break;
            case FRAGMENT_FLAG_ROUTE:
                changeTabToFragmentRoute(transaction);
                break;

            case FRAGMENT_FLAG_MINE:
                changeTabToFragmentMine(transaction);
                break;

        }
        transaction.commitAllowingStateLoss();
    }


    /**
     * 先隐藏掉所有的fragment
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null && !homeFragment.isHidden()) {
            transaction.hide(homeFragment);
        }
        if (routeFragment != null && !routeFragment.isHidden()) {
            transaction.hide(routeFragment);
        }
        if (mineFragment != null && !mineFragment.isHidden()) {
            transaction.hide(mineFragment);
        }

    }



    /**
     * 跳转到fragmentA
     */
    private void changeTabToFragmentHome(FragmentTransaction transaction) {
        setTitle("首页");
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.fl_content, homeFragment);
        } else {
            transaction.show(homeFragment);
        }
    }

    /**
     * 跳转到fragmentB
     */
    private void changeTabToFragmentRoute(FragmentTransaction transaction) {
        setTitle("路线");
        if (routeFragment == null) {
            routeFragment = new RouteFragment();
            transaction.add(R.id.fl_content, routeFragment);
        } else {
            transaction.show(routeFragment);
        }

    }

    /**
     * 跳转到fragmentC
     */
    private void changeTabToFragmentMine(FragmentTransaction transaction) {
        setTitle("我的");
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            transaction.add(R.id.fl_content, mineFragment);
        } else {
            transaction.show(mineFragment);
        }
    }

}
