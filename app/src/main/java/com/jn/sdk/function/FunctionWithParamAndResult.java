package com.jn.sdk.function;

/**
 * Created by jn
 * date on 2018/9/16.
 */

public abstract class FunctionWithParamAndResult<Result,Param> extends Function {
    public FunctionWithParamAndResult(String functionName) {
        super(functionName);
    }

    public abstract Result funciton(Param... params);
}
