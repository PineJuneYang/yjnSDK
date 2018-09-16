package com.jn.sdk.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jn.sdk.R;


/**
 * Created by jn on 2018/3/22.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public TextView tvTitle;
    public Toolbar toolbar;
    public RelativeLayout content;
    public Context context;
    //是否有返回
    public boolean hasBackview = false;
    //是否使用toolbar原生的返回
    public boolean isToolbarBack = false;

    //是否有LOGO
    public boolean hasLogo = false;


    //是否显示大标题
    public boolean hasAppTitle = false;
    //是否显示小标题
    public boolean hasSubTitle = false ;
    private int appTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
        setContentView(R.layout.activity_base);
        bindViews();
        initTitleData();
        setSupportActionBar(toolbar);
        setToolbarStyle();
        addToolbarStyle();


        setContentLayout(getContentLayout());


    }

    protected abstract void initTitleData();


    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.common_title_tb);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        content = (RelativeLayout) findViewById(R.id.content);
    }


    public abstract int getContentLayout();

    /**
     * 设置toolbar下面内容区域的内容
     *
     * @param layoutId
     */
    public void setContentLayout(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(contentView, params);
    }




    /**
     * 子类调用，重新设置Toolbar
     *
     * @param layout
     */
    public void setToolBar(int layout) {
        hidetoolBar();
        toolbar = (Toolbar) content.findViewById(layout);
        setSupportActionBar(toolbar);
        //设置actionBar的标题是否显示，对应ActionBar.DISPLAY_SHOW_TITLE。
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setToolbarStyle();
    }

    /**
     * 隐藏ToolBar，通过setToolBar重新定制ToolBar
     */
    public void hidetoolBar() {
        toolbar.setVisibility(View.GONE);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        tvTitle.setText(resId);
    }


    /**
     * 设置toolbar的样式显示
     * 主要是setHomeButtonEnabled()决定左上角的图标是否可以点击,这个小于4.0版本的默认值为true的。但是在4.0及其以上是false
     * setDisplayHomeAsUpEnabled(true) 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
     * setDisplayShowHomeEnabled(true) 使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
     *setDisplayShowCustomEnabled(true) 使自定义的普通View能在title栏显示，即actionBar.setCustomView能起作用
     * setDisplayShowTitleEnabled(true) 对应ActionBar.DISPLAY_SHOW_TITLE。
     */
    public  void setToolbarStyle(){
        if (hasBackview){
            if (isToolbarBack){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }



    }


    public void addToolbarStyle() {
        if (hasBackview) {
            if (!isToolbarBack) {
                setBackArrow();
            }
        }
        if (hasLogo) {
            toolbar.setLogo(R.drawable.logo);
        }

        if (hasAppTitle) {
            toolbar.setTitle(getAppTitle());
        }else {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (hasSubTitle){

        }
    }



    /**
     * 设置左上角back按钮
     */
    public void setBackArrow() {
        final Drawable upArrow = getResources().getDrawable(R.drawable.common_back_ic);
        //给ToolBar设置左侧的图标
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回按钮的点击事件
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }


    public abstract  int getAppTitle();

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
