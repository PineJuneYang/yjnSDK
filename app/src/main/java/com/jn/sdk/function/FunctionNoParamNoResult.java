package com.jn.sdk.function;

/**
 * Created by jn
 * date on 2018/9/16.
 */

public abstract class FunctionNoParamNoResult extends Function{

    public FunctionNoParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function();

}
