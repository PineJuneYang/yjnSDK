package com.jn.sdk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jn.sdk.MainActivity;
import com.jn.sdk.function.FunctionsManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-20  14:47
 */

public abstract class BaseFragment extends Fragment {


    public FunctionsManager functionsManager;

    public void setFunctionsManager(FunctionsManager functionsManager) {
        this.functionsManager = functionsManager;
    }

    private Unbinder unbinder;

    /**
     * 获取布局ID
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化
     */
    protected abstract void initView();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity  = (MainActivity) context;
        mainActivity.setFunctionForFragment(getTag());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        loadData();
        initListener();

    }

    protected abstract void initListener();

    protected abstract void loadData();

    protected abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }



}
