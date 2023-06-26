package code.vi.prot.impl;

import code.expressionlanguage.exec.dbg.AbsKeyString;
import code.expressionlanguage.exec.dbg.AbsCollection;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ConcMap<T> implements AbsCollection<T> {
    private final ConcurrentMap<String,T> elements = new ConcurrentHashMap<String, T>();
    private final AbsKeyString<T> id;
    private final AbstractInterceptorStdCaller interceptor;

    public ConcMap(AbsKeyString<T> _i, AbstractInterceptorStdCaller _inter) {
        this.id = _i;
        this.interceptor = _inter;
    }

    @Override
    public Iterable<T> elts() {
        return elements.values();
    }

    @Override
    public void add(T _b) {
        elements.put(id.keyString(_b),_b);
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public void remove(int _i, T _b) {
        elements.remove(id.keyString(_b));
    }

    @Override
    public void setAll(AbsCollection<T> _t) {
        clear();
        elements.putAll(((ConcMap<T>)_t).elements);
    }

    @Override
    public AbstractInterceptorStdCaller intercept() {
        return interceptor;
    }

}
