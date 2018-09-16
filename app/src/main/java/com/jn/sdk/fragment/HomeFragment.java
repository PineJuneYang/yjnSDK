package com.jn.sdk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jn.sdk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jn
 * date on 2018/4/2.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.new_message)
    View newMessage;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.search_icon)
    View searchIcon;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.ll_whole)
    LinearLayout llWhole;
    Unbinder unbinder;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.tab_main_content;
    }

    @Override
    protected void initView() {
        int statusBarHeight = ((com.jn.sdk.MainActivity) getActivity()).getStatusBarHeight();
        llWhole.setPadding(0,statusBarHeight,0,0);
    }

    @Override
    protected void initListener() {



    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
