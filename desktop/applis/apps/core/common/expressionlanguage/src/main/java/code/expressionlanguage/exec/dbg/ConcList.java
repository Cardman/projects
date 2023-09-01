package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.util.StringMap;

public final class ConcList<T> implements AbsCollection<T> {
    private final StringMap<T> listTmp = new StringMap<T>();
    private final AbsKeyString<T> id;
    private final AbstractInterceptorStdCaller interceptor;
    public ConcList(AbsKeyString<T> _id, AbstractInterceptorStdCaller _i) {
        this.id = _id;
        interceptor = _i;
    }
    @Override
    public Iterable<T> elts() {
        return listTmp.values();
    }

    @Override
    public void add(T _b) {
        listTmp.put(id.keyString(_b),_b);
    }

    @Override
    public void clear() {
        listTmp.clear();
    }

    @Override
    public void remove(T _b) {
        listTmp.removeKey(id.keyString(_b));
    }

    @Override
    public void setAll(AbsCollection<T> _t) {
        clear();
        for (T e: _t.elts()) {
            add(e);
        }
    }

    @Override
    public AbstractInterceptorStdCaller intercept() {
        return interceptor;
    }
}
