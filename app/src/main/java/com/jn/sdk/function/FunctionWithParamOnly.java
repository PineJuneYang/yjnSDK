package com.jn.sdk.function;

/**
 * Created by jn
 * date on 2018/9/16.
 */

public abstract class FunctionWithParamOnly<Param> extends Function {
    public FunctionWithParamOnly(String functionName) {
        super(functionName);
    }

    public abstract void function(Param... params);
}
