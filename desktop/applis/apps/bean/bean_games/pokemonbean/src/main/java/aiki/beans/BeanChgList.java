package aiki.beans;

import code.util.*;

public class BeanChgList<T> implements IntBeanChgList<T> {
    private CustList<T> value = new CustList<T>();

    @Override
    public CustList<T> genericValue() {
        return tryRet();
    }

    @Override
    public CustList<T> tryRet() {
        return value;
    }

    @Override
    public void setupValue(CustList<T> _v) {
        value = _v;
    }
}
