package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.util.CustList;

public final class ConcList<T> implements AbsCollection<T> {
    private final CustList<T> listTmp = new CustList<T>();
    private final AbstractInterceptorStdCaller interceptor;
    public ConcList(AbstractInterceptorStdCaller _i) {
        interceptor = _i;
    }
    @Override
    public Iterable<T> elts() {
        return listTmp.getList();
    }

    @Override
    public void add(T _b) {
        listTmp.add(_b);
    }

    @Override
    public void clear() {
        listTmp.clear();
    }

    @Override
    public void remove(int _i, T _b) {
        listTmp.remove(_i);
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
