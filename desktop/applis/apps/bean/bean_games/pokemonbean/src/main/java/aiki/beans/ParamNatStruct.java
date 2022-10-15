package aiki.beans;

import code.expressionlanguage.structs.AbNullStruct;

public abstract class ParamNatStruct<T> extends AbNullStruct {
    private final T instance;
    protected ParamNatStruct(T _instance) {
        instance = _instance;
    }

    public T getInstance() {
        return instance;
    }
}
