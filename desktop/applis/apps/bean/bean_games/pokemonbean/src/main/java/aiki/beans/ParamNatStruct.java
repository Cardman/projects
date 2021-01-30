package aiki.beans;

import code.bean.nat.CommNatStruct;

public abstract class ParamNatStruct<T> extends CommNatStruct {
    private final T instance;
    protected ParamNatStruct(T _instance,String _className) {
        super(_className);
        instance = _instance;
    }

    public T getInstance() {
        return instance;
    }
}
