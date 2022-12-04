package aiki.beans;

import code.bean.nat.*;

public abstract class ParamNatStruct<T> extends NaNuSt {
    private final T instance;
    protected ParamNatStruct(T _instance) {
        instance = _instance;
    }

    public T getInstance() {
        return instance;
    }
}
