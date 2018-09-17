package com.jn.sdk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jn.sdk.MainActivity;
import com.jn.sdk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jn
 * date on 2018/4/2.
 */

public class HomeFragment extends BaseFragment {


    //定义接口
    public static final String INTERFACE_WITHPARAMONLY = HomeFragment.class.getName() + "WPO";


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
        int statusBarHeight = ((MainActivity) getActivity()).getStatusBarHeight();
        llWhole.setPadding(0, statusBarHeight, 0, 0);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.search, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:

                functionsManager.invokeFuntionWithParamOnly(INTERFACE_WITHPARAMONLY,"我是HomeFragment里面回调出来的");

                break;
            case R.id.iv_scan:
                break;
        }
    }
}
