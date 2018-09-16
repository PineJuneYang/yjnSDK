package com.jn.sdk.function;

/**
 * Created by jn
 * date on 2018/9/16.
 */

public abstract class FunctionWithResultOnly<Result> extends Function {
    public FunctionWithResultOnly(String functionName) {
        super(functionName);
    }


    public abstract Result function();
}
