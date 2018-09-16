package com.jn.sdk.function;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jn
 * date on 2018/9/16.
 */

public class FunctionsManager  {


    private FunctionsManager instance=null;

    public FunctionsManager(){
        mFunctionNoParaNoResul = new HashMap<>();
        mFunctionWithParamAndResult = new HashMap<>();
        mFunctionWithParamOnly = new HashMap<>();
        mFunctionWithResultOnly = new HashMap<>();

    }

    public FunctionsManager getInstance(){

        synchronized (FunctionsManager.class){
            if (instance==null){
                instance = new FunctionsManager();
            }
        }

        return instance;
    }


    private Map<String,FunctionNoParamNoResult> mFunctionNoParaNoResul;
    private Map<String,FunctionWithParamAndResult> mFunctionWithParamAndResult;
    private Map<String,FunctionWithParamOnly> mFunctionWithParamOnly;
    private Map<String,FunctionWithResultOnly> mFunctionWithResultOnly;


    public FunctionsManager addFunctionWithParamOnly(FunctionWithParamOnly functionWithParamOnly){

        if (null!=functionWithParamOnly) {
            mFunctionWithParamOnly.put(functionWithParamOnly.functionName,functionWithParamOnly);
        }

        return this;
    }


    public void invokeFuntionWithParamOnly (String funcitonName){
        if (TextUtils.isEmpty(funcitonName)) {
            return;
        }
        if (mFunctionWithParamOnly!=null){
            FunctionWithParamOnly functionWithParamOnly = mFunctionWithParamOnly.get(funcitonName);
            if (functionWithParamOnly!=null){
                functionWithParamOnly.function();
            }else {
                try {
                    throw new FunctionException("has no this function:"+funcitonName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
